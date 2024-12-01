package com.sheep.difficulteye.info.blockInfo;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class Endframe extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public Endframe() {
        super(Properties.of().strength(-1f,1500f).noLootTable());
    }
    @Override //当たり判定1*1*0.75
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        // AABBを定義
        double minX = 0.0;
        double minY = 0.0;
        double minZ = 0.0;
        double maxX = 1.0;
        double maxY = 0.75; // 高さを0.75に設定
        double maxZ = 1.0;

        AABB aabb = new AABB(minX, minY, minZ, maxX, maxY, maxZ);

        // VoxelShapeを作成
        return Shapes.create(aabb);
    }

}
