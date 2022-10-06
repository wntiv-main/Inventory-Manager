package net.wntiv.inventorymanager.client.window.entity;

import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.InventoryWindowProvider;
import net.wntiv.inventorymanager.client.window.WindowProviderManager;

import java.util.List;

public class EntityWindowProvider extends InventoryWindowProvider {
    public EntityWindowProvider(WindowProviderManager manager) {
        super(manager);
    }

    @Override
    public List<? extends InventoryWindow> getWindows(EventContext context) {
        return null;
    }
}
