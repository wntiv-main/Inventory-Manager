package net.wntiv.inventorymanager.client.render.generic;

import net.wntiv.inventorymanager.Colored;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

public class Colored9x3Renderer extends Generic9x3Renderer {
    @Override
    public void render(InventoryWindow window) {
        assert window instanceof Colored;
        //
    }
}
