package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.client.entity.armor.HorseBardingFeatureRenderer;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Horse;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRenderersAddLayersHandler {
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        LivingEntityRenderer<Horse, HorseModel<Horse>> renderer = event.getRenderer(EntityType.HORSE);
        if (renderer != null) {
            renderer.addLayer(new HorseBardingFeatureRenderer(renderer, event.getEntityModels()));
        }
    }
}