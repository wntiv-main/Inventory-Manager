package net.wntiv.inventorymanager.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;

public class EventContext {
    public final MinecraftClient client;
    @NotNull
    public final ClientPlayerEntity player;
    @NotNull
    public final ClientWorld world;

    public EventContext(MinecraftClient client) {
        assert client.player != null;
        assert client.world != null;
        this.client = client;
        this.player = client.player;
        this.world = client.world;
    }
}
