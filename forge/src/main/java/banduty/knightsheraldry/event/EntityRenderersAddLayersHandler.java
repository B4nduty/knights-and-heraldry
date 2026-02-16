package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.client.entity.armor.HorseBardingFeatureRenderer;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EntityRenderersAddLayersHandler {
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        LivingEntityRenderer<Horse, HorseModel<Horse>> renderer = event.getRenderer(EntityType.HORSE);
        if (renderer != null) {
            renderer.addLayer(new HorseBardingFeatureRenderer(renderer, event.getEntityModels()));
        }
    }
}