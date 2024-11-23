package com.sheep.difficulteye.worldgen.feature;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
public class DifficulteyeFeature {
    public static final ResourceKey<ConfiguredFeature<?,?>> BlackTettacotta_KEY =createKey("black_terracotta");
    public static final ResourceKey<ConfiguredFeature<?,?>> TerracottaTree_KEY =createKey("terracottatree");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stone=new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslate=new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> blackterracotta=List.of(
                OreConfiguration.target(stone,Blocks.BLACK_TERRACOTTA.defaultBlockState()),
                OreConfiguration.target(deepslate,Blocks.BLACK_TERRACOTTA.defaultBlockState())
        );

        FeatureUtils.register(context, BlackTettacotta_KEY, Feature.ORE, new OreConfiguration(blackterracotta,9));
        FeatureUtils.register(context, TerracottaTree_KEY, Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.GRAY_TERRACOTTA.defaultBlockState()),
                new StraightTrunkPlacer(3,2,0),
                BlockStateProvider.simple(Blocks.GREEN_TERRACOTTA.defaultBlockState()),
                new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3),
                new TwoLayersFeatureSize(1,0,1)).build()
        );

    }

    public static ResourceKey<ConfiguredFeature<?,?>> createKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                new ResourceLocation(Difficulteye.MODID,name));
    }
}
