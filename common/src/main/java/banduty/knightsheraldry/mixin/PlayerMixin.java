package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.item.khweapon.Lance;
import banduty.knightsheraldry.util.playerdata.PlayerVelocity;
import banduty.stoneycore.util.data.entitydata.IEntityDataSaver;
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
public abstract class PlayerMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void knightsheraldry$onTick(CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (!(player instanceof ServerPlayer serverPlayer)) return;

        boolean lanceStackCharged = knightsheraldry$lanceStackCharged(player);
        if (!lanceStackCharged) return;

        float velocity = knightsheraldry$calculateVelocity(serverPlayer);

        PlayerVelocity.updateSpeedHistory((IEntityDataSaver) player, velocity);
    }

    @Unique
    private boolean knightsheraldry$lanceStackCharged(Player player) {
        ItemStack mainHand = player.getMainHandItem();
        if (mainHand.getItem() instanceof Lance) return Lance.isCharged(mainHand);

        ItemStack offHand = player.getOffhandItem();
        if (offHand.getItem() instanceof Lance) return Lance.isCharged(offHand);

        return false;
    }

    @Unique
    private float knightsheraldry$calculateVelocity(ServerPlayer player) {
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