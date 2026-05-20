package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.event.ItemTooltipHandler;
import banduty.knightsheraldry.event.RenderFirstPersonAccessoryArmorHandler;
import banduty.knightsheraldry.event.RenderOverlayAndAdditionsHandler;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.model.HorseBardingModel;
import banduty.knightsheraldry.model.ModEntityModelLayers;
import banduty.knightsheraldry.networking.KHPayloadsClient;
import banduty.knightsheraldry.util.itemdata.ItemTooltipComponent;
import banduty.knightsheraldry.util.itemdata.ItemTooltipData;
import banduty.knightsheraldry.util.itemdata.ModModelPredicates;
import banduty.stoneycore.event.custom.RenderFirstPersonAccesoryArmorEvents;
import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import banduty.stoneycore.items.custom.armor.underarmor.SCDyeableUnderArmor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

import java.util.List;
import java.util.Objects;

public class KnightsHeraldryFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);

        KHPayloadsClient.init();
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

        EntityRendererRegistry.register(KHEntities.SWALLOWTAIL_ARROW.get(), KHSwallowtailArrowEntityRenderer::new);
        EntityRendererRegistry.register(KHEntities.BODKIN_ARROW.get(), KHBodkinArrowEntityRenderer::new);
        EntityRendererRegistry.register(KHEntities.BROADHEAD_ARROW.get(), KHBroadheadArrowEntityRenderer::new);
        EntityRendererRegistry.register(KHEntities.CLOTH_ARROW.get(), KHClothArrowEntityRenderer::new);


        Item[] items = new Item[]{
                KHItems.WOODEN_LANCE.get(), KHItems.QUILTED_COIF.get(), KHItems.GAMBESON.get(), KHItems.GAMBESON_BREECHES.get(),
                KHItems.GAMBESON_BOOTS.get(), KHItems.BRIGANDINE_SPAULDERS.get(), KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(),
                KHItems.DARK_BRIGANDINE_SPAULDERS.get(), KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(),
                KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get(),
                KHItems.BRIGANDINE.get(), KHItems.DARK_BRIGANDINE.get(), KHItems.GOLDEN_BRIGANDINE.get(),
                KHItems.BRIG_BREASTPLATE.get(), KHItems.DARK_BRIG_BREASTPLATE.get(), KHItems.GOLDEN_BRIG_BREASTPLATE.get(),
                KHItems.BRIGANDINE_HARNESS.get(), KHItems.DARK_BRIGANDINE_HARNESS.get(), KHItems.GOLDEN_BRIGANDINE_HARNESS.get(),
                KHItems.BRIGANDINE_CUISSES.get(), KHItems.DARK_BRIGANDINE_CUISSES.get(), KHItems.GOLDEN_BRIGANDINE_CUISSES.get(),
                KHItems.CLOAK.get(), KHItems.TORN_CLOAK.get(), KHItems.HOOD.get(), KHItems.TORN_HOOD.get(),
                KHItems.JESTER_HOOD.get(), KHItems.HELMET_HOOD.get(), KHItems.HELMET_TORN_HOOD.get(),
                KHItems.HORSE_BARDING.get(), KHItems.DARK_HORSE_BARDING.get(), KHItems.GOLDEN_HORSE_BARDING.get(),
                KHItems.PLUME.get(), KHItems.TRI_PLUME.get(), KHItems.FLUFFY_PLUME.get(),
                KHItems.CHAPERON.get(), KHItems.GILDED_CHAPERON.get(), KHItems.TORSE.get()
        };
        for (Item item : items) {
            if (item instanceof SCDyeableUnderArmor || item instanceof SCAccessoryItem || item instanceof HorseBardingArmorItem) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    if (tintIndex > 0) {
                        return -1;
                    }

                    int defaultColor = -1;
                    switch (stack.getItem()) {
                        case SCDyeableUnderArmor dyeable -> defaultColor = dyeable.getDefaultColor();
                        case SCAccessoryItem accessory -> defaultColor = accessory.getDefaultColor();
                        case HorseBardingArmorItem horseBardingArmorItem -> defaultColor = horseBardingArmorItem.getDefaultColor();
                        default -> {}
                    }

                    return DyedItemColor.getOrDefault(stack, defaultColor);
                }, item);
            } else if (item instanceof TwoLayerDyeableItem) {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    if (tintIndex == 0) return TwoLayerDyeableItem.getColor1(stack); // top
                    if (tintIndex == 1) return TwoLayerDyeableItem.getColor2(stack); // bottom
                    return -1;
                }, item);
            } else {
                ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : DyedItemColor.getOrDefault(stack, -1), item);
            }
        }

        for (Item item : BuiltInRegistries.ITEM) {
            if (Objects.equals(BuiltInRegistries.ITEM.getKey(item).getNamespace(), KnightsHeraldry.MOD_ID)) {
                ModModelPredicates.registerModelPredicates(item);
            }
        }
    }
}
