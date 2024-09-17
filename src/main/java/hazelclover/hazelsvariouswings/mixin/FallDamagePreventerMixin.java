package hazelclover.hazelsvariouswings.mixin;

import hazelclover.hazelsvariouswings.fuctionality.WingsHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class FallDamagePreventerMixin {

	@Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
	public void handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (entity instanceof PlayerEntity player) {
			// Replace this with your check for space bar being held and WingsItem equipped
			if (WingsHandler.isSpacePressed() && WingsHandler.getEquippedWings(player) != null) {
				// Cancel fall damage
				cir.setReturnValue(false);
			}
		}
	}
}