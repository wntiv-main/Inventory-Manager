package net.wntiv.inventorymanager.mixin;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HandledScreen.class)
public interface HandledScreenInvoker {
    @Invoker
    void invokeDrawSlot(MatrixStack matrices, Slot slot);
    @Invoker
    boolean invokeIsPointOverSlot(Slot slot, double pointX, double pointY);
}
