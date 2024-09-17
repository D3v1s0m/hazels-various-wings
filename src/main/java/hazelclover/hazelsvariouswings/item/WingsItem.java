package hazelclover.hazelsvariouswings.item;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class WingsItem extends TrinketItem implements TrinketRenderer {
    public WingsItem(Settings settings) {
        super(settings);
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        // Save the current transformation state
        matrices.push();

        // Apply transformations: Translate and scale the item
        matrices.translate(0.0D, 0.0D, 0.0D); // Adjust position based on where the item should be
        matrices.scale(1.0F, 1.0F, 1.0F);    // Adjust scale if needed

        // Get the ItemRenderer and BakedModel
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        BakedModel model = itemRenderer.getModel(stack, entity.getWorld(), entity, 0);

        // Render the item model
        itemRenderer.renderItem(stack, ModelTransformationMode.GROUND, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, model);

        // Restore the transformation state
        matrices.pop();
    }
}
