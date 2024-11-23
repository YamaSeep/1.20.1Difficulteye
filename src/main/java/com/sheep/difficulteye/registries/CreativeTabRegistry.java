package com.sheep.difficulteye.registries;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Difficulteye.MODID);

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("difficulteye", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("difficulteye")) // タブのタイトルを設定
                    .withTabsBefore(CreativeModeTabs.BUILDING_BLOCKS)
                    .icon(() -> ItemRegistry.Eyes[1].get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        for (RegistryObject<Item> item: ItemRegistry.Eyes)
                            output.accept(item.get());
                    })
                    .build()
    );
}

