package com.knightsheraldry.event;

import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class UseItemEventHandler implements UseItemCallback {
    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        boolean staminaBlocked = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getBoolean("stamina_blocked");
        if (!player.isSpectator()) {
            int stamina = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getInt("stamina_int");

            int staminaCost = 10;
            if (stamina >= staminaCost && !staminaBlocked) {
                ClientPlayNetworking.send(ModMessages.ATTACK_ID, PacketByteBufs.create());
            } else {
                return TypedActionResult.fail(itemStack);
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}
