package com.sheep.difficulteye.jei;

import com.sheep.difficulteye.main.Difficulteye;
import static com.sheep.difficulteye.registries.ItemRegistry.*;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import static net.minecraft.world.item.Items.*;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@JeiPlugin
public class JEIItemDescription implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(Difficulteye.MODID, "jei-plugin");

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addItemDescriptions(registration);
    }

    public void addItemInfo(IRecipeRegistration registration, Item item) {
        ItemStack itemStack = new ItemStack(item);
        String description = Component.translatable(itemStack.getItem().getDescriptionId()+".description").getString();
        registration.addIngredientInfo(itemStack, VanillaTypes.ITEM_STACK, Component.literal(description));
    }
    // 自作アイテムに対する説明を追加するメソッド
    public void addItemDescriptions(IRecipeRegistration registration) {
        addItemInfo(registration,TOTEM_OF_WARDEN.get());
        addItemInfo(registration,TOTEM_OF_ELDER_GARDIAN.get());
        addItemInfo(registration,TOTEM_OF_ELDER_GARDIAN_RIGHT.get());
        addItemInfo(registration,TOTEM_OF_ELDER_GARDIAN_CENTER.get());
        addItemInfo(registration,TOTEM_OF_ELDER_GARDIAN_LEFT.get());
        addItemInfo(registration,TOTEM_OF_WITHER.get());
        addItemInfo(registration,TOTEM_OF_PIGLINBRUTE.get());
        addItemInfo(registration,WAND_OF_ORE.get());

        addItemInfo(registration,TERRACOTTA);
        addItemInfo(registration, RED_TERRACOTTA);
        addItemInfo(registration, ORANGE_TERRACOTTA);
        addItemInfo(registration, YELLOW_TERRACOTTA);
        addItemInfo(registration, BROWN_TERRACOTTA);
        addItemInfo(registration, WHITE_TERRACOTTA);
        addItemInfo(registration, LIGHT_GRAY_TERRACOTTA);
        addItemInfo(registration, CYAN_TERRACOTTA);
        addItemInfo(registration, BLUE_TERRACOTTA);

        addItemInfo(registration,BLACK_TERRACOTTA);
        addItemInfo(registration, GREEN_TERRACOTTA);
        addItemInfo(registration, LIGHT_BLUE_TERRACOTTA); // 空色
        addItemInfo(registration, LIME_TERRACOTTA);       // 黄緑色
        addItemInfo(registration, GRAY_TERRACOTTA);       // 灰色
        addItemInfo(registration, PINK_TERRACOTTA);       // 桃色
        addItemInfo(registration, MAGENTA_TERRACOTTA);    // 赤紫色
        addItemInfo(registration, PURPLE_TERRACOTTA);

    }

}