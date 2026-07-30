package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.entity.custom.Craftman;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributesHandler {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        KnightsHeraldry.LOG.info("Registering Entity Attributes for {}", KnightsHeraldry.MOD_ID);

        event.put(KHEntities.CRAFTMAN.get(), Craftman.createAttributes().build());
    }
}