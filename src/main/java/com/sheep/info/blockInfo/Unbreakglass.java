package com.sheep.info.blockInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class Unbreakglass extends TintedGlassBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public Unbreakglass() {
        super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).
                strength(-1, 1500)
                .sound(SoundType.GLASS));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }
    @Override //当たり判定
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        Direction facing = state.getValue(FACING);
        if (facing == Direction.NORTH || facing == Direction.SOUTH) {
            return Shapes.create(new AABB(0.4375, 0.0, 0.0, 0.5625, 1.0, 1.0));  // Z軸方向に薄い
        } else {
            return Shapes.create(new AABB(0.0, 0.0, 0.4375, 1.0, 1.0, 0.5625));  // X軸方向に薄い
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING); // 方角プロパティを追加
    }

}
