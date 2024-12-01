package com.sheep.difficulteye.mixin;

import com.sheep.difficulteye.registries.ItemRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {


    @Shadow
    public native ItemStack getItemInHand(InteractionHand hand_1);

    @Shadow
    public native void setHealth(float health);

    @Shadow
    public native boolean removeAllEffects();

    @Shadow
    public native boolean addEffect(MobEffectInstance statusEffectInstance_1);

    protected LivingEntityMixin(EntityType<?> entityType_1, Level world_1) {
        super(entityType_1, world_1);
    }


    @Inject(at = @At("HEAD"), method = "checkTotemDeathProtection", cancellable = true)
    public void useWardeTotem(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {
        /*inits PlayerEntity entity, which is SummonersZombie copy of this casted to Living Entity and then PlayerEntity*/
        Entity entity = this;
        /*ItemStack object that is set to the offhand item that entity is carrying*/
        ItemStack offhand_stack = ((LivingEntityMixin) entity).getItemInHand(InteractionHand.OFF_HAND);

        ItemStack mainhand_stack = ((LivingEntityMixin) entity).getItemInHand(InteractionHand.MAIN_HAND);

        //Executes if the item in offhand_stack is equal to the explosive totem of Undying
        if ((offhand_stack.getItem() == ItemRegistry.TOTEM_OF_WARDEN.get()) || (mainhand_stack.getItem() == ItemRegistry.TOTEM_OF_WARDEN.get())) {

            /*If the damagesource is something that could kill SummonersZombie player in creative mode, the totem does not work*/
            if (damageSource_1.type().equals(DamageTypes.FELL_OUT_OF_WORLD)) {

                callback.setReturnValue(false);
            } else {
                if ((offhand_stack.getItem() == ItemRegistry.TOTEM_OF_WARDEN.get())) {
                    offhand_stack.hurtAndBreak(1, (LivingEntity) entity, (e) -> {
                    });
                } else if ((mainhand_stack.getItem() == ItemRegistry.TOTEM_OF_WARDEN.get())) {
                    mainhand_stack.hurtAndBreak(1, (LivingEntity) entity, (e) -> {
                    });
                }
                callback.setReturnValue(true);
                this.setHealth(4.0F);
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 125, 2));
                this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 350, 4));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 4));

                this.level().broadcastEntityEvent(this, (byte) 35);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "checkTotemDeathProtection", cancellable = true)
    public void useElderTotem(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {
        /*inits PlayerEntity entity, which is SummonersZombie copy of this casted to Living Entity and then PlayerEntity*/
        Entity entity = this;
        /*ItemStack object that is set to the offhand item that entity is carrying*/
        ItemStack offhand_stack = ((LivingEntityMixin) entity).getItemInHand(InteractionHand.OFF_HAND);

        ItemStack mainhand_stack = ((LivingEntityMixin) entity).getItemInHand(InteractionHand.MAIN_HAND);

        //Executes if the item in offhand_stack is equal to the explosive totem of Undying
        if ((offhand_stack.getItem() == ItemRegistry.TOTEM_OF_ELDER_GARDIAN.get()) || (mainhand_stack.getItem() == ItemRegistry.TOTEM_OF_ELDER_GARDIAN.get())) {

            /*If the damagesource is something that could kill SummonersZombie player in creative mode, the totem does not work*/
            if (damageSource_1.type().equals(DamageTypes.FELL_OUT_OF_WORLD)) {

                callback.setReturnValue(false);
            } else {
                if ((offhand_stack.getItem() == ItemRegistry.TOTEM_OF_ELDER_GARDIAN.get())) {
                    offhand_stack.shrink(1);
                } else if ((mainhand_stack.getItem() == ItemRegistry.TOTEM_OF_ELDER_GARDIAN.get())) {
                    mainhand_stack.shrink(1);
                }
                callback.setReturnValue(true);
                this.setHealth(2.0F);
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 125, 2));
                this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 350, 4));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                this.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 6));

                this.level().broadcastEntityEvent(this, (byte) 35);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "checkTotemDeathProtection", cancellable = true)
    public void useWitherTotem(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {
        /*inits PlayerEntity entity, which is SummonersZombie copy of this casted to Living Entity and then PlayerEntity*/
        Entity entity = this;
        /*ItemStack object that is set to the offhand item that entity is carrying*/
        ItemStack offhand_stack = ((LivingEntityMixin) entity).getItemInHand(InteractionHand.OFF_HAND);

        ItemStack mainhand_stack = ((LivingEntityMixin) entity).getItemInHand(InteractionHand.MAIN_HAND);

        //Executes if the item in offhand_stack is equal to the explosive totem of Undying
        if ((offhand_stack.getItem() == ItemRegistry.TOTEM_OF_WITHER.get()) || (mainhand_stack.getItem() == ItemRegistry.TOTEM_OF_WITHER.get())) {

            /*If the damagesource is something that could kill SummonersZombie player in creative mode, the totem does not work*/
            if (damageSource_1.type().equals(DamageTypes.FELL_OUT_OF_WORLD)) {

                callback.setReturnValue(false);
            } else {
                if ((offhand_stack.getItem() == ItemRegistry.TOTEM_OF_WITHER.get())) {
                    offhand_stack.hurtAndBreak(1, (LivingEntity) entity, (e) -> {
                    });
                } else if ((mainhand_stack.getItem() == ItemRegistry.TOTEM_OF_WITHER.get())) {
                    mainhand_stack.hurtAndBreak(1, (LivingEntity) entity, (e) -> {
                    });
                }
                callback.setReturnValue(true);
                this.setHealth(9.0F);
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 185, 6));
                this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 8));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 180, 6));

                this.level().broadcastEntityEvent(this, (byte) 35);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "checkTotemDeathProtection", cancellable = true)
    public void usePiglinbruteTotem(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {
        /*inits PlayerEntity entity, which is SummonersZombie copy of this casted to Living Entity and then PlayerEntity*/
        Entity entity = this;
        /*ItemStack object that is set to the offhand item that entity is carrying*/
        ItemStack offhand_stack = ((LivingEntityMixin) entity).getItemInHand(InteractionHand.OFF_HAND);

        ItemStack mainhand_stack = ((LivingEntityMixin) entity).getItemInHand(InteractionHand.MAIN_HAND);

        //Executes if the item in offhand_stack is equal to the explosive totem of Undying
        if ((offhand_stack.getItem() == ItemRegistry.TOTEM_OF_PIGLINBRUTE.get()) || (mainhand_stack.getItem() == ItemRegistry.TOTEM_OF_PIGLINBRUTE.get())) {

            /*If the damagesource is something that could kill SummonersZombie player in creative mode, the totem does not work*/
            if (damageSource_1.type().equals(DamageTypes.FELL_OUT_OF_WORLD)) {

                callback.setReturnValue(false);
            } else {
                if ((offhand_stack.getItem() == ItemRegistry.TOTEM_OF_PIGLINBRUTE.get())) {
                    offhand_stack.shrink(1);
                } else if ((mainhand_stack.getItem() == ItemRegistry.TOTEM_OF_PIGLINBRUTE.get())) {
                    mainhand_stack.shrink(1);
                }
                callback.setReturnValue(true);
                this.setHealth(3.0F);
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 125, 2));
                this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 350, 4));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0));

                this.level().broadcastEntityEvent(this, (byte) 35);
            }
        }
    }
}

