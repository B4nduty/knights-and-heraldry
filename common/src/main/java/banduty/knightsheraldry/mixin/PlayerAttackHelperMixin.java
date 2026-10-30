package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.item.khweapon.Estoc;
import banduty.stoneycore.combat.melee.ShieldUtil;
import net.bettercombat.logic.PlayerAttackHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerAttackHelper.class, remap = false)
public class PlayerAttackHelperMixin {

    @Inject(method = "isTwoHandedWielding", at = @At("HEAD"), cancellable = true)
    private static void kh$estocTwoHanded(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (knightsheraldry$mountedEstoc(player) || knightsheraldry$hideOffHand(player)) cir.setReturnValue(true);
    }

    @Inject(method = "isDualWielding(Lnet/minecraft/world/entity/player/Player;)Z", at = @At("HEAD"), cancellable = true)
    private static void kh$estocNoDual(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (knightsheraldry$mountedEstoc(player) || knightsheraldry$hideOffHand(player)) cir.setReturnValue(false);
    }

    @Unique
    private static boolean knightsheraldry$mountedEstoc(Player player) {
        return player.getMainHandItem().getItem() instanceof Estoc
                && player.getVehicle() instanceof LivingEntity;
    }

    @Unique
    private static boolean knightsheraldry$hideOffHand(LivingEntity livingEntity) {
        return !ShieldUtil.canBlock(livingEntity.getOffhandItem()) && !(livingEntity.getMainHandItem().getItem() instanceof Estoc ||
                livingEntity.getMainHandItem().getItem() == KHItems.BASTARD_SWORD.get() ||
                livingEntity.getMainHandItem().getItem() == KHItems.GILDED_BASTARD_SWORD.get());
    }
}