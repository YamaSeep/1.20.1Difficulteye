package com.sheep.difficulteye.datagen.server;

import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.worldgen.biome.DifficulteyeBiomeModifier;
import com.sheep.difficulteye.worldgen.feature.DifficulteyeFeature;
import com.sheep.difficulteye.worldgen.placement.DifficulteyeOrePlacement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DifficulteyeWorldGenProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER=new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, DifficulteyeFeature::bootstrap)
            .add(Registries.PLACED_FEATURE, DifficulteyeOrePlacement::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, DifficulteyeBiomeModifier::bootstrap);

    public DifficulteyeWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Difficulteye.MODID));
    }
}
