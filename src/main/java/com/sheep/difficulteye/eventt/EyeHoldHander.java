package com.sheep.difficulteye.eventt;

import com.sheep.difficulteye.info.blockInfo.*;
import com.sheep.difficulteye.registries.BlockRegistry;
import com.sheep.difficulteye.registries.ItemRegistry;
import com.sheep.difficulteye.registries.TagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class EyeHoldHander {

    public static Block[][] endportalframe;
    public static BooleanProperty[] eyeProperties = {Endframe_Artifact.EYE, Endframe_Color.EYE, Endframe_Creature.EYE, Endframe_Dungeon.EYE, Endframe_Element.EYE, Endframe_Food.EYE, Endframe_Magic.EYE, Endframe_Nature.EYE, Endframe_Potion.EYE, Endframe_Sea.EYE, Endframe_Season.EYE, Endframe_Technology.EYE};

    @SubscribeEvent
    public static void rightClickBock(PlayerInteractEvent.RightClickBlock clickBlock) {
        Entity entity = clickBlock.getEntity();
        BlockPos blockPos=clickBlock.getPos();
        Level level=clickBlock.getLevel();
        BlockState blockState=level.getBlockState(blockPos);
        
        if (!level.isClientSide) {
            if (entity instanceof Player) {
                if (clickBlock.getHand() == InteractionHand.MAIN_HAND) {
                    if (!blockState.is(TagRegistry.Blocks.PORTAL_FRAMES)) {
                        return;
                    }
                    setEye(clickBlock);
                    BlockState[][] blockStates = new BlockState[9][9];
                    BlockPos start = blockPos.offset(4, 0, 4);
                    BlockPos end = blockPos.offset(-4, 0, -4);
                    int i = 0;
                    int j = 0;
                    for (BlockPos pos : BlockPos.betweenClosed(start, end)) {
                        BlockState State = level.getBlockState(pos);
                        blockStates[i][j] = State;
                        i++;
                        if (i % 9 == 0) {
                            i = 0;
                            j++;
                        }
                    }
                    if (isStructure(blockStates, level, blockPos))
                        entity.level().playSound(null, entity.blockPosition(),
                                SoundEvent.createVariableRangeEvent(new ResourceLocation("minecraft:block.end_portal.spawn")),
                                SoundSource.BLOCKS, 0.6F, 1.0F);
                }
            }
        }

    }

    private static void setEye(PlayerInteractEvent.RightClickBlock clickBlock) {
        Entity entity = clickBlock.getEntity();
        if (entity instanceof Player) {
            boolean isPortal = false;
            int i = 0;
            for (RegistryObject<Block> state : BlockRegistry.EndPortals) {
                if (clickBlock.getLevel().getBlockState(clickBlock.getPos()).is(state.get().defaultBlockState().getBlock())) {
                    isPortal = true;
                    break;
                }
                i++;
            }
            ItemStack itemStack = ((Player) entity).getItemInHand(clickBlock.getHand());

            if (isPortal) {
                if (itemStack.is(ItemRegistry.Eyes[i].get())) {
                    if (clickBlock.getLevel().getBlockState(clickBlock.getPos()).getValue(eyeProperties[i])) return;
                    entity.sendSystemMessage(Component.translatable("message.difficulteye.hold_eye",ItemRegistry.Eyes[i].get().getDefaultInstance().getDisplayName()));
                    entity.level().playSound(null, entity.blockPosition(),
                            SoundEvent.createVariableRangeEvent(new ResourceLocation("minecraft:block.end_portal_frame.fill")),
                            SoundSource.BLOCKS, 1.0F, 1.0F);
                    Direction currentFacing = clickBlock.getLevel().getBlockState(clickBlock.getPos()).getValue(Endframe.FACING); // FACINGプロパティを取得


                    BlockState newState = BlockRegistry.EndPortals[i].get().defaultBlockState().setValue(eyeProperties[i], true)
                                            .setValue(Endframe.FACING, currentFacing); // 向きを設定
                    clickBlock.getLevel().setBlock(clickBlock.getPos(), newState, 3);

                } else {
                    if (!clickBlock.getLevel().getBlockState(clickBlock.getPos()).getValue(eyeProperties[i]))
                        entity.sendSystemMessage(Component.translatable("message.difficulteye.wrong_hold_eye",ItemRegistry.Eyes[i].get().getDefaultInstance().getDisplayName()));
                }

            }
        }
    }

    public static Boolean isStructure(BlockState[][] bigArray, Level level, BlockPos blockPos) {
        if (endportalframe == null) {
            endportalframe = new Block[][]{
                    {null, BlockRegistry.EndPortals[0].get(), BlockRegistry.EndPortals[0].get(), BlockRegistry.EndPortals[0].get(), null},
                    {BlockRegistry.EndPortals[0].get(), null, null, null, BlockRegistry.EndPortals[0].get()},
                    {BlockRegistry.EndPortals[0].get(), null, null, null, BlockRegistry.EndPortals[0].get()},
                    {BlockRegistry.EndPortals[0].get(), null, null, null, BlockRegistry.EndPortals[0].get()},
                    {null, BlockRegistry.EndPortals[0].get(), BlockRegistry.EndPortals[0].get(), BlockRegistry.EndPortals[0].get(), null}
            };
        }
        int n = bigArray.length;          // 大配列の行数
        int m = endportalframe.length;          // 小配列の行数

        // 大配列の各位置から小配列を比較する
        for (int i = 0; i <= n - m; i++) {
            for (int j = 0; j <= n - m; j++) {
                boolean found = true; // 一致フラグを初期化
                // 小配列のすべての要素を比較
                for (int k = 0; k < m; k++) {
                    for (int l = 0; l < m; l++) {
                        Block bigBlock = bigArray[i + k][j + l].getBlock();
                        Block smallBlock = endportalframe[k][l];

                        // 完全一致のチェック（nullの場合は一致とみなす）
                        if (smallBlock != null && !bigBlock.defaultBlockState().is(TagRegistry.Blocks.PORTAL_FRAMES)) {
                            found = false;
                            break; // 内側のループを終了
                        }
                    }
                    if (!found) {
                        break; // 一致しない場合、外側のループを終了
                    }
                }
                if (found) {
                    for (int x = 0; x < 5; x++) { // x: 1, 2, 3
                        for (int z = 0; z < 5; z++) { // z: 1, 2, 3
                            int centerX = i + x - 4 + blockPos.getX(); // 中心のX座標
                            int centerZ = j + z - 4 + blockPos.getZ(); // 中心のZ座標
                            BlockPos posToCheck = new BlockPos(centerX, blockPos.getY(), centerZ);
                            BlockState blockState = level.getBlockState(posToCheck);
                            if (blockState.is(TagRegistry.Blocks.PORTAL_FRAMES)) {
                                for (int l = 0; l < BlockRegistry.EndPortals.length; l++) {
                                    if (blockState.is(BlockRegistry.EndPortals[l].get())) {
                                        if (!blockState.getValue(eyeProperties[l])) return false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    for (int x = 1; x <= 3; x++) { // x: 1, 2, 3
                        for (int z = 1; z <= 3; z++) { // z: 1, 2, 3
                            int centerX = i + x - 4 + blockPos.getX(); // 中心のX座標
                            int centerZ = j + z - 4 + blockPos.getZ(); // 中心のZ座標
                            BlockPos posToCheck = new BlockPos(centerX, blockPos.getY(), centerZ);
                            if (!level.getBlockState(posToCheck).is(Blocks.END_PORTAL)) {
                                if (!level.isClientSide())
                                    level.setBlock(posToCheck, Blocks.END_PORTAL.defaultBlockState(), 3);
                            } else return false;
                        }
                    }
                    return true; // 完全一致が見つかった場合

                }
            }
        }
        return false; // 完全一致が見つからなかった場合
    }
}
