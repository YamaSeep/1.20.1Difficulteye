package com.sheep.difficulteye.enchantment;

import com.sheep.difficulteye.registries.TagRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class RangeBoost extends Enchantment {
    protected RangeBoost(Rarity rarity,EquipmentSlot... equipmentSlots) {
        super(rarity,EnchantmentCategory.create("wand",item -> new ItemStack(item).is(TagRegistry.Items.WAND)),equipmentSlots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMaxCost(int max) {
        return this.getMinCost(max) + 5;
    }

    @Override
    public int getMinCost(int min) {
        return 1 + min * 10;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }
}
