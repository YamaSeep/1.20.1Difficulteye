package com.sheep.difficulteye.enchantment;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantment {
    public static final DeferredRegister<Enchantment> ENCHANTMENT=DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Difficulteye.MODID);
    public static RegistryObject<Enchantment> RANGE_BOOST=ENCHANTMENT.register(
            "range_boost",()->new RangeBoost(Enchantment.Rarity.RARE,EquipmentSlot.MAINHAND)
    );
    public static void register(IEventBus eventBus){
        ENCHANTMENT.register(eventBus);
    }
}
