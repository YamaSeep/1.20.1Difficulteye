package com.sheep.difficulteye.entity;

import com.sheep.difficulteye.entity.custom.SummonersZombie;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DifficulteyeEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Difficulteye.MODID);

    public static final RegistryObject<EntityType<SummonersZombie>> HYLIGHTBLOCK =
            ENTITY_TYPE.register("hylightblock",()->EntityType.Builder.of(SummonersZombie::new, MobCategory.AMBIENT).build("hylightblock"));
    public static void register(IEventBus eventBus){
        ENTITY_TYPE.register(eventBus);
    }
}
