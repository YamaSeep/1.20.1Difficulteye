package com.sheep.difficulteye.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class Vein extends Entity {
    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(Vein.class, EntityDataSerializers.INT);
    private int lifetime = 20*10;

    public Vein(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (lifetime <= 0) this.discard();
            lifetime--;
        }
    }
    @Override
    protected void defineSynchedData() {
        this.entityData.define(STATE, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.contains("State")) {
            this.setState(compoundTag.getInt("State")); // 保存されたStateを読み込む
        }
    }
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("State", this.getState()); // 現在のStateを保存
    }
    public void setState(int state) {
        this.entityData.set(STATE, state);
    }
    public int getState(){
        return this.entityData.get(STATE);
    }
}
