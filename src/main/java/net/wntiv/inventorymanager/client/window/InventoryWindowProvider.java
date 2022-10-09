package net.wntiv.inventorymanager.client.window;

import net.wntiv.inventorymanager.client.EventContext;

import java.util.List;

public abstract class InventoryWindowProvider {
    protected final WindowProviderManager manager;

    public InventoryWindowProvider(WindowProviderManager manager) {
        this.manager = manager;
    }

    public void update(EventContext context) {}
    public void onOpenScreen(EventContext context) {}
}
