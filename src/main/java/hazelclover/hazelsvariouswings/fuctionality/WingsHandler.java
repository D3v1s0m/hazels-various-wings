package hazelclover.hazelsvariouswings.fuctionality;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import hazelclover.hazelsvariouswings.item.WingsItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class WingsHandler {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (isSpacePressed()) {
                    onSpaceHeld(client.player);
                }
            }
        });
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

    private static void onSpaceHeld(ClientPlayerEntity player) {
        if (player.groundCollision) {return;}
        WingsItem wings = getEquippedWings(player);
        if (wings != null) {
            Vec3d vel = player.getVelocity();
            player.setVelocity(vel.x, Math.min(vel.y+0.1f, 0.2f), vel.z);
        }
    }
}
