package com.sheep.difficulteye.worldgen.feature;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class DifficulteyeFeature {
    public static final ResourceKey<ConfiguredFeature<?,?>> GrayTettacotta_KEY =createKey("gray_terracotta");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stone=new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslate=new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> grayterracotta=List.of(
                OreConfiguration.target(stone,Blocks.GRAY_TERRACOTTA.defaultBlockState()),
                OreConfiguration.target(deepslate,Blocks.GRAY_TERRACOTTA.defaultBlockState())
        );

        FeatureUtils.register(context, GrayTettacotta_KEY, Feature.ORE, new OreConfiguration(grayterracotta,9));
    }

    public static ResourceKey<ConfiguredFeature<?,?>> createKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                new ResourceLocation(Difficulteye.MODID,name));
    }
}
