package com.sheep.difficulteye.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class DepthStriderEffect extends MobEffect {
    protected DepthStriderEffect(MobEffectCategory mobEffectCategory, int p_19452_) {
        super(mobEffectCategory, p_19452_);
    }
    public final float SPRRD= 0.67F;
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.isInWater()) {
            // 現在の速度をリセット（基準値に戻す）
            Vec3 currentVelocity = entity.getDeltaMovement();
            if (entity instanceof Player player) {
                // プレイヤーの移動入力方向を取得
                Vec3 input = new Vec3(player.xxa, 0, player.zza); // xxaとzzaは移動入力
                System.out.println(input);
                if (input.length() > 0.01) {
                    input = input.yRot(-player.getYRot() * ((float) Math.PI / 180F));
                    double multiplier = 3.0 + SPRRD * (amplifier+1); // 強度ごとの速度増加
                    Vec3 adjustedVelocity = input.normalize().scale(0.1 * multiplier);

                    // 新しい速度を適用（垂直方向を維持する）
                    entity.setDeltaMovement(adjustedVelocity.x, currentVelocity.y, adjustedVelocity.z);
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // このエフェクトを毎tick適用する
        return true;
    }
}
