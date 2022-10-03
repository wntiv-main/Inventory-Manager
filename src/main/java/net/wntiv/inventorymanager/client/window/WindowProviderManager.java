package net.wntiv.inventorymanager.client.window;

import java.util.ArrayList;
import java.util.List;

public class WindowProviderManager {
    List<InventoryWindowProvider> providers = new ArrayList<>();

    public void register(InventoryWindowProvider provider) {
        providers.add(provider);
    }

    public List<InventoryWindow> getWindows() {
        List<InventoryWindow> windows = new ArrayList<>();
        providers.forEach(provider -> {
            windows.addAll(provider.getWindows());
        });
        return windows;
    }
}
