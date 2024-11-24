package com.sheep.difficulteye.worldgen.tree;

import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.worldgen.tree.custom.FlowerFoliagePlacer;
import com.sheep.difficulteye.worldgen.tree.custom.FlowerTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER=
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Difficulteye.MODID);

    public static final RegistryObject<FoliagePlacerType<FlowerFoliagePlacer>> Flower_Foliage_Placer =
            FOLIAGE_PLACER.register("flower_foliage_placer",()->new FoliagePlacerType<>(FlowerFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus){
        FOLIAGE_PLACER.register(eventBus);
    }

}
