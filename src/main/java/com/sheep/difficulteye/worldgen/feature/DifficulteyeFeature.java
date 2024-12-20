package com.sheep.difficulteye.worldgen.feature;

import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.worldgen.tree.custom.FlowerFoliagePlacer;
import com.sheep.difficulteye.worldgen.tree.custom.FlowerTrunkPlacer;
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
    public static final ResourceKey<ConfiguredFeature<?,?>> TerracottaLimeTree_KEY =createKey("terracottalimetree");
    public static final ResourceKey<ConfiguredFeature<?,?>> TerracottaGreenTree_KEY =createKey("terracottagreentree");
    public static final ResourceKey<ConfiguredFeature<?,?>> PinkFlower_KEY =createKey("pink_flower");
    public static final ResourceKey<ConfiguredFeature<?,?>> LiteBlueFlower_KEY =createKey("liteblure_flower");
    public static final ResourceKey<ConfiguredFeature<?,?>> MagentaFlower_KEY =createKey("magenta_flower");
    public static final ResourceKey<ConfiguredFeature<?,?>> PurpleFlower_KEY =createKey("purple_flower");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stone=new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslate=new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> blackterracotta=List.of(
                OreConfiguration.target(stone,Blocks.BLACK_TERRACOTTA.defaultBlockState()),
                OreConfiguration.target(deepslate,Blocks.BLACK_TERRACOTTA.defaultBlockState())
        );

        FeatureUtils.register(context, BlackTettacotta_KEY, Feature.ORE, new OreConfiguration(blackterracotta,9));
        FeatureUtils.register(context, TerracottaLimeTree_KEY, Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.GRAY_TERRACOTTA.defaultBlockState()),
                new StraightTrunkPlacer(4,2,0),
                BlockStateProvider.simple(Blocks.LIME_TERRACOTTA.defaultBlockState()),
                new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3),
                new TwoLayersFeatureSize(1,0,1)).build()
        );
        FeatureUtils.register(context, TerracottaGreenTree_KEY, Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.GRAY_TERRACOTTA.defaultBlockState()),
                new StraightTrunkPlacer(4,2,0),
                BlockStateProvider.simple(Blocks.GREEN_TERRACOTTA.defaultBlockState()),
                new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3),
                new TwoLayersFeatureSize(1,0,1)).build()
        );
        FeatureUtils.register(context, PinkFlower_KEY, Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.LIME_TERRACOTTA.defaultBlockState()),
                new FlowerTrunkPlacer(6,1,1),
                BlockStateProvider.simple(Blocks.PINK_TERRACOTTA.defaultBlockState()),
                new FlowerFoliagePlacer(ConstantInt.of(1),ConstantInt.of(0),1),
                new TwoLayersFeatureSize(1,3,3)).build()
        );
        FeatureUtils.register(context, LiteBlueFlower_KEY, Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.LIME_TERRACOTTA.defaultBlockState()),
                new FlowerTrunkPlacer(6,1,1),
                BlockStateProvider.simple(Blocks.LIGHT_BLUE_TERRACOTTA.defaultBlockState()),
                new FlowerFoliagePlacer(ConstantInt.of(1),ConstantInt.of(0),1),
                new TwoLayersFeatureSize(1,3,3)).build()
        );
        FeatureUtils.register(context, MagentaFlower_KEY, Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.LIME_TERRACOTTA.defaultBlockState()),
                new FlowerTrunkPlacer(6,1,1),
                BlockStateProvider.simple(Blocks.MAGENTA_TERRACOTTA.defaultBlockState()),
                new FlowerFoliagePlacer(ConstantInt.of(1),ConstantInt.of(0),1),
                new TwoLayersFeatureSize(1,3,3)).build()
        );
        FeatureUtils.register(context, PurpleFlower_KEY, Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.LIME_TERRACOTTA.defaultBlockState()),
                new FlowerTrunkPlacer(6,1,1),
                BlockStateProvider.simple(Blocks.PURPLE_TERRACOTTA.defaultBlockState()),
                new FlowerFoliagePlacer(ConstantInt.of(1),ConstantInt.of(0),1),
                new TwoLayersFeatureSize(1,3,3)).build()
        );
    }

    public static ResourceKey<ConfiguredFeature<?,?>> createKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                new ResourceLocation(Difficulteye.MODID,name));
    }
}
