package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.entity.ModEntities;
import banduty.knightsheraldry.event.ItemTooltipHandler;
import banduty.knightsheraldry.event.RenderFirstPersonAccessoryArmorHandler;
import banduty.knightsheraldry.event.RenderOverlayAndAdditionsHandler;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.model.HorseBardingModel;
import banduty.knightsheraldry.model.ModEntityModelLayers;
import banduty.knightsheraldry.networking.ModMessages;
import banduty.knightsheraldry.util.itemdata.ItemTooltipComponent;
import banduty.knightsheraldry.util.itemdata.ItemTooltipData;
import banduty.knightsheraldry.util.itemdata.ModModelPredicates;
import banduty.stoneycore.event.custom.RenderFirstPersonAccesoryArmorEvents;
import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class KnightsHeraldryFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WarDartModel.LAYER_LOCATION, WarDartModel::createLayer);

        ModMessages.registerS2CPackets();

        RenderFirstPersonAccesoryArmorEvents.EVENT.register(new RenderFirstPersonAccessoryArmorHandler());
        RenderOverlayAndAdditionsEvents.EVENT.register(new RenderOverlayAndAdditionsHandler());
        ItemTooltipCallback.EVENT.register(new ItemTooltipHandler());
        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof ItemTooltipData itemData) {
                return new ItemTooltipComponent(itemData.items());
            }
            return null;
        });

        EntityRendererRegistry.register(ModEntities.WARDART_PROJECTILE, WarDartRenderer::new);
        EntityRendererRegistry.register(ModEntities.SWALLOWTAIL_ARROW, KHSwallowtailArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BODKIN_ARROW, KHBodkinArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BROADHEAD_ARROW, KHBroadheadArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CLOTH_ARROW, KHClothArrowEntityRenderer::new);
        
        Item[] items = new Item[]{
                ModItems.WOODEN_LANCE, ModItems.QUILTED_COIF, ModItems.GAMBESON, ModItems.GAMBESON_BREECHES,
                ModItems.GAMBESON_BOOTS,
                ModItems.BRIGANDINE_SPAULDERS, ModItems.BRIGANDINE_SPAULDERS_BESAGEWS,
                ModItems.DARK_BRIGANDINE_SPAULDERS, ModItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS,
                ModItems.GOLDEN_BRIGANDINE_SPAULDERS, ModItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS,
                ModItems.BRIGANDINE, ModItems.DARK_BRIGANDINE, ModItems.GOLDEN_BRIGANDINE,
                ModItems.BRIG_BREASTPLATE, ModItems.DARK_BRIG_BREASTPLATE, ModItems.GOLDEN_BRIG_BREASTPLATE,
                ModItems.BRIG_BREASTPLATE_TASSETS, ModItems.DARK_BRIG_BREASTPLATE_TASSETS, ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS,
                ModItems.BRIGANDINE_HARNESS, ModItems.DARK_BRIGANDINE_HARNESS, ModItems.GOLDEN_BRIGANDINE_HARNESS,
                ModItems.BRIGANDINE_CUISSES, ModItems.DARK_BRIGANDINE_CUISSES, ModItems.GOLDEN_BRIGANDINE_CUISSES,
                ModItems.CLOAK, ModItems.TORN_CLOAK, ModItems.HOOD, ModItems.TORN_HOOD,
                ModItems.JESTER_HOOD, ModItems.HELMET_HOOD, ModItems.HELMET_TORN_HOOD,
                ModItems.HORSE_BARDING, ModItems.DARK_HORSE_BARDING, ModItems.GOLDEN_HORSE_BARDING,
                ModItems.PLUME, ModItems.TRI_PLUME, ModItems.FLUFFY_PLUME,
                ModItems.CHAPERON, ModItems.GILDED_CHAPERON, ModItems.TORSE
        };
        for (Item item : items) {
            if (item instanceof TwoLayerDyeableItem twoLayerItem) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    if (tintIndex == 0) return twoLayerItem.getColor1(stack); // top
                    if (tintIndex == 1) return twoLayerItem.getColor2(stack); // bottom
                    return -1;
                }, item);
            }
            if (item instanceof DyeableLeatherItem dyeableItems)
                ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : dyeableItems.getColor(stack), item);

            ModModelPredicates.registerModelPredicates(item);
        }
        ItemProperties.register(
                ModItems.WARDART,
                new ResourceLocation("throwing"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }

                    return entity.isUsingItem()
                            && entity.getUseItem() == stack
                            ? 1.0F
                            : 0.0F;
                }
        );
    }
}
