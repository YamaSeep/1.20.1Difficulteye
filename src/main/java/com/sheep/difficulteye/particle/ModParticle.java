package com.sheep.difficulteye.particle;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticle {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Difficulteye.MODID);

    public static final RegistryObject<SimpleParticleType> Range_PARTICLES =
            PARTICLE_TYPES.register("range_particle", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> SandStom_PARTICLES =
            PARTICLE_TYPES.register("sandstom_particle", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
