package banduty.knightsheraldry;

import banduty.knightsheraldry.client.entity.*;
import banduty.knightsheraldry.client.item.SurcoatWithBannerModel;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.armor.attachment.KHChaperon;
import banduty.knightsheraldry.items.armor.attachment.KHChestplateAttachment;
import banduty.knightsheraldry.items.armor.attachment.KHCloak;
import banduty.knightsheraldry.items.armor.attachment.KHLeggingsAttachment;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.TwoLayerDyeableDeco;
import banduty.knightsheraldry.model.HorseBardingModel;
import banduty.knightsheraldry.model.ModEntityModelLayers;
import banduty.knightsheraldry.util.itemdata.ModModelPredicates;
import banduty.stoneycore.items.custom.armor.underarmor.SCDyeableUnderArmor;
import net.minecraft.client.resources.model.ModelResourceLocation;
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
        event.register((stack, tintIndex) -> {
            if (tintIndex == 0) return TwoLayerDyeableDeco.getColor1(stack);
            if (tintIndex == 1) return TwoLayerDyeableDeco.getColor2(stack);
            return -1;
        }, KHItems.TORSE.get());

        Item[] items = new Item[]{
                KHItems.WOODEN_LANCE.get(), KHItems.QUILTED_COIF.get(), KHItems.GAMBESON.get(), KHItems.GAMBESON_BREECHES.get(),
                KHItems.GAMBESON_BOOTS.get(), KHItems.BRIGANDINE_SPAULDERS.get(), KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(),
                KHItems.DARK_BRIGANDINE_SPAULDERS.get(), KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(),
                KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get(),
                KHItems.BRIGANDINE.get(), KHItems.DARK_BRIGANDINE.get(), KHItems.GOLDEN_BRIGANDINE.get(),
                KHItems.BRIG_BREASTPLATE.get(), KHItems.DARK_BRIG_BREASTPLATE.get(), KHItems.GOLDEN_BRIG_BREASTPLATE.get(),
                KHItems.BRIG_BREASTPLATE_TASSETS.get(), KHItems.DARK_BRIG_BREASTPLATE_TASSETS.get(), KHItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get(),
                KHItems.BRIGANDINE_HARNESS.get(), KHItems.DARK_BRIGANDINE_HARNESS.get(), KHItems.GOLDEN_BRIGANDINE_HARNESS.get(),
                KHItems.BRIGANDINE_CUISSES.get(), KHItems.DARK_BRIGANDINE_CUISSES.get(), KHItems.GOLDEN_BRIGANDINE_CUISSES.get(),
                KHItems.CLOAK.get(), KHItems.TORN_CLOAK.get(), KHItems.HOOD.get(), KHItems.TORN_HOOD.get(),
                KHItems.JESTER_HOOD.get(), KHItems.HELMET_HOOD.get(), KHItems.HELMET_TORN_HOOD.get(),
                KHItems.HORSE_BARDING.get(), KHItems.DARK_HORSE_BARDING.get(), KHItems.GOLDEN_HORSE_BARDING.get(),
                KHItems.PLUME.get(), KHItems.TRI_PLUME.get(), KHItems.FLUFFY_PLUME.get(),
                KHItems.CHAPERON.get(), KHItems.GILDED_CHAPERON.get(), KHItems.TORSE.get()
        };
        for (Item item : items) {
            if (item instanceof SCDyeableUnderArmor || item instanceof KHChestplateAttachment || item instanceof KHCloak
                    || item instanceof HorseBardingArmorItem || item instanceof KHLeggingsAttachment || item instanceof KHChaperon) {
                event.register((stack, tintIndex) -> {
                    if (tintIndex != 0) {
                        return -1;
                    }

                    int defaultColor = -1;
                    switch (stack.getItem()) {
                        case SCDyeableUnderArmor dyeable -> defaultColor = dyeable.getDefaultColor();
                        case KHChestplateAttachment khChestplateAttachment -> defaultColor = khChestplateAttachment.getDefaultColor();
                        case KHCloak khCloak -> defaultColor = khCloak.getDefaultColor();
                        case KHLeggingsAttachment khLeggingsAttachment -> defaultColor = khLeggingsAttachment.getDefaultColor();
                        case KHChaperon khChaperon -> defaultColor = khChaperon.getDefaultColor();
                        case HorseBardingArmorItem horseBardingArmorItem -> defaultColor = horseBardingArmorItem.getDefaultColor();
                        default -> {}
                    }

                    return DyedItemColor.getOrDefault(stack, defaultColor);
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
    }

    @SubscribeEvent
    public static void registerLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "surcoat_with_banner"), SurcoatWithBannerModel.INSTANCE);
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
                KHItems.SURCOAT.get(),
                KHItems.SURCOAT_SLEEVELESS.get()
        };

        for (Item item : patternedItems) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            for (String pattern : patterns) {
                ResourceLocation modelLoc = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "item/" + id.getPath() + "/" + pattern);
                event.register(ModelResourceLocation.standalone(modelLoc));
            }
        }

        // 3D Models
        Item[] weapons3D = {
                KHItems.DAGGER.get(),
                KHItems.STILETTO.get(),
                KHItems.RAPIER.get(),
                KHItems.SWORD.get(),
                KHItems.V_SWORD.get(),
                KHItems.ARMING_SWORD.get(),
                KHItems.AXE.get(),
                KHItems.BROAD_AXE.get(),
                KHItems.CROOKED_AXE.get(),
                KHItems.STRAIGHT_CROOKED_AXE.get(),
                KHItems.MACE.get(),
                KHItems.SPIKED_MACE.get(),
                KHItems.HAMMER.get(),
                KHItems.WAR_HAMMER.get(),
                KHItems.LONGSWORD.get(),
                KHItems.V_LONGSWORD.get(),
                KHItems.FALCHION.get(),
                KHItems.SCIMITAR.get(),
                KHItems.PITCHFORK.get(),
                KHItems.SPEAR.get(),
                KHItems.PIKE.get(),
                KHItems.BILLHOOK.get(),
                KHItems.GLAIVE.get(),
                KHItems.CURVED_GLAIVE.get(),
                KHItems.HALBERD.get(),
                KHItems.LANCE.get(),
                KHItems.WOODEN_LANCE.get(),
                KHItems.POLEAXE.get(),
                KHItems.POLEHAMMER.get(),
                KHItems.BEC_DE_CORBIN.get(),
                KHItems.MORNING_STAR.get(),
                KHItems.BARDICHE.get(),
                KHItems.GREATSWORD.get(),
                KHItems.CLAYMORE.get(),
                KHItems.FLAMBERGE.get(),
                KHItems.ZWEIHANDER.get(),
                KHItems.WARDART.get(),
                KHItems.LONGBOW.get()
        };

        // 2D ICON-ONLY weapons
        Item[] weaponsIcon = {
                KHItems.FLAIL.get(),
                KHItems.BALL_FLAIL.get(),
                KHItems.HEAVY_CROSSBOW.get(),
                KHItems.ARQUEBUS.get(),
                KHItems.HANDGONNE.get()
        };

        // ===== register 3D models =====
        for (Item item : weapons3D) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            ResourceLocation modelLoc = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "item/" + id.getPath() + "_3d");
            event.register(ModelResourceLocation.standalone(modelLoc));
        }

        // ===== register icon models =====
        for (Item item : weaponsIcon) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            ResourceLocation modelLoc = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "item/" + id.getPath() + "_icon");
            event.register(ModelResourceLocation.standalone(modelLoc));
        }

        // Manuscript Items
        Item[] manuscriptItems = {
                KHItems.DAGGER.get(),
                KHItems.STILETTO.get(),
                KHItems.RAPIER.get(),
                KHItems.SWORD.get(),
                KHItems.V_SWORD.get(),
                KHItems.ARMING_SWORD.get(),
                KHItems.AXE.get(),
                KHItems.BROAD_AXE.get(),
                KHItems.CROOKED_AXE.get(),
                KHItems.STRAIGHT_CROOKED_AXE.get(),
                KHItems.MACE.get(),
                KHItems.SPIKED_MACE.get(),
                KHItems.FLAIL.get(),
                KHItems.BALL_FLAIL.get(),
                KHItems.HAMMER.get(),
                KHItems.WAR_HAMMER.get(),
                KHItems.LONGSWORD.get(),
                KHItems.V_LONGSWORD.get(),
                KHItems.FALCHION.get(),
                KHItems.SCIMITAR.get(),
                KHItems.PITCHFORK.get(),
                KHItems.SPEAR.get(),
                KHItems.PIKE.get(),
                KHItems.BILLHOOK.get(),
                KHItems.GLAIVE.get(),
                KHItems.CURVED_GLAIVE.get(),
                KHItems.HALBERD.get(),
                KHItems.LANCE.get(),
                KHItems.WOODEN_LANCE.get(),
                KHItems.POLEAXE.get(),
                KHItems.POLEHAMMER.get(),
                KHItems.BEC_DE_CORBIN.get(),
                KHItems.MORNING_STAR.get(),
                KHItems.BARDICHE.get(),
                KHItems.GREATSWORD.get(),
                KHItems.CLAYMORE.get(),
                KHItems.FLAMBERGE.get(),
                KHItems.ZWEIHANDER.get(),
                KHItems.WARDART.get(),

                KHItems.QUILTED_COIF.get(),
                KHItems.GAMBESON.get(),
                KHItems.GAMBESON_BREECHES.get(),
                KHItems.GAMBESON_BOOTS.get(),

                KHItems.MAIL_COIF.get(),
                KHItems.HAUBERK.get(),
                KHItems.MAIL_BREECHES.get(),
                KHItems.MAIL_BOOTS.get(),

                KHItems.MAIL_SPAULDERS.get(),
                KHItems.BRIGANDINE_SPAULDERS.get(),
                KHItems.PLATE_SPAULDERS.get(),

                KHItems.BRIGANDINE.get(),

                KHItems.BRIG_BREASTPLATE.get(),
                KHItems.BRIG_BREASTPLATE_TASSETS.get(),

                KHItems.PLATE_CUIRASS.get(),
                KHItems.PLATE_CUIRASS_TASSETS.get(),
                KHItems.MAXIMILLIAN_CUIRASS.get(),
                KHItems.MAXIMILLIAN_CUIRASS_TASSETS.get(),
                KHItems.XIIII_PLATE_CUIRASS.get(),
                KHItems.XIIII_PLATE_CUIRASS_TASSETS.get(),
                KHItems.XIIII_PLATE_BREASTPLATE.get(),

                KHItems.BARBUTE.get(),
                KHItems.BASCINET.get(),
                KHItems.KETTLE_HELM.get(),
                KHItems.NASAL_HELM.get(),
                KHItems.VIKING_HELM.get(),
                KHItems.BURGONET.get(),

                KHItems.ARMET.get(),
                KHItems.ARMET_2.get(),
                KHItems.VISORED_BARBUTE.get(),
                KHItems.HOUNDSKULL.get(),
                KHItems.CAGE.get(),
                KHItems.VISORED_BASCINET.get(),
                KHItems.GREAT_HELM.get(),
                KHItems.SALLET.get(),
                KHItems.BURGONET_FALLING_BUFFE.get(),
                KHItems.CLOSE_HELM.get(),

                KHItems.SALLET_BEVOR.get(),

                KHItems.FROGMOUTH.get(),
                KHItems.GREAT_ARMET.get(),
                KHItems.GREAT_ARMET_2.get(),
                KHItems.GREAT_BASCINET.get(),
                KHItems.GREAT_HOUNDSKUL_BASCINET.get(),
                KHItems.MAXIMILLIAN_HELMET.get(),
                KHItems.SAVOYARD.get(),

                KHItems.GAUNTLET.get(),
                KHItems.BRIGANDINE_HARNESS.get(),
                KHItems.PLATE_HARNESS.get(),

                KHItems.BRIGANDINE_CUISSES.get(),
                KHItems.PLATE_CUISSES.get(),

                KHItems.GREAVES.get(),

                KHItems.SABATONS.get(),

                KHItems.SURCOAT.get(),
                KHItems.SURCOAT_SLEEVELESS.get(),
                KHItems.CIVILIAN_SURCOAT.get(),
                KHItems.GIORNEA.get(),

                KHItems.CLOAK.get(),
                KHItems.TORN_CLOAK.get(),

                KHItems.HOOD.get(),
                KHItems.TORN_HOOD.get(),
                KHItems.JESTER_HOOD.get(),
                KHItems.HELMET_HOOD.get(),
                KHItems.HELMET_TORN_HOOD.get(),

                KHItems.LONGBOW.get(),

                KHItems.HEAVY_CROSSBOW.get(),

                KHItems.ARQUEBUS.get(),

                KHItems.HANDGONNE.get(),

                KHItems.SWALLOWTAIL_ARROW.get(),
                KHItems.BODKIN_ARROW.get(),
                KHItems.BROADHEAD_ARROW.get(),
                KHItems.CLOTH_ARROW.get(),

                KHItems.HORSE_BARDING.get(),

                KHItems.RIM_GUARDS.get(),
                KHItems.BESAGEWS.get()
        };

        for (Item item : manuscriptItems) {
            String itemName = BuiltInRegistries.ITEM.getKey(item).getPath();
            ResourceLocation modelLoc = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "item/" +"manuscript_" + itemName);
            event.register(ModelResourceLocation.standalone(modelLoc));
        }
    }
}