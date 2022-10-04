package net.wntiv.inventorymanager.client.window.block;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

public class Generic9x3Window extends InventoryWindow {
    Generic9x3Window(BlockPos pos, Block at) {
        super(new BlockId(pos, at));
    }

    @Override
    public void onSelect() {

    }

    protected static class BlockId extends Identifier{
        private final BlockPos pos;
        private final Block blockAt;

        protected BlockId(BlockPos pos, Block blockAt) {
            this.pos = pos;
            this.blockAt = blockAt;
        }

        @Override
        public boolean equals(Identifier id) {
            if(!(id instanceof BlockId)) return false;
            BlockId other = (BlockId) id;
            return other.pos.equals(pos) && other.blockAt.is(blockAt);
        }
    }
}
