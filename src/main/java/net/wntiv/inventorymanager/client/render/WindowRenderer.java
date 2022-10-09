package net.wntiv.inventorymanager.client.render;

import net.wntiv.inventorymanager.client.RenderingEventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

public interface WindowRenderer {
    void render(InventoryWindow window, RenderingEventContext context);
    static Integer[] remapSlotCoords(int x, int y) {
        throw new RuntimeException("This method should not be called");
    }
}
