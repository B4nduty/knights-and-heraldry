package com.knightsheraldry.event;

import com.knightsheraldry.items.custom.KHWeapons;
import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityEventHandler implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        boolean staminaBlocked = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getBoolean("stamina_blocked");
        boolean ableStamina = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getBoolean("able_stamina");
        boolean isHoldingKHWeapon = player.getStackInHand(hand).getItem() instanceof KHWeapons;
        if (isHoldingKHWeapon && ableStamina && !player.isSpectator()) {
            int stamina = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getInt("stamina_int");

            int staminaCost = 10;
            if (stamina >= staminaCost && !staminaBlocked) {
                ClientPlayNetworking.send(ModMessages.ATTACK_ID, PacketByteBufs.create());
            } else {
                return ActionResult.FAIL;
            }
        }
        return ActionResult.PASS;
    }
}
