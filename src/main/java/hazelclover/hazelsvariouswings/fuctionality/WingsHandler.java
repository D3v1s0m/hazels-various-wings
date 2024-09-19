package hazelclover.hazelsvariouswings.fuctionality;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import hazelclover.hazelsvariouswings.item.WingsItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;
import java.util.function.Consumer;

public class WingsHandler {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                WingsItem wings = getEquippedWings(client.player);
                if (wings == null) {return;}
                tickClientWithWings(wings, client.player, client);
            }
        });
    }

    private static void tickClientWithWings(WingsItem wings, ClientPlayerEntity player, MinecraftClient client) {
        wings.setFallDistOnServerToZero = false;

        final boolean isInWater = player.isTouchingWater();

        if (player.groundCollision || isInWater) {
            if (player.isFallFlying())
            {player.stopFallFlying();}
            wings.currentFlyDuration = wings.flyDuration;
            wings.currentHoverDuration = wings.hoverDuration;
        }
        if (player.isFallFlying()) {
            wings.damageItemTimer -= 0.026f;
            return;
        }
        if (client.currentScreen instanceof InventoryScreen) {return;}
        if (isSpacePressed() && (wings.inWaterFunctional || !isInWater)) {
            onSpaceHeld(player, wings);
            wings.flightHeldTicks++;
        } else {
            if (wings.doesGlide && wings.flightHeldTicks > 0 && wings.flightHeldTicks < 3) {
                player.startFallFlying(); // Trigger Elytra flight
                player.fallDistance = 0;
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

    private static void onSpaceHeld(ClientPlayerEntity player, WingsItem wings) {
        player.fallDistance = 0f;
        wings.setFallDistOnServerToZero = true;
        Vec3d vel = player.getVelocity();
        if (wings.currentFlyDuration > 0) {
            player.setVelocity(vel.x, Math.max(Math.min(vel.y+wings.flyPower*0.4f, wings.flyPower), vel.y), vel.z);
            wings.currentFlyDuration -= 0.05f; // 0.05 is so that it ends up being in seconds (20 ticks in a second)
            wings.damageItemTimer -= 0.05f;
        } else if (wings.currentHoverDuration > 0) {
            player.setVelocity(vel.x, Math.max(Math.min(vel.y+(0.3f-wings.hoverPower), -wings.hoverPower), vel.y), vel.z);
            wings.currentHoverDuration -= 0.05f;
            wings.damageItemTimer -= 0.02f;
        }
    }
}
