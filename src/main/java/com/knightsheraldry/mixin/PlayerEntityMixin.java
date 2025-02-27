package com.knightsheraldry.mixin;

import banduty.stoneycore.util.playerdata.IEntityDataSaver;
import com.knightsheraldry.items.item.khweapon.Lance;
import com.knightsheraldry.util.playerdata.PlayerVelocity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IEntityDataSaver {
    @Unique
    private final PlayerEntity playerEntity = (PlayerEntity) (Object) this;

    @Inject(method = "tick", at = @At("HEAD"))
    private void knightsheraldry$onTick(CallbackInfo ci) {
        ItemStack lanceStack = getLanceStack(playerEntity);
        if (lanceStack != null && lanceStack.getNbt() != null && lanceStack.getNbt().getBoolean("kh_charged")
                && playerEntity instanceof ServerPlayerEntity serverPlayer) {

            NbtCompound nbt = stoneycore$getPersistentData();
            int nonSprintingTicks = nbt.getInt("nonSprintingTicks");

            BlockPos previousBlockPos = BlockPos.fromLong(nbt.getLong("previousBlockPos"));
            BlockPos currentBlockPos = serverPlayer.getBlockPos();

            boolean staying = currentBlockPos.equals(previousBlockPos);

            float velocity = calculateVelocity(serverPlayer, nonSprintingTicks, staying);

            PlayerVelocity.updatePreviousBlockPos(this, currentBlockPos.asLong());
            PlayerVelocity.updateSpeedHistory(this, velocity);
            PlayerVelocity.updateNonSprintingTicks(this, nonSprintingTicks);
        }
    }

    @Unique
    private ItemStack getLanceStack(PlayerEntity player) {
        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();
        return mainHandStack.getItem() instanceof Lance ? mainHandStack :
                offHandStack.getItem() instanceof Lance ? offHandStack : null;
    }

    @Unique
    private float calculateVelocity(ServerPlayerEntity player, int nonSprintingTicks, boolean staying) {
        float velocity = player.getMovementSpeed();

        if (player.isSprinting()) {
            velocity *= 1.3f;
            stoneycore$getPersistentData().putInt("nonSprintingTicks", 0);
        } else {
            nonSprintingTicks++;
            if (nonSprintingTicks >= 3) {
                if (nonSprintingTicks >= 5 && staying) {
                    velocity *= 0.1f;
                    stoneycore$getPersistentData().putInt("nonSprintingTicks", 0);
                } else if (player.isSneaking()) {
                    velocity *= 0.3f;
                    stoneycore$getPersistentData().putInt("nonSprintingTicks", 0);
                } else if (player.hasVehicle()) {
                    velocity = getVehicleVelocity(player);
                    stoneycore$getPersistentData().putInt("nonSprintingTicks", 0);
                }
            }
        }

        return velocity;
    }

    @Unique
    private float getVehicleVelocity(ServerPlayerEntity player) {
        Entity vehicle = player.getVehicle();
        if (vehicle instanceof MinecartEntity minecart) {
            return (float) minecart.getVelocity().length();
        } else if (vehicle instanceof BoatEntity boat) {
            return (float) boat.getVelocity().length();
        } else if (vehicle instanceof AbstractHorseEntity horse) {
            return (float) horse.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        }
        return player.getMovementSpeed();
    }
}