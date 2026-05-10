package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.platform.services.IClientPlatformHelper;
import net.bettercombat.api.AttackHand;
import net.bettercombat.logic.PlayerAttackHelper;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.client.Minecraft;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.RawAnimation;

public class FabricClientPlatformHelper implements IClientPlatformHelper {
    @Override
    public RawAnimation loadAnimation(Minecraft client) {
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
