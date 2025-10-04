package com.knightsheraldry.mixin;

import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import com.knightsheraldry.items.item.khweapon.Lance;
import com.knightsheraldry.util.playerdata.PlayerVelocity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
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
        if (!(lanceStack != null && lanceStack.getNbt() != null && lanceStack.getNbt().getBoolean("charged")
                && playerEntity instanceof ServerPlayerEntity serverPlayer)) return;

        float velocity = calculateVelocity(serverPlayer, lanceStack);

        PlayerVelocity.updateSpeedHistory(this, velocity);
    }

    @Unique
    private ItemStack getLanceStack(PlayerEntity player) {
        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();
        return mainHandStack.getItem() instanceof Lance ? mainHandStack :
                offHandStack.getItem() instanceof Lance ? offHandStack : null;
    }

    @Unique
    private float calculateVelocity(ServerPlayerEntity player, ItemStack lanceStack) {
        double prevX = lanceStack.getOrCreateNbt().getDouble("prevX");
        double prevZ = lanceStack.getOrCreateNbt().getDouble("prevZ");

        Entity entity = player.getVehicle();
        if (entity == null) entity = player;

        // Compute delta since last tick
        Vec3d delta = new Vec3d(entity.getX() - prevX, 0, entity.getZ() - prevZ);

        // Save current position for next tick
        lanceStack.getOrCreateNbt().putDouble("prevX", entity.getX());
        lanceStack.getOrCreateNbt().putDouble("prevZ", entity.getZ());

        return (float) delta.length();
    }

}