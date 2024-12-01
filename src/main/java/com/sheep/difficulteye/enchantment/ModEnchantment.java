package com.sheep.difficulteye.enchantment;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantment {
    public static final DeferredRegister<Enchantment> ENCHANTMENT=DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Difficulteye.MODID);
    public static RegistryObject<Enchantment> EFFECT_BOOST=ENCHANTMENT.register(
            "effect_boost",()->new EffectBoost(Enchantment.Rarity.RARE,EquipmentSlot.MAINHAND)
    );
    public static RegistryObject<Enchantment> QUICK_USE=ENCHANTMENT.register(
            "quick_use",()->new QuickUse(Enchantment.Rarity.RARE,EquipmentSlot.MAINHAND)
    );
    public static void register(IEventBus eventBus){
        ENCHANTMENT.register(eventBus);
    }
}
