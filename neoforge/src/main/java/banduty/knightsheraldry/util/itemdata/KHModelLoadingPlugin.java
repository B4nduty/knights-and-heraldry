package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(
        modid = KnightsHeraldry.MOD_ID,
        value = Dist.CLIENT
)
public final class KHModelLoadingPlugin {

    @SubscribeEvent
    public static void onRegisterAdditional(ModelEvent.RegisterAdditional event) {
        for (ResourceLocation model : BannerModelScanner.collect(
                Minecraft.getInstance().getResourceManager(),
                Services.PLATFORM.getConfig().getBannerModelItemPaths()
        )) {
            event.register(ModelResourceLocation.standalone(model));
        }
    }
}
