package com.knightsheraldry;

import banduty.stoneycore.event.custom.RenderFirstPersonTrinketsArmorEvents;
import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import com.knightsheraldry.client.entity.*;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.event.RenderFirstPersonTrinketsArmorHandler;
import com.knightsheraldry.event.RenderOverlayAndAdditionsHandler;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import com.knightsheraldry.items.armor.trinkets.KHChaperon;
import com.knightsheraldry.items.item.DyeableItems;
import com.knightsheraldry.model.HorseBardingModel;
import com.knightsheraldry.model.ModEntityModelLayers;
import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.itemdata.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class KnightsHeraldryClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        RenderOverlayAndAdditionsEvents.EVENT.register(new RenderOverlayAndAdditionsHandler());
        RenderFirstPersonTrinketsArmorEvents.EVENT.register(new RenderFirstPersonTrinketsArmorHandler());
        EntityRendererRegistry.register(ModEntities.WARDART_PROJECTILE, WarDartRenderer::new);
        ModItems.items.forEach(item -> {
            if (item == ModItems.SWALLOWTAIL_ARROW) EntityRendererRegistry.register(banduty.stoneycore.entity.ModEntities.SC_ARROW, KHSwallowtailArrowEntityRenderer::new);
            if (item == ModItems.BODKIN_ARROW) EntityRendererRegistry.register(banduty.stoneycore.entity.ModEntities.SC_ARROW, KHBodkinArrowEntityRenderer::new);
            if (item == ModItems.BROADHEAD_ARROW) EntityRendererRegistry.register(banduty.stoneycore.entity.ModEntities.SC_ARROW, KHBroadheadArrowEntityRenderer::new);
            if (item == ModItems.CLOTH_ARROW) EntityRendererRegistry.register(banduty.stoneycore.entity.ModEntities.SC_ARROW, KHClothArrowEntityRenderer::new);
            if (item instanceof DyeableItems dyeableItems) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : dyeableItems.getColor(stack), item);
            if (item instanceof HorseBardingArmorItem horseBardingArmorItem) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : horseBardingArmorItem.getColor(stack), item);
            if (item instanceof KHChaperon khChaperon) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : khChaperon.getColor(stack), item);
        });

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);

        ModItems.items.forEach(ModModelPredicates::registerModelPredicates);
    }
}