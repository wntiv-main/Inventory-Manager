package net.wntiv.inventorymanager.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.wntiv.inventorymanager.InventoryManager;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.InventoryManagerClient;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow @Nullable protected MinecraftClient client;
    @Shadow public int width;
    @Shadow public int height;
    private static int consumeResize = 0;

    @Inject(method = "resize", at = @At("HEAD"))
    void onResize(MinecraftClient client, int width, int height, CallbackInfo ci) {
        if(!(this instanceof ScreenHandlerProvider)) return;
        consumeResize++;
    }

    @Inject(method = "init()V", at = @At("HEAD"))
    // Also called on resize
    void onScreenOpen(CallbackInfo ci) {
        if(!(this instanceof ScreenHandlerProvider)) return;
        InventoryManagerClient.renderer.setScreenSize(width, height);
        if(consumeResize > 0) {
            consumeResize--;
            // RESIZE
            // do something here?
        } else {
            assert client != null;
            EventContext context = new EventContext(client);
            InventoryManagerClient.windowProvider.onOpenScreen(context);
            InventoryManagerClient.renderer.onOpenScreen(context);
        }
    }

    @Inject(method = "onClose", at = @At("RETURN"))
    void onScreenClose(CallbackInfo ci) {
        if(!(this instanceof ScreenHandlerProvider)) return;
        assert client != null;
        EventContext context = new EventContext(client);
        InventoryManagerClient.windowProvider.onCloseScreen(context);
        InventoryManagerClient.renderer.onCloseScreen(context);
    }
}
