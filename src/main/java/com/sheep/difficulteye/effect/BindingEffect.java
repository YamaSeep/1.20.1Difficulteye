package com.sheep.difficulteye.effect;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class BindingEffect extends MobEffect {
    private static final String NBT_INITIAL_X = "InitialX";
    private static final String NBT_INITIAL_Y = "InitialY";
    private static final String NBT_INITIAL_Z = "InitialZ";


    protected BindingEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        CompoundTag entityData = entity.getPersistentData();
        // 初回のみ初期位置をNBTに記録
        if (!entityData.contains(NBT_INITIAL_X)) {
            entityData.putDouble(NBT_INITIAL_X, entity.getX());
            entityData.putDouble(NBT_INITIAL_Y, entity.getY());
            entityData.putDouble(NBT_INITIAL_Z, entity.getZ());
        }
        entity.setDeltaMovement(0.0, 0.0, 0.0);
        entity.hasImpulse = false;

        double initialX = entityData.getDouble(NBT_INITIAL_X);
        double initialY = entityData.getDouble(NBT_INITIAL_Y);
        double initialZ = entityData.getDouble(NBT_INITIAL_Z);
        entity.setPos(initialX, initialY, initialZ);

        if (entity instanceof Mob mob) {
            mob.setNoAi(true);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_19470_, int p_19471_) {
        super.removeAttributeModifiers(entity, p_19470_, p_19471_);
        CompoundTag entityData = entity.getPersistentData();
        entityData.remove(NBT_INITIAL_X);
        entityData.remove(NBT_INITIAL_Y);
        entityData.remove(NBT_INITIAL_Z);
        if (entity instanceof Mob mob) {
            mob.setNoAi(false);
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
