package hazelclover.hazelsvariouswings.mixin;

import hazelclover.hazelsvariouswings.fuctionality.WingsHandler;
import hazelclover.hazelsvariouswings.item.WingsItem;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityTickFallFlyingWingCheck {
    /**
     * @reason Hazel's Various Wings needs to count certain wings as being able to trigger gliding
     * @author Hazel
     */
    @Inject(method = "tickFallFlying", at = @At("HEAD"), cancellable = true)
    private void handleMetadata(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        EntityGetSetFlagInvoker invoker = (EntityGetSetFlagInvoker) entity;

        boolean bl = invoker.invokeGetFlag(7);
        if (bl && !entity.isOnGround() && !entity.hasVehicle() && !entity.hasStatusEffect(StatusEffects.LEVITATION)) {
            bl = false;
            if (entity instanceof PlayerEntity player) {
                WingsItem wings = WingsHandler.getEquippedWings(player);
                if (wings != null && wings.doesGlide) {
                    bl = true;
                    int i = entity.getFallFlyingTicks() + 1;
                    if (!entity.getWorld().isClient && i % 10 == 0) {
//                        int j = i / 10;
//                        if (j % 2 == 0) {
//                            itemStack.damage(1, entity, EquipmentSlot.CHEST);
//                        }

                        entity.emitGameEvent(GameEvent.ELYTRA_GLIDE);
                    }
                }
            }
        } else {
            bl = false;
        }

        if (!entity.getWorld().isClient) {
            invoker.invokeSetFlag(7, bl);
        }
        ci.cancel();
    }
}
