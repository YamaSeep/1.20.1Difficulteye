package com.sheep.difficulteye.worldgen.placement;

import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.registries.BlockRegistry;
import com.sheep.difficulteye.worldgen.feature.DifficulteyeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class DifficulteyePlacement {
    public static final ResourceKey<PlacedFeature> BlackTerracotta = createKey("black_terracotta");
    public static final ResourceKey<PlacedFeature> TerracottaLimeTree =createKey("terracottalimetree");
    public static final ResourceKey<PlacedFeature> TerracottaGreenTree =createKey("terracottagreentree");
    public static final ResourceKey<PlacedFeature> PinkFlower =createKey("pink_flower");
    public static final ResourceKey<PlacedFeature> LiteBlueFlower=createKey("liteblure_flower");
    public static final ResourceKey<PlacedFeature> MagentaFlower =createKey("magenta_flower");
    public static final ResourceKey<PlacedFeature> PurpleFlower =createKey("purple_flower");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        // 鉱石の配置情報を設定
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, BlackTerracotta,
                configuredFeatures.getOrThrow(DifficulteyeFeature.BlackTettacotta_KEY),
                commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-20))));
        PlacementUtils.register(context, TerracottaLimeTree,
                configuredFeatures.getOrThrow(DifficulteyeFeature.TerracottaLimeTree_KEY),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BiomeFilter.biome(),PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,InSquarePlacement.spread(),RarityFilter.onAverageOnceEvery(20),
                PlacementUtils.countExtra(1,0.1f,0),BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(BlockRegistry.TerracottaTreeLimeSapling.get().defaultBlockState(), BlockPos.ZERO)));
        PlacementUtils.register(context, TerracottaGreenTree,
                configuredFeatures.getOrThrow(DifficulteyeFeature.TerracottaGreenTree_KEY),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BiomeFilter.biome(),PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,InSquarePlacement.spread(),RarityFilter.onAverageOnceEvery(20),
                PlacementUtils.countExtra(1,0.1f,0),BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(BlockRegistry.TerracottaTreeGreenSapling.get().defaultBlockState(), BlockPos.ZERO)));
        PlacementUtils.register(context, PinkFlower,
                configuredFeatures.getOrThrow(DifficulteyeFeature.PinkFlower_KEY),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BiomeFilter.biome(),PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,InSquarePlacement.spread(),RarityFilter.onAverageOnceEvery(45),
                PlacementUtils.countExtra(1,0.1f,0),BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(BlockRegistry.PinkFlowerSapling.get().defaultBlockState(), BlockPos.ZERO)));
        PlacementUtils.register(context, LiteBlueFlower,
                configuredFeatures.getOrThrow(DifficulteyeFeature.LiteBlueFlower_KEY),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BiomeFilter.biome(),PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,InSquarePlacement.spread(),RarityFilter.onAverageOnceEvery(45),
                PlacementUtils.countExtra(1,0.1f,0),BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(BlockRegistry.LiteBlueFlowerSapling.get().defaultBlockState(), BlockPos.ZERO)));
        PlacementUtils.register(context, MagentaFlower,
                configuredFeatures.getOrThrow(DifficulteyeFeature.MagentaFlower_KEY),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BiomeFilter.biome(),PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,InSquarePlacement.spread(),RarityFilter.onAverageOnceEvery(45),
                PlacementUtils.countExtra(1,0.1f,0),BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(BlockRegistry.MagentaFlowerSapling.get().defaultBlockState(), BlockPos.ZERO)));
        PlacementUtils.register(context, PurpleFlower,
                configuredFeatures.getOrThrow(DifficulteyeFeature.PurpleFlower_KEY),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BiomeFilter.biome(),PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,InSquarePlacement.spread(),RarityFilter.onAverageOnceEvery(45),
                PlacementUtils.countExtra(1,0.1f,0),BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(BlockRegistry.PurpleFlowerSapling.get().defaultBlockState(), BlockPos.ZERO)));

    }


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Difficulteye.MODID, name));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
