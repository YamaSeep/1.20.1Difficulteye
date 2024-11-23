package com.sheep.difficulteye.eventt;

import com.sheep.difficulteye.registries.TagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEvent {
    @SubscribeEvent
    public static void onPlased(BlockEvent.EntityPlaceEvent event){
        if (event.getLevel().isClientSide()||!(event.getEntity() instanceof Player player))return;
        if (event.getState().is(Blocks.TORCH)||event.getState().is(TagRegistry.Blocks.UNBREAKS))return;
        for(int x = -4; x <= 4; x++){
            for(int y = -4; y <= 4; y++){
                for(int z = -4; z <= 4; z++){
                    BlockPos blockPos=event.getPos().offset(x,y,z);
                    if (event.getLevel().getBlockState(blockPos).is(TagRegistry.Blocks.UNBREAKS)){
                        player.sendSystemMessage(Component.translatable("message.difficulteye.cannot_place"));
                        event.setCanceled(true);
                        return;
                    }

                }
            }
        }
    }
}
