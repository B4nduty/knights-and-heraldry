package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.client.item.SurcoatWithBannerModel;
import banduty.knightsheraldry.entity.ModEntities;
import banduty.knightsheraldry.items.armor.accessory.KHChaperon;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.DyeableItems;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.model.HorseBardingModel;
import banduty.knightsheraldry.model.ModEntityModelLayers;
import banduty.knightsheraldry.util.itemdata.ItemTooltipComponent;
import banduty.knightsheraldry.util.itemdata.ItemTooltipData;
import banduty.knightsheraldry.util.itemdata.ModModelPredicates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KnightsHeraldryForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (Item item : ForgeRegistries.ITEMS) {
                if (ForgeRegistries.ITEMS.getKey(item).getNamespace().equals(KnightsHeraldry.MOD_ID)) {
                    ModModelPredicates.registerModelPredicates(item);
                }
            }
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.WARDART_PROJECTILE.get(), WarDartRenderer::new);
        event.registerEntityRenderer(ModEntities.SWALLOWTAIL_ARROW.get(), KHSwallowtailArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntities.BODKIN_ARROW.get(), KHBodkinArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntities.BROADHEAD_ARROW.get(), KHBroadheadArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntities.CLOTH_ARROW.get(), KHClothArrowEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item instanceof TwoLayerDyeableItem twoLayerItem) {
                event.register((stack, tintIndex) -> {
                    if (tintIndex == 0) return twoLayerItem.getColor1(stack);
                    if (tintIndex == 1) return twoLayerItem.getColor2(stack);
                    return -1;
                }, item);
            } else if (item instanceof DyeableItems dyeableItems) {
                event.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : dyeableItems.getColor(stack), item);
            } else if (item instanceof HorseBardingArmorItem horseBardingArmorItem) {
                event.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : horseBardingArmorItem.getColor(stack), item);
            } else if (item instanceof KHChaperon khChaperon) {
                event.register((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : khChaperon.getColor(stack), item);
            }
        }
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER, HorseBardingModel::getTexturedModelData);
    }

    @SubscribeEvent
    public static void registerLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register("surcoat_with_banner", SurcoatWithBannerModel.INSTANCE);
    }

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        // Register all pattern models
        String[] patterns = new String[]{"bl", "bo", "br", "bri", "bs", "bt", "bts", "cbo", "cr", "cre", "cs", "dls", "drs", "flo", "glb", "gra", "gru", "hh", "hhb", "ld", "ls", "lud", "mc", "moj", "mr", "ms", "pig", "rd", "rs", "rud", "sc", "sku", "ss", "tl", "tr", "ts", "tt", "tts", "vh", "vhr"};

        for (String pattern : patterns) {
            event.register(new ResourceLocation(KnightsHeraldry.MOD_ID, "item/surcoat/" + pattern));
            event.register(new ResourceLocation(KnightsHeraldry.MOD_ID, "item/surcoat_sleeveless/" + pattern));
        }
    }

    @SubscribeEvent
    public static void onRegisterClientTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ItemTooltipData.class, (data) -> new ItemTooltipComponent(data.items()));
    }
}