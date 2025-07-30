package com.knightsheraldry;

import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import com.knightsheraldry.client.entity.*;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.event.RenderFirstPersonAccessoryArmorHandler;
import com.knightsheraldry.event.RenderOverlayAndAdditionsHandler;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.armor.accessory.KHChaperon;
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import com.knightsheraldry.items.item.DyeableItems;
import com.knightsheraldry.model.HorseBardingModel;
import com.knightsheraldry.model.ModEntityModelLayers;
import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.itemdata.ModModelPredicates;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.platform.Platform;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.Objects;

public class KnightsHeraldryClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        RenderOverlayAndAdditionsEvents.EVENT.register(new RenderOverlayAndAdditionsHandler());
        RenderFirstPersonAccessoryArmorHandler.EVENT.register(new RenderFirstPersonAccessoryArmorHandler());

        if (Platform.isForge()) {
            ClientLifecycleEvent.CLIENT_SETUP.register(minecraftClient -> {
                registerClient();
            });
        } else {
            registerClient();
        }

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);
    }

    private void registerClient() {
        EntityRendererRegistry.register(ModEntities.WARDART_PROJECTILE.get(), WarDartRenderer::new);

        for (Item item : Registries.ITEM) {
            if (item == ModItems.SWALLOWTAIL_ARROW.get()) EntityRendererRegistry.register(ModEntities.SWALLOWTAIL_ARROW.get(), KHSwallowtailArrowEntityRenderer::new);
            if (item == ModItems.BODKIN_ARROW.get()) EntityRendererRegistry.register(ModEntities.BODKING_ARROW.get(), KHBodkinArrowEntityRenderer::new);
            if (item == ModItems.BROADHEAD_ARROW.get()) EntityRendererRegistry.register(ModEntities.BROADHEAD_ARROW.get(), KHBroadheadArrowEntityRenderer::new);
            if (item == ModItems.CLOTH_ARROW.get()) EntityRendererRegistry.register(ModEntities.CLOTH_ARROW.get(), KHClothArrowEntityRenderer::new);
            if (item instanceof DyeableItems dyeableItems) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : dyeableItems.getColor(stack), item);
            if (item instanceof HorseBardingArmorItem horseBardingArmorItem) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : horseBardingArmorItem.getColor(stack), item);
            if (item instanceof KHChaperon khChaperon) ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    tintIndex > 0 ? -1 : khChaperon.getColor(stack), item);
            if (Objects.equals(Registries.ITEM.getId(item).getNamespace(), "knightsheraldry")) {
                ModModelPredicates.registerModelPredicates(item);
            }
        }
    }
}