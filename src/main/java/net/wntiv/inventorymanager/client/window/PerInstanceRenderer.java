package net.wntiv.inventorymanager.client.window;

import net.wntiv.inventorymanager.client.render.WindowRenderer;
import net.wntiv.inventorymanager.client.render.WindowRendererManager;

public interface PerInstanceRenderer {
    Class<? extends WindowRenderer> getRendererClass(WindowRendererManager manager);
}
