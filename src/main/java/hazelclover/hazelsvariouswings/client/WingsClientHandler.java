package hazelclover.hazelsvariouswings.client;

import hazelclover.hazelsvariouswings.fuctionality.WingsHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class WingsClientHandler implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                WingsHandler.tickClient(client.player, client);
            }
        });
    }

    public static boolean isSpacePressed() {
        return MinecraftClient.getInstance().options.jumpKey.isPressed();
    }
}