package com.sheep.difficulteye.eventt;

import com.sheep.difficulteye.registries.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber
public class OverrideLoot {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (event.getSource().getEntity() instanceof Player) {
            if (entity instanceof Warden) {
                entity.spawnAtLocation(new ItemStack(ItemRegistry.TOTEM_OF_WARDEN.get(), 1));
                event.setCanceled(true);
            }
            if (entity instanceof ElderGuardian) {
                if (entity.getTags().isEmpty()) return;
                if (entity.getTags().contains("Elder_no.0"))
                    entity.spawnAtLocation(new ItemStack(ItemRegistry.TOTEM_OF_ELDER_GARDIAN_RIGHT.get(), 1));
                else if (entity.getTags().contains("Elder_no.1"))
                    entity.spawnAtLocation(new ItemStack(ItemRegistry.TOTEM_OF_ELDER_GARDIAN_LEFT.get(), 1));
                 else if (entity.getTags().contains("Elder_no.2"))
                    entity.spawnAtLocation(new ItemStack(ItemRegistry.TOTEM_OF_ELDER_GARDIAN_CENTER.get(), 1));
            }
            if (entity instanceof PiglinBrute){
                if (ThreadLocalRandom.current().nextBoolean()){
                    entity.spawnAtLocation(new ItemStack(ItemRegistry.TOTEM_OF_PIGLINBRUTE.get(), 1));
                }
            }

        }
    }

    @SubscribeEvent
    public static void onSpawnElder(MobSpawnEvent.FinalizeSpawn event) throws IOException {
        Entity entity = event.getEntity();
        if (entity instanceof ElderGuardian) {
            File datadirectory = new File(Objects.requireNonNull(event.getLevel().getServer()).getWorldPath(LevelResource.ROOT).toFile(), "data");
            if (!datadirectory.exists()) if (datadirectory.mkdir()) System.out.println("created data.dat");
            int i = loadIntFromFile(new File(datadirectory, "data.dat"));
            if (!entity.hasCustomName()) {
                if (((ElderGuardian) entity).getSpawnType() == MobSpawnType.SPAWN_EGG) return;
                if (((ElderGuardian) entity).getSpawnType() == MobSpawnType.COMMAND) return;
                if (i % 3 == 0) i = 0;
                entity.addTag("Elder_no." + i);
                entity.setCustomNameVisible(false);
                i++;
                saveIntToFile(new File(datadirectory, "data.dat"), i);
            }
        }

    }

    private static void saveIntToFile(File file, int value) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(value);
            System.out.println("iの値を保存しました: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int loadIntFromFile(File file) {
        if (!file.exists()) {
            System.out.println("ファイルが存在しません。");
            return 0; // デフォルト値を返す
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            return dis.readInt();
        } catch (IOException e) {
            e.printStackTrace();
            return 0; // 読み込み失敗時のデフォルト値
        }
    }
}
