package com.knightsheraldry.mixin;

import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import com.knightsheraldry.items.item.khweapon.Lance;
import com.knightsheraldry.util.playerdata.PlayerVelocity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin implements IEntityDataSaver {
    @Unique
    private final Player playerEntity = (Player) (Object) this;

    @Inject(method = "tick", at = @At("HEAD"))
    private void knightsheraldry$onTick(CallbackInfo ci) {
        ItemStack lanceStack = getLanceStack(playerEntity);
        if (!(lanceStack != null && lanceStack.getTag() != null && lanceStack.getTag().getBoolean("charged")
                && playerEntity instanceof ServerPlayer serverPlayer)) return;

        float velocity = calculateVelocity(serverPlayer);

        PlayerVelocity.updateSpeedHistory(this, velocity);
    }

    @Unique
    private ItemStack getLanceStack(Player player) {
        ItemStack mainHandStack = player.getMainHandItem();
        ItemStack offHandStack = player.getOffhandItem();
        return mainHandStack.getItem() instanceof Lance ? mainHandStack :
                offHandStack.getItem() instanceof Lance ? offHandStack : null;
    }

    @Unique
    private float calculateVelocity(ServerPlayer player) {
        CompoundTag playerData = ((IEntityDataSaver) player).stoneycore$getPersistentData();

        double prevX = playerData.getDouble("lancePrevX");
        double prevZ = playerData.getDouble("lancePrevZ");

        Entity entity = player.getVehicle();
        if (entity == null) entity = player;

        Vec3 delta = new Vec3(entity.getX() - prevX, 0, entity.getZ() - prevZ);

        playerData.putDouble("lancePrevX", entity.getX());
        playerData.putDouble("lancePrevZ", entity.getZ());

        return (float) delta.length();
    }
}