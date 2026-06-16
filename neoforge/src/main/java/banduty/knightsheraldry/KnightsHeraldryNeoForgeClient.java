package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.client.item.SurcoatWithBannerModel;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.items.armor.attachment.KHChaperon;
import banduty.knightsheraldry.items.armor.attachment.KHChestplateAttachment;
import banduty.knightsheraldry.items.armor.attachment.KHCloak;
import banduty.knightsheraldry.items.armor.attachment.KHLeggingsAttachment;
import banduty.knightsheraldry.items.armor.deco.TwoLayerDyeableDeco;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.model.HorseBardingModel;
import banduty.knightsheraldry.model.ModEntityModelLayers;
import banduty.knightsheraldry.util.itemdata.ModModelPredicates;
import banduty.stoneycore.items.custom.armor.underarmor.SCDyeableUnderArmor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, value = Dist.CLIENT)
public class KnightsHeraldryNeoForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (Item item : BuiltInRegistries.ITEM) {
                if (BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(KnightsHeraldry.MOD_ID)) {
                    ModModelPredicates.registerModelPredicates(item);
                }
            }
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(KHEntities.WARDART_PROJECTILE.get(), WarDartRenderer::new);
        event.registerEntityRenderer(KHEntities.SWALLOWTAIL_ARROW.get(), KHSwallowtailArrowEntityRenderer::new);
        event.registerEntityRenderer(KHEntities.BODKIN_ARROW.get(), KHBodkinArrowEntityRenderer::new);
        event.registerEntityRenderer(KHEntities.BROADHEAD_ARROW.get(), KHBroadheadArrowEntityRenderer::new);
        event.registerEntityRenderer(KHEntities.CLOTH_ARROW.get(), KHClothArrowEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof SCDyeableUnderArmor || item instanceof KHChestplateAttachment || item instanceof KHCloak
                    || item instanceof HorseBardingArmorItem || item instanceof KHLeggingsAttachment || item instanceof KHChaperon) {
                event.register((stack, tintIndex) -> {
                    if (tintIndex > 0) {
                        return -1;
                    }

                    int defaultColor = -1;
                    switch (stack.getItem()) {
                        case SCDyeableUnderArmor dyeable -> defaultColor = dyeable.getDefaultColor();
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
            } else if (item instanceof TwoLayerDyeableDeco) {
                event.register((stack, tintIndex) -> {
                    if (tintIndex == 0) return TwoLayerDyeableDeco.getColor1(stack); // top
                    if (tintIndex == 1) return TwoLayerDyeableDeco.getColor2(stack); // bottom
                    return -1;
                }, item);
            } else {
                event.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : DyedItemColor.getOrDefault(stack, -1), item);
            }
        }
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);
        event.registerLayerDefinition(WarDartModel.LAYER_LOCATION, WarDartModel::createLayer);
    }

    @SubscribeEvent
    public static void registerLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "surcoat_with_banner"), SurcoatWithBannerModel.INSTANCE);
    }
}