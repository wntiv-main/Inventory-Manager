package net.wntiv.inventorymanager.client.window.item;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerFactory;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.InventoryWindowProvider;
import net.wntiv.inventorymanager.client.window.WindowProviderManager;

import java.util.ArrayList;
import java.util.List;

public class ItemWindowProvider extends InventoryWindowProvider {
    public ItemWindowProvider(WindowProviderManager manager) {
        super(manager);
    }

    @Override
    public List<? extends InventoryWindow> getWindows(EventContext context) {
        List<InventoryWindow> result = new ArrayList<>();
        context.player.currentScreenHandler.getStacks().forEach(itemStack -> {
            Item item = itemStack.getItem();
            if(item instanceof BlockItem && ((BlockItem) item).getBlock().hasBlockEntity() &&
                    ((BlockEntityProvider)((BlockItem) item).getBlock()).createBlockEntity(context.world) instanceof ScreenHandlerFactory) {
                result.add(new ItemWindow(itemStack));
            }
        });
        return result;
    }
}
