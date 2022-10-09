package net.wntiv.inventorymanager.client.window.entity.player;

import net.minecraft.client.network.ClientPlayerEntity;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.entity.EntityInventoryWindow;

import java.util.UUID;

public class PlayerInventoryWindow extends EntityInventoryWindow {
    protected PlayerInventoryWindow(ClientPlayerEntity player) {
        super(player);
    }

    @Override
    public boolean onSelect(EventContext context) {
        return true;
    }
}
