package hazelclover.hazelsvariouswings.mixin;

import hazelclover.hazelsvariouswings.config.GeneralConfig;
import hazelclover.hazelsvariouswings.fuctionality.WingsHandler;
import hazelclover.hazelsvariouswings.item.WingsItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketItemUseWithWings {
    /**
     * @reason Hazel's Various Wings needs to disable firework rocket use if canUseFirework setting is false
     * @author D3v1s0m
     */
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isFallFlying()Z", shift = At.Shift.AFTER), cancellable = true)
    private void handleMetadata(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack itemStack = user.getEquippedStack(EquipmentSlot.CHEST);
        WingsItem wings = WingsHandler.getEquippedWings(user);
        if (itemStack.isOf(Items.ELYTRA) && (GeneralConfig.INSTANCE.disableVanillaElytra.get() || !GeneralConfig.INSTANCE.canUseFireworks.get()) && wings == null) {
            cir.setReturnValue(TypedActionResult.pass(user.getStackInHand(hand)));
        } else {
            if (wings != null && wings.config.doesGlide.get() && !wings.config.canUseFireworks.get()) {
                cir.setReturnValue(TypedActionResult.pass(user.getStackInHand(hand)));
            }
        }
    }

}
