package hazelclover.hazelsvariouswings.mixin;

import hazelclover.hazelsvariouswings.fuctionality.WingsHandler;
import hazelclover.hazelsvariouswings.item.WingsItem;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerEntity.class)
public class PlayerEntityCheckFallFlyingHasWings {
	/**
	 * @reason Hazel's Various Wings needs to count certain wings as being able to trigger gliding
	 * @author Hazel
	 */
	@Inject(method = "checkFallFlying", at = @At("HEAD"), cancellable = true)
	private void handleMetadata(CallbackInfoReturnable<Boolean> cir) {
		WingsItem wings = WingsHandler.getEquippedWings((PlayerEntity) (Object) this);
		if (wings == null || !wings.config.doesGlide) {return;}
		cir.setReturnValue(true);
		cir.cancel();
	}
}