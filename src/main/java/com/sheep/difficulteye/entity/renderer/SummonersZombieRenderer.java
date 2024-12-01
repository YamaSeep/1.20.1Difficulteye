package com.sheep.difficulteye.entity.renderer;

import com.sheep.difficulteye.entity.custom.SummonersZombie;
import com.sheep.difficulteye.entity.model.SummonersZombieModel;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SummonersZombieRenderer extends MobRenderer<SummonersZombie, SummonersZombieModel<SummonersZombie>> {
    private static final ResourceLocation SUMMONERSZOMBIE_LOCATION=new ResourceLocation(Difficulteye.MODID,"textures/entity/summonerszombie.png");
    public SummonersZombieRenderer(EntityRendererProvider.Context pcontext) {
        super(pcontext,
                new SummonersZombieModel<>(pcontext.bakeLayer(SummonersZombieModel.LAYER_LOCATION))
                ,0.25f);
    }
    @Override
    public ResourceLocation getTextureLocation(SummonersZombie summonersZombie) {
        return SUMMONERSZOMBIE_LOCATION;
    }
}
