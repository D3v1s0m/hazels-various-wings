package hazelclover.hazelsvariouswings.item;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import hazelclover.hazelsvariouswings.HazelsVariousWings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class WingsItem extends TrinketItem implements TrinketRenderer {
    public WingsItem(Settings settings) {
        super(settings);
        TrinketRendererRegistry.registerRenderer(this, (TrinketRenderer) this);
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        // Bind your custom texture
        Identifier texture = Identifier.of("hazels-various-wings", "textures/entity/flimsy_wings.png");
        RenderSystem.setShaderTexture(0, texture);

        // Prepare the matrix stack for transformations
        matrices.push();

        // Set up the texture
        TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();
        textureManager.bindTexture(texture);

        // Apply transformations (e.g., scale, rotate, translate)
        matrices.translate(0.0, 0.0, 0.0); // Adjust position
        matrices.scale(1.0f, 1.0f, 1.0f);   // Adjust scale

        // Render the texture
        VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate();
        Sprite sprite = MinecraftClient.getInstance().getSpriteAtlas().apply(texture);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(VertexFormats.POSITION_TEXTURE);
        drawTexture(matrices, vertexConsumer, 0, 0, 16, 16, 0, 0, 1, 1, light, OverlayTexture.DEFAULT_UV);

        // Restore the matrix stack
        matrices.pop();
    }

    private void drawTexture(MatrixStack matrices, VertexConsumer vertexConsumer, int x, int y, int width, int height, float u0, float v0, float u1, float v1, int light, int overlay) {
        MatrixStack.Entry entry = matrices.peek();
        float x0 = x;
        float y0 = y;
        float x1 = x + width;
        float y1 = y + height;
        vertexConsumer.vertex(0, 1, 0).color(255, 255, 255, 255).texture(u0, v1).light(light).overlay(overlay).next();
        vertexConsumer.vertex(1, 1, 0).color(255, 255, 255, 255).texture(u1, v1).light(light).overlay(overlay).next();
        vertexConsumer.vertex(1, 0, 0).color(255, 255, 255, 255).texture(u1, v0).light(light).overlay(overlay).next();
        vertexConsumer.vertex(0, 0, 0).color(255, 255, 255, 255).texture(u0, v0).light(light).overlay(overlay).next();
    }
}
