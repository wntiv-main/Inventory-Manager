package net.wntiv.inventorymanager.client.render.generic;

import net.wntiv.inventorymanager.client.RenderingEventContext;
import net.wntiv.inventorymanager.client.render.WindowRenderer;
import net.wntiv.inventorymanager.client.render.WindowRendererManager;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import org.apache.commons.lang3.NotImplementedException;

public class Generic9x3Renderer implements WindowRenderer {
    public Generic9x3Renderer(WindowRendererManager manager) {
//        super(manager);
    }

    @Override
    public void render(InventoryWindow window, RenderingEventContext context) {

    }

    public static Integer[] remapSlotCoords(int x, int y) {
        throw new NotImplementedException("oops");
    }
}
