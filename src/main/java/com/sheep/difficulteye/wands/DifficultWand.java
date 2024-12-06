package com.sheep.difficulteye.wands;

import com.sheep.difficulteye.enchantment.ModEnchantment;
import com.sheep.difficulteye.particle.ModParticle;
import com.sheep.difficulteye.registries.TagRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Consumer;

public class DifficultWand extends Item {
    public int durabity=100;
    public int cooltime=20;
    public final int decreaceCooltime=15;
    private final int addsearchAreaRadius=2;

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
    public int getCooltime(int num){
        return Math.max(cooltime - num * decreaceCooltime, 0);
    }
    public void circle(ItemStack stack, Level level, Entity entity, int radius1){
        int radius=radius1+getEnchantLevel(ModEnchantment.EFFECT_BOOST.get(),(Player) entity);
        if (level.isClientSide && entity instanceof Player player) {
            if (player.getMainHandItem() == stack) {
                double centerX = player.getX();
                double centerY = player.getY();
                double centerZ = player.getZ();
                int points = 48; // 円を構成する点の数

                for (int i = 0; i < points; i++) {
                    double angle = 2 * Math.PI * i / points; // 角度
                    double x = centerX + radius * Math.cos(angle);
                    double z = centerZ + radius * Math.sin(angle);
                    level.addParticle(ModParticle.Range_PARTICLES.get(), x, centerY, z, 0, 1, 0);
                }
            }
        }
    }

    public List<Mob> getMoblist(Level level, Player player,int radius1){
        int radius=radius1+getEnchantLevel(ModEnchantment.EFFECT_BOOST.get(),player);
        int searchAreaRadius=radius+addsearchAreaRadius;
        AABB searchArea = new AABB(
                player.getX() - searchAreaRadius, player.getY() - searchAreaRadius, player.getZ() - searchAreaRadius,
                player.getX() + searchAreaRadius, player.getY() + searchAreaRadius, player.getZ() + searchAreaRadius
        );
        return level.getEntitiesOfClass(Mob.class, searchArea, mob -> {
            double distance = mob.distanceToSqr(player); // 距離の二乗を計算
            return mob.isAlive() && distance <= radius * radius; // 球体条件
        });
    }
}