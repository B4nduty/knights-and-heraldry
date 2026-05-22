package banduty.knightsheraldry;

import banduty.knightsheraldry.event.UseItemHandler;
import banduty.knightsheraldry.items.KHItemGroups;
import banduty.knightsheraldry.items.armor.deco.KHDeco;
import banduty.knightsheraldry.util.loottable.ArchaeologyLootModifier;
import banduty.knightsheraldry.util.loottable.ChestLootTableModifier;
import banduty.knightsheraldry.util.loottable.VillagerTradesModifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class KnightsHeraldryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        KnightsHeraldry.init();

        KHItemGroups.init();
        KHDeco.init();

        UseItemCallback.EVENT.register(new UseItemHandler());
        ArchaeologyLootModifier.registerArchaeologyLoot();
        ChestLootTableModifier.modifyChestLootTables();
        VillagerTradesModifier.registerCustomTrades();

    }
}
