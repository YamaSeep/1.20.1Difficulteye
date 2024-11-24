package com.sheep.difficulteye.worldgen.tree;

import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.worldgen.tree.custom.FlowerTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> Trunk_Placer=
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Difficulteye.MODID);

    public static final RegistryObject<TrunkPlacerType<FlowerTrunkPlacer>> Flower_Placer =
            Trunk_Placer.register("flower_placer",()->new TrunkPlacerType<>(FlowerTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus){
        Trunk_Placer.register(eventBus);
    }
}
