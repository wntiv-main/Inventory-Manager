package net.wntiv.inventorymanager.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    private static int consumeResize = 0;

    @Inject(method = "resize", at = @At("HEAD"))
    void onResize(MinecraftClient client, int width, int height, CallbackInfo ci) {
        consumeResize++;
    }

    @Inject(method = "init()V", at = @At("HEAD"))
    // Also called on resize
    void onScreenOpen(CallbackInfo ci) {
        if(consumeResize > 0) {
            consumeResize--;
            // RESIZE
        } else {
            // OPEN
        }
    }

    @Inject(method = "onClose", at = @At("RETURN"))
    void onScreenClose(CallbackInfo ci) {

    }
}
