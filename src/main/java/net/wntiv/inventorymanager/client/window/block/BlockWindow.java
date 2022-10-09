package net.wntiv.inventorymanager.client.window.block;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

public abstract class BlockWindow extends InventoryWindow {
    private final BlockPos pos;
    private final Block blockAt;
    BlockWindow(BlockPos pos, Block at) {
        super(new BlockId(pos, at));
        this.pos = pos;
        blockAt = at;
    }

    @Override
    public boolean onSelect(EventContext context) {
        return context.world.getBlockState(pos).getBlock().is(blockAt);
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

        @Override
        public int hashCode() {
            return pos.hashCode() ^ blockAt.hashCode();
        }
    }
}
