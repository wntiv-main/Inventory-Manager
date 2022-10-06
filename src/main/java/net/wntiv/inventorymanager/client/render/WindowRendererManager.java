package net.wntiv.inventorymanager.client.render;

import net.wntiv.inventorymanager.InventoryManager;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.PerInstanceRenderer;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WindowRendererManager {
    private final Map<Class<? extends WindowRenderer>, WindowRenderer> renderers = new HashMap<>();
    private final Map<Class<? extends InventoryWindow>, WindowRenderer> windowRendererMap = new HashMap<>();

    public void register(Class<? extends InventoryWindow> windowType, Class<? extends WindowRenderer> renderer) {
        windowRendererMap.put(windowType, getRenderer(renderer));
    }

    public <T extends InventoryWindow & PerInstanceRenderer> void register(Class<T> windowType) {
        // Do nothing, function is purely for completeness
    }

    private WindowRenderer getRenderer(Class<? extends WindowRenderer> rendererType) {
        if(renderers.values().stream().anyMatch(rendererType::isInstance)) {
            return renderers.values().stream().filter(rendererType::isInstance).findFirst().orElseThrow(RuntimeException::new);
        } else {
            try {
                WindowRenderer renderer = rendererType.getConstructor().newInstance();
                renderers.put(rendererType, renderer);
                return renderer;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Class<? extends WindowRenderer> getRendererFor(Class<? extends InventoryWindow> windowType) {
        return windowRendererMap.get(windowType).getClass();
    }

    public void render(InventoryWindow window) {
        WindowRenderer renderer;
        Class<? extends InventoryWindow> clazz = window.getClass();
        if (window instanceof PerInstanceRenderer) {
            renderer = getRenderer(((PerInstanceRenderer) window).getRendererClass(this));
        } else {
            if (!windowRendererMap.containsKey(clazz)) {
                InventoryManager.LOGGER.warn("InventoryWindow {} of type {} does not have a registered renderer", window, clazz);
                return;
            }
            renderer = renderers.get(clazz);
        }
        renderer.render(clazz.cast(window));
    }

    public void renderAll(List<InventoryWindow> windows) {
        windows.forEach(this::render);
    }
}
