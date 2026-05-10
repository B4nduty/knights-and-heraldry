package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.event.ItemTooltipHandler;
import banduty.knightsheraldry.event.RenderFirstPersonAccessoryArmorHandler;
import banduty.knightsheraldry.event.RenderOverlayAndAdditionsHandler;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.item.DyeableItems;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.model.HorseBardingModel;
import banduty.knightsheraldry.model.ModEntityModelLayers;
import banduty.knightsheraldry.networking.KHPayloadsClient;
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
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class KnightsHeraldryFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);

        KHPayloadsClient.registerS2CReceivers();

        RenderFirstPersonAccesoryArmorEvents.EVENT.register(new RenderFirstPersonAccessoryArmorHandler());
        RenderOverlayAndAdditionsEvents.EVENT.register(new RenderOverlayAndAdditionsHandler());
        ItemTooltipCallback.EVENT.register(new ItemTooltipHandler());
        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof ItemTooltipData(List<ItemStack> items)) {
                return new ItemTooltipComponent(items);
            }
            return null;
        });

        EntityRendererRegistry.register(KHEntities.WARDART_PROJECTILE.get(), WarDartRenderer::new);

        for (Item item : BuiltInRegistries.ITEM) {
            if (item == KHItems.SWALLOWTAIL_ARROW)
                EntityRendererRegistry.register(KHEntities.SWALLOWTAIL_ARROW.get(), KHSwallowtailArrowEntityRenderer::new);
            if (item == KHItems.BODKIN_ARROW)
                EntityRendererRegistry.register(KHEntities.BODKIN_ARROW.get(), KHBodkinArrowEntityRenderer::new);
            if (item == KHItems.BROADHEAD_ARROW)
                EntityRendererRegistry.register(KHEntities.BROADHEAD_ARROW.get(), KHBroadheadArrowEntityRenderer::new);
            if (item == KHItems.CLOTH_ARROW)
                EntityRendererRegistry.register(KHEntities.CLOTH_ARROW.get(), KHClothArrowEntityRenderer::new);
            if (item instanceof TwoLayerDyeableItem twoLayerItem) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    if (tintIndex == 0) return twoLayerItem.getColor1(stack); // top
                    if (tintIndex == 1) return twoLayerItem.getColor2(stack); // bottom
                    return -1;
                }, item);
            }
            if (item instanceof DyeableItems dyeableItems)
                ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : DyeableItems.getColor(stack), item);
            if (Objects.equals(BuiltInRegistries.ITEM.getKey(item).getNamespace(), "knightsheraldry")) {
                ModModelPredicates.registerModelPredicates(item);
            }
        }
    }
}
