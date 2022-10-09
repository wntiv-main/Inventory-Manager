package net.wntiv.inventorymanager.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.wntiv.inventorymanager.client.InventoryManagerClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.InvocationTargetException;

@Mixin(Slot.class)
public class SlotMixin {
    @Mutable
    @Shadow @Final public int x;

    @Mutable
    @Shadow @Final public int y;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    void onConstruct(Inventory inventory, int index, int x, int y, CallbackInfo ci) {
        Integer[] newCoords = remapCoords(InventoryManagerClient.currentHandler.getClass(), x, y);
        this.x = newCoords[0];
        this.y = newCoords[1];
    }

    Integer[] remapCoords(Class<? extends ScreenHandler> type, int x, int y) {
//        return InventoryManagerClient.renderer.getRendererInstance(
        try {
            return (Integer[]) InventoryManagerClient.renderer.getRendererForHandler(type).getMethod("remapSlotCoords", int.class, int.class).invoke(null, x, y);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return new Integer[]{x, y};
        }
//        ).remapSlotCoords(x, y);
    }
}
