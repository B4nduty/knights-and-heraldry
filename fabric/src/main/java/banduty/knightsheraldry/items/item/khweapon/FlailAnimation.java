package banduty.knightsheraldry.items.item.khweapon;

import banduty.knightsheraldry.platform.Services;
import net.minecraft.client.Minecraft;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class FlailAnimation {
    public static PlayState clientPredicate(AnimationState<Flail> animationState) {
        RawAnimation animation = RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        final var client = Minecraft.getInstance();

        if (client.player == null) return PlayState.STOP;

        if (Services.PLATFORM.isModLoaded("bettercombat")) animation = FlailAnimationBC.loadAnimation(client);

        animationState.getController().setAnimation(animation);

        return PlayState.CONTINUE;
    }
}
