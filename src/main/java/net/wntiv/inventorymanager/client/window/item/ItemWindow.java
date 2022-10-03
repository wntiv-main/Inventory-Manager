package net.wntiv.inventorymanager.client.window.item;

import net.minecraft.item.ItemStack;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

public class ItemWindow extends InventoryWindow {
    ItemWindow(ItemStack provider) {
        super(new ItemStackId(provider));
    }

    @Override
    public void onSelect() {
        //
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
    }
}
