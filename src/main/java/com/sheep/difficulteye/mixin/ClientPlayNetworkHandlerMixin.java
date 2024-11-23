package com.sheep.difficulteye.mixin;

import com.sheep.difficulteye.registries.ItemRegistry;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPacketListener.class)
public class ClientPlayNetworkHandlerMixin {
    @Unique
    private static boolean isCustomTotem(ItemStack itemStack) {
        return itemStack.is(ItemRegistry.TOTEM_OF_WARDEN.get())
                ||itemStack.is(ItemRegistry.TOTEM_OF_ELDER_GARDIAN.get())
                ||itemStack.is(ItemRegistry.TOTEM_OF_WITHER.get())
                ||itemStack.is(ItemRegistry.TOTEM_OF_PIGLINBRUTE.get());
    }
    @Inject(method = "findTotem", at = @At(value = "RETURN"), cancellable = true)
    private static void getActiveMoreTotemOfUndying(Player player, CallbackInfoReturnable<ItemStack> cir){
        for(InteractionHand hand : InteractionHand.values()) {
            ItemStack itemStack = player.getItemInHand(hand);
            if(isCustomTotem(itemStack)) cir.setReturnValue(itemStack);

        }
    }
}
