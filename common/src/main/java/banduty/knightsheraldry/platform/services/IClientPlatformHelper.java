package banduty.knightsheraldry.platform.services;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animation.RawAnimation;

public interface IClientPlatformHelper {
    RawAnimation loadAnimation(LivingEntity livingEntity);
}