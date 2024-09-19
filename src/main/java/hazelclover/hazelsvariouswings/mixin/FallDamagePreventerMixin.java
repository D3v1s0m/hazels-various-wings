package hazelclover.hazelsvariouswings.mixin;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import hazelclover.hazelsvariouswings.fuctionality.WingsHandler;
import hazelclover.hazelsvariouswings.item.WingsItem;
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
			WingsItem wings = WingsHandler.getEquippedWings(player);
			if (wings == null) {return;}
			if (WingsHandler.isSpacePressed()) {
				// Cancel fall damage
				cir.setReturnValue(false);
			}
		}
	}
}