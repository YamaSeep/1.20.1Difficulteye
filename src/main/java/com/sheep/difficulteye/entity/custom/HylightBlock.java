package com.sheep.difficulteye.entity.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class HylightBlock extends Slime {
    public HylightBlock(EntityType<? extends Slime> p_33588_, Level p_33589_) {
        super(p_33588_, p_33589_);
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0) // 最大体力
                .add(Attributes.MOVEMENT_SPEED, 0.2) // 移動速度
                .add(Attributes.ATTACK_DAMAGE, 0.0); // 攻撃ダメージ (友好モブなら0);
    }

    @Override
    public void setSilent(boolean p_20226_) {
        super.setSilent(true);
    }

    @Override
    public void setNoAi(boolean p_21558_) {
        super.setNoAi(true);
    }

    @Override
    public void setNoGravity(boolean p_20243_) {
        super.setNoGravity(true);
    }

    @Override
    public void setInvisible(boolean p_20304_) {
        super.setInvisible(true);
    }

    @Override
    public void setSize(int p_33594_, boolean p_33595_) {
        super.setSize(2, true);
    }

    @Override
    public boolean shouldDropExperience() {
        return false;
    }

    @Override
    public void setInvulnerable(boolean p_20332_) {
        super.setInvulnerable(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.hasEffect(MobEffects.GLOWING)) {
            this.addEffect(new MobEffectInstance(MobEffects.GLOWING, Integer.MAX_VALUE, 1,false,false));
        }
        if (this.tickCount>20*10)this.discard();
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public boolean attackable() {
        return false;
    }
}
