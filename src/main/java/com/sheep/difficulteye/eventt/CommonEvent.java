package com.sheep.difficulteye.eventt;

import com.ibm.icu.impl.Pair;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.sheep.difficulteye.particle.ModParticle;
import com.sheep.difficulteye.particle.RangeParticle;
import com.sheep.difficulteye.particle.RangeParticleFactory;
import com.sheep.difficulteye.registries.TagRegistry;
import com.sheep.difficulteye.wands.WandOre;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.level.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber
public class CommonEvent {
    public static final RenderType LINES;

    static {
        LINES = RenderType.create(
                "custom_lines", // 独自の名前
                DefaultVertexFormat.POSITION_COLOR_NORMAL,
                VertexFormat.Mode.LINES,
                256, // 最大頂点数
                false, // ソート不要
                true,  // 動的
                RenderType.CompositeState.builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeLinesShader))// 線幅デフォルト
                        .setLayeringState(new RenderStateShard.LayeringStateShard("view_offset_z_layering", () -> {
                            PoseStack $$0 = RenderSystem.getModelViewStack();
                            $$0.pushPose();
                            $$0.scale(0.99975586F, 0.99975586F, 0.99975586F);
                            RenderSystem.applyModelViewMatrix();
                        }, () -> {
                            PoseStack $$0 = RenderSystem.getModelViewStack();
                            $$0.popPose();
                            RenderSystem.applyModelViewMatrix();
                        }))
                        .setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
                            RenderSystem.enableBlend();
                            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                        }, () -> {
                            RenderSystem.disableBlend();
                            RenderSystem.defaultBlendFunc();
                        })) // 半透明
                        .setOutputState(new RenderStateShard.OutputStateShard("item_entity_target", () -> {
                            if (Minecraft.useShaderTransparency()) {
                                Minecraft.getInstance().levelRenderer.getItemEntityTarget().bindWrite(false);
                            }

                        }, () -> {
                            if (Minecraft.useShaderTransparency()) {
                                Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
                            }

                        }))// Zバッファ微調整
                        .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false)) // 深度バッファ無効
                        .setDepthTestState(new RenderStateShard.DepthTestStateShard("always", GL11.GL_ALWAYS))
                        .createCompositeState(false) // ソートなし
        );
    }

    @SubscribeEvent
    public static void onRenderWorld(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vec3 cameraPos = camera.getPosition(); // カメラ座標
        long currentTime = System.currentTimeMillis();

        // 深度テスト無効化
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.lineWidth(5.0F);
        RenderSystem.disableCull();
        WandOre.highlightedBlocks.entrySet().removeIf(entry -> {
            long expiryTime = entry.getValue().second; // Pair から有効期限を取得
            return currentTime > expiryTime; // 現在時刻と比較して期限切れなら削除
        });

        // 描画ループ
        for (Map.Entry<BlockPos, Pair<BlockState, Long>> entry : WandOre.highlightedBlocks.entrySet()) {
            BlockPos pos = entry.getKey();
            Pair<BlockState, Long> pair = entry.getValue();
            BlockState blockState = pair.first; // BlockState を取得
            long expiryTime = pair.second; // 有効期限を取得
            // ハイライトの期限切れチェック
            if (currentTime > expiryTime) {
                continue; // 時間切れならスキップ
            }
            ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(blockState.getBlock());


            double x1 = pos.getX() - cameraPos.x;
            double y1 = pos.getY() - cameraPos.y;
            double z1 = pos.getZ() - cameraPos.z;

            double x2 = x1 + 1.0;
            double y2 = y1 + 1.0;
            double z2 = z1 + 1.0;
            LevelRenderer.renderLineBox(
                    poseStack, bufferSource.getBuffer(LINES),
                    x1, y1, z1, x2, y2, z2,
                    getOreColor(blockState)[0], getOreColor(blockState)[1], getOreColor(blockState)[2], 1.0F // 緑色、不透明
            );

        }
        // 描画を確定
        bufferSource.endBatch();
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
    }

    public static float[] getOreColor(BlockState blockState) {
        if (blockState.is(BlockTags.COAL_ORES)) {
            return new float[]{0.1F, 0.1F, 0.1F}; // 黒
        } else if (blockState.is(BlockTags.IRON_ORES)) {
            return new float[]{0.8F, 0.6F, 0.4F}; // 茶色
        } else if (blockState.is(BlockTags.COPPER_ORES)) {
            return new float[]{0.9F, 0.4F, 0.2F}; // オレンジ
        } else if (blockState.is(BlockTags.GOLD_ORES)) {
            return new float[]{1.0F, 0.8F, 0.0F}; // 金
        } else if (blockState.is(BlockTags.LAPIS_ORES)) {
            return new float[]{0.2F, 0.4F, 1.0F}; // 青
        } else if (blockState.is(BlockTags.DIAMOND_ORES)) {
            return new float[]{0.0F, 1.0F, 1.0F}; // 水色
        } else if (blockState.is(BlockTags.REDSTONE_ORES)) {
            return new float[]{1.0F, 0.0F, 0.0F}; // 赤
        } else if (blockState.is(BlockTags.EMERALD_ORES)) {
            return new float[]{0.0F, 1.0F, 0.0F}; // 緑
        } else if (blockState.is(Blocks.NETHER_GOLD_ORE)) {
            return new float[]{1.0F, 0.6F, 0.0F}; // ネザー金
        } else if (blockState.is(Blocks.NETHER_QUARTZ_ORE)) {
            return new float[]{1.0F, 0.6F, 0.0F}; // ネザー金
        }


        // その他: デフォルト色（白）
        return new float[]{1.0F, 1.0F, 1.0F};
    }


    @SubscribeEvent
    public static void onPlased(BlockEvent.EntityPlaceEvent event) {
        if (event.getLevel().isClientSide() || !(event.getEntity() instanceof Player player)) return;
        if (event.getState().is(Blocks.TORCH) || event.getState().is(TagRegistry.Blocks.UNBREAKS)) return;
        BlockPos blockPos = event.getPos();
        BlockState state = event.getState();
        Level level = (Level) event.getLevel();
        for (int x = -4; x <= 4; x++) {
            for (int y = -4; y <= 4; y++) {
                for (int z = -4; z <= 4; z++) {
                    if (event.getLevel().getBlockState(blockPos.offset(x, y, z)).is(TagRegistry.Blocks.UNBREAKS)) {
                        player.sendSystemMessage(Component.translatable("message.difficulteye.cannot_place"));
                        event.setCanceled(true);
                        return;
                    }

                }
            }
        }
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            // ネザーレンガの場合、設置情報を保存
            if (event.getPlacedBlock().getBlock() == Blocks.NETHER_BRICKS) {
                PlacedBlockData data = PlacedBlockData.get(serverLevel);
                data.addBlock(blockPos);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            BlockPos pos = event.getPos();
            PlacedBlockData data = PlacedBlockData.get(serverLevel);
            if (event.getState().getBlock() == Blocks.NETHER_BRICKS) {
                if (!data.isBlockPlaced(pos)) {
                    event.setCanceled(true);
                } else {
                    data.removeBlock(pos);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;

        PlacedBlockData data = PlacedBlockData.get(serverLevel);

        // 爆発の影響を受けるブロックリストをカスタマイズ
        event.getAffectedBlocks().removeIf(pos -> {
            if (serverLevel.getBlockState(pos).getBlock() == Blocks.NETHER_BRICKS) {
                boolean isPlaced = data.isBlockPlaced(pos);
                if (isPlaced) data.removeBlock(pos);
                return !isPlaced;
            }
            return false;
        });
    }

    @SubscribeEvent
    public static void onPistonPre(PistonEvent.Pre event) {
        Level level = (Level) event.getLevel();
        if (level.isClientSide()) return; // クライアント側では無視

        if (!(level instanceof ServerLevel serverLevel)) return; // サーバーレベルであることを確認
        PlacedBlockData data = PlacedBlockData.get(serverLevel); // プレイヤー設置データを取得
        Direction direction = event.getDirection();
        BlockPos pistonPos = event.getPos();
        for (int i = 1; i <= 12; i++) {
            BlockPos checkPos = pistonPos.relative(direction, i); // 動かされる予定のブロック位置
            BlockState blockState = level.getBlockState(checkPos);
            if (blockState.isAir()) break;
            if (blockState.is(Blocks.NETHER_BRICKS))
                if (!data.isBlockPlaced(checkPos)) {
                    event.setCanceled(true);
                    return;
                }
        }
    }
}