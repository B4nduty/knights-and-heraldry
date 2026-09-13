package banduty.knightsheraldry.trades;
import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class KHNeoForgeVillagerTrades {

    private KHNeoForgeVillagerTrades() {}

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FLETCHER) {
            KHVillagerTrades.FLETCHER_TRADES.forEach((level, trades) ->
                    event.getTrades().get(level).addAll(trades));
        }
    }
}