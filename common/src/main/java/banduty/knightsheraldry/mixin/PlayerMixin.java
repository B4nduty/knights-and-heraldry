package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.item.khsield.HeaterShield;
import banduty.knightsheraldry.items.item.khweapon.Estoc;
import banduty.knightsheraldry.items.item.khweapon.Lance;
import banduty.knightsheraldry.util.playerdata.PlayerVelocity;
import banduty.stoneycore.data.IEntityDataSaver;
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
    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
    private void knightsheraldry$noAxeDisable(CallbackInfo ci) {
        Player self = (Player) (Object) this;
        if (self.getUseItem().getItem() instanceof HeaterShield) {
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void knightsheraldry$onTick(CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (!(player instanceof ServerPlayer serverPlayer)) return;

        if (!knightsheraldry$holdingLanceWeapon(player)) return;

        float velocity = knightsheraldry$calculateVelocity(serverPlayer);

        PlayerVelocity.updateSpeedHistory((IEntityDataSaver) player, velocity);
    }

    // Tracks mount speed while a lance-style weapon (lance, or the estoc's mounted charge) is held. Tracking
    // before the weapon is charged keeps the previous position fresh, so the first charged tick doesn't measure
    // the distance travelled since the weapon was last used.
    @Unique
    private boolean knightsheraldry$holdingLanceWeapon(Player player) {
        return knightsheraldry$isLanceWeapon(player.getMainHandItem())
                || knightsheraldry$isLanceWeapon(player.getOffhandItem());
    }

    @Unique
    private static boolean knightsheraldry$isLanceWeapon(ItemStack stack) {
        return stack.getItem() instanceof Lance || stack.getItem() instanceof Estoc;
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