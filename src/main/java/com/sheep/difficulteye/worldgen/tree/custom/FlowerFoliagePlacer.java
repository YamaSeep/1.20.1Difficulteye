package com.sheep.difficulteye.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sheep.difficulteye.worldgen.tree.ModFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class FlowerFoliagePlacer extends FoliagePlacer {
    private final int height;
public static final Codec<FlowerFoliagePlacer> CODEC= RecordCodecBuilder.create(flowerFoliagePlacerInstance ->
        foliagePlacerParts(flowerFoliagePlacerInstance).and(Codec.intRange(0,16).fieldOf("height")
                .forGetter(fp->fp.height)).apply(flowerFoliagePlacerInstance,FlowerFoliagePlacer::new));

public FlowerFoliagePlacer(IntProvider p_161411_, IntProvider p_161412_,int height) {
        super(p_161411_, p_161412_);
        this.height=height;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ModFoliagePlacers.Flower_Foliage_Placer.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig,
                                 int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        BlockPos pos;
        for(int i = 0; i < 4; i++){
            for(int dx = -1; dx <= 1; dx++){
                for(int dy = -1; dy <= 1; dy++){
                    if (i==0||i==3) {
                        if (3 * (Math.abs(dx) + Math.abs(dy)) == i + 3) {
                            pos = pAttachment.pos().offset(dx, i, dy);
                            tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
                        }
                    } else tryPlaceLeaf(pLevel,pBlockSetter,pRandom,pConfig,pAttachment.pos().offset(dx,i,dy));
                }
            }

        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource randomSource, int i, @NotNull TreeConfiguration treeConfiguration) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}
