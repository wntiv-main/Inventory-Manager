package net.wntiv.inventorymanager.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.wntiv.inventorymanager.client.InventoryManagerClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    void onRenderHotbar(float tickDelta, MatrixStack matrices, CallbackInfo ci) {
        if(InventoryManagerClient.windowProvider.isScreenOpen())
            ci.cancel();
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    void onRenderCrosshair(MatrixStack matrices, CallbackInfo ci) {
        if(InventoryManagerClient.windowProvider.isScreenOpen())
            ci.cancel();
    }
}
