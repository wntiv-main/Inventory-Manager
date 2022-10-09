package net.wntiv.inventorymanager.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.wntiv.inventorymanager.InventoryManager;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.RenderingEventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;
import net.wntiv.inventorymanager.mixin.HandledScreenInvoker;

import java.util.Objects;

public class PlayerInventoryRenderer extends InventoryScreen implements WindowRenderer {
    private static final Texture BACKGROUND_TEXTURE = new Texture(
            new Identifier(InventoryManager.MODID, "textures/gui/player.png"),
            269,
            100
    );

    private final WindowRendererManager manager;

    public PlayerInventoryRenderer(WindowRendererManager manager, EventContext context) {
        super(context.player);
        this.client = context.client;
        this.manager = manager;
        this.backgroundWidth = BACKGROUND_TEXTURE.width;
        this.backgroundHeight = BACKGROUND_TEXTURE.height;
    }

    @Override
    public void render(InventoryWindow window, RenderingEventContext context) {
//        RenderSystem.shadeModel(7424);
        RenderSystem.enableBlend();
//        RenderSystem.enableAlphaTest();
        RenderSystem.enableTexture();
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        itemRenderer = client.getItemRenderer();
        textRenderer = client.textRenderer;
        x = (manager.getWidth() - BACKGROUND_TEXTURE.width) / 2;
        y = (manager.getHeight() - BACKGROUND_TEXTURE.height) / 2;
        BACKGROUND_TEXTURE.bind(context.client);
        BACKGROUND_TEXTURE.draw(context.matrices, x, y);
        InventoryScreen.drawEntity(x + 219, y + 85, 30, x + 219 - context.mouseX, y + 85 - 50 - context.mouseY, context.player);
        int n, o;
        RenderSystem.glMultiTexCoord2f(33986, 240.0f, 240.0f);
        for (int m = 0; m < (Objects.requireNonNull(context.client.player).currentScreenHandler).slots.size(); ++m) {
            Slot slot = (Objects.requireNonNull(context.client.player).currentScreenHandler).slots.get(m);
            if (slot.doDrawHoveringEffect()) {
                ((HandledScreenInvoker)this).invokeDrawSlot(context.matrices, slot);
            }
            if (!((HandledScreenInvoker)this).invokeIsPointOverSlot(slot, context.mouseX, context.mouseY) || !slot.doDrawHoveringEffect()) continue;
            this.focusedSlot = slot;
            RenderSystem.disableDepthTest();
            n = slot.x + this.x;
            o = slot.y + this.y;
            RenderSystem.colorMask(true, true, true, false);
            this.fillGradient(context.matrices, n, o, n + 16, o + 16, -2130706433, -2130706433);
            RenderSystem.colorMask(true, true, true, true);
            RenderSystem.enableDepthTest();
        }
    }

    public static Integer[] remapSlotCoords(int x, int y) {
        if(x < 10 && y < 85){
            x = 245;
            y = 22 + y - 8;
        } else if(y > 60 && y < 82) {
            x = 8;
            y = 76;
        } else if (y > 82) {
            x += 22;
            y -= 66;
        }
        return new Integer[]{x, y};
    }
}
