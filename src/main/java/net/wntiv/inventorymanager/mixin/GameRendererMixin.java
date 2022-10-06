package net.wntiv.inventorymanager.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V"))
    void onRenderScreen(Screen instance, MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if(instance instanceof ScreenHandlerProvider) {
            // CANCEL RENDERING
        } else {
            instance.render(matrices, mouseX, mouseY, delta);
        }
    }
}
