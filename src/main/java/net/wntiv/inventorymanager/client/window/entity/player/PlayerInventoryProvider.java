package net.wntiv.inventorymanager.client.window.entity.player;

import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindowProvider;
import net.wntiv.inventorymanager.client.window.WindowProviderManager;


public class PlayerInventoryProvider extends InventoryWindowProvider {
    public PlayerInventoryProvider(WindowProviderManager manager) {
        super(manager);
    }

    @Override
    public void onOpenScreen(EventContext context) {
        super.onOpenScreen(context);
        if(context.client.player != null)
            manager.addWindow(new PlayerInventoryWindow(context.player));
    }
}
