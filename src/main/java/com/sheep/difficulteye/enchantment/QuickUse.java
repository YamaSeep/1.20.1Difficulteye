package com.sheep.difficulteye.enchantment;

import com.sheep.difficulteye.registries.TagRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class QuickUse extends Enchantment {
    protected QuickUse(Rarity p_44676_, EquipmentSlot... p_44678_) {
        super(p_44676_, EnchantmentCategory.create("wand",item -> new ItemStack(item).is(TagRegistry.Items.WAND)), p_44678_);
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
