package com.sheep.difficulteye.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OreHighlightRenderer {
    public static void highlightBlock(BlockPos blockPos, PoseStack poseStack, MultiBufferSource bufferSource) {
        Minecraft mc = Minecraft.getInstance();
        Camera camera = mc.gameRenderer.getMainCamera();

        Vec3 cameraPos = camera.getPosition();
        double x = blockPos.getX() - cameraPos.x;
        double y = blockPos.getY() - cameraPos.y;
        double z = blockPos.getZ() - cameraPos.z;

        RenderType renderType = RenderType.outline(new ResourceLocation("textures/misc/outline.png"));
        VertexConsumer buffer = bufferSource.getBuffer(renderType);

        // 描画する鉱石ブロックの範囲を指定
        poseStack.pushPose();
        poseStack.translate(x, y, z);

        AABB boundingBox = new AABB(0, 0, 0, 1, 1, 1); // ブロック全体
        LevelRenderer.renderLineBox(poseStack, buffer, boundingBox, 1.0F, 1.0F, 0.0F, 1.0F); // 黄色の線

        poseStack.popPose();
    }
}

