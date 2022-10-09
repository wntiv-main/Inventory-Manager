package net.wntiv.inventorymanager.mixin;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.wntiv.inventorymanager.client.InventoryManagerClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    void onConstruct(ScreenHandlerType<?> type, int syncId, CallbackInfo ci) {
        InventoryManagerClient.currentHandler = (ScreenHandler)(Object) this;
    }
}
