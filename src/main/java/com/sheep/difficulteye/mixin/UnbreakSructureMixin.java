package com.sheep.difficulteye.mixin;

import com.sheep.difficulteye.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressPieces;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import net.minecraft.world.level.levelgen.structure.structures.NetherFossilPieces;
import net.minecraft.world.level.levelgen.structure.structures.OceanMonumentPieces;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(Structure.class)
public abstract class UnbreakSructureMixin {

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

        List<StructurePiece> pieces = piecesContainer.pieces();
        Class<?> targetClass = NetherFortressPieces.class;
        Class<?>[] nestedClasses = targetClass.getDeclaredClasses();

        for (StructurePiece piece : pieces) {
            if (piece instanceof OceanMonumentPieces.MonumentBuilding) { // OceanMonumentPiece に置き換え
                replaceblock("overworld",piece.getBoundingBox(),worldGenLevel,new Block[]{Blocks.PRISMARINE,Blocks.PRISMARINE_BRICKS,Blocks.DARK_PRISMARINE,Blocks.SEA_LANTERN},
                        BlockRegistry.Monument);
            }
            for (Class<?> nestedClass : nestedClasses) {
                if (nestedClass.isAssignableFrom(piece.getClass())) {
                    worldGenLevel.getLevel().getServer().execute(()->
                            replaceblock("",piece.getBoundingBox(),worldGenLevel,new Block[]{Blocks.NETHER_BRICKS},new RegistryObject[]{BlockRegistry.UnbreakNetherbrick}));
                }
            }
        }

    }
    @Unique
    private static void replaceblock(String dim,BoundingBox box, WorldGenLevel worldGenLevel, Block[] replaceblock, RegistryObject<Block>[] replace){

        if (replaceblock==null||replace==null)return;
        if (replaceblock.length!= replace.length)return;
        for (BlockPos pos:BlockPos.betweenClosed(box.minX(),box.minY(),box.minZ(),box.maxX(),box.maxY(),box.maxZ())){

            BlockState currentBlock = worldGenLevel.getBlockState(pos);
            for(int i = 0; i < replaceblock.length; i++){
                if (currentBlock.is(replaceblock[i])) {
                    BlockState newState = replace[i].get().defaultBlockState();

                    if (currentBlock.getBlock() instanceof RotatedPillarBlock && newState.getBlock() instanceof RotatedPillarBlock) {
                        Direction.Axis axis = currentBlock.getValue(RotatedPillarBlock.AXIS);
                        newState.setValue(RotatedPillarBlock.AXIS, axis);
                    }
                    if (Objects.equals(dim, "overworld")) worldGenLevel.setBlock(pos,newState,2);
                    else worldGenLevel.getLevel().setBlock(pos,newState,2);

                }
            }
        }
    }
}