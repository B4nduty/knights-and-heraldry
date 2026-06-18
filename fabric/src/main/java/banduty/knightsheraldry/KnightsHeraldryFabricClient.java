package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.event.ItemTooltipHandler;
import banduty.knightsheraldry.items.armor.attachment.KHChaperon;
import banduty.knightsheraldry.items.armor.attachment.KHChestplateAttachment;
import banduty.knightsheraldry.items.armor.attachment.KHCloak;
import banduty.knightsheraldry.items.armor.attachment.KHLeggingsAttachment;
import banduty.knightsheraldry.items.armor.deco.DecoItem;
import banduty.knightsheraldry.items.armor.deco.TwoLayerDyeableDeco;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.model.HorseBardingModel;
import banduty.knightsheraldry.model.ModEntityModelLayers;
import banduty.knightsheraldry.networking.KHPayloadsClient;
import banduty.knightsheraldry.util.itemdata.ModModelPredicates;
import banduty.stoneycore.items.custom.armor.underarmor.SCDyeableUnderArmor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;

import java.util.Objects;

public class KnightsHeraldryFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WarDartModel.LAYER_LOCATION, WarDartModel::createLayer);

        KHPayloadsClient.init();
        KHPayloadsClient.registerS2CReceivers();

        ItemTooltipCallback.EVENT.register(new ItemTooltipHandler());

        EntityRendererRegistry.register(KHEntities.WARDART_PROJECTILE.get(), WarDartRenderer::new);

        EntityRendererRegistry.register(KHEntities.SWALLOWTAIL_ARROW.get(), KHSwallowtailArrowEntityRenderer::new);
        EntityRendererRegistry.register(KHEntities.BODKIN_ARROW.get(), KHBodkinArrowEntityRenderer::new);
        EntityRendererRegistry.register(KHEntities.BROADHEAD_ARROW.get(), KHBroadheadArrowEntityRenderer::new);
        EntityRendererRegistry.register(KHEntities.CLOTH_ARROW.get(), KHClothArrowEntityRenderer::new);

        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof TwoLayerDyeableDeco) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    if (tintIndex == 0) return TwoLayerDyeableDeco.getColor1(stack); // top
                    if (tintIndex == 1) return TwoLayerDyeableDeco.getColor2(stack); // bottom
                    return -1;
                }, item);
            } else if (item instanceof SCDyeableUnderArmor || item instanceof KHChestplateAttachment || item instanceof KHCloak
                    || item instanceof HorseBardingArmorItem || item instanceof KHLeggingsAttachment || item instanceof KHChaperon || item instanceof DecoItem) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    if (tintIndex > 0) {
                        return -1;
                    }

                    int defaultColor = -1;
                    switch (stack.getItem()) {
                        case SCDyeableUnderArmor dyeable -> defaultColor = dyeable.getDefaultColor();
                        case DecoItem dyeable -> {
                            defaultColor = dyeable.getDefaultColor(stack);
                        }
                        case KHChestplateAttachment khChestplateAttachment ->
                                defaultColor = khChestplateAttachment.getDefaultColor();
                        case KHCloak khCloak -> defaultColor = khCloak.getDefaultColor();
                        case KHLeggingsAttachment khLeggingsAttachment ->
                                defaultColor = khLeggingsAttachment.getDefaultColor();
                        case KHChaperon khChaperon -> defaultColor = khChaperon.getDefaultColor();
                        case HorseBardingArmorItem horseBardingArmorItem ->
                                defaultColor = horseBardingArmorItem.getDefaultColor();
                        default -> {
                        }
                    }

                    return DyedItemColor.getOrDefault(stack, defaultColor);
                }, item);
            }
        }

        for (Item item : BuiltInRegistries.ITEM) {
            if (Objects.equals(BuiltInRegistries.ITEM.getKey(item).getNamespace(), KnightsHeraldry.MOD_ID)) {
                ModModelPredicates.registerModelPredicates(item);
            }
        }
    }
}
