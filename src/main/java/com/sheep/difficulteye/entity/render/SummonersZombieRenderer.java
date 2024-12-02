package com.sheep.difficulteye.entity.render;

import com.sheep.difficulteye.entity.custom.SummonersZombie;
import com.sheep.difficulteye.entity.model.SummonersZombieModel;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SummonersZombieRenderer extends MobRenderer<SummonersZombie, SummonersZombieModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Difficulteye.MODID, "textures/entity/summonerszombie.png");

    public SummonersZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new SummonersZombieModel(context.bakeLayer(SummonersZombieModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SummonersZombie entity) {
        return TEXTURE;
    }
}
