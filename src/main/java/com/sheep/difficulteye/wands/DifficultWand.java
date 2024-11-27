package com.sheep.difficulteye.wands;

import com.sheep.difficulteye.registries.TagRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class DifficultWand extends Item {
    private int durabity=100;
    public int cooltime=20;

    public DifficultWand(int durabity,int cooltime) {
        super(new Item.Properties().defaultDurability(durabity).rarity(Rarity.EPIC));
        this.durabity=durabity;
        this.cooltime=cooltime;
    }
    public DifficultWand() {
        super(new Item.Properties().defaultDurability(100).rarity(Rarity.EPIC));
    }

    public int getDurabity() {
        return durabity;
    }

    public void setDurabity(int durabity) {
        this.durabity = durabity;
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_41402_, ItemStack p_41403_) {
        return p_41402_.is(TagRegistry.Items.WAND)&&p_41403_.getItem()==Items.DIAMOND;
    }
}