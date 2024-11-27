package com.sheep.difficulteye.registries;

import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagRegistry {
    public static class Blocks{
        public static final TagKey<Block> PORTAL_FRAMES=blocktag("portal_frames");
        public static final TagKey<Block> UNBREAKS=blocktag("unbreak");
        public static final TagKey<Block> ORES=blocktag("ores");

        private static TagKey<Block> blocktag (String name){

            return BlockTags.create(new ResourceLocation(Difficulteye.MODID,name));
        }
    }
    public static class Items{
        public static final TagKey<Item> TOTEM=itemtag("totem");
        public static final TagKey<Item> WAND=itemtag("wand");
        private static TagKey<Item> itemtag (String name){

            return ItemTags.create(new ResourceLocation(Difficulteye.MODID,name));
        }
    }
}
