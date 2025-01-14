package com.knightsheraldry.event;

import com.knightsheraldry.items.custom.item.KHWeapons;
import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.playerdata.IEntityDataSaver;
import com.knightsheraldry.util.itemdata.KHTags;
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
        IEntityDataSaver dataSaver = (IEntityDataSaver) player;
        var persistentData = dataSaver.knightsheraldry$getPersistentData();

        boolean isHoldingWeapon = player.getStackInHand(hand).getItem() instanceof KHWeapons;
        boolean isTaggedWeapon = player.getStackInHand(hand).isIn(KHTags.WEAPONS.getTag());
        boolean isValidWeapon = isHoldingWeapon && isTaggedWeapon;

        if (!isValidWeapon || player.isCreative()) {
            return ActionResult.PASS;
        }

        boolean staminaBlocked = persistentData.getBoolean("stamina_blocked");
        int stamina = persistentData.getInt("stamina_int");
        int staminaCost = 10;

        if (stamina < staminaCost || staminaBlocked) {
            return ActionResult.FAIL;
        }

        ClientPlayNetworking.send(ModMessages.ATTACK_ID, PacketByteBufs.create());
        return ActionResult.PASS;
    }
}
