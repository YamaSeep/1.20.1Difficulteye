package com.sheep.info.blockInfo;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class Endframe_Creature extends Endframe{
    public static final BooleanProperty EYE=BooleanProperty.create("endframe_creature");
    public Endframe_Creature(){
        super();
        this.registerDefaultState(this.defaultBlockState().setValue(EYE,false));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));

    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EYE);
        builder.add(FACING);
    }

}