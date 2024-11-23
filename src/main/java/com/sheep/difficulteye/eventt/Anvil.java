package com.sheep.difficulteye.eventt;

import com.sheep.difficulteye.registries.TagRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Anvil {
    @SubscribeEvent
    public static void onAnviledItem(AnvilUpdateEvent event){
        ItemStack left=event.getLeft();
        ItemStack right=event.getRight();
       if (left.isDamageableItem()&&left.is(TagRegistry.Items.TOTEM)){
        if (right.is(Items.ENCHANTED_BOOK)||right.isEnchanted())
            event.setCanceled(true);
       }
    }
}
