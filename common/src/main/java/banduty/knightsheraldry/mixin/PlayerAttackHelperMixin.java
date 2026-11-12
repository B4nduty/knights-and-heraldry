package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.item.khweapon.Estoc;
import banduty.knightsheraldry.util.OffhandUtil;
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
        if (knightsheraldry$mountedEstoc(player) || OffhandUtil.shouldHideOffhand(player)) cir.setReturnValue(true);
    }

    // Mapping-agnostic selector: Player is class_1657 on Fabric but Player on NeoForge, and remap=false
    // means the descriptor isn't translated. Matches only the single-arg overload isDualWielding(Player).
    @Inject(method = "name=/^isDualWielding$/ desc=/^\\(L[^;]+;\\)Z$/", at = @At("HEAD"), cancellable = true)
    private static void kh$estocNoDual(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (knightsheraldry$mountedEstoc(player) || OffhandUtil.shouldHideOffhand(player)) {
            cir.setReturnValue(false);
        }
    }

    @Unique
    private static boolean knightsheraldry$mountedEstoc(Player player) {
        return player.getMainHandItem().getItem() instanceof Estoc
                && player.getVehicle() instanceof LivingEntity;
    }

}