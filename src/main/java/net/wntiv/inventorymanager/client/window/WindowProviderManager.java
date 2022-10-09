package net.wntiv.inventorymanager.client.window;

import net.wntiv.inventorymanager.client.EventContext;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class WindowProviderManager {
    private final List<InventoryWindowProvider> providers = new ArrayList<>();
    private final Map<InventoryWindow.Identifier, WindowRecord> windows = new HashMap<>();
    private boolean screenOpen = false;

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
        return windows.values().stream().map(record -> record.window).collect(Collectors.toList());
    }

    public void onOpenScreen(EventContext context) {
        screenOpen = true;
        // Ask for all providers to re-open ALL windows to verify what should and shouldn't be open
        providers.forEach(provider -> provider.onOpenScreen(context));
        // Anything that has not been re-opened by the above should be removed
        new ArrayList<>(windows.values()).forEach(windowRecord -> {
            if(windowRecord.dirty) removeWindow(windowRecord.window);
        });
    }

    public void onCloseScreen(EventContext context) {
        screenOpen = false;
        // Mark everything as dirty to be removed next onScreenOpen if it has not been re-opened
        windows.values().forEach(windowRecord -> windowRecord.dirty = true);
    }

    public void update(EventContext context) {
        if(!screenOpen) return;
        // Every tick while a screen is open, providers can check for new/old windows
        providers.forEach(provider -> provider.update(context));
    }

    public boolean isScreenOpen() {
        return screenOpen;
    }

    public void addWindow(InventoryWindow window) {
        if(windows.containsKey(window.id)){
            windows.get(window.id).dirty = false;
            windows.get(window.id).window = window;
        } else {
            windows.put(window.id, new WindowRecord(window));
        }
    }

    public void removeWindow(InventoryWindow window) {
        windows.remove(window.id);
    }

    private static class WindowRecord {
        public InventoryWindow window;
        public boolean dirty = false;

        public WindowRecord(InventoryWindow window) {
            this.window = window;
        }
    }
}
