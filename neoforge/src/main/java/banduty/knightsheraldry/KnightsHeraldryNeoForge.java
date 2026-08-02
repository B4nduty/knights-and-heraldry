package banduty.knightsheraldry;

import banduty.knightsheraldry.config.KHConfigs;
import banduty.knightsheraldry.entity.custom.CraftmanTradeManager;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.platform.NeoForgePlatformHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(KnightsHeraldry.MOD_ID)
public class KnightsHeraldryNeoForge {

    public KnightsHeraldryNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        KnightsHeraldry.init();

        modContainer.registerConfig(ModConfig.Type.COMMON, KHConfigs.SPEC);

        NeoForgePlatformHelper.registerRegistries(modEventBus);
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(KHItems.CRAFTMAN_SPAWN_EGG.get());
        }
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new CraftmanTradeManager());
    }
}