package net.wntiv.inventorymanager.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.InventoryManagerClient;
import net.wntiv.inventorymanager.client.RenderingEventContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow @Final private MinecraftClient client;

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V"))
    void onRenderScreen(Screen instance, MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if(instance instanceof ScreenHandlerProvider) {
            // CANCEL VANILLA RENDERING
            InventoryManagerClient.renderer.renderAll(
                InventoryManagerClient.windowProvider.getWindows(new EventContext(client)),
                new RenderingEventContext(client, matrices, mouseX, mouseY, delta)
            );
        } else {
            // VANILLA RENDERING
            instance.render(matrices, mouseX, mouseY, delta);
        }
    }
}
