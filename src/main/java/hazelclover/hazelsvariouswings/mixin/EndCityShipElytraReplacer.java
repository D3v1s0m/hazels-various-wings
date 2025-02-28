package hazelclover.hazelsvariouswings.mixin;

import hazelclover.hazelsvariouswings.config.GeneralConfig;
import hazelclover.hazelsvariouswings.item.ModItems;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.EndCityGenerator;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EndCityGenerator.Piece.class)
public class EndCityShipElytraReplacer {
	/**
	 * @reason Hazel's Various Wings adds its own elytra
	 * @author Hazel
	 */
	@Inject(method = "handleMetadata", at = @At("HEAD"), cancellable = true)
	private void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox, CallbackInfo ci) {
		if (GeneralConfig.INSTANCE.replaceElytraInEndCityShip.get()) {
			if (metadata.startsWith("Elytra")) {
				EndCityGenerator.Piece piece = (EndCityGenerator.Piece) (Object) this;
				ItemFrameEntity itemFrameEntity = new ItemFrameEntity(world.toServerWorld(), pos, piece.getPlacementData().getRotation().rotate(Direction.SOUTH));
				Item item = world.getRegistryManager().get(RegistryKeys.ITEM).get(GeneralConfig.INSTANCE.replaceElytraInEndCityShipWith.get());
				if (item == null) {
					item = ModItems.ELYTRA_WINGS;
				}
				itemFrameEntity.setHeldItemStack(new ItemStack(item), false);
				world.spawnEntity(itemFrameEntity);

				ci.cancel();
			}
		}
	}
}