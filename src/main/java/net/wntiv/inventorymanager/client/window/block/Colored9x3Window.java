package net.wntiv.inventorymanager.client.window.block;

import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.wntiv.inventorymanager.Colored;

public class Colored9x3Window extends Generic9x3Window implements Colored {
    private final DyeColor color;

    Colored9x3Window(BlockPos pos, Block at, DyeColor color) {
        super(pos, at);
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return color;
    }
}
