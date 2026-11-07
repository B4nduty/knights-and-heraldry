package banduty.knightsheraldry;

import banduty.knightsheraldry.config.KHConfigs;
import banduty.knightsheraldry.entity.KHEntityDataSerializers;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.platform.NeoForgePlatformHelper;
import net.minecraft.world.item.CreativeModeTabs;
import banduty.knightsheraldry.items.KHItemAliases;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(KnightsHeraldry.MOD_ID)
public class KnightsHeraldryNeoForge {

    public KnightsHeraldryNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        KnightsHeraldry.init();
        // Old item ids (e.g. the removed helmet hoods) load as their replacement item
        KHItemAliases.ALIASES.forEach(BuiltInRegistries.ITEM::addAlias);

        modContainer.registerConfig(ModConfig.Type.COMMON, KHConfigs.SPEC);

        NeoForgePlatformHelper.registerRegistries(modEventBus);
        KHEntityDataSerializers.register(modEventBus);

        modEventBus.addListener(KnightsHeraldryNeoForge::buildContents);
    }

    private static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(KHItems.CRAFTMAN_SPAWN_EGG.get());
        }
    }

}