package net.wntiv.inventorymanager.client.window.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.render.WindowRenderer;
import net.wntiv.inventorymanager.client.render.WindowRendererManager;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.client.window.PerInstanceRenderer;
import net.wntiv.inventorymanager.client.window.block.BlockWindowProvider;

public class ItemWindow extends InventoryWindow implements PerInstanceRenderer {
    private final ItemStack fromStack;

    ItemWindow(ItemStack provider) {
        super(new ItemStackId(provider));
        fromStack = provider;
    }

    @Override
    public boolean onSelect(EventContext context) {
        return false;
    }

    @Override
    public Class<? extends WindowRenderer> getRendererClass(WindowRendererManager manager) {
        return manager.getRendererForWindow(BlockWindowProvider.windowFromBlock(((BlockItem)fromStack.getItem()).getBlock()));
    }

    protected static class ItemStackId extends Identifier {
        private final ItemStack identity;

        protected ItemStackId(ItemStack identity) {
            this.identity = identity;
        }

        @Override
        public boolean equals(Identifier id) {
            return id instanceof ItemStackId && ItemStack.areEqual(((ItemStackId) id).identity, identity);
        }

        @Override
        public int hashCode() {
            NbtCompound tag = identity.getTag();
            return identity.getItem().hashCode() ^ identity.getCount() ^ (tag == null ? 0 : tag.hashCode());
        }
    }
}
