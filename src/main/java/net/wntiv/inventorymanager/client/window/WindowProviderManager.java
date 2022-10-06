package net.wntiv.inventorymanager.client.window;

import net.wntiv.inventorymanager.client.EventContext;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class WindowProviderManager {
    List<InventoryWindowProvider> providers = new ArrayList<>();
    Map<InventoryWindow.Identifier, InventoryWindow> windows = new HashMap<>();

    public InventoryWindowProvider register(Class<? extends InventoryWindowProvider> provider) {
        try {
            InventoryWindowProvider instance = provider.getConstructor(WindowProviderManager.class).newInstance(this);
            providers.add(instance);
            return instance;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(InventoryWindowProvider provider) {
        providers.add(provider);
    }

    public List<InventoryWindow> getWindows(EventContext context) {
        return new ArrayList<>(windows.values());
//        List<InventoryWindow> windows = new ArrayList<>();
//        providers.forEach(provider -> {
//            windows.addAll(provider.getWindows(context));
//        });
//        return windows;
    }



    public void update() {
        providers.forEach(provider -> provider.update(this));
    }

    public void addWindow(InventoryWindow window) {
        windows.put(window.id, window);
    }

    public void removeWindow(InventoryWindow window) {
        windows.remove(window.id);
    }
}
