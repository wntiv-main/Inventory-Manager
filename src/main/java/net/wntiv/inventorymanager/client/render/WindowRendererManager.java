package net.wntiv.inventorymanager.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;
import net.wntiv.inventorymanager.InventoryManager;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.RenderingEventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.PerInstanceRenderer;
import net.wntiv.inventorymanager.mixin.GameRendererInvoker;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WindowRendererManager {
    private final Map<Class<? extends WindowRenderer>, WindowRenderer> renderers = new HashMap<>();
    private final Map<Class<? extends InventoryWindow>, Class<? extends WindowRenderer>> windowRendererMap = new HashMap<>();
    private final Map<Class<? extends ScreenHandler>, Class<? extends WindowRenderer>> handlerRendererMap = new HashMap<>();
    private int width = 0;
    private int height = 0;
    private boolean wasHudHidden;

    public void register(Class<? extends InventoryWindow> windowType, Class<? extends WindowRenderer> renderer, Class<? extends ScreenHandler> handler) {
        windowRendererMap.put(windowType, renderer);
        handlerRendererMap.put(handler, renderer);
    }

    public <T extends InventoryWindow & PerInstanceRenderer> void register(Class<T> windowType) {
        // Do nothing, function is purely for completeness
    }

    public WindowRenderer getRendererInstance(Class<? extends WindowRenderer> rendererType) {
        if (rendererType == null) return null;
        if(renderers.values().stream().anyMatch(rendererType::isInstance)) {
            return renderers.values().stream().filter(rendererType::isInstance).findFirst().orElseThrow(RuntimeException::new);
        } else {
            try {
                WindowRenderer renderer = rendererType.getConstructor(WindowRendererManager.class, EventContext.class)
                        .newInstance(this, new EventContext(MinecraftClient.getInstance()));
                renderers.put(rendererType, renderer);
                return renderer;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Class<? extends WindowRenderer> getRendererForWindow(Class<? extends InventoryWindow> windowType) {
        return windowRendererMap.get(windowType);
    }

    public Class<? extends WindowRenderer> getRendererForHandler(Class<? extends ScreenHandler> handlerType) {
        return handlerRendererMap.get(handlerType);
    }

    public void render(InventoryWindow window, RenderingEventContext context) {
        WindowRenderer renderer;
        Class<? extends InventoryWindow> clazz = window.getClass();
        if (window instanceof PerInstanceRenderer) {
            renderer = getRendererInstance(((PerInstanceRenderer) window).getRendererClass(this));
        } else {
            if (!windowRendererMap.containsKey(clazz)) {
                InventoryManager.LOGGER.warn("InventoryWindow {} of type {} does not have a registered renderer", window, clazz);
                return;
            }
            renderer = getRendererInstance(getRendererForWindow(clazz));
        }
        if(renderer == null) return;
        renderer.render(clazz.cast(window), context);
    }

    public void renderAll(List<InventoryWindow> windows, RenderingEventContext context) {
        if(context.world == null || context.player == null) return;
        windows.forEach(window -> render(window, context));
    }

    public void setScreenSize(int w, int h) {
        width = w;
        height = h;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void onOpenScreen(EventContext context) {
        ((GameRendererInvoker)context.client.gameRenderer).invokeLoadShader(new Identifier(InventoryManager.MODID, "shaders/post/blur.json"));
//        RenderSystem.disableAlphaTest();
        wasHudHidden = context.client.options.hudHidden;
        context.client.options.hudHidden = true;
    }

    public void onCloseScreen(EventContext context) {
        context.client.gameRenderer.disableShader();
//        RenderSystem.enableAlphaTest();
        context.client.options.hudHidden = wasHudHidden;
        renderers.clear();
    }
}
