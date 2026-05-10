package banduty.knightsheraldry.platform.services;

import net.minecraft.client.Minecraft;
import software.bernie.geckolib.animation.RawAnimation;

public interface IClientPlatformHelper {
    RawAnimation loadAnimation(Minecraft client);
}