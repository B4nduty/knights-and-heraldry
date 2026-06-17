package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.StoneyCore;
import banduty.stoneycore.datagen.FabricModelProviderPlus;
import banduty.stoneycore.items.custom.manuscript.Manuscript;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ModModelProvider extends FabricModelProviderPlus {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(KHItems.CHAPERON.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.BROADHEAD_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.BODKIN_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.SWALLOWTAIL_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TORN_HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.HELMET_HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.HELMET_TORN_HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.CLOAK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TORN_CLOAK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.SURCOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.SURCOAT_SLEEVELESS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.CIVILIAN_SURCOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.GIORNEA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.AVENTAIL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.RIM_GUARDS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.BESAGEWS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.QUILTED_COIF.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.GAMBESON_BREECHES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.GAMBESON_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.MAIL_COIF.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.HAUBERK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.MAIL_BREECHES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.MAIL_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.LONGBOW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.LANCE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(KHItems.PLUME.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TRI_PLUME.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.FLUFFY_PLUME.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TEUTONIC_SNAKES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TEUTONIC_BLACK_SNAKES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.GOLD_HORNS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.BLACK_HORNS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TEUTONIC_GOLD_WINGS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TEUTONIC_BLACK_WINGS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TEUTONIC_WINGS_BALL_ENDS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.TEUTONIC_WINGS_SHARP_ENDS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.DRAGON.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.LION.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.SNAKE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.UNICORN.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.STAG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.BOAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.EAGLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(KHItems.PEGASUS.get(), ModelTemplates.FLAT_ITEM);

        registerSimpleItems(itemModelGenerators);

        registerManuscriptsItems(itemModelGenerators);

        generateBannerPatternModels(KHItems.SURCOAT.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        generateBannerPatternModels(KHItems.SURCOAT_SLEEVELESS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);

        // Dyeable Items
        registerDyeableItems(KHItems.BRIGANDINE_HARNESS.get(), itemModelGenerators);
        registerDyeableItems(KHItems.DARK_BRIGANDINE_HARNESS.get(), itemModelGenerators);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE_HARNESS.get(), itemModelGenerators);
        registerDyeableItems(KHItems.BRIGANDINE_CUISSES.get(), itemModelGenerators);
        registerDyeableItems(KHItems.DARK_BRIGANDINE_CUISSES.get(), itemModelGenerators);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE_CUISSES.get(), itemModelGenerators);
        registerDyeableItems(KHItems.JESTER_HOOD.get(), itemModelGenerators);
        registerDyeableItems(KHItems.HORSE_BARDING.get(), itemModelGenerators);
        registerDyeableItems(KHItems.DARK_HORSE_BARDING.get(), itemModelGenerators);
        registerDyeableItems(KHItems.GOLDEN_HORSE_BARDING.get(), itemModelGenerators);
        registerDyeableItems(KHItems.GILDED_CHAPERON.get(), itemModelGenerators);
        registerDyeableItems(KHItems.GAMBESON.get(), itemModelGenerators);
        registerDyeableItems(KHItems.ARMING_DOUBLET.get(), itemModelGenerators);
        registerDyeableItems(KHItems.ARMING_HOSE.get(), itemModelGenerators);
        registerDyeableItems(KHItems.WOODEN_LANCE.get(), itemModelGenerators);

        registerDyeableItems(KHItems.BRIGANDINE_SPAULDERS.get(), itemModelGenerators);
        registerDyeableItems(KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(), itemModelGenerators);
        registerDyeableItems(KHItems.DARK_BRIGANDINE_SPAULDERS.get(), itemModelGenerators);
        registerDyeableItems(KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(), itemModelGenerators);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), itemModelGenerators);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get(), itemModelGenerators);

        registerDyeableItems(KHItems.BRIGANDINE.get(), itemModelGenerators);
        registerDyeableItems(KHItems.DARK_BRIGANDINE.get(), itemModelGenerators);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE.get(), itemModelGenerators);
    }

    private void registerSimpleItems(ItemModelGenerators itemModelGenerators) {
        Item[] simpleHandheldItems = {
                KHItems.DAGGER.get(), KHItems.STILETTO.get(), KHItems.RAPIER.get(),
                KHItems.SWORD.get(), KHItems.V_SWORD.get(), KHItems.ARMING_SWORD.get(),
                KHItems.AXE.get(), KHItems.BROAD_AXE.get(), KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get(),
                KHItems.MACE.get(), KHItems.SPIKED_MACE.get(),
                KHItems.HAMMER.get(), KHItems.WAR_HAMMER.get(),
                KHItems.LONGSWORD.get(), KHItems.V_LONGSWORD.get(),
                KHItems.FALCHION.get(), KHItems.SCIMITAR.get(),
                KHItems.PITCHFORK.get(),
                KHItems.SPEAR.get(),
                KHItems.PIKE.get(),
                KHItems.BILLHOOK.get(),
                KHItems.GLAIVE.get(), KHItems.CURVED_GLAIVE.get(),
                KHItems.HALBERD.get(),
                KHItems.POLEAXE.get(),
                KHItems.POLEHAMMER.get(), KHItems.BEC_DE_CORBIN.get(),
                KHItems.MORNING_STAR.get(),
                KHItems.BARDICHE.get(),
                KHItems.GREATSWORD.get(), KHItems.CLAYMORE.get(), KHItems.FLAMBERGE.get(), KHItems.ZWEIHANDER.get(),
                KHItems.WARDART.get()
        };

        Item[] simpleArmor = {
                KHItems.MAIL_SPAULDERS.get(), KHItems.MAIL_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_MAIL_SPAULDERS.get(),
                KHItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get(),
                KHItems.PLATE_SPAULDERS.get(), KHItems.PLATE_SPAULDERS_BESAGEWS.get(),
                KHItems.PLATE_SPAULDERS_RIMMED.get(), KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS.get(),
                KHItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.DARK_PLATE_SPAULDERS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(),
                KHItems.GOLDEN_PLATE_SPAULDERS.get(), KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(),
                KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(),
                KHItems.PLATE_CUIRASS.get(), KHItems.GOLDEN_PLATE_CUIRASS.get(), KHItems.DARK_PLATE_CUIRASS.get(),
                KHItems.MAXIMILLIAN_CUIRASS.get(), KHItems.DARK_MAXIMILLIAN_CUIRASS.get(), KHItems.GOLDEN_MAXIMILLIAN_CUIRASS.get(),
                KHItems.XIIII_PLATE_CUIRASS.get(), KHItems.XIIII_PLATE_BREASTPLATE.get(),
                KHItems.PLACKART.get(), KHItems.DARK_PLACKART.get(), KHItems.GOLDEN_PLACKART.get(),
                KHItems.TASSETS.get(), KHItems.DARK_TASSETS.get(), KHItems.GOLDEN_TASSETS.get(),
                KHItems.DARK_XIIII_PLATE_CUIRASS.get(), KHItems.DARK_XIIII_PLATE_BREASTPLATE.get(),
                KHItems.GOLDEN_XIIII_PLATE_CUIRASS.get(), KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get(),
                KHItems.GREAVES.get(), KHItems.DARK_GREAVES.get(), KHItems.GOLDEN_GREAVES.get(),
                KHItems.SABATONS.get(), KHItems.DARK_SABATONS.get(), KHItems.GOLDEN_SABATONS.get(),
                KHItems.BARBUTE.get(), KHItems.DARK_BARBUTE.get(), KHItems.GOLDEN_BARBUTE.get(),
                KHItems.BASCINET.get(), KHItems.DARK_BASCINET.get(), KHItems.GOLDEN_BASCINET.get(),
                KHItems.KETTLE_HELM.get(), KHItems.DARK_KETTLE_HELM.get(), KHItems.GOLDEN_KETTLE_HELM.get(),
                KHItems.NASAL_HELM.get(), KHItems.DARK_NASAL_HELM.get(), KHItems.GOLDEN_NASAL_HELM.get(),
                KHItems.VIKING_HELM.get(), KHItems.DARK_VIKING_HELM.get(), KHItems.GOLDEN_VIKING_HELM.get(),
                KHItems.BURGONET.get(), KHItems.DARK_BURGONET.get(), KHItems.GOLDEN_BURGONET.get(),
                KHItems.VISORLESS_SALLET.get(), KHItems.DARK_VISORLESS_SALLET.get(), KHItems.GOLDEN_VISORLESS_SALLET.get(),
                KHItems.FROGMOUTH.get(), KHItems.DARK_FROGMOUTH.get(), KHItems.GOLDEN_FROGMOUTH.get(),
                KHItems.LEATHER_GLOVES.get(), KHItems.MAIL_GLOVES.get(),
                KHItems.GAUNTLET.get(), KHItems.DARK_GAUNTLET.get(), KHItems.GOLDEN_GAUNTLET.get(),
                KHItems.PLATE_HARNESS.get(), KHItems.DARK_PLATE_HARNESS.get(), KHItems.GOLDEN_PLATE_HARNESS.get(),
                KHItems.PLATE_CUISSES.get(), KHItems.DARK_PLATE_CUISSES.get(), KHItems.GOLDEN_PLATE_CUISSES.get(),
                KHItems.GREAT_HELM.get(), KHItems.DARK_GREAT_HELM.get(), KHItems.GOLDEN_GREAT_HELM.get(),
                KHItems.GREAT_HELM_2.get(), KHItems.DARK_GREAT_HELM_2.get(), KHItems.GOLDEN_GREAT_HELM_2.get()
        };

        Item[] openVisorHelmet = {
                KHItems.ARMET.get(), KHItems.DARK_ARMET.get(), KHItems.GOLDEN_ARMET.get(),
                KHItems.ARMET_2.get(), KHItems.DARK_ARMET_2.get(), KHItems.GOLDEN_ARMET_2.get(),
                KHItems.VISORED_BARBUTE.get(), KHItems.DARK_VISORED_BARBUTE.get(), KHItems.GOLDEN_VISORED_BARBUTE.get(),
                KHItems.HOUNDSKULL.get(), KHItems.DARK_HOUNDSKULL.get(), KHItems.GOLDEN_HOUNDSKULL.get(),
                KHItems.CAGE.get(), KHItems.DARK_CAGE.get(), KHItems.GOLDEN_CAGE.get(),
                KHItems.VISORED_BASCINET.get(), KHItems.DARK_VISORED_BASCINET.get(), KHItems.GOLDEN_VISORED_BASCINET.get(),
                KHItems.SALLET.get(), KHItems.DARK_SALLET.get(), KHItems.GOLDEN_SALLET.get(),
                KHItems.BURGONET_FALLING_BUFFE.get(), KHItems.DARK_BURGONET_FALLING_BUFFE.get(), KHItems.GOLDEN_BURGONET_FALLING_BUFFE.get(),
                KHItems.CLOSE_HELM.get(), KHItems.DARK_CLOSE_HELM.get(), KHItems.GOLDEN_CLOSE_HELM.get(),
                KHItems.GREAT_ARMET.get(), KHItems.DARK_GREAT_ARMET.get(), KHItems.GOLDEN_GREAT_ARMET.get(),
                KHItems.GREAT_ARMET_2.get(), KHItems.DARK_GREAT_ARMET_2.get(), KHItems.GOLDEN_GREAT_ARMET_2.get(),
                KHItems.GREAT_BASCINET.get(), KHItems.DARK_GREAT_BASCINET.get(), KHItems.GOLDEN_GREAT_BASCINET.get(),
                KHItems.GREAT_HOUNDSKUL_BASCINET.get(), KHItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(), KHItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get(),
                KHItems.MAXIMILLIAN_HELMET.get(), KHItems.DARK_MAXIMILLIAN_HELMET.get(), KHItems.GOLDEN_MAXIMILLIAN_HELMET.get(),
                KHItems.SAVOYARD.get(), KHItems.DARK_SAVOYARD.get(), KHItems.GOLDEN_SAVOYARD.get(),
                KHItems.ARAGONESE_SALLET.get(), KHItems.DARK_ARAGONESE_SALLET.get(), KHItems.GOLDEN_ARAGONESE_SALLET.get(),
                KHItems.SALLET_BEVOR.get() ,KHItems.DARK_SALLET_BEVOR.get(), KHItems.GOLDEN_SALLET_BEVOR.get()
        };

        for (Item item : simpleHandheldItems) {
            registerItemWConditions(item, itemModelGenerators, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "broken"), 1));
        }

        for (Item item : openVisorHelmet) {
            registerItemWConditions(item, itemModelGenerators, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1));
        }

        for (Item item : simpleArmor) {
            registerItemWConditions(item, itemModelGenerators);
        }
    }

    private void registerDyeableItems(Item item, ItemModelGenerators itemModelGenerators) {
        registerItemWConditions(item, itemModelGenerators, false, true);
    }

    private void registerManuscriptsItems(ItemModelGenerators itemModelGenerators) {
        Map<Item, Manuscript.Types> itemTypesMap = Map.<Item, Manuscript.Types>ofEntries(
                Map.entry(KHItems.DAGGER.get(), Manuscript.Types.BROAD),
                Map.entry(KHItems.STILETTO.get(), Manuscript.Types.BROAD),
                Map.entry(KHItems.RAPIER.get(), Manuscript.Types.BROAD),
                Map.entry(KHItems.SWORD.get(), Manuscript.Types.SWORDS),
                Map.entry(KHItems.V_SWORD.get(), Manuscript.Types.SWORDS),
                Map.entry(KHItems.ARMING_SWORD.get(), Manuscript.Types.SWORDS),
                Map.entry(KHItems.AXE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.BROAD_AXE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.CROOKED_AXE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.STRAIGHT_CROOKED_AXE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.MACE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.SPIKED_MACE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.FLAIL.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.BALL_FLAIL.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.HAMMER.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.WAR_HAMMER.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.LONGSWORD.get(), Manuscript.Types.LONGSWORDS),
                Map.entry(KHItems.V_LONGSWORD.get(), Manuscript.Types.LONGSWORDS),
                Map.entry(KHItems.FALCHION.get(), Manuscript.Types.SWORDS),
                Map.entry(KHItems.SCIMITAR.get(), Manuscript.Types.SWORDS),
                Map.entry(KHItems.PITCHFORK.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.SPEAR.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.PIKE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.BILLHOOK.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.GLAIVE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.CURVED_GLAIVE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.HALBERD.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.LANCE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.WOODEN_LANCE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.POLEAXE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.POLEHAMMER.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.BEC_DE_CORBIN.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(KHItems.MORNING_STAR.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.BARDICHE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(KHItems.GREATSWORD.get(), Manuscript.Types.GREATSWORDS),
                Map.entry(KHItems.CLAYMORE.get(), Manuscript.Types.GREATSWORDS),
                Map.entry(KHItems.FLAMBERGE.get(), Manuscript.Types.GREATSWORDS),
                Map.entry(KHItems.ZWEIHANDER.get(), Manuscript.Types.GREATSWORDS),
                Map.entry(KHItems.WARDART.get(), Manuscript.Types.LONG_HAFTED),

                Map.entry(KHItems.QUILTED_COIF.get(), Manuscript.Types.COIF),
                Map.entry(KHItems.GAMBESON.get(), Manuscript.Types.COAT),
                Map.entry(KHItems.GAMBESON_BREECHES.get(), Manuscript.Types.BREECHES),
                Map.entry(KHItems.GAMBESON_BOOTS.get(), Manuscript.Types.BOOTS),

                Map.entry(KHItems.ARMING_DOUBLET.get(), Manuscript.Types.COAT),
                Map.entry(KHItems.ARMING_HOSE.get(), Manuscript.Types.BREECHES),

                Map.entry(KHItems.MAIL_COIF.get(), Manuscript.Types.COIF),
                Map.entry(KHItems.HAUBERK.get(), Manuscript.Types.COAT),
                Map.entry(KHItems.MAIL_BREECHES.get(), Manuscript.Types.BREECHES),
                Map.entry(KHItems.MAIL_BOOTS.get(), Manuscript.Types.BOOTS),

                Map.entry(KHItems.MAIL_SPAULDERS.get(), Manuscript.Types.SPAULDERS),
                Map.entry(KHItems.BRIGANDINE_SPAULDERS.get(), Manuscript.Types.SPAULDERS),
                Map.entry(KHItems.PLATE_SPAULDERS.get(), Manuscript.Types.SPAULDERS),

                Map.entry(KHItems.BRIGANDINE.get(), Manuscript.Types.BREASTPLATE),

                Map.entry(KHItems.PLATE_CUIRASS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(KHItems.MAXIMILLIAN_CUIRASS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(KHItems.XIIII_PLATE_CUIRASS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(KHItems.XIIII_PLATE_BREASTPLATE.get(), Manuscript.Types.BREASTPLATE),

                Map.entry(KHItems.PLACKART.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(KHItems.TASSETS.get(), Manuscript.Types.BREASTPLATE),

                Map.entry(KHItems.RIM_GUARDS.get(), Manuscript.Types.RIM_GUARDS),
                Map.entry(KHItems.BESAGEWS.get(), Manuscript.Types.BESAGEWS),

                Map.entry(KHItems.BARBUTE.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.BASCINET.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.KETTLE_HELM.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.NASAL_HELM.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.VIKING_HELM.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.BURGONET.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.VISORLESS_SALLET.get(), Manuscript.Types.HELMET),

                Map.entry(KHItems.ARMET.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.ARMET_2.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.VISORED_BARBUTE.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.HOUNDSKULL.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.CAGE.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.VISORED_BASCINET.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.GREAT_HELM.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.BURGONET_FALLING_BUFFE.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.CLOSE_HELM.get(), Manuscript.Types.HELMET),

                Map.entry(KHItems.FROGMOUTH.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.GREAT_ARMET.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.GREAT_ARMET_2.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.GREAT_BASCINET.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.GREAT_HOUNDSKUL_BASCINET.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.MAXIMILLIAN_HELMET.get(), Manuscript.Types.HELMET),
                Map.entry(KHItems.SAVOYARD.get(), Manuscript.Types.HELMET),

                Map.entry(KHItems.LEATHER_GLOVES.get(), Manuscript.Types.GAUNTLETS),
                Map.entry(KHItems.MAIL_GLOVES.get(), Manuscript.Types.GAUNTLETS),

                Map.entry(KHItems.GAUNTLET.get(), Manuscript.Types.GAUNTLETS),
                Map.entry(KHItems.BRIGANDINE_HARNESS.get(), Manuscript.Types.GAUNTLETS),
                Map.entry(KHItems.PLATE_HARNESS.get(), Manuscript.Types.GAUNTLETS),

                Map.entry(KHItems.BRIGANDINE_CUISSES.get(), Manuscript.Types.CHAUSSES),
                Map.entry(KHItems.PLATE_CUISSES.get(), Manuscript.Types.CHAUSSES),

                Map.entry(KHItems.GREAVES.get(), Manuscript.Types.GREAVES),

                Map.entry(KHItems.SABATONS.get(), Manuscript.Types.BOOTS),

                Map.entry(KHItems.SURCOAT.get(), Manuscript.Types.CLOTH),
                Map.entry(KHItems.SURCOAT_SLEEVELESS.get(), Manuscript.Types.CLOTH),
                Map.entry(KHItems.CIVILIAN_SURCOAT.get(), Manuscript.Types.CLOTH),
                Map.entry(KHItems.GIORNEA.get(), Manuscript.Types.CLOTH),

                Map.entry(KHItems.CLOAK.get(), Manuscript.Types.COAT),
                Map.entry(KHItems.TORN_CLOAK.get(), Manuscript.Types.COAT),

                Map.entry(KHItems.HOOD.get(), Manuscript.Types.COIF),
                Map.entry(KHItems.TORN_HOOD.get(), Manuscript.Types.COIF),
                Map.entry(KHItems.JESTER_HOOD.get(), Manuscript.Types.COIF),
                Map.entry(KHItems.HELMET_HOOD.get(), Manuscript.Types.COIF),
                Map.entry(KHItems.HELMET_TORN_HOOD.get(), Manuscript.Types.COIF),

                Map.entry(KHItems.LONGBOW.get(), Manuscript.Types.LONGBOW),

                Map.entry(KHItems.HEAVY_CROSSBOW.get(), Manuscript.Types.CROSSBOW),

                Map.entry(KHItems.ARQUEBUS.get(), Manuscript.Types.ARQUEBUS),

                Map.entry(KHItems.HANDGONNE.get(), Manuscript.Types.HANDGONNE),

                Map.entry(KHItems.SWALLOWTAIL_ARROW.get(), Manuscript.Types.SWALLOWTAIL),
                Map.entry(KHItems.BODKIN_ARROW.get(), Manuscript.Types.BODKIN),
                Map.entry(KHItems.BROADHEAD_ARROW.get(), Manuscript.Types.BROAD),
                Map.entry(KHItems.CLOTH_ARROW.get(), Manuscript.Types.CLOTH),

                Map.entry(KHItems.HORSE_BARDING.get(), Manuscript.Types.HORSE)
        );

        for (Map.Entry<Item, Manuscript.Types> entry : itemTypesMap.entrySet()) {
            Item item = entry.getKey();
            Manuscript.Types type = entry.getValue();

            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
            String itemPath = itemId.getPath();

            registerWCustomName(item, ModelTemplates.FLAT_ITEM, itemModelGenerators, "manuscript_" + itemPath,
                    ResourceLocation.fromNamespaceAndPath(StoneyCore.MOD_ID, "item/" + "manuscript_" + type.name().toLowerCase()));
        }
    }
}