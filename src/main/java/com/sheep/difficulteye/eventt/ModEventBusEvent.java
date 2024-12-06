package com.sheep.difficulteye.eventt;

import com.sheep.difficulteye.entity.DifficulteyeEntities;
import com.sheep.difficulteye.entity.custom.SummonersZombie;
import com.sheep.difficulteye.entity.custom.Vein;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Difficulteye.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvent {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(DifficulteyeEntities.SUMMONERS_ZOMBIE.get(), SummonersZombie.createAttributes().build());

    }
}
