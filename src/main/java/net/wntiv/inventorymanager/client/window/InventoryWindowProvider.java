package net.wntiv.inventorymanager.client.window;

import java.util.List;

public interface InventoryWindowProvider {
    List<? extends InventoryWindow> getWindows();
}
