package com.knightsheraldry;

import com.knightsheraldry.client.UnderArmourRenderer;
import com.knightsheraldry.client.entity.*;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.event.AttackCancelHandler;
import com.knightsheraldry.event.ItemTooltipHandler;
import com.knightsheraldry.event.KeyInputHandler;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.armor.KHUnderArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.items.custom.item.WoodenLance;
import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.itemdata.ModModelPredicates;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.item.DyeableItem;

public class KnightsHeraldryClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        ClientPreAttackCallback.EVENT.register(new AttackCancelHandler());
        KeyInputHandler.register();

        EntityRendererRegistry.register(ModEntities.WARDART_PROJECTILE, WarDartRenderer::new);
        EntityRendererRegistry.register(ModEntities.KH_BULLET, KHBulletEntityRenderer::new);
        ModItems.items.forEach(item -> {
            if (item == ModItems.SWALLOWTAIL_ARROW) EntityRendererRegistry.register(ModEntities.KH_ARROW, KHSwallowtailArrowEntityRenderer::new);
            if (item == ModItems.BODKIN_ARROW) EntityRendererRegistry.register(ModEntities.KH_ARROW, KHBodkinArrowEntityRenderer::new);
            if (item == ModItems.BROADHEAD_ARROW) EntityRendererRegistry.register(ModEntities.KH_ARROW, KHBroadheadArrowEntityRenderer::new);
            if (item == ModItems.CLOTH_ARROW) EntityRendererRegistry.register(ModEntities.KH_ARROW, KHClothArrowEntityRenderer::new);
        });

        ItemTooltipCallback.EVENT.register(new ItemTooltipHandler());

        ModItems.items.forEach(ModModelPredicates::registerModelPredicates);

        ModItems.items.forEach(item -> {
            if ((item instanceof KHTrinketsItem khTrinketsItem && khTrinketsItem.isDyeable())
                    || (item instanceof KHUnderArmorItem khArmorItem && khArmorItem.isDyeable()) || item instanceof WoodenLance) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), item);
            }
            if (item instanceof KHTrinketsItem) {
                TrinketRendererRegistry.registerRenderer(item, (TrinketRenderer) item);
            }
            if (item instanceof KHUnderArmorItem khArmorItem && khArmorItem.getPath() != null) {
                ArmorRenderer.register(new UnderArmourRenderer(), item);
            }
        });
    }
}