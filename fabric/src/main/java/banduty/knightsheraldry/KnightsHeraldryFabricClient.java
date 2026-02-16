package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.entity.ModEntities;
import banduty.knightsheraldry.event.ItemTooltipHandler;
import banduty.knightsheraldry.event.RenderFirstPersonAccessoryArmorHandler;
import banduty.knightsheraldry.event.RenderOverlayAndAdditionsHandler;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.items.armor.accessory.KHChaperon;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.DyeableItems;
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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class KnightsHeraldryFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);

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

        for (Item item : BuiltInRegistries.ITEM) {
            if (item == ModItems.SWALLOWTAIL_ARROW) EntityRendererRegistry.register(ModEntities.SWALLOWTAIL_ARROW, KHSwallowtailArrowEntityRenderer::new);
            if (item == ModItems.BODKIN_ARROW) EntityRendererRegistry.register(ModEntities.BODKIN_ARROW, KHBodkinArrowEntityRenderer::new);
            if (item == ModItems.BROADHEAD_ARROW) EntityRendererRegistry.register(ModEntities.BROADHEAD_ARROW, KHBroadheadArrowEntityRenderer::new);
            if (item == ModItems.CLOTH_ARROW) EntityRendererRegistry.register(ModEntities.CLOTH_ARROW, KHClothArrowEntityRenderer::new);
            if (item instanceof TwoLayerDyeableItem twoLayerItem) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    if (tintIndex == 0) return twoLayerItem.getColor1(stack); // top
                    if (tintIndex == 1) return twoLayerItem.getColor2(stack); // bottom
                    return -1;
                }, item);
            }
            if (item instanceof DyeableItems dyeableItems) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : dyeableItems.getColor(stack), item);
            if (item instanceof HorseBardingArmorItem horseBardingArmorItem) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : horseBardingArmorItem.getColor(stack), item);
            if (item instanceof KHChaperon khChaperon) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : khChaperon.getColor(stack), item);
            if (Objects.equals(BuiltInRegistries.ITEM.getKey(item).getNamespace(), "knightsheraldry")) {
                ModModelPredicates.registerModelPredicates(item);
            }
        }
    }
}
