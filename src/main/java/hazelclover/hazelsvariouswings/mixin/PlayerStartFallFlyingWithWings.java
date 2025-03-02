package hazelclover.hazelsvariouswings.mixin;

import hazelclover.hazelsvariouswings.fuctionality.WingsHandler;
import hazelclover.hazelsvariouswings.item.WingsItem;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class PlayerStartFallFlyingWithWings {
    /**
     * @reason Hazel's Various Wings needs to count certain wings as being able to trigger gliding
     * @author Hazel
     */
    @Inject(method = "tickMovement", at = @At("TAIL"))
    private void handleMetadata(CallbackInfo ci) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        if (player.input.jumping && !player.hasVehicle() && !player.isClimbing()) {
            WingsItem wings = WingsHandler.getEquippedWings(player);
            if (wings != null && wings.config.doesGlide.get() && player.checkFallFlying()) {
                player.networkHandler.sendPacket(new ClientCommandC2SPacket(player, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            }
        }
    }
}