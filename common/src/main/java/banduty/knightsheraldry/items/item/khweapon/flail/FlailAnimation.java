package banduty.knightsheraldry.items.item.khweapon.flail;

import banduty.knightsheraldry.platform.ClientServices;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.client.Minecraft;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class FlailAnimation {
    public static PlayState clientPredicate(AnimationState<Flail> animationState) {
        RawAnimation animation = RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        final var client = Minecraft.getInstance();

        if (client.player == null) return PlayState.STOP;

        if (Services.PLATFORM.isModLoaded("bettercombat")) animation = ClientServices.getClientHelper().loadAnimation(client);

        animationState.getController().setAnimation(animation);

        return PlayState.CONTINUE;
    }
}
