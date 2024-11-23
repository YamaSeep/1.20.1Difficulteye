package com.sheep.difficulteye.worldgen.biome;

import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.worldgen.placement.DifficulteyeOrePlacement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class DifficulteyeBiomeModifier {
    public static final ResourceKey<BiomeModifier> ADD_BlackTerracotta=
            createkey("add_black_terracotta");
    public static final ResourceKey<BiomeModifier> ADD_TerracottaTree=
            createkey("add_terracottatree");
    private static ResourceKey<BiomeModifier> createkey(String name){
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
                new ResourceLocation(Difficulteye.MODID,name));
    }
    public static void bootstrap(BootstapContext<BiomeModifier> context){
        HolderGetter<PlacedFeature> placedFeature=context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes=context.lookup(Registries.BIOME);
        context.register(ADD_BlackTerracotta,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeature.getOrThrow(DifficulteyeOrePlacement.BlackTerracotta)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_TerracottaTree,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS), HolderSet.direct(placedFeature.getOrThrow(DifficulteyeOrePlacement.TerracottaTree)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));


    }
}
