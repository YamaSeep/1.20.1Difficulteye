package com.sheep.difficulteye.main;

import com.mojang.logging.LogUtils;
import com.sheep.difficulteye.effect.ModMobEffect;
import com.sheep.difficulteye.enchantment.ModEnchantment;
import com.sheep.difficulteye.entity.DifficulteyeEntities;
import com.sheep.difficulteye.particle.ModParticle;
import com.sheep.difficulteye.registries.BlockRegistry;
import com.sheep.difficulteye.registries.CreativeTabRegistry;
import com.sheep.difficulteye.registries.EntityRegistry;
import com.sheep.difficulteye.registries.ItemRegistry;
import com.sheep.difficulteye.worldgen.tree.ModFoliagePlacers;
import com.sheep.difficulteye.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(Difficulteye.MODID)
public class Difficulteye {
    public static final BooleanProperty PLACED_BY_PLAYER = BooleanProperty.create("placed_by_player");

    public static final String MODID = "difficulteye";
    private static final Logger LOGGER = LogUtils.getLogger();
     public Difficulteye() {
         IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

         // 各レジストリをイベントバスに登録
         BlockRegistry.BLOCKS.register(modEventBus);
         ItemRegistry.BLOCK_ITEMS.register(modEventBus);
         ItemRegistry.ITEMS.register(modEventBus);
         CreativeTabRegistry.CREATIVE_MODE_TABS.register(modEventBus);
         EntityRegistry.ENTITY_TYPES.register(modEventBus);
         // Register ourselves for server and other game events we are interested in
         MinecraftForge.EVENT_BUS.register(this);
         ModTrunkPlacerTypes.register(modEventBus);
         ModFoliagePlacers.register(modEventBus);
         DifficulteyeEntities.register(modEventBus);
         ModEnchantment.register(modEventBus);
         ModMobEffect.register(modEventBus);
         ModParticle.register(modEventBus);
     }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
        var recipeManager = event.getServer().getRecipeManager();
        List<Recipe<?>> recipes = new ArrayList<>(recipeManager.getRecipes());

        List<Recipe<?>> newRecipes = new ArrayList<>();

        List<ResourceLocation> recipesToRemove = List.of(
                recipename("terracotta"), // 未着色テラコッタ
                recipename("red_terracotta"),
                recipename("orange_terracotta"),
                recipename("yellow_terracotta"),
                recipename("brown_terracotta"),
                recipename("white_terracotta"),
                recipename("light_gray_terracotta"),
                recipename("cyan_terracotta"),
                recipename("blue_terracotta"),
                recipename("green_terracotta"),


                recipename("black_terracotta"),
                recipename("light_blue_terracotta"),
                recipename("lime_terracotta"),
                recipename("gray_terracotta"),
                recipename("pink_terracotta"),
                recipename("magenta_terracotta"),
                recipename("purple_terracotta")
        );

        for (Recipe<?> recipe : recipes) {
            if (!recipesToRemove.contains(recipe.getId())) {
                newRecipes.add(recipe);
            }
        }
        recipeManager.replaceRecipes(newRecipes);
    }
    private static ResourceLocation recipename(String name) {
         return new ResourceLocation("minecraft",name);
    }

}
