package net.wntiv.inventorymanager.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class RenderingEventContext extends EventContext {
    public final int mouseX, mouseY;
    public final MatrixStack matrices;
    public final float tickDelta;
    public RenderingEventContext(MinecraftClient client, MatrixStack matrices, int mx, int my, float delta) {
        super(client);
        mouseX = mx;
        mouseY = my;
        this.matrices = matrices;
        tickDelta = delta;
    }
}
