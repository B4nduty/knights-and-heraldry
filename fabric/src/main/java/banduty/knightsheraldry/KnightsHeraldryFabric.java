package banduty.knightsheraldry;

import banduty.knightsheraldry.event.CanEquipHandler;
import banduty.knightsheraldry.event.StartTickHandler;
import banduty.knightsheraldry.event.UseItemHandler;
import banduty.knightsheraldry.items.KHItemGroups;
import banduty.knightsheraldry.util.itemdata.KHHelmetDeco;
import banduty.knightsheraldry.util.loottable.ArchaeologyLootModifier;
import banduty.knightsheraldry.util.loottable.ChestLootTableModifier;
import banduty.knightsheraldry.util.loottable.VillagerTradesModifier;
import io.wispforest.accessories.api.events.CanEquipCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class KnightsHeraldryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        KnightsHeraldry.init();

        KHItemGroups.init();
        KHHelmetDeco.init();

        CanEquipCallback.EVENT.register(new CanEquipHandler());
        ServerTickEvents.START_SERVER_TICK.register(new StartTickHandler());
        UseItemCallback.EVENT.register(new UseItemHandler());
        ArchaeologyLootModifier.registerArchaeologyLoot();
        ChestLootTableModifier.modifyChestLootTables();
        VillagerTradesModifier.registerCustomTrades();

    }
}
