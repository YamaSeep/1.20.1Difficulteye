package com.sheep.difficulteye.worldgen.tree;

import com.sheep.difficulteye.worldgen.feature.DifficulteyeFeature;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class TerracottaTreeGreen extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return DifficulteyeFeature.TerracottaGreenTree_KEY;
    }
}
