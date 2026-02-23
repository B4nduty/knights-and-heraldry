package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.client.item.SurcoatWithBannerModel;
import banduty.knightsheraldry.entity.ModEntities;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.items.armor.accessory.KHChaperon;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.DyeableItems;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.model.HorseBardingModel;
import banduty.knightsheraldry.model.ModEntityModelLayers;
import banduty.knightsheraldry.util.itemdata.ItemTooltipComponent;
import banduty.knightsheraldry.util.itemdata.ItemTooltipData;
import banduty.knightsheraldry.util.itemdata.ModModelPredicates;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
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

        String[] patterns = new String[]{
                "bl", "bo", "br", "bri", "bs", "bt", "bts", "cbo", "cr", "cre", "cs",
                "dls", "drs", "flo", "glb", "gra", "gru", "hh", "hhb", "ld", "ls",
                "lud", "mc", "moj", "mr", "ms", "pig", "rd", "rs", "rud", "sc", "sku",
                "ss", "tl", "tr", "ts", "tt", "tts", "vh", "vhr"
        };

        // Register for each base item that supports patterns
        Item[] patternedItems = new Item[]{
                ModItems.SURCOAT.get(),
                ModItems.SURCOAT_SLEEVELESS.get()
        };

        for (Item item : patternedItems) {

            String basePath = BuiltInRegistries.ITEM.getKey(item).getPath();
            String namespace = BuiltInRegistries.ITEM.getKey(item).getNamespace();

            for (String pattern : patterns) {

                String modelPath = item + "/" + pattern;

                event.register(new ModelResourceLocation(namespace, modelPath, "inventory"));
            }
        }

        // 3D Models
        String[] modelNames = {
                "dagger_3d", "stiletto_3d", "rapier_3d", "sword_3d", "v_sword_3d",
                "arming_sword_3d", "axe_3d", "broad_axe_3d", "crooked_axe_3d",
                "straight_crooked_axe_3d", "mace_3d", "spiked_mace_3d", "flail_icon",
                "ball_flail_icon", "hammer_3d", "war_hammer_3d", "longsword_3d",
                "v_longsword_3d", "falchion_3d", "scimitar_3d", "pitchfork_3d",
                "spear_3d", "pike_3d", "billhook_3d", "glaive_3d", "curved_glaive_3d",
                "halberd_3d", "lance_3d", "wooden_lance_3d", "poleaxe_3d",
                "polehammer_3d", "bec_de_corbin_3d", "morning_star_3d", "bardiche_3d",
                "greatsword_3d", "claymore_3d", "flamberge_3d", "zweihander_3d",
                "wardart_3d", "longbow_3d", "heavy_crossbow_icon", "arquebus_icon",
                "handgonne_icon"
        };

        for (String modelName : modelNames) {
            event.register(new ModelResourceLocation(KnightsHeraldry.MOD_ID, modelName, "inventory"));
        }

        // Manuscript Items
        Item[] manuscriptItems = {
                ModItems.DAGGER.get(),
                ModItems.STILETTO.get(),
                ModItems.RAPIER.get(),
                ModItems.SWORD.get(),
                ModItems.V_SWORD.get(),
                ModItems.ARMING_SWORD.get(),
                ModItems.AXE.get(),
                ModItems.BROAD_AXE.get(),
                ModItems.CROOKED_AXE.get(),
                ModItems.STRAIGHT_CROOKED_AXE.get(),
                ModItems.MACE.get(),
                ModItems.SPIKED_MACE.get(),
                ModItems.FLAIL.get(),
                ModItems.BALL_FLAIL.get(),
                ModItems.HAMMER.get(),
                ModItems.WAR_HAMMER.get(),
                ModItems.LONGSWORD.get(),
                ModItems.V_LONGSWORD.get(),
                ModItems.FALCHION.get(),
                ModItems.SCIMITAR.get(),
                ModItems.PITCHFORK.get(),
                ModItems.SPEAR.get(),
                ModItems.PIKE.get(),
                ModItems.BILLHOOK.get(),
                ModItems.GLAIVE.get(),
                ModItems.CURVED_GLAIVE.get(),
                ModItems.HALBERD.get(),
                ModItems.LANCE.get(),
                ModItems.WOODEN_LANCE.get(),
                ModItems.POLEAXE.get(),
                ModItems.POLEHAMMER.get(),
                ModItems.BEC_DE_CORBIN.get(),
                ModItems.MORNING_STAR.get(),
                ModItems.BARDICHE.get(),
                ModItems.GREATSWORD.get(),
                ModItems.CLAYMORE.get(),
                ModItems.FLAMBERGE.get(),
                ModItems.ZWEIHANDER.get(),
                ModItems.WARDART.get(),

                ModItems.QUILTED_COIF.get(),
                ModItems.GAMBESON.get(),
                ModItems.GAMBESON_BREECHES.get(),
                ModItems.GAMBESON_BOOTS.get(),

                ModItems.MAIL_COIF.get(),
                ModItems.HAUBERK.get(),
                ModItems.MAIL_BREECHES.get(),
                ModItems.MAIL_BOOTS.get(),

                ModItems.MAIL_SPAULDERS.get(),
                ModItems.BRIGANDINE_SPAULDERS.get(),
                ModItems.PLATE_SPAULDERS.get(),

                ModItems.BRIGANDINE.get(),

                ModItems.BRIG_BREASTPLATE.get(),
                ModItems.BRIG_BREASTPLATE_TASSETS.get(),

                ModItems.PLATE_CUIRASS.get(),
                ModItems.PLATE_CUIRASS_TASSETS.get(),
                ModItems.MAXIMILLIAN_CUIRASS.get(),
                ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(),
                ModItems.XIIII_PLATE_CUIRASS.get(),
                ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(),
                ModItems.XIIII_PLATE_BREASTPLATE.get(),

                ModItems.BARBUTE.get(),
                ModItems.BASCINET.get(),
                ModItems.KETTLE_HELM.get(),
                ModItems.NASAL_HELM.get(),
                ModItems.VIKING_HELM.get(),
                ModItems.BURGONET.get(),

                ModItems.ARMET.get(),
                ModItems.ARMET_2.get(),
                ModItems.VISORED_BARBUTE.get(),
                ModItems.HOUNDSKULL.get(),
                ModItems.CAGE.get(),
                ModItems.CAGE_2.get(),
                ModItems.VISORED_BASCINET.get(),
                ModItems.GREAT_HELM.get(),
                ModItems.SALLET.get(),
                ModItems.BURGONET_FALLING_BUFFE.get(),
                ModItems.CLOSE_HELM.get(),

                ModItems.FROGMOUTH.get(),
                ModItems.GREAT_ARMET.get(),
                ModItems.GREAT_ARMET_2.get(),
                ModItems.GREAT_BASCINET.get(),
                ModItems.GREAT_HOUNDSKUL_BASCINET.get(),
                ModItems.MAXIMILLIAN_HELMET.get(),
                ModItems.SAVOYARD.get(),

                ModItems.GAUNTLET.get(),
                ModItems.BRIGANDINE_HARNESS.get(),
                ModItems.PLATE_HARNESS.get(),

                ModItems.BRIGANDINE_CUISSES.get(),
                ModItems.PLATE_CUISSES.get(),

                ModItems.GREAVES.get(),

                ModItems.SABATONS.get(),

                ModItems.SURCOAT.get(),
                ModItems.SURCOAT_SLEEVELESS.get(),
                ModItems.CIVILIAN_SURCOAT.get(),
                ModItems.GIORNEA.get(),

                ModItems.CLOAK.get(),
                ModItems.TORN_CLOAK.get(),

                ModItems.HOOD.get(),
                ModItems.TORN_HOOD.get(),
                ModItems.JESTER_HOOD.get(),
                ModItems.HELMET_HOOD.get(),
                ModItems.HELMET_TORN_HOOD.get(),

                ModItems.LONGBOW.get(),

                ModItems.HEAVY_CROSSBOW.get(),

                ModItems.ARQUEBUS.get(),

                ModItems.HANDGONNE.get(),

                ModItems.SWALLOWTAIL_ARROW.get(),
                ModItems.BODKIN_ARROW.get(),
                ModItems.BROADHEAD_ARROW.get(),
                ModItems.CLOTH_ARROW.get(),

                ModItems.HORSE_BARDING.get(),

                ModItems.RIM_GUARDS.get(),
                ModItems.BESAGEWS.get()
        };

        for (Item item : manuscriptItems) {
            String itemName = BuiltInRegistries.ITEM.getKey(item).getPath();
            event.register(new ModelResourceLocation(KnightsHeraldry.MOD_ID, "manuscript_" + itemName, "inventory"));
        }
    }

    @SubscribeEvent
    public static void onRegisterClientTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ItemTooltipData.class, (data) -> new ItemTooltipComponent(data.items()));
    }
}