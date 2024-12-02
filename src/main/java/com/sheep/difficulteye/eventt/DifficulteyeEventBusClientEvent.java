package com.sheep.difficulteye.eventt;

import com.sheep.difficulteye.entity.DifficulteyeEntities;
import com.sheep.difficulteye.entity.model.SummonersZombieModel;
import com.sheep.difficulteye.entity.render.SummonersZombieRenderer;
import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.particle.ModParticle;
import com.sheep.difficulteye.particle.RangeParticleFactory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Difficulteye.MODID,
bus = Mod.EventBusSubscriber.Bus.MOD,
value = Dist.CLIENT)
public class DifficulteyeEventBusClientEvent {
    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticle.Range_PARTICLES.get(), RangeParticleFactory::new); // ファクトリ
    }
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SummonersZombieModel.LAYER_LOCATION, SummonersZombieModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DifficulteyeEntities.SUMMONERS_ZOMBIE.get(), SummonersZombieRenderer::new);
    }
}
