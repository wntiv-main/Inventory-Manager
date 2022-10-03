package net.wntiv.inventorymanager.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.wntiv.inventorymanager.client.render.WindowRendererManager;
import net.wntiv.inventorymanager.client.render.generic.Colored9x3Renderer;
import net.wntiv.inventorymanager.client.render.generic.Generic9x3Renderer;
import net.wntiv.inventorymanager.client.window.WindowProviderManager;
import net.wntiv.inventorymanager.client.window.block.BlockWindowProvider;
import net.wntiv.inventorymanager.client.window.block.Colored9x3Window;
import net.wntiv.inventorymanager.client.window.block.Generic9x3Window;
import net.wntiv.inventorymanager.client.window.item.ItemWindow;
import net.wntiv.inventorymanager.client.window.item.ItemWindowProvider;

@Environment(EnvType.CLIENT)
public class InventoryManagerClient implements ClientModInitializer {
    public static WindowRendererManager renderer = new WindowRendererManager();
    public static WindowProviderManager windowProvider = new WindowProviderManager();
    @Override
    public void onInitializeClient() {
        // Set up renderers
        Generic9x3Renderer generic9x3 = new Generic9x3Renderer();
        Colored9x3Renderer colored9x3 = new Colored9x3Renderer();
        renderer.register(ItemWindow.class, generic9x3);
        renderer.register(Generic9x3Window.class, generic9x3);
        renderer.register(Colored9x3Window.class, colored9x3);
        windowProvider.register(new ItemWindowProvider());
        windowProvider.register(new BlockWindowProvider());
    }
}
