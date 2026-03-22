package banduty.knightsheraldry;

import banduty.knightsheraldry.config.KHConfigs;
import banduty.knightsheraldry.effect.ModEffects;
import banduty.knightsheraldry.entity.ModEntities;
import banduty.knightsheraldry.event.CanEquipHandler;
import banduty.knightsheraldry.items.ModItemGroups;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.recipes.ModRecipes;
import banduty.knightsheraldry.sounds.ModSounds;
import banduty.knightsheraldry.util.itemdata.KHHelmetDeco;
import banduty.stoneycore.networking.ModMessages;
import io.wispforest.accessories.api.events.CanEquipCallback;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(KnightsHeraldry.MOD_ID)
public class KnightsHeraldryForge {

    public KnightsHeraldryForge() {
        KnightsHeraldry.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        KHConfigs.loadConfig(KHConfigs.SPEC, FMLPaths.GAMEDIR.get().resolve(FMLPaths.CONFIGDIR.get()).resolve(KnightsHeraldry.MOD_ID + "-common.toml"));


        ModEffects.register(modEventBus);
        ModEntities.register(modEventBus);
        ModItems.registerItems(modEventBus);
        ModItemGroups.register(modEventBus);
        ModMessages.register();
        ModSounds.register(modEventBus);
        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        CanEquipCallback.EVENT.register(new CanEquipHandler());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(KHHelmetDeco::registerHelmetDeco);
    }
}