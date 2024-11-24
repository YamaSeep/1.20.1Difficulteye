package com.sheep.difficulteye.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sheep.difficulteye.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class FlowerTrunkPlacer extends TrunkPlacer {
    public static final Codec<FlowerTrunkPlacer> CODEC= RecordCodecBuilder.create(flowerTrunkPlacerInstance->
            trunkPlacerParts(flowerTrunkPlacerInstance).apply(flowerTrunkPlacerInstance,FlowerTrunkPlacer::new));

    public FlowerTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.Flower_Placer.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(
            @NotNull LevelSimulatedReader reader,
            @NotNull BiConsumer<BlockPos, BlockState> placer,
            @NotNull RandomSource pRandom,
            int pheight,
            @NotNull BlockPos pPos,
            @NotNull TreeConfiguration config){
        setDirtAt(reader,placer,pRandom,pPos.below(),config);
        int height =pheight + pRandom.nextInt(heightRandA + 3) + pRandom.nextInt(heightRandB -1,heightRandB+1);
        BlockPos pos;
        for(int i = 0; i < height; i++){
            if (i>0){
                pos= pPos.offset(0,i,0);
                placeLog(reader,placer,pRandom, pos,config);
            }
            else {
                for(int j = 1; j < 4; j++){
                    for (int dx = -1; dx <= 1; dx++) {
                        for(int dz = -1; dz <= 1; dz++){
                            if (Math.abs(dx)+Math.abs(dz)==1) {
                                pos = pPos.offset(dx*j, j-1, dz*j);
                                placeLog(reader, placer, pRandom, pos, config);
                            }
                        }
                    }
                }
            }
        }
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.offset(0,height-3,0),0,false));
    }
}
