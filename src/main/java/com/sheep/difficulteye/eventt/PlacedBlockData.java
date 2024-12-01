package com.sheep.difficulteye.eventt;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashSet;
import java.util.Set;

public class PlacedBlockData extends SavedData {
    private static final String DATA_NAME = "placed_block_data";
    private final Set<BlockPos> placedBlocks = new HashSet<>();

    public static PlacedBlockData load(CompoundTag tag) {
        PlacedBlockData data = new PlacedBlockData();
        ListTag blocksList = tag.getList("PlacedBlocks", Tag.TAG_COMPOUND);

        for (Tag t : blocksList) {
            CompoundTag blockTag = (CompoundTag) t;
            int x = blockTag.getInt("x");
            int y = blockTag.getInt("y");
            int z = blockTag.getInt("z");
            data.placedBlocks.add(new BlockPos(x, y, z));
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag blocksList = new ListTag();

        for (BlockPos pos : placedBlocks) {
            CompoundTag blockTag = new CompoundTag();
            blockTag.putInt("x", pos.getX());
            blockTag.putInt("y", pos.getY());
            blockTag.putInt("z", pos.getZ());
            blocksList.add(blockTag);
        }

        tag.put("PlacedBlocks", blocksList);
        return tag;
    }

    public void addBlock(BlockPos pos) {
        placedBlocks.add(pos.immutable());
        setDirty(); // データが変更されたことを通知
    }

    public void removeBlock(BlockPos pos) {
        placedBlocks.remove(pos);
        setDirty();
    }

    public boolean isBlockPlaced(BlockPos pos) {
        return placedBlocks.contains(pos);
    }

    public static PlacedBlockData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(PlacedBlockData::load, PlacedBlockData::new, DATA_NAME);
    }
}
