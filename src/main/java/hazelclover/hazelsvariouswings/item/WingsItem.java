package hazelclover.hazelsvariouswings.item;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

import static java.lang.Math.*;

public class WingsItem extends TrinketItem implements TrinketRenderer {
    final float scale;

    public WingsItem(Settings settings, float scale) {
        super(settings);
        TrinketRendererRegistry.registerRenderer(this, (TrinketRenderer) this);
        this.scale = scale;
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        // Get the baked model for the item stack
        BakedModel itemModel = itemRenderer.getModel(stack, entity.getWorld(), entity, 0);

        // Follow body rotations and translate to chest
        TrinketRenderer.followBodyRotations(entity, (BipedEntityModel<LivingEntity>) contextModel);
        TrinketRenderer.translateToChest(matrices, (PlayerEntityModel<AbstractClientPlayerEntity>) contextModel, (AbstractClientPlayerEntity) entity);


        // Push matrix first
        matrices.push();



        // Shift matrices position and animate
        float rot = (float) (0.4f*sin(0.08f*animationProgress)+0.8f);
        float out = 0.48f;
        matrices.scale(scale, scale, scale);
        matrices.translate(cos(rot)*out, out*-0.2f, (0.28f/scale)+sin(rot)*out); // X right-left Y down-up Z forwards-backwards
        matrices.multiply(RotationAxis.POSITIVE_X.rotation((float) PI)); // flip right way up
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation((float) (rot+PI)));

        // Render the item
        itemRenderer.renderItem(stack, ModelTransformationMode.FIXED, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, itemModel);

        // Pop matrix
        matrices.pop();
    }
}
