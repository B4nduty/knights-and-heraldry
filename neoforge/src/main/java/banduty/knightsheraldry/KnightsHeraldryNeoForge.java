package banduty.knightsheraldry;

import banduty.knightsheraldry.config.KHConfigs;
import banduty.knightsheraldry.items.KHItemGroups;
import banduty.knightsheraldry.platform.NeoForgePlatformHelper;
import banduty.knightsheraldry.items.armor.deco.KHDeco;
import banduty.knightsheraldry.util.loottable.ModLootTable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(KnightsHeraldry.MOD_ID)
public class KnightsHeraldryNeoForge {

    public KnightsHeraldryNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        KnightsHeraldry.init();

        modContainer.registerConfig(ModConfig.Type.COMMON, KHConfigs.SPEC);

        KHItemGroups.register(modEventBus);
        ModLootTable.registerLootTables(modEventBus);

        modEventBus.addListener(this::commonSetup);

        NeoForgePlatformHelper.registerRegistries(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(KHDeco::init);
    }
}