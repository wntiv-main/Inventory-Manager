package net.wntiv.inventorymanager.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.wntiv.inventorymanager.client.render.PlayerInventoryRenderer;
import net.wntiv.inventorymanager.client.render.WindowRendererManager;
import net.wntiv.inventorymanager.client.render.generic.Colored9x3Renderer;
import net.wntiv.inventorymanager.client.render.generic.Generic9x3Renderer;
import net.wntiv.inventorymanager.client.window.WindowProviderManager;
import net.wntiv.inventorymanager.client.window.block.BlockWindowProvider;
import net.wntiv.inventorymanager.client.window.block.Colored9x3Window;
import net.wntiv.inventorymanager.client.window.block.Generic9x3Window;
import net.wntiv.inventorymanager.client.window.entity.EntityWindowProvider;
import net.wntiv.inventorymanager.client.window.item.ItemWindow;
import net.wntiv.inventorymanager.client.window.item.ItemWindowProvider;
import net.wntiv.inventorymanager.client.window.entity.player.PlayerInventoryProvider;
import net.wntiv.inventorymanager.client.window.entity.player.PlayerInventoryWindow;

@Environment(EnvType.CLIENT)
public class InventoryManagerClient implements ClientModInitializer {
    public static WindowRendererManager renderer = new WindowRendererManager();
    public static WindowProviderManager windowProvider = new WindowProviderManager();
    public static ScreenHandler currentHandler;
    @Override
    public void onInitializeClient() {
        renderer.register(ItemWindow.class);
        renderer.register(PlayerInventoryWindow.class, PlayerInventoryRenderer.class, PlayerScreenHandler.class);
//        renderer.register(Generic9x3Window.class, Generic9x3Renderer.class, GenericContainerScreenHandler.class);
//        renderer.register(Colored9x3Window.class, Colored9x3Renderer.class, GenericContainerScreenHandler.class);
        windowProvider.register(ItemWindowProvider.class);
        windowProvider.register(BlockWindowProvider.class);
        windowProvider.register(PlayerInventoryProvider.class);
        windowProvider.register(EntityWindowProvider.class);
        ClientTickEvents.END_CLIENT_TICK.register(client -> windowProvider.update(new EventContext(client)));
    }
}
