package com.sheep.difficulteye.registries;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Difficulteye.MODID);

    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Difficulteye.MODID);

    public static final RegistryObject<Item> EYE_OF_ARTIFACT = itemRegistryObject("eye_of_artifact", 16,Rarity.RARE); // アーティファクトの目
    public static final RegistryObject<Item> EYE_OF_COLOR = itemRegistryObject("eye_of_color", 16,Rarity.RARE);
    public static final RegistryObject<Item> EYE_OF_CREATURE = itemRegistryObject("eye_of_creature", 16,Rarity.RARE); // 生物の目
    public static final RegistryObject<Item> EYE_OF_DUNGEON = itemRegistryObject("eye_of_dungeon", 16,Rarity.RARE); // ダンジョンの目
    public static final RegistryObject<Item> EYE_OF_ELEMENT = itemRegistryObject("eye_of_element", 16,Rarity.RARE); // 元素の目
    public static final RegistryObject<Item> EYE_OF_FOOD = itemRegistryObject("eye_of_food", 16,Rarity.RARE);
    public static final RegistryObject<Item> EYE_OF_MAGIC = itemRegistryObject("eye_of_magic", 16,Rarity.RARE); // 魔法の目
    public static final RegistryObject<Item> EYE_OF_NATURE = itemRegistryObject("eye_of_nature", 16,Rarity.RARE); // 自然の目
    public static final RegistryObject<Item> EYE_OF_POTION = itemRegistryObject("eye_of_potion", 16,Rarity.RARE);
    public static final RegistryObject<Item> EYE_OF_SEA = itemRegistryObject("eye_of_sea", 16,Rarity.RARE);
    public static final RegistryObject<Item> EYE_OF_SEASON = itemRegistryObject("eye_of_season", 16,Rarity.RARE); // 季節の目
    public static final RegistryObject<Item> EYE_OF_TECHNOLOGY = itemRegistryObject("eye_of_technology", 16,Rarity.RARE); // テクノロジーの目

    public static final RegistryObject<Item> EYE_OF_COLOR_WOOL1 = itemRegistryObject("eye_of_color_wool1", 1);
    public static final RegistryObject<Item> EYE_OF_COLOR_WOOL2 = itemRegistryObject("eye_of_color_wool2", 1);
    public static final RegistryObject<Item> EYE_OF_COLOR_GLASS1 = itemRegistryObject("eye_of_color_glass1", 1);
    public static final RegistryObject<Item> EYE_OF_COLOR_GLASS2 = itemRegistryObject("eye_of_color_glass2", 1);
    public static final RegistryObject<Item> EYE_OF_COLOR_CONCRATE1 = itemRegistryObject("eye_of_color_concrate1", 1); // コンクリート
    public static final RegistryObject<Item> EYE_OF_COLOR_CONCRATE2 = itemRegistryObject("eye_of_color_concrate2", 1); // コンクリート
    public static final RegistryObject<Item> EYE_OF_COLOR_TERRACOTTA1 = itemRegistryObject("eye_of_color_terracotta1", 1);
    public static final RegistryObject<Item> EYE_OF_COLOR_TERRACOTTA2 = itemRegistryObject("eye_of_color_terracotta2", 1);
    public static final RegistryObject<Item> EYE_OF_COLOR_EYECORE = itemRegistryObject("eye_of_color_eyecore", 1);

    public static final RegistryObject<Item> END_FRAME_ARTIFACT = endframeRegistryObject("endframe_artifact", 0);
    public static final RegistryObject<Item> END_FRAME_COLOR = endframeRegistryObject("endframe_color", 1);
    public static final RegistryObject<Item> END_FRAME_CREATURE = endframeRegistryObject("endframe_creature", 2);
    public static final RegistryObject<Item> END_FRAME_DUNGEON = endframeRegistryObject("endframe_dungeon", 3);
    public static final RegistryObject<Item> END_FRAME_ELEMENT = endframeRegistryObject("endframe_element", 4);
    public static final RegistryObject<Item> END_FRAME_FOOD = endframeRegistryObject("endframe_food", 5);
    public static final RegistryObject<Item> END_FRAME_MAGIC = endframeRegistryObject("endframe_magic", 6);
    public static final RegistryObject<Item> END_FRAME_NATURE = endframeRegistryObject("endframe_nature", 7);
    public static final RegistryObject<Item> END_FRAME_POTION = endframeRegistryObject("endframe_potion", 8);
    public static final RegistryObject<Item> END_FRAME_SEASON = endframeRegistryObject("endframe_season", 10);
    public static final RegistryObject<Item> END_FRAME_SEA = endframeRegistryObject("endframe_sea", 9);
    public static final RegistryObject<Item> END_FRAME_TECHNOLOGY = endframeRegistryObject("endframe_technology", 11);

    public static final RegistryObject<Item> TOTEM_OF_WARDEN = ITEMS.register("totem_of_warden",()->new Item(new Item.Properties().defaultDurability(2).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> TOTEM_OF_ELDER_GARDIAN = itemRegistryObject("totem_of_elder_gardian",8,Rarity.UNCOMMON);
    public static final RegistryObject<Item> TOTEM_OF_WITHER = ITEMS.register("totem_of_wither",()->new Item(new Item.Properties().defaultDurability(5).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> TOTEM_OF_PIGLINBRUTE = itemRegistryObject("totem_of_piglinbrute",8,Rarity.UNCOMMON);
    public static final RegistryObject<Item> TOTEM_OF_ELDER_GARDIAN_RIGHT = itemRegistryObject("totem_of_elder_gardian_right",16,Rarity.UNCOMMON);
    public static final RegistryObject<Item> TOTEM_OF_ELDER_GARDIAN_CENTER = itemRegistryObject("totem_of_elder_gardian_center",16,Rarity.UNCOMMON);
    public static final RegistryObject<Item> TOTEM_OF_ELDER_GARDIAN_LEFT = itemRegistryObject("totem_of_elder_gardian_left",16,Rarity.UNCOMMON);

    public static final RegistryObject<Item>[] Eyes = new RegistryObject[]{
            EYE_OF_ARTIFACT,
            EYE_OF_COLOR,
            EYE_OF_CREATURE,
            EYE_OF_DUNGEON,
            EYE_OF_ELEMENT,
            EYE_OF_FOOD,
            EYE_OF_MAGIC,
            EYE_OF_NATURE,
            EYE_OF_POTION,
            EYE_OF_SEA,
            EYE_OF_SEASON,
            EYE_OF_TECHNOLOGY,

            EYE_OF_COLOR_WOOL1, EYE_OF_COLOR_WOOL2, EYE_OF_COLOR_GLASS1, EYE_OF_COLOR_GLASS2, EYE_OF_COLOR_CONCRATE1, EYE_OF_COLOR_CONCRATE2,
            EYE_OF_COLOR_TERRACOTTA1, EYE_OF_COLOR_TERRACOTTA2, EYE_OF_COLOR_EYECORE,

            END_FRAME_ARTIFACT, END_FRAME_COLOR, END_FRAME_CREATURE, END_FRAME_DUNGEON, END_FRAME_ELEMENT, END_FRAME_FOOD,
            END_FRAME_MAGIC, END_FRAME_NATURE, END_FRAME_POTION, END_FRAME_SEASON, END_FRAME_SEA, END_FRAME_TECHNOLOGY,

            TOTEM_OF_WARDEN,TOTEM_OF_ELDER_GARDIAN,TOTEM_OF_WITHER,TOTEM_OF_PIGLINBRUTE,

            TOTEM_OF_ELDER_GARDIAN_RIGHT,TOTEM_OF_ELDER_GARDIAN_LEFT,TOTEM_OF_ELDER_GARDIAN_CENTER
    };

    private static RegistryObject<Item> itemRegistryObject(String name, int stack){
        return ItemRegistry.ITEMS.register(name,() -> new Item(new Item.Properties().stacksTo(stack)));
    }
    private static RegistryObject<Item> itemRegistryObject(String name, int stack,Rarity rarity){
        return ItemRegistry.ITEMS.register(name,() -> new Item(new Item.Properties().stacksTo(stack).rarity(rarity)));
    }
    private static RegistryObject<Item> endframeRegistryObject(String name, int num){
        return ItemRegistry.BLOCK_ITEMS.register(name,() -> new BlockItem(BlockRegistry.EndPortals[num].get(),new Item.Properties().stacksTo(64)));
    }

}
