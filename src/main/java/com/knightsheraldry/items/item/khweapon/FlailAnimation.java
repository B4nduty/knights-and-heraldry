package com.knightsheraldry.items.item.khweapon;

import net.bettercombat.api.AttackHand;
import net.bettercombat.logic.PlayerAttackHelper;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.client.Minecraft;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class FlailAnimation {
    public static PlayState clientPredicate(AnimationState<Flail> animationState) {
        RawAnimation animation;
        final var client = Minecraft.getInstance();

        if (client.player == null) return PlayState.STOP;

        AttackHand hand = PlayerAttackHelper.getCurrentAttack(client.player, ((PlayerAttackProperties)client.player).getComboCount());
        if (hand != null && client.player.getAttackStrengthScale(0) < (1.0 - hand.upswingRate())) {
            animation = RawAnimation.begin().then("attack", Animation.LoopType.LOOP);
        } else {
            animation = RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        }

        animationState.getController().setAnimation(animation);

        return PlayState.CONTINUE;
    }
}
