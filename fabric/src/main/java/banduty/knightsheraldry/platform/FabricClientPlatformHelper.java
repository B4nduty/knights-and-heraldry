package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.platform.services.IClientPlatformHelper;
import net.bettercombat.api.AttackHand;
import net.bettercombat.logic.PlayerAttackHelper;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.RawAnimation;

public class FabricClientPlatformHelper implements IClientPlatformHelper {
    @Override
    public RawAnimation loadAnimation(LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player player)) return RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        AttackHand hand = PlayerAttackHelper.getCurrentAttack(player, ((PlayerAttackProperties)player).getComboCount());
        RawAnimation animation;
        if (hand != null && player.getAttackStrengthScale(0) < (1.0 - hand.upswingRate())) {
            animation = RawAnimation.begin().then("attack", Animation.LoopType.LOOP);
        } else {
            animation = RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        }
        return animation;
    }
}
