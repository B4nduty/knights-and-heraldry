package banduty.knightsheraldry.trades;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;

public final class KHFabricVillagerTrades {

    private KHFabricVillagerTrades() {}

    public static void register() {
        KHVillagerTrades.FLETCHER_TRADES.forEach((level, trades) ->
                TradeOfferHelper.registerVillagerOffers(VillagerProfession.FLETCHER, level,
                        factories -> factories.addAll(trades)));
    }
}