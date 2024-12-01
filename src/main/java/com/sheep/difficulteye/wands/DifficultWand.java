package com.sheep.difficulteye.wands;

import com.sheep.difficulteye.enchantment.ModEnchantment;
import com.sheep.difficulteye.registries.TagRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.function.Consumer;

public class DifficultWand extends Item {
    public int durabity=100;
    public int cooltime=20;
    public final int decreaceCooltime=15;

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
    public int getEnchantLevel(Enchantment enchantment, Player player){
        return EnchantmentHelper.getEnchantmentLevel(enchantment,player);
    }

    public Consumer<Player> breaksound(){
        return player1 -> player1.level().playSound(null, player1.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);

    }

    @Override
    public boolean isValidRepairItem(ItemStack p_41402_, ItemStack p_41403_) {
        return p_41402_.is(TagRegistry.Items.WAND)&&p_41403_.getItem()==Items.DIAMOND;
    }
    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 5;
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment== ModEnchantment.EFFECT_BOOST.get()||
                enchantment== ModEnchantment.QUICK_USE.get()||
                enchantment== Enchantments.UNBREAKING;
    }
}