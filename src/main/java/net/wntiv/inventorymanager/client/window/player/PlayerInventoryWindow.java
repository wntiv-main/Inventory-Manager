package net.wntiv.inventorymanager.client.window.player;

import net.minecraft.client.network.ClientPlayerEntity;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

import java.util.UUID;

public class PlayerInventoryWindow extends InventoryWindow {
    protected PlayerInventoryWindow(ClientPlayerEntity player) {
        super(new PlayerId(player));
    }

    @Override
    public boolean onSelect(EventContext context) {
        return true;
    }

    protected static class PlayerId extends Identifier {
        private final UUID playerId;
        PlayerId(ClientPlayerEntity player) {
            playerId = player.getUuid();
        }
        @Override
        public boolean equals(Identifier id) {
            return id instanceof PlayerId && ((PlayerId) id).playerId.equals(playerId);
        }
    }
}
