package net.wntiv.inventorymanager.client.window.item;

import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.InventoryWindowProvider;

import java.util.Collections;
import java.util.List;

public class ItemWindowProvider implements InventoryWindowProvider {
    @Override
    public List<? extends InventoryWindow> getWindows() {
        return Collections.emptyList();
    }
}
