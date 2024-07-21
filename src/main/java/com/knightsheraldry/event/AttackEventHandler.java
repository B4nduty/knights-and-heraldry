package com.knightsheraldry.event;

import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEventHandler implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (player instanceof IEntityDataSaver) {
            int stamina = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getInt("stamina_int");

            int staminaCost = 10;
            if (stamina >= staminaCost) {
                ClientPlayNetworking.send(ModMessages.ATTACK_ID, PacketByteBufs.create());
                player.sendMessage(Text.literal("This is the Cost:" + staminaCost +
                        " Now you have:" + (stamina-staminaCost)), true);
            } else {
                return ActionResult.FAIL;
            }
        }
        return ActionResult.PASS;
    }
}
