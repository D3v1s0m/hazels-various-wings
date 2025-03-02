package hazelclover.hazelsvariouswings.fuctionality;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import hazelclover.hazelsvariouswings.client.WingsClientHandler;
import hazelclover.hazelsvariouswings.item.WingsFlightState;
import hazelclover.hazelsvariouswings.item.WingsItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class WingsHandler {
    public static WingsItem getEquippedWings(PlayerEntity player) {
        return TrinketsApi.getTrinketComponent(player).map(trinketComponent -> {
            ItemStack stack = getItemFromTrinketSlot(trinketComponent, "chest", "cape");
            return (stack.getItem() instanceof WingsItem) ? (WingsItem) stack.getItem() : null;
        }).orElse(null);
    }

    private static ItemStack getItemFromTrinketSlot(TrinketComponent trinketComponent, String group, String slot) {
        return trinketComponent.getInventory().get(group).get(slot).getStack(0);
    }

    // This method is now only referenced in client code
    @Environment(EnvType.CLIENT)
    public static void tickClient(PlayerEntity player, MinecraftClient client) {
        WingsItem wings = getEquippedWings(player);
        if (wings == null) return;

        final boolean isInWater = player.isTouchingWater();
        final boolean isInvOpen = client.currentScreen instanceof InventoryScreen;

        wings.flightState = player.isOnGround() ? WingsFlightState.GROUNDED : WingsFlightState.FALL;

        if (player.isOnGround() || isInWater) {
            if (player.isFallFlying()) {
                player.stopFallFlying();
                wings.startGlideOnServer = false;
            }
            wings.currentFlyDuration = wings.config.flyDuration;
            wings.currentHoverDuration = wings.config.hoverDuration;
        }

        if (player.isFallFlying()) {
            wings.flightState = WingsFlightState.GLIDE;
            wings.damageItemTimer -= 0.03f;
            return;
        }

        if ((isInvOpen && wings.flightHeldTicks > 0) || WingsClientHandler.isSpacePressed()) {
            onSpaceHeld((ClientPlayerEntity) player, wings, isInvOpen);
            if (!isInvOpen) wings.flightHeldTicks++;
        } else if (!isInvOpen) {
            if (wings.config.doesGlide.get() && wings.flightHeldTicks > 0 && wings.flightHeldTicks < 4 && !isInWater) {
                wings.startGlideOnServer = true;
                wings.pastGlideSpeed = (float) player.getVelocity().length();
            }
            wings.flightHeldTicks = 0;
        }
    }

    private static void onSpaceHeld(ClientPlayerEntity player, WingsItem wings, boolean isInvOpen) {
        if (!isInvOpen && wings.currentFlyDuration > 0) {
            player.fallDistance /= wings.config.flyPower + 1;
            player.setVelocity(player.getVelocity().x, Math.min(player.getVelocity().y + wings.config.flyPower * 0.4f, wings.config.flyPower), player.getVelocity().z);
            wings.currentFlyDuration -= 0.05f;
            wings.damageItemTimer -= 0.045f;
            wings.flightState = WingsFlightState.UP;
        } else if (wings.currentHoverDuration > 0) {
            player.fallDistance *= wings.config.hoverPower * 4.2f;
            player.setVelocity(player.getVelocity().x, 0, player.getVelocity().z);
            wings.currentHoverDuration -= 0.05f;
            wings.damageItemTimer -= 0.025f;
            wings.flightState = WingsFlightState.HOVER;
        }

        wings.setFallDistOnServer = player.fallDistance;
    }
}
