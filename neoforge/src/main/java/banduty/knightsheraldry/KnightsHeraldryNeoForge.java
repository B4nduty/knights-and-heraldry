package banduty.knightsheraldry;

import banduty.knightsheraldry.config.KHConfigs;
import banduty.knightsheraldry.event.CanEquipHandler;
import banduty.knightsheraldry.items.ModItemGroups;
import banduty.knightsheraldry.platform.NeoForgePlatformHelper;
import banduty.knightsheraldry.util.loottable.ModLootTable;
import io.wispforest.accessories.api.events.CanEquipCallback;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(KnightsHeraldry.MOD_ID)
public class KnightsHeraldryNeoForge {

    public KnightsHeraldryNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        KnightsHeraldry.init();

        modContainer.registerConfig(ModConfig.Type.COMMON, KHConfigs.SPEC);

        ModItemGroups.register();
        ModLootTable.registerLootTables();

        NeoForgePlatformHelper.registerRegistries(modEventBus);

        CanEquipCallback.EVENT.register(new CanEquipHandler());
    }
}