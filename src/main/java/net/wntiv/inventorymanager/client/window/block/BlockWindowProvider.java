package net.wntiv.inventorymanager.client.window.block;

import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.InventoryWindowProvider;

import java.util.Collections;
import java.util.List;

public class BlockWindowProvider implements InventoryWindowProvider {
    @Override
    public List<? extends InventoryWindow> getWindows() {
        return Collections.emptyList();
    }
}
