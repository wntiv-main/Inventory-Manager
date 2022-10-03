package net.wntiv.inventorymanager.client.render;

import net.wntiv.inventorymanager.InventoryManager;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WindowRendererManager {
    private final Map<Class<? extends InventoryWindow>, WindowRenderer> renderers = new HashMap<>();

    public void register(Class<? extends InventoryWindow> windowType, WindowRenderer renderer) {
        renderers.put(windowType, renderer);
    }

    public void render(InventoryWindow window) {
        Class<? extends InventoryWindow> clazz = window.getClass();
        if(!renderers.containsKey(window.getClass())){
            InventoryManager.LOGGER.warn("InventoryWindow {} of type {} does not have a registered renderer", window, clazz);
            return;
        }
        renderers.get(clazz).render(clazz.cast(window));
    }

    public void renderAll(List<InventoryWindow> windows) {
        windows.forEach(this::render);
    }
}
