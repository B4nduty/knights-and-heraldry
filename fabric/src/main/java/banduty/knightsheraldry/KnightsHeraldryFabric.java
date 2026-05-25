package banduty.knightsheraldry;

import banduty.knightsheraldry.config.KHConfigs;
import banduty.knightsheraldry.event.UseItemHandler;
import banduty.knightsheraldry.items.armor.deco.KHDeco;
import banduty.knightsheraldry.util.loottable.ArchaeologyLootModifier;
import banduty.knightsheraldry.util.loottable.ChestLootTableModifier;
import banduty.knightsheraldry.util.loottable.VillagerTradesModifier;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class KnightsHeraldryFabric implements ModInitializer {
    public static KHConfigs CONFIG;
    @Override
    public void onInitialize() {
        KnightsHeraldry.init();

        KHDeco.init();

        UseItemCallback.EVENT.register(new UseItemHandler());
        ArchaeologyLootModifier.registerArchaeologyLoot();
        ChestLootTableModifier.modifyChestLootTables();
        VillagerTradesModifier.registerCustomTrades();

        AutoConfig.register(KHConfigs.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(KHConfigs.class).getConfig();
    }
}
