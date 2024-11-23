package com.sheep.difficulteye.mixin;

import com.sheep.difficulteye.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Structure.class)
public abstract class CustomStrongHolder {

   // z
    /////9202538180492847113
    @Inject(method = "afterPlace", at = @At(value = "HEAD"))
    public void afterPlace(
            WorldGenLevel worldGenLevel,
            StructureManager structureManager,
            ChunkGenerator chunkGenerator,
            RandomSource randomSource,
            BoundingBox boundingBox,
            ChunkPos chunkPos,
            PiecesContainer piecesContainer,
            CallbackInfo ci) {
        Block[] blocks= new Block[16];
        for (int i=0;i<BlockRegistry.EndPortals.length;i++) blocks[i]=BlockRegistry.EndPortals[i].get();
        List<StructurePiece> pieces = piecesContainer.pieces();
        int blockNum=0;
        for (StructurePiece piece:pieces){
            if (piece instanceof StrongholdPieces.PortalRoom) {
                BlockPos blockPos = piece.getLocatorPosition();
                BlockPos start = blockPos.offset(-10, -1, -10);
                BlockPos end = blockPos.offset(10, -1, 10);

                // 範囲内を特定のブロックで埋める
                for (BlockPos pos : BlockPos.betweenClosed(start, end)) {
                        BlockState currentBlock=worldGenLevel.getBlockState(pos);
                    if (currentBlock.is(Blocks.END_PORTAL_FRAME)){

                        Direction facing=currentBlock.getValue(BlockStateProperties.HORIZONTAL_FACING);

                        BlockState newBlock=blocks[blockNum].defaultBlockState().setValue(BlockStateProperties.FACING,facing);

                        worldGenLevel.setBlock(pos, newBlock, 3);
                        blockNum++;
                        blockNum%=blocks.length;
                    }


                }
            }
        }
    }

}
