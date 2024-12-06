package com.sheep.difficulteye.eventt;

import com.sheep.difficulteye.entity.DifficulteyeEntities;
import com.sheep.difficulteye.entity.custom.Vein;
import com.sheep.difficulteye.entity.model.SummonersZombieModel;
import com.sheep.difficulteye.entity.model.VeinEntityModel;
import com.sheep.difficulteye.entity.render.SummonersZombieRenderer;
import com.sheep.difficulteye.entity.render.VeinRenderer;
import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.particle.ModParticle;
import com.sheep.difficulteye.particle.RangeParticleFactory;
import com.sheep.difficulteye.particle.SandStomParticleFactory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Difficulteye.MODID,
bus = Mod.EventBusSubscriber.Bus.MOD,
value = Dist.CLIENT)
public class ModEventBusClientEvent {
    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticle.Range_PARTICLES.get(), RangeParticleFactory::new); // ファクトリ
        event.registerSpriteSet(ModParticle.SandStom_PARTICLES.get(), SandStomParticleFactory::new); // ファクトリ
    }
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(SummonersZombieModel.LAYER_LOCATION, SummonersZombieModel::createBodyLayer);
        event.registerLayerDefinition(VeinEntityModel.LAYER_LOCATION, VeinEntityModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DifficulteyeEntities.SUMMONERS_ZOMBIE.get(), SummonersZombieRenderer::new);
        event.registerEntityRenderer(DifficulteyeEntities.Vein.get(), VeinRenderer::new);
    }
}
