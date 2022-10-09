package net.wntiv.inventorymanager.client.render.generic;

import net.wntiv.inventorymanager.Colored;
import net.wntiv.inventorymanager.client.RenderingEventContext;
import net.wntiv.inventorymanager.client.render.WindowRendererManager;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

public class Colored9x3Renderer extends Generic9x3Renderer {
    public Colored9x3Renderer(WindowRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(InventoryWindow window, RenderingEventContext context) {
        assert window instanceof Colored;
        //
    }
}
