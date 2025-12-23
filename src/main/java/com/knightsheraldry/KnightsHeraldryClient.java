package com.knightsheraldry;

import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import com.knightsheraldry.client.entity.*;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.event.ItemTooltipHandler;
import com.knightsheraldry.event.RenderFirstPersonAccessoryArmorHandler;
import com.knightsheraldry.event.RenderOverlayAndAdditionsHandler;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.armor.accessory.KHChaperon;
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import com.knightsheraldry.items.item.DyeableItems;
import com.knightsheraldry.items.item.TwoLayerDyeableItem;
import com.knightsheraldry.model.HorseBardingModel;
import com.knightsheraldry.model.ModEntityModelLayers;
import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.itemdata.ItemTooltipComponent;
import com.knightsheraldry.util.itemdata.ItemTooltipData;
import com.knightsheraldry.util.itemdata.ModModelPredicates;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.platform.Platform;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class KnightsHeraldryClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        ItemTooltipCallback.EVENT.register(new ItemTooltipHandler());
        RenderOverlayAndAdditionsEvents.EVENT.register(new RenderOverlayAndAdditionsHandler());
        RenderFirstPersonAccessoryArmorHandler.EVENT.register(new RenderFirstPersonAccessoryArmorHandler());
        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof ItemTooltipData itemData) {
                return new ItemTooltipComponent(itemData.items());
            }
            return null;
        });

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

        for (Item item : BuiltInRegistries.ITEM) {
            if (item == ModItems.SWALLOWTAIL_ARROW.get()) EntityRendererRegistry.register(ModEntities.SWALLOWTAIL_ARROW.get(), KHSwallowtailArrowEntityRenderer::new);
            if (item == ModItems.BODKIN_ARROW.get()) EntityRendererRegistry.register(ModEntities.BODKING_ARROW.get(), KHBodkinArrowEntityRenderer::new);
            if (item == ModItems.BROADHEAD_ARROW.get()) EntityRendererRegistry.register(ModEntities.BROADHEAD_ARROW.get(), KHBroadheadArrowEntityRenderer::new);
            if (item == ModItems.CLOTH_ARROW.get()) EntityRendererRegistry.register(ModEntities.CLOTH_ARROW.get(), KHClothArrowEntityRenderer::new);
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