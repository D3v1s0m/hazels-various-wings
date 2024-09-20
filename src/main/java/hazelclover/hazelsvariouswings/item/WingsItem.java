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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.RotationAxis;

import java.util.List;

import static java.lang.Math.*;

public class WingsItem extends TrinketItem implements TrinketRenderer {
    private final float scale;
    public boolean setFallDistOnServerToZero = false;

    public final float flyDuration;
    public final float flyPower;
    public final float hoverDuration;
    public final float hoverPower;
    public float currentFlyDuration;
    public float currentHoverDuration;

    public final boolean inWaterFunctional;
    public final boolean doesGlide;

    public int flightHeldTicks = 0;
    public float damageItemTimer = 1;
    private boolean tookDurabilityDamage = false;

    public WingsFlightState flightState = WingsFlightState.GROUNDED;

    public WingsItem(Settings settings, float scale, float flyDuration, float flyPower, float hoverDuration, float hoverPower, boolean inWaterFunctional, boolean doesGlide) {
        super(settings);
        TrinketRendererRegistry.registerRenderer(this, (TrinketRenderer) this);
        this.scale = scale;
        this.flyDuration = flyDuration;
        this.flyPower = flyPower;
        this.hoverDuration = hoverDuration;
        this.hoverPower = hoverPower;
        this.currentFlyDuration = flyDuration;
        this.currentHoverDuration = hoverDuration;
        this.inWaterFunctional = inWaterFunctional;
        this.doesGlide = doesGlide;
    }

    // To make you stop gliding if you currently are
    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player && entity.isFallFlying() && !tookDurabilityDamage)
        {
            player.stopFallFlying();
        }
        tookDurabilityDamage = false;
    }
    @Override
    public void onBreak(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onBreak(stack, slot, entity);
        if (entity instanceof PlayerEntity player && entity.isFallFlying())
        {
            player.stopFallFlying();
        }
    }

    // For taking durability damage and setFallDistOnServerToZero
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        if (entity.getWorld().isClient()) {return;}

        if (setFallDistOnServerToZero) {entity.fallDistance = 0;}

        if (damageItemTimer > 0) {return;}
        damageItemTimer++;
        tookDurabilityDamage = true;
        stack.damage(1, (ServerWorld) entity.getWorld(), (ServerPlayerEntity) entity, item -> {});
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (doesGlide) {tooltip.add(Text.translatable("tooltip.hazels-various-wings.wingsitem.doesGlide").formatted(Formatting.LIGHT_PURPLE));}
        tooltip.add(Text.translatable("tooltip.hazels-various-wings.wingsitem.generic").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        // Get the baked model for the item stack
        BakedModel itemModel = itemRenderer.getModel(stack, entity.getWorld(), entity, 0);

        // Follow body rotations and translate to chest
        TrinketRenderer.followBodyRotations(entity, (BipedEntityModel<LivingEntity>) contextModel);
        TrinketRenderer.translateToChest(matrices, (PlayerEntityModel<AbstractClientPlayerEntity>) contextModel, (AbstractClientPlayerEntity) entity);

        // Animation settings
        float sYawBaseAngle = 0;
        float sYawFlapSpeed = 0;
        float sYawFlapAngle = 0;
        float sPitchAngle = 0;
        switch (flightState) {
            case GROUNDED -> {
                sYawBaseAngle = 0.8f;
                sYawFlapSpeed = 0.08f;
                sYawFlapAngle = 0.4f;
                sPitchAngle = 0.9f;
            }
            case FALL -> {

            }
        }

        // Render left wing
        // Push matrix first
        matrices.push();
        // Shift matrices position and animate
        float rot = (float) (sYawFlapAngle * sin(sYawFlapSpeed * animationProgress) + sYawBaseAngle);
        rot = animationProgress*0.05f;
        matrices.scale(scale, scale, scale);
//        matrices.translate(cos(rot) * 0.48f + (0.16f / scale), (-0.2f / scale), (0.28f / scale) + sin(rot) * 0.48f); // X right-left Y up-down Z forwards-backwards
//        matrices.multiply(RotationAxis.POSITIVE_X.rotation((float) (PI))); // flip right way up
//        matrices.multiply(RotationAxis.POSITIVE_Y.rotation((float) (rot + PI)));
        // Render the item
        itemRenderer.renderItem(stack, ModelTransformationMode.FIXED, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, itemModel);
        // Pop matrix
        matrices.pop();

//        // Render right wing
//        // Push matrix first
//        matrices.push();
//        // Shift matrices position and animate
//        rot = (float) (PI - rot);
//        matrices.scale(scale, scale, scale);
//        matrices.translate(cos(rot) * 0.48f - (0.16f / scale), (-0.2f / scale), (0.28f / scale) + sin(rot) * 0.48f); // X right-left Y up-down Z forwards-backwards
//        matrices.multiply(RotationAxis.POSITIVE_X.rotation((float) (PI))); // flip right way up
//        matrices.multiply(RotationAxis.POSITIVE_Y.rotation((float) (rot + PI)));
//        //matrices.multiply(RotationAxis.POSITIVE_Z.rotation((0.7f)));
//
//        // Render the item
//        itemRenderer.renderItem(stack, ModelTransformationMode.FIXED, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, itemModel);
//        // Pop matrix
//        matrices.pop();
    }
}
