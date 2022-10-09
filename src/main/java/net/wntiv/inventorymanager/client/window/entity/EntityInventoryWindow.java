package net.wntiv.inventorymanager.client.window.entity;

import net.minecraft.entity.Entity;
import net.wntiv.inventorymanager.client.EventContext;
import net.wntiv.inventorymanager.client.window.InventoryWindow;

import java.util.UUID;

public class EntityInventoryWindow extends InventoryWindow {
    protected EntityInventoryWindow(Entity player) {
        super(new EntityId(player));
    }

    @Override
    public boolean onSelect(EventContext context) {
        return true;
    }

    protected static class EntityId extends Identifier {
        private final UUID uuid;
        public EntityId(Entity entity) {
            uuid = entity.getUuid();
        }
        @Override
        public boolean equals(Identifier id) {
            return id instanceof EntityId && ((EntityId) id).uuid.equals(uuid);
        }

        @Override
        public int hashCode() {
            return uuid.hashCode();
        }
    }
}
