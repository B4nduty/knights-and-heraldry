package banduty.knightsheraldry.items.item.khweapon;

import net.bettercombat.api.AttackHand;
import net.bettercombat.logic.PlayerAttackHelper;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.client.Minecraft;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.RawAnimation;

public class FlailAnimationBC {
    public static RawAnimation loadAnimation(Minecraft client) {
        AttackHand hand = PlayerAttackHelper.getCurrentAttack(client.player, ((PlayerAttackProperties)client.player).getComboCount());
        RawAnimation animation;
        if (hand != null && client.player.getAttackStrengthScale(0) < (1.0 - hand.upswingRate())) {
            animation = RawAnimation.begin().then("attack", Animation.LoopType.LOOP);
        } else {
            animation = RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        }
        return animation;
    }
}
