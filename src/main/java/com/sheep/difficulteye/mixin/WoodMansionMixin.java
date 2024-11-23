package com.sheep.difficulteye.mixin;

import com.sheep.difficulteye.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(TemplateStructurePiece.class)
public class WoodMansionMixin {
    @Inject(method = "postProcess", at = @At(value = "HEAD"))
    public void postProcess(WorldGenLevel worldGenLevel, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource randomSource, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos, CallbackInfo ci) {
        replaceBlocks(worldGenLevel, boundingBox);
    }


    private void replaceBlocks(WorldGenLevel worldGenLevel, BoundingBox box) {
        // ここで置き換え処理を実装
        Block[] replaceblock = {Blocks.DARK_OAK_PLANKS, Blocks.BIRCH_PLANKS, Blocks.COBBLESTONE, Blocks.DARK_OAK_LOG, Blocks.GLASS_PANE, Blocks.COBBLESTONE_STAIRS,Blocks.RED_CARPET,Blocks.WHITE_CARPET,Blocks.TORCH}; // 置き換え元ブロック
        Block[] replace =new Block[BlockRegistry.Mansion.length+1];
        for(int i = 0; i < replace.length; i++){
            if (i<BlockRegistry.Mansion.length)replace[i]=BlockRegistry.Mansion[i].get();
            else replace[i]=Blocks.AIR;
        }
        // 置き換え先ブロック
        int count = 0;
        for (BlockPos pos : BlockPos.betweenClosed(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ())) {
            if (worldGenLevel.getBlockState(pos).is(Blocks.RED_CARPET) ||
                    worldGenLevel.getBlockState(pos).is(Blocks.WHITE_CARPET) ||
                    worldGenLevel.getBlockState(pos).is(Blocks.DARK_OAK_PLANKS) ||
                    worldGenLevel.getBlockState(pos).is(BlockTags.WOOL) ||
                    worldGenLevel.getBlockState(pos).is(Blocks.DARK_OAK_LOG)) count++;
            if (count > 10) break;
        }
        Random random=new Random();
        if (count < 10) return;
        for (BlockPos pos : BlockPos.betweenClosed(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ())) {
            BlockState currentBlock = worldGenLevel.getBlockState(pos);
            for (int i = 0; i < replaceblock.length; i++) {
                if (currentBlock.is(replaceblock[i])) {
                    BlockState newState;

                    if (currentBlock.getBlock() instanceof RotatedPillarBlock) {
                        Direction.Axis axis = currentBlock.getValue(RotatedPillarBlock.AXIS);
                        newState = replace[i].defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis);
                    } else if (currentBlock.getBlock() instanceof IronBarsBlock) {
                        boolean n = currentBlock.getValue(IronBarsBlock.NORTH);
                        boolean e = currentBlock.getValue(IronBarsBlock.EAST);
                        boolean w = currentBlock.getValue(IronBarsBlock.WEST);
                        boolean s = currentBlock.getValue(IronBarsBlock.SOUTH);
                        if (n) newState = replace[i].defaultBlockState().setValue(BlockStateProperties.FACING,Direction.NORTH);
                        else if (e) newState = replace[i].defaultBlockState().setValue(BlockStateProperties.FACING,Direction.EAST);
                        else if (w)newState = replace[i].defaultBlockState().setValue(BlockStateProperties.FACING,Direction.WEST);
                        else if (s)newState = replace[i].defaultBlockState().setValue(BlockStateProperties.FACING,Direction.SOUTH);
                        else newState = replace[i].defaultBlockState().setValue(BlockStateProperties.FACING,Direction.NORTH);
                    } else if (currentBlock.getBlock() instanceof StairBlock) {
                        Direction direction = currentBlock.getValue(StairBlock.FACING);
                        Half half = currentBlock.getValue(StairBlock.HALF);
                        StairsShape shapes = currentBlock.getValue(StairBlock.SHAPE);
                        newState = replace[i].defaultBlockState().setValue(StairBlock.FACING, direction).setValue(StairBlock.HALF, half).setValue(StairBlock.SHAPE, shapes);
                    } else{
                        if (currentBlock.is(Blocks.TORCH)) {
                            newState= (random.nextDouble()<0.15?Blocks.REDSTONE_TORCH.defaultBlockState():replace[i].defaultBlockState());
                        }
                        else newState = replace[i].defaultBlockState();
                    }
                    worldGenLevel.setBlock(pos, newState, 2);
                    //System.out.println("Replaced block at " + pos + " from " + currentBlock + " to " + newState);
                }
            }
        }
    }
}
