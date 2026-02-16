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
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KnightsHeraldry.MOD_ID)
public class KnightsHeraldryForge {
    public static KHConfigs CONFIG;
    
    public KnightsHeraldryForge() {
        KnightsHeraldry.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        AutoConfig.register(KHConfigs.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(KHConfigs.class).getConfig();

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