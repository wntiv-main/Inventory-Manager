package net.wntiv.inventorymanager.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class Texture {
    public final Identifier id;
    public final int width;
    public final int height;
    public final int resolution;

    public Texture(Identifier id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
        int res = 1;
        while (res < width || res < height){
            res <<= 1;
        }
        resolution = res;
    }

    public void bind(MinecraftClient client) {
        client.getTextureManager().bindTexture(id);
    }

    public void draw(MatrixStack matrices, int x, int y) {
        DrawableHelper.drawTexture(matrices, x, y, 0, 0, width, height, resolution, resolution);
    }
}
