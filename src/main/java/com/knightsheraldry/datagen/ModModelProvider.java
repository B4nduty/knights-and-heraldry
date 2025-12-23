package com.knightsheraldry.datagen;

import banduty.stoneycore.StoneyCore;
import banduty.stoneycore.datagen.FabricModelProviderPlus;
import banduty.stoneycore.items.item.Manuscript;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.*;

public class ModModelProvider extends FabricModelProviderPlus {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.CHAPERON.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BROADHEAD_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BODKIN_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SWALLOWTAIL_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TORN_HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HELMET_HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HELMET_TORN_HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CLOAK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TORN_CLOAK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SURCOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SURCOAT_SLEEVELESS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CIVILIAN_SURCOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GIORNEA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.AVENTAIL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BEVOR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DARK_BEVOR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GOLDEN_BEVOR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RIM_GUARDS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BESAGEWS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.QUILTED_COIF.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GAMBESON_BREECHES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GAMBESON_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MAIL_COIF.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HAUBERK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MAIL_BREECHES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MAIL_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LONGBOW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LANCE.get(), ModelTemplates.FLAT_ITEM);

        registerSimpleItems(itemModelGenerators);

        registerManuscriptsItems(itemModelGenerators);

        generateBannerPatternModels(ModItems.SURCOAT.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        generateBannerPatternModels(ModItems.SURCOAT_SLEEVELESS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);

        registerItemWConditions(ModItems.MAIL_SPAULDERS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators,
                new OverrideCondition("besagews", 1));
        registerItemWConditions(ModItems.GOLDEN_MAIL_SPAULDERS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators,
                new OverrideCondition("besagews", 1));
        registerItemWConditions(ModItems.BRIGANDINE_SPAULDERS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators,
                new OverrideCondition("besagews", 1));
        registerItemWConditions(ModItems.DARK_BRIGANDINE_SPAULDERS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators,
                new OverrideCondition("besagews", 1));
        registerItemWConditions(ModItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators,
                new OverrideCondition("besagews", 1));
        registerItemWConditions(ModItems.PLATE_SPAULDERS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators,
                new OverrideCondition("besagews", 1),
                new OverrideCondition("rimmed", 1));
        registerItemWConditions(ModItems.DARK_PLATE_SPAULDERS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators,
                new OverrideCondition("besagews", 1),
                new OverrideCondition("rimmed", 1));
        registerItemWConditions(ModItems.GOLDEN_PLATE_SPAULDERS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators,
                new OverrideCondition("besagews", 1),
                new OverrideCondition("rimmed", 1));

        registerHelmetDecoItems(itemModelGenerators);

        // Dyeable Items
        registerItemWConditions(ModItems.BRIGANDINE_HARNESS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.DARK_BRIGANDINE_HARNESS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GOLDEN_BRIGANDINE_HARNESS.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.BRIGANDINE_CUISSES.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.DARK_BRIGANDINE_CUISSES.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GOLDEN_BRIGANDINE_CUISSES.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.JESTER_HOOD.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.HORSE_BARDING.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.DARK_HORSE_BARDING.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GOLDEN_HORSE_BARDING.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GILDED_CHAPERON.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GAMBESON.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.WOODEN_LANCE.get(), ModelTemplates.FLAT_ITEM, itemModelGenerators);
    }

    private void registerSimpleItems(ItemModelGenerators itemModelGenerators) {
        Item[] simpleHandheldItems = {
                ModItems.DAGGER.get(), ModItems.STILETTO.get(), ModItems.RAPIER.get(),
                ModItems.SWORD.get(), ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get(),
                ModItems.AXE.get(), ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get(),
                ModItems.MACE.get(), ModItems.SPIKED_MACE.get(),
                ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get(),
                ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get(),
                ModItems.FALCHION.get(), ModItems.SCIMITAR.get(),
                ModItems.PITCHFORK.get(),
                ModItems.SPEAR.get(),
                ModItems.PIKE.get(),
                ModItems.BILLHOOK.get(),
                ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get(),
                ModItems.HALBERD.get(),
                ModItems.POLEAXE.get(),
                ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get(),
                ModItems.MORNING_STAR.get(),
                ModItems.BARDICHE.get(),
                ModItems.GREATSWORD.get(), ModItems.CLAYMORE.get(), ModItems.FLAMBERGE.get(), ModItems.ZWEIHANDER.get(),
                ModItems.WARDART.get()
        };

        Item[] simpleArmor = {
                ModItems.PLATE_CUIRASS.get(), ModItems.GOLDEN_PLATE_CUIRASS.get(), ModItems.DARK_PLATE_CUIRASS.get(),
                ModItems.PLATE_CUIRASS_TASSETS.get(),ModItems.DARK_PLATE_CUIRASS_TASSETS.get(),  ModItems.GOLDEN_PLATE_CUIRASS_TASSETS.get(),
                ModItems.MAXIMILLIAN_CUIRASS.get(), ModItems.DARK_MAXIMILLIAN_CUIRASS.get(), ModItems.GOLDEN_MAXIMILLIAN_CUIRASS.get(),
                ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), ModItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS.get(), ModItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS.get(),
                ModItems.XIIII_PLATE_CUIRASS.get(), ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(), ModItems.XIIII_PLATE_BREASTPLATE.get(),
                ModItems.DARK_XIIII_PLATE_CUIRASS.get(), ModItems.DARK_XIIII_PLATE_CUIRASS_TASSETS.get(), ModItems.DARK_XIIII_PLATE_BREASTPLATE.get(),
                ModItems.GOLDEN_XIIII_PLATE_CUIRASS.get(), ModItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS.get(), ModItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get(),
                ModItems.GREAVES.get(), ModItems.DARK_GREAVES.get(), ModItems.GOLDEN_GREAVES.get(),
                ModItems.SABATONS.get(), ModItems.DARK_SABATONS.get(), ModItems.GOLDEN_SABATONS.get(),
                ModItems.BARBUTE.get(), ModItems.DARK_BARBUTE.get(), ModItems.GOLDEN_BARBUTE.get(),
                ModItems.BASCINET.get(), ModItems.DARK_BASCINET.get(), ModItems.GOLDEN_BASCINET.get(),
                ModItems.KETTLE_HELM.get(), ModItems.DARK_KETTLE_HELM.get(), ModItems.GOLDEN_KETTLE_HELM.get(),
                ModItems.NASAL_HELM.get(), ModItems.DARK_NASAL_HELM.get(), ModItems.GOLDEN_NASAL_HELM.get(),
                ModItems.VIKING_HELM.get(), ModItems.DARK_VIKING_HELM.get(), ModItems.GOLDEN_VIKING_HELM.get(),
                ModItems.BURGONET.get(), ModItems.DARK_BURGONET.get(), ModItems.GOLDEN_BURGONET.get(),
                ModItems.FROGMOUTH.get(), ModItems.DARK_FROGMOUTH.get(), ModItems.GOLDEN_FROGMOUTH.get(),
                ModItems.GAUNTLET.get(), ModItems.DARK_GAUNTLET.get(), ModItems.GOLDEN_GAUNTLET.get(),
                ModItems.PLATE_HARNESS.get(), ModItems.DARK_PLATE_HARNESS.get(), ModItems.GOLDEN_PLATE_HARNESS.get(),
                ModItems.PLATE_CUISSES.get(), ModItems.DARK_PLATE_CUISSES.get(), ModItems.GOLDEN_PLATE_CUISSES.get(),
                ModItems.BRIGANDINE.get(), ModItems.DARK_BRIGANDINE.get(), ModItems.GOLDEN_BRIGANDINE.get(),
                ModItems.BRIG_BREASTPLATE.get(), ModItems.DARK_BRIG_BREASTPLATE.get(), ModItems.GOLDEN_BRIG_BREASTPLATE.get(),
                ModItems.BRIG_BREASTPLATE_TASSETS.get(), ModItems.DARK_BRIG_BREASTPLATE_TASSETS.get(), ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get(),
                ModItems.GREAT_HELM.get(), ModItems.DARK_GREAT_HELM.get(), ModItems.GOLDEN_GREAT_HELM.get(),
                ModItems.GREAT_HELM_2.get(), ModItems.DARK_GREAT_HELM_2.get(), ModItems.GOLDEN_GREAT_HELM_2.get()
        };

        Item[] openVisorHelmet = {
                ModItems.ARMET.get(), ModItems.DARK_ARMET.get(), ModItems.GOLDEN_ARMET.get(),
                ModItems.ARMET_2.get(), ModItems.DARK_ARMET_2.get(), ModItems.GOLDEN_ARMET_2.get(),
                ModItems.VISORED_BARBUTE.get(), ModItems.DARK_VISORED_BARBUTE.get(), ModItems.GOLDEN_VISORED_BARBUTE.get(),
                ModItems.HOUNDSKULL.get(), ModItems.DARK_HOUNDSKULL.get(), ModItems.GOLDEN_HOUNDSKULL.get(),
                ModItems.CAGE.get(), ModItems.DARK_CAGE.get(), ModItems.GOLDEN_CAGE.get(),
                ModItems.CAGE_2.get(), ModItems.DARK_CAGE_2.get(), ModItems.GOLDEN_CAGE_2.get(),
                ModItems.VISORED_BASCINET.get(), ModItems.DARK_VISORED_BASCINET.get(), ModItems.GOLDEN_VISORED_BASCINET.get(),
                ModItems.SALLET.get(), ModItems.DARK_SALLET.get(), ModItems.GOLDEN_SALLET.get(),
                ModItems.BURGONET_FALLING_BUFFE.get(), ModItems.DARK_BURGONET_FALLING_BUFFE.get(), ModItems.GOLDEN_BURGONET_FALLING_BUFFE.get(),
                ModItems.CLOSE_HELM.get(), ModItems.DARK_CLOSE_HELM.get(), ModItems.GOLDEN_CLOSE_HELM.get(),
                ModItems.GREAT_ARMET.get(), ModItems.DARK_GREAT_ARMET.get(), ModItems.GOLDEN_GREAT_ARMET.get(),
                ModItems.GREAT_ARMET_2.get(), ModItems.DARK_GREAT_ARMET_2.get(), ModItems.GOLDEN_GREAT_ARMET_2.get(),
                ModItems.GREAT_BASCINET.get(), ModItems.DARK_GREAT_BASCINET.get(), ModItems.GOLDEN_GREAT_BASCINET.get(),
                ModItems.GREAT_HOUNDSKUL_BASCINET.get(), ModItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(), ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get(),
                ModItems.MAXIMILLIAN_HELMET.get(), ModItems.DARK_MAXIMILLIAN_HELMET.get(), ModItems.GOLDEN_MAXIMILLIAN_HELMET.get(),
                ModItems.SAVOYARD.get(), ModItems.DARK_SAVOYARD.get(), ModItems.GOLDEN_SAVOYARD.get()
        };

        for (Item item : simpleHandheldItems) {
            registerItemWConditions(item, ModelTemplates.FLAT_ITEM, itemModelGenerators, new OverrideCondition("broken", 1));
        }

        for (Item item : openVisorHelmet) {
            registerItemWConditions(item, ModelTemplates.FLAT_ITEM, itemModelGenerators, new OverrideCondition("open", 1));
        }

        for (Item item : simpleArmor) {
            registerItemWConditions(item, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        }
    }

    private void registerHelmetDecoItems(ItemModelGenerators itemModelGenerators) {
        for (Item deco : HelmetDeco.HELMET_DECO.keySet()) {
            if (deco == ModItems.TORSE.get()) continue;
            itemModelGenerators.generateFlatItem(deco, ModelTemplates.FLAT_ITEM);
        }
    }

    private void registerManuscriptsItems(ItemModelGenerators itemModelGenerators) {
        Map<Item, Manuscript.Types> itemTypesMap = Map.<Item, Manuscript.Types>ofEntries(
                Map.entry(ModItems.DAGGER.get(), Manuscript.Types.BROAD),
                Map.entry(ModItems.STILETTO.get(), Manuscript.Types.BROAD),
                Map.entry(ModItems.RAPIER.get(), Manuscript.Types.BROAD),
                Map.entry(ModItems.SWORD.get(), Manuscript.Types.SWORDS),
                Map.entry(ModItems.V_SWORD.get(), Manuscript.Types.SWORDS),
                Map.entry(ModItems.ARMING_SWORD.get(), Manuscript.Types.SWORDS),
                Map.entry(ModItems.AXE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.BROAD_AXE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.CROOKED_AXE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.STRAIGHT_CROOKED_AXE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.MACE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.SPIKED_MACE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.FLAIL.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.BALL_FLAIL.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.HAMMER.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.WAR_HAMMER.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.LONGSWORD.get(), Manuscript.Types.LONGSWORDS),
                Map.entry(ModItems.V_LONGSWORD.get(), Manuscript.Types.LONGSWORDS),
                Map.entry(ModItems.FALCHION.get(), Manuscript.Types.SWORDS),
                Map.entry(ModItems.SCIMITAR.get(), Manuscript.Types.SWORDS),
                Map.entry(ModItems.PITCHFORK.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.SPEAR.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.PIKE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.BILLHOOK.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.GLAIVE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.CURVED_GLAIVE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.HALBERD.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.LANCE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.WOODEN_LANCE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.POLEAXE.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.POLEHAMMER.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.BEC_DE_CORBIN.get(), Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.MORNING_STAR.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.BARDICHE.get(), Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.GREATSWORD.get(), Manuscript.Types.GREATSWORDS),
                Map.entry(ModItems.CLAYMORE.get(), Manuscript.Types.GREATSWORDS),
                Map.entry(ModItems.FLAMBERGE.get(), Manuscript.Types.GREATSWORDS),
                Map.entry(ModItems.ZWEIHANDER.get(), Manuscript.Types.GREATSWORDS),
                Map.entry(ModItems.WARDART.get(), Manuscript.Types.LONG_HAFTED),

                Map.entry(ModItems.QUILTED_COIF.get(), Manuscript.Types.COIF),
                Map.entry(ModItems.GAMBESON.get(), Manuscript.Types.COAT),
                Map.entry(ModItems.GAMBESON_BREECHES.get(), Manuscript.Types.BREECHES),
                Map.entry(ModItems.GAMBESON_BOOTS.get(), Manuscript.Types.BOOTS),

                Map.entry(ModItems.MAIL_COIF.get(), Manuscript.Types.COIF),
                Map.entry(ModItems.HAUBERK.get(), Manuscript.Types.COAT),
                Map.entry(ModItems.MAIL_BREECHES.get(), Manuscript.Types.BREECHES),
                Map.entry(ModItems.MAIL_BOOTS.get(), Manuscript.Types.BOOTS),

                Map.entry(ModItems.MAIL_SPAULDERS.get(), Manuscript.Types.SPAULDERS),
                Map.entry(ModItems.BRIGANDINE_SPAULDERS.get(), Manuscript.Types.SPAULDERS),
                Map.entry(ModItems.PLATE_SPAULDERS.get(), Manuscript.Types.SPAULDERS),

                Map.entry(ModItems.BRIGANDINE.get(), Manuscript.Types.BREASTPLATE),

                Map.entry(ModItems.BRIG_BREASTPLATE.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.BRIG_BREASTPLATE_TASSETS.get(), Manuscript.Types.BREASTPLATE),

                Map.entry(ModItems.PLATE_CUIRASS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.PLATE_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.MAXIMILLIAN_CUIRASS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.XIIII_PLATE_CUIRASS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.XIIII_PLATE_BREASTPLATE.get(), Manuscript.Types.BREASTPLATE),

                Map.entry(ModItems.RIM_GUARDS.get(), Manuscript.Types.RIM_GUARDS),
                Map.entry(ModItems.BESAGEWS.get(), Manuscript.Types.BESAGEWS),

                Map.entry(ModItems.BARBUTE.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.BASCINET.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.KETTLE_HELM.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.NASAL_HELM.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.VIKING_HELM.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.BURGONET.get(), Manuscript.Types.HELMET),

                Map.entry(ModItems.ARMET.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.ARMET_2.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.VISORED_BARBUTE.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.HOUNDSKULL.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.CAGE.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.CAGE_2.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.VISORED_BASCINET.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_HELM.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.SALLET.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.BURGONET_FALLING_BUFFE.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.CLOSE_HELM.get(), Manuscript.Types.HELMET),

                Map.entry(ModItems.FROGMOUTH.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_ARMET.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_ARMET_2.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_BASCINET.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_HOUNDSKUL_BASCINET.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.MAXIMILLIAN_HELMET.get(), Manuscript.Types.HELMET),
                Map.entry(ModItems.SAVOYARD.get(), Manuscript.Types.HELMET),

                Map.entry(ModItems.GAUNTLET.get(), Manuscript.Types.GAUNTLETS),
                Map.entry(ModItems.BRIGANDINE_HARNESS.get(), Manuscript.Types.GAUNTLETS),
                Map.entry(ModItems.PLATE_HARNESS.get(), Manuscript.Types.GAUNTLETS),

                Map.entry(ModItems.BRIGANDINE_CUISSES.get(), Manuscript.Types.CHAUSSES),
                Map.entry(ModItems.PLATE_CUISSES.get(), Manuscript.Types.CHAUSSES),

                Map.entry(ModItems.GREAVES.get(), Manuscript.Types.GREAVES),

                Map.entry(ModItems.SABATONS.get(), Manuscript.Types.BOOTS),

                Map.entry(ModItems.SURCOAT.get(), Manuscript.Types.CLOTH),
                Map.entry(ModItems.SURCOAT_SLEEVELESS.get(), Manuscript.Types.CLOTH),
                Map.entry(ModItems.CIVILIAN_SURCOAT.get(), Manuscript.Types.CLOTH),
                Map.entry(ModItems.GIORNEA.get(), Manuscript.Types.CLOTH),

                Map.entry(ModItems.CLOAK.get(), Manuscript.Types.COAT),
                Map.entry(ModItems.TORN_CLOAK.get(), Manuscript.Types.COAT),

                Map.entry(ModItems.HOOD.get(), Manuscript.Types.COIF),
                Map.entry(ModItems.TORN_HOOD.get(), Manuscript.Types.COIF),
                Map.entry(ModItems.JESTER_HOOD.get(), Manuscript.Types.COIF),
                Map.entry(ModItems.HELMET_HOOD.get(), Manuscript.Types.COIF),
                Map.entry(ModItems.HELMET_TORN_HOOD.get(), Manuscript.Types.COIF),

                Map.entry(ModItems.LONGBOW.get(), Manuscript.Types.LONGBOW),

                Map.entry(ModItems.HEAVY_CROSSBOW.get(), Manuscript.Types.CROSSBOW),

                Map.entry(ModItems.ARQUEBUS.get(), Manuscript.Types.ARQUEBUS),

                Map.entry(ModItems.HANDGONNE.get(), Manuscript.Types.HANDGONNE),

                Map.entry(ModItems.SWALLOWTAIL_ARROW.get(), Manuscript.Types.SWALLOWTAIL),
                Map.entry(ModItems.BODKIN_ARROW.get(), Manuscript.Types.BODKIN),
                Map.entry(ModItems.BROADHEAD_ARROW.get(), Manuscript.Types.BROAD),
                Map.entry(ModItems.CLOTH_ARROW.get(), Manuscript.Types.CLOTH),

                Map.entry(ModItems.HORSE_BARDING.get(), Manuscript.Types.HORSE)
        );

        for (Map.Entry<Item, Manuscript.Types> entry : itemTypesMap.entrySet()) {
            Item item = entry.getKey();
            Manuscript.Types type = entry.getValue();

            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
            String itemPath = itemId.getPath();

            registerWCustomName(item, ModelTemplates.FLAT_ITEM, itemModelGenerators, "manuscript_" + itemPath,
                    new ResourceLocation(StoneyCore.MOD_ID, "item/" + "manuscript_" + type.name().toLowerCase()));
        }
    }
}