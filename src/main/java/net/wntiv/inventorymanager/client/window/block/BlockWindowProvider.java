package net.wntiv.inventorymanager.client.window.block;

import net.minecraft.block.Block;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.InventoryWindowProvider;
import net.wntiv.inventorymanager.client.window.WindowProviderManager;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Collections;
import java.util.List;

public class BlockWindowProvider extends InventoryWindowProvider {
    public BlockWindowProvider(WindowProviderManager manager) {
        super(manager);
    }

    @Override
    public List<? extends InventoryWindow> getWindows(EventContext context) {
        return Collections.emptyList();
    }

    public static Class<? extends InventoryWindow> windowFromBlock(Block block) {
        throw new NotImplementedException("");
    }
}
