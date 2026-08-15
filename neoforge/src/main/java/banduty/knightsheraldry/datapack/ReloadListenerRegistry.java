package banduty.knightsheraldry.datapack;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.CraftmanTradeManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ReloadListenerRegistry {
    @SubscribeEvent
    public static void onReload(AddReloadListenerEvent event) {
        event.addListener(new CraftmanTradeManager());
    }
}
