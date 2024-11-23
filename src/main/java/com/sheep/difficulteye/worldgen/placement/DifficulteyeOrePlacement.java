package com.sheep.difficulteye.worldgen.placement;

import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.registries.BlockRegistry;
import com.sheep.difficulteye.worldgen.feature.DifficulteyeFeature;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class DifficulteyeOrePlacement  {
    public static final ResourceKey<PlacedFeature> BlackTerracotta = createKey("black_terracotta");
    public static final ResourceKey<PlacedFeature> TerracottaTree =createKey("terracottatree");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        // 鉱石の配置情報を設定
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, BlackTerracotta,
                configuredFeatures.getOrThrow(DifficulteyeFeature.BlackTettacotta_KEY),
                commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0))));
        PlacementUtils.register(context, TerracottaTree,
                configuredFeatures.getOrThrow(DifficulteyeFeature.TerracottaTree_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.05f,0), BlockRegistry.TerracottaTreeSapling.get()
                ));

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
