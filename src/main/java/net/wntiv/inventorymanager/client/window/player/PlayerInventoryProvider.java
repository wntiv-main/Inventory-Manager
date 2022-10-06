package net.wntiv.inventorymanager.client.window.player;

import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.InventoryWindowProvider;
import net.wntiv.inventorymanager.client.window.WindowProviderManager;

import java.util.Collections;
import java.util.List;

public class PlayerInventoryProvider extends InventoryWindowProvider {
    public PlayerInventoryProvider(WindowProviderManager manager) {
        super(manager);
    }

    @Override
    public List<? extends InventoryWindow> getWindows(EventContext context) {
        return Collections.singletonList(new PlayerInventoryWindow(context.player));
    }
}
