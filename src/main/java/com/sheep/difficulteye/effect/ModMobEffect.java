package com.sheep.difficulteye.effect;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffect {
    public static final DeferredRegister<MobEffect> MOB_EFFECT=DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Difficulteye.MODID);

    public static final RegistryObject<MobEffect> DepthStriderEffect=MOB_EFFECT.register("depth_strider",
            ()->new DepthStriderEffect(MobEffectCategory.NEUTRAL,0x4f8a98));

    public static final RegistryObject<MobEffect> BleedingDamageEffect=MOB_EFFECT.register("bleeding",
            ()->new BleedingEffect(MobEffectCategory.NEUTRAL,0xff0000));

    public static final RegistryObject<MobEffect> BrindingEffect=MOB_EFFECT.register("binding",
            ()->new BindingEffect(MobEffectCategory.NEUTRAL,0x9aafa7));

    public static void register(IEventBus eventBus){
        MOB_EFFECT.register(eventBus);
    }
}
