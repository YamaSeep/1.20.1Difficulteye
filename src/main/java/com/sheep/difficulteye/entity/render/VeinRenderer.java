package com.sheep.difficulteye.entity.render;

import com.sheep.difficulteye.entity.custom.Vein;
import com.sheep.difficulteye.entity.model.VeinEntityModel;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.client.renderer.texture.OverlayTexture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;

public class VeinRenderer extends EntityRenderer<Vein> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Difficulteye.MODID, "textures/entity/vein.png");
    private final VeinEntityModel<Vein> model;

    public VeinRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new VeinEntityModel<>(context.bakeLayer(VeinEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(Vein entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose(); // レンダリングの状態を保存

        poseStack.translate(0.0D, 0.0D, 0.0D); // エンティティを上にオフセット
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(-entityYaw)); // Y軸の回転

        // モデルの描画
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose(); // レンダリングの状態を復元
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Vein entity) {
        return TEXTURE;
    }
}
