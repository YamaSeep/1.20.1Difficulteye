package com.sheep.difficulteye.info.iteminfo;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WardenTotem extends Item {
    public WardenTotem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }
}
