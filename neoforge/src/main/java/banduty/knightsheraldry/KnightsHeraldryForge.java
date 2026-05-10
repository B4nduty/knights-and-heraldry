package banduty.knightsheraldry;

import banduty.knightsheraldry.config.KHConfigs;
import banduty.knightsheraldry.effect.KHEffects;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.event.CanEquipHandler;
import banduty.knightsheraldry.items.ModItemGroups;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.networking.ModMessages;
import banduty.knightsheraldry.platform.NeoForgePlatformHelper;
import banduty.knightsheraldry.recipes.KHRecipes;
import banduty.knightsheraldry.sounds.KHSounds;
import banduty.knightsheraldry.util.itemdata.KHHelmetDeco;
import banduty.knightsheraldry.util.loottable.ModLootTable;
import io.wispforest.accessories.api.events.CanEquipCallback;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(KnightsHeraldry.MOD_ID)
public class KnightsHeraldryForge {

    public KnightsHeraldryForge() {
        KnightsHeraldry.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        KHConfigs.loadConfig(KHConfigs.SPEC, FMLPaths.GAMEDIR.get().resolve(FMLPaths.CONFIGDIR.get()).resolve(KnightsHeraldry.MOD_ID + "-common.toml"));
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KHConfigs.SPEC);

        KHEffects.register(modEventBus);
        KHEntities.register(modEventBus);
        KHItems.registerItems(modEventBus);
        ModItemGroups.register(modEventBus);
        KHSounds.register(modEventBus);
        KHRecipes.register(modEventBus);
        ModLootTable.registerLootTables(modEventBus);

        NeoForgePlatformHelper.registerRegistries(modEventBus);

        modEventBus.addListener(this::commonSetup);

        CanEquipCallback.EVENT.register(new CanEquipHandler());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        event.enqueueWork(KHHelmetDeco::registerHelmetDeco);
    }
}