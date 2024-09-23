package hazelclover.hazelsvariouswings.fuctionality;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import hazelclover.hazelsvariouswings.HazelsVariousWings;
import hazelclover.hazelsvariouswings.item.WingsFlightState;
import hazelclover.hazelsvariouswings.item.WingsItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class WingsHandler {
    public static void register() {
        HazelsVariousWings.LOGGER.info("Registering Wings Logic Handler for " + HazelsVariousWings.MOD_ID);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                WingsItem wings = getEquippedWings(client.player);
                if (wings == null) {return;}
                tickClientWithWings(wings, client.player, client);
            }
        });
    }

    private static void tickClientWithWings(WingsItem wings, ClientPlayerEntity player, MinecraftClient client) {
        final boolean isInWater = player.isTouchingWater();
        final boolean isInvOpen = (client.currentScreen instanceof InventoryScreen);

        wings.flightState = (player.groundCollision ? WingsFlightState.GROUNDED : WingsFlightState.FALL);

        if (player.groundCollision || isInWater) {
            if (player.isFallFlying())
            {player.stopFallFlying();}
            wings.currentFlyDuration = wings.flyDuration;
            wings.currentHoverDuration = wings.hoverDuration;
        }
        if (player.isFallFlying()) {
            wings.flightState = WingsFlightState.GLIDE;
            wings.damageItemTimer -= 0.03f;
            return;
        }
        if (((isInvOpen && wings.flightHeldTicks > 0) || isSpacePressed()) && (wings.inWaterFunctional || !isInWater)) {
            onSpaceHeld(player, wings, isInvOpen);
            if (!isInvOpen)
                wings.flightHeldTicks++;
        } else if (!isInvOpen) {
            if (wings.doesGlide && wings.flightHeldTicks > 0 && wings.flightHeldTicks < 4) {
                player.startFallFlying(); // Trigger Elytra flight
                player.fallDistance = 0;
                wings.pastGlideSpeed = player.speed;
            }
            wings.flightHeldTicks = 0;
        }
    }

    public static boolean isSpacePressed() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_SPACE);
    }

    public static WingsItem getEquippedWings(PlayerEntity player) {
        // Use Trinket API to get the item equipped in the chest:cape slot
        return TrinketsApi.getTrinketComponent(player).map(trinketComponent -> {
            ItemStack stack = getItemFromTrinketSlot(trinketComponent, "chest", "cape");
            if (stack.getItem() instanceof WingsItem) {
                // Return the WingItem if found in the trinket slot
                return (WingsItem) stack.getItem();
            }
            return null; // Return null if no WingItem is found
        }).orElse(null);
    }
    private static ItemStack getItemFromTrinketSlot(TrinketComponent trinketComponent, String group, String slot) {
        return trinketComponent.getInventory().get(group).get(slot).getStack(0); // 0 is the slot index
    }

    private static void onSpaceHeld(ClientPlayerEntity player, WingsItem wings, boolean isInvOpen) {
        Vec3d vel = player.getVelocity();
        if (wings.currentFlyDuration > 0 && !isInvOpen) {
            player.fallDistance /= wings.flyPower+1;
            player.setVelocity(vel.x, Math.max(Math.min(vel.y+wings.flyPower*0.4f, wings.flyPower), vel.y), vel.z);
            wings.currentFlyDuration -= 0.05f; // 0.05 is so that it ends up being in seconds (20 ticks in a second)
            wings.damageItemTimer -= 0.045f;

            wings.flightState = WingsFlightState.UP;
        } else if (wings.currentHoverDuration > 0 && vel.y < 0) {
            player.fallDistance *= wings.hoverPower*4.2f;
            player.setVelocity(vel.x, Math.max(Math.min(vel.y+(0.3f-wings.hoverPower), -wings.hoverPower), vel.y), vel.z);
            wings.currentHoverDuration -= 0.05f;
            wings.damageItemTimer -= 0.025f;

            wings.flightState = WingsFlightState.HOVER;
        }
        wings.setFallDistOnServer = player.fallDistance;
        //HazelsVariousWings.LOGGER.info("dist " + player.fallDistance);
    }
}
