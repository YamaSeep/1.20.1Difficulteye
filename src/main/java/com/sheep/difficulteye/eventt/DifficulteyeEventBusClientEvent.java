package com.sheep.difficulteye.eventt;

import com.sheep.difficulteye.entity.DifficulteyeEntities;
import com.sheep.difficulteye.entity.custom.HylightBlock;
import com.sheep.difficulteye.entity.model.HylightBlockmodel;
import com.sheep.difficulteye.entity.renderer.HylightBlockRenderer;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Difficulteye.MODID,
bus = Mod.EventBusSubscriber.Bus.MOD,
value = Dist.CLIENT)
public class DifficulteyeEventBusClientEvent {
    @SubscribeEvent
    public static void regsterLayerDeffinitons(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(HylightBlockmodel.LAYER_LOCATION,HylightBlockmodel::createBodyLayer);
    }
    @SubscribeEvent
    public static void regsterRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(DifficulteyeEntities.HYLIGHTBLOCK.get(), HylightBlockRenderer::new);
    }

}
