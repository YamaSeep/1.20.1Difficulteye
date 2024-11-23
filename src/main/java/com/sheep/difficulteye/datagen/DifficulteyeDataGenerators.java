package com.sheep.difficulteye.datagen;

import com.sheep.difficulteye.datagen.server.DifficulteyeWorldGenProvider;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Difficulteye.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DifficulteyeDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator=event.getGenerator();
        PackOutput packOutput=generator.getPackOutput();
        ExistingFileHelper existingFileHelper=event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookUpProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(),new DifficulteyeWorldGenProvider(packOutput,lookUpProvider));
    }
}
