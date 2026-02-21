package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.stoneycore.StoneyCore;
import banduty.stoneycore.datagen.FabricModelProviderPlus;
import banduty.stoneycore.items.manuscript.Manuscript;
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
        itemModelGenerators.generateFlatItem(ModItems.CHAPERON, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BROADHEAD_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BODKIN_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SWALLOWTAIL_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HOOD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TORN_HOOD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HELMET_HOOD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HELMET_TORN_HOOD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CLOAK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TORN_CLOAK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SURCOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SURCOAT_SLEEVELESS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CIVILIAN_SURCOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GIORNEA, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.AVENTAIL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BEVOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DARK_BEVOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GOLDEN_BEVOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RIM_GUARDS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BESAGEWS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.QUILTED_COIF, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GAMBESON_BREECHES, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GAMBESON_BOOTS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MAIL_COIF, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.HAUBERK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MAIL_BREECHES, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MAIL_BOOTS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LONGBOW, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LANCE, ModelTemplates.FLAT_ITEM);

        registerSimpleItems(itemModelGenerators);

        registerManuscriptsItems(itemModelGenerators);

        generateBannerPatternModels(ModItems.SURCOAT, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        generateBannerPatternModels(ModItems.SURCOAT_SLEEVELESS, ModelTemplates.FLAT_ITEM, itemModelGenerators);

        registerHelmetDecoItems(itemModelGenerators);

        // Dyeable Items
        registerItemWConditions(ModItems.BRIGANDINE_HARNESS, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.DARK_BRIGANDINE_HARNESS, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GOLDEN_BRIGANDINE_HARNESS, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.BRIGANDINE_CUISSES, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.DARK_BRIGANDINE_CUISSES, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GOLDEN_BRIGANDINE_CUISSES, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.JESTER_HOOD, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.HORSE_BARDING, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.DARK_HORSE_BARDING, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GOLDEN_HORSE_BARDING, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GILDED_CHAPERON, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.GAMBESON, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        registerItemWConditions(ModItems.WOODEN_LANCE, ModelTemplates.FLAT_ITEM, itemModelGenerators);
    }

    private void registerSimpleItems(ItemModelGenerators itemModelGenerators) {
        Item[] simpleHandheldItems = {
                ModItems.DAGGER, ModItems.STILETTO, ModItems.RAPIER,
                ModItems.SWORD, ModItems.V_SWORD, ModItems.ARMING_SWORD,
                ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE, ModItems.STRAIGHT_CROOKED_AXE,
                ModItems.MACE, ModItems.SPIKED_MACE,
                ModItems.HAMMER, ModItems.WAR_HAMMER,
                ModItems.LONGSWORD, ModItems.V_LONGSWORD,
                ModItems.FALCHION, ModItems.SCIMITAR,
                ModItems.PITCHFORK,
                ModItems.SPEAR,
                ModItems.PIKE,
                ModItems.BILLHOOK,
                ModItems.GLAIVE, ModItems.CURVED_GLAIVE,
                ModItems.HALBERD,
                ModItems.POLEAXE,
                ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN,
                ModItems.MORNING_STAR,
                ModItems.BARDICHE,
                ModItems.GREATSWORD, ModItems.CLAYMORE, ModItems.FLAMBERGE, ModItems.ZWEIHANDER,
                ModItems.WARDART
        };

        Item[] simpleArmor = {
                ModItems.MAIL_SPAULDERS, ModItems.MAIL_SPAULDERS_BESAGEWS, ModItems.GOLDEN_MAIL_SPAULDERS,
                ModItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS, ModItems.BRIGANDINE_SPAULDERS, ModItems.BRIGANDINE_SPAULDERS_BESAGEWS,
                ModItems.DARK_BRIGANDINE_SPAULDERS, ModItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS, ModItems.GOLDEN_BRIGANDINE_SPAULDERS,
                ModItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS, ModItems.PLATE_SPAULDERS, ModItems.PLATE_SPAULDERS_BESAGEWS,
                ModItems.PLATE_SPAULDERS_RIMMED, ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.DARK_PLATE_SPAULDERS,
                ModItems.DARK_PLATE_SPAULDERS_BESAGEWS, ModItems.DARK_PLATE_SPAULDERS_RIMMED, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED,
                ModItems.GOLDEN_PLATE_SPAULDERS, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS, ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED,
                ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED,
                ModItems.PLATE_CUIRASS, ModItems.GOLDEN_PLATE_CUIRASS, ModItems.DARK_PLATE_CUIRASS,
                ModItems.PLATE_CUIRASS_TASSETS,ModItems.DARK_PLATE_CUIRASS_TASSETS,  ModItems.GOLDEN_PLATE_CUIRASS_TASSETS,
                ModItems.MAXIMILLIAN_CUIRASS, ModItems.DARK_MAXIMILLIAN_CUIRASS, ModItems.GOLDEN_MAXIMILLIAN_CUIRASS,
                ModItems.MAXIMILLIAN_CUIRASS_TASSETS, ModItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS, ModItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS,
                ModItems.XIIII_PLATE_CUIRASS, ModItems.XIIII_PLATE_CUIRASS_TASSETS, ModItems.XIIII_PLATE_BREASTPLATE,
                ModItems.DARK_XIIII_PLATE_CUIRASS, ModItems.DARK_XIIII_PLATE_CUIRASS_TASSETS, ModItems.DARK_XIIII_PLATE_BREASTPLATE,
                ModItems.GOLDEN_XIIII_PLATE_CUIRASS, ModItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS, ModItems.GOLDEN_XIIII_PLATE_BREASTPLATE,
                ModItems.GREAVES, ModItems.DARK_GREAVES, ModItems.GOLDEN_GREAVES,
                ModItems.SABATONS, ModItems.DARK_SABATONS, ModItems.GOLDEN_SABATONS,
                ModItems.BARBUTE, ModItems.DARK_BARBUTE, ModItems.GOLDEN_BARBUTE,
                ModItems.BASCINET, ModItems.DARK_BASCINET, ModItems.GOLDEN_BASCINET,
                ModItems.KETTLE_HELM, ModItems.DARK_KETTLE_HELM, ModItems.GOLDEN_KETTLE_HELM,
                ModItems.NASAL_HELM, ModItems.DARK_NASAL_HELM, ModItems.GOLDEN_NASAL_HELM,
                ModItems.VIKING_HELM, ModItems.DARK_VIKING_HELM, ModItems.GOLDEN_VIKING_HELM,
                ModItems.BURGONET, ModItems.DARK_BURGONET, ModItems.GOLDEN_BURGONET,
                ModItems.FROGMOUTH, ModItems.DARK_FROGMOUTH, ModItems.GOLDEN_FROGMOUTH,
                ModItems.GAUNTLET, ModItems.DARK_GAUNTLET, ModItems.GOLDEN_GAUNTLET,
                ModItems.PLATE_HARNESS, ModItems.DARK_PLATE_HARNESS, ModItems.GOLDEN_PLATE_HARNESS,
                ModItems.PLATE_CUISSES, ModItems.DARK_PLATE_CUISSES, ModItems.GOLDEN_PLATE_CUISSES,
                ModItems.BRIGANDINE, ModItems.DARK_BRIGANDINE, ModItems.GOLDEN_BRIGANDINE,
                ModItems.BRIG_BREASTPLATE, ModItems.DARK_BRIG_BREASTPLATE, ModItems.GOLDEN_BRIG_BREASTPLATE,
                ModItems.BRIG_BREASTPLATE_TASSETS, ModItems.DARK_BRIG_BREASTPLATE_TASSETS, ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS,
                ModItems.GREAT_HELM, ModItems.DARK_GREAT_HELM, ModItems.GOLDEN_GREAT_HELM,
                ModItems.GREAT_HELM_2, ModItems.DARK_GREAT_HELM_2, ModItems.GOLDEN_GREAT_HELM_2
        };

        Item[] openVisorHelmet = {
                ModItems.ARMET, ModItems.DARK_ARMET, ModItems.GOLDEN_ARMET,
                ModItems.ARMET_2, ModItems.DARK_ARMET_2, ModItems.GOLDEN_ARMET_2,
                ModItems.VISORED_BARBUTE, ModItems.DARK_VISORED_BARBUTE, ModItems.GOLDEN_VISORED_BARBUTE,
                ModItems.HOUNDSKULL, ModItems.DARK_HOUNDSKULL, ModItems.GOLDEN_HOUNDSKULL,
                ModItems.CAGE, ModItems.DARK_CAGE, ModItems.GOLDEN_CAGE,
                ModItems.CAGE_2, ModItems.DARK_CAGE_2, ModItems.GOLDEN_CAGE_2,
                ModItems.VISORED_BASCINET, ModItems.DARK_VISORED_BASCINET, ModItems.GOLDEN_VISORED_BASCINET,
                ModItems.SALLET, ModItems.DARK_SALLET, ModItems.GOLDEN_SALLET,
                ModItems.BURGONET_FALLING_BUFFE, ModItems.DARK_BURGONET_FALLING_BUFFE, ModItems.GOLDEN_BURGONET_FALLING_BUFFE,
                ModItems.CLOSE_HELM, ModItems.DARK_CLOSE_HELM, ModItems.GOLDEN_CLOSE_HELM,
                ModItems.GREAT_ARMET, ModItems.DARK_GREAT_ARMET, ModItems.GOLDEN_GREAT_ARMET,
                ModItems.GREAT_ARMET_2, ModItems.DARK_GREAT_ARMET_2, ModItems.GOLDEN_GREAT_ARMET_2,
                ModItems.GREAT_BASCINET, ModItems.DARK_GREAT_BASCINET, ModItems.GOLDEN_GREAT_BASCINET,
                ModItems.GREAT_HOUNDSKUL_BASCINET, ModItems.DARK_GREAT_HOUNDSKUL_BASCINET, ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET,
                ModItems.MAXIMILLIAN_HELMET, ModItems.DARK_MAXIMILLIAN_HELMET, ModItems.GOLDEN_MAXIMILLIAN_HELMET,
                ModItems.SAVOYARD, ModItems.DARK_SAVOYARD, ModItems.GOLDEN_SAVOYARD
        };

        for (Item item : simpleHandheldItems) {
            registerItemWConditions(item, ModelTemplates.FLAT_HANDHELD_ITEM, itemModelGenerators, new OverrideCondition(new ResourceLocation(KnightsHeraldry.MOD_ID,"broken"), 1));
        }

        for (Item item : openVisorHelmet) {
            registerItemWConditions(item, ModelTemplates.FLAT_ITEM, itemModelGenerators, new OverrideCondition(new ResourceLocation(KnightsHeraldry.MOD_ID,"open"), 1));
        }

        for (Item item : simpleArmor) {
            registerItemWConditions(item, ModelTemplates.FLAT_ITEM, itemModelGenerators);
        }
    }

    private void registerHelmetDecoItems(ItemModelGenerators itemModelGenerators) {
        for (Item deco : HelmetDeco.getRegisteredItems()) {
            if (deco == ModItems.TORSE) continue;
            itemModelGenerators.generateFlatItem(deco, ModelTemplates.FLAT_ITEM);
        }
    }

    private void registerManuscriptsItems(ItemModelGenerators itemModelGenerators) {
        Map<Item, Manuscript.Types> itemTypesMap = Map.<Item, Manuscript.Types>ofEntries(
                Map.entry(ModItems.DAGGER, Manuscript.Types.BROAD),
                Map.entry(ModItems.STILETTO, Manuscript.Types.BROAD),
                Map.entry(ModItems.RAPIER, Manuscript.Types.BROAD),
                Map.entry(ModItems.SWORD, Manuscript.Types.SWORDS),
                Map.entry(ModItems.V_SWORD, Manuscript.Types.SWORDS),
                Map.entry(ModItems.ARMING_SWORD, Manuscript.Types.SWORDS),
                Map.entry(ModItems.AXE, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.BROAD_AXE, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.CROOKED_AXE, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.STRAIGHT_CROOKED_AXE, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.MACE, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.SPIKED_MACE, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.FLAIL, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.BALL_FLAIL, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.HAMMER, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.WAR_HAMMER, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.LONGSWORD, Manuscript.Types.LONGSWORDS),
                Map.entry(ModItems.V_LONGSWORD, Manuscript.Types.LONGSWORDS),
                Map.entry(ModItems.FALCHION, Manuscript.Types.SWORDS),
                Map.entry(ModItems.SCIMITAR, Manuscript.Types.SWORDS),
                Map.entry(ModItems.PITCHFORK, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.SPEAR, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.PIKE, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.BILLHOOK, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.GLAIVE, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.CURVED_GLAIVE, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.HALBERD, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.LANCE, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.WOODEN_LANCE, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.POLEAXE, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.POLEHAMMER, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.BEC_DE_CORBIN, Manuscript.Types.LONG_HAFTED),
                Map.entry(ModItems.MORNING_STAR, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.BARDICHE, Manuscript.Types.SHORT_HAFTED),
                Map.entry(ModItems.GREATSWORD, Manuscript.Types.GREATSWORDS),
                Map.entry(ModItems.CLAYMORE, Manuscript.Types.GREATSWORDS),
                Map.entry(ModItems.FLAMBERGE, Manuscript.Types.GREATSWORDS),
                Map.entry(ModItems.ZWEIHANDER, Manuscript.Types.GREATSWORDS),
                Map.entry(ModItems.WARDART, Manuscript.Types.LONG_HAFTED),

                Map.entry(ModItems.QUILTED_COIF, Manuscript.Types.COIF),
                Map.entry(ModItems.GAMBESON, Manuscript.Types.COAT),
                Map.entry(ModItems.GAMBESON_BREECHES, Manuscript.Types.BREECHES),
                Map.entry(ModItems.GAMBESON_BOOTS, Manuscript.Types.BOOTS),

                Map.entry(ModItems.MAIL_COIF, Manuscript.Types.COIF),
                Map.entry(ModItems.HAUBERK, Manuscript.Types.COAT),
                Map.entry(ModItems.MAIL_BREECHES, Manuscript.Types.BREECHES),
                Map.entry(ModItems.MAIL_BOOTS, Manuscript.Types.BOOTS),

                Map.entry(ModItems.MAIL_SPAULDERS, Manuscript.Types.SPAULDERS),
                Map.entry(ModItems.BRIGANDINE_SPAULDERS, Manuscript.Types.SPAULDERS),
                Map.entry(ModItems.PLATE_SPAULDERS, Manuscript.Types.SPAULDERS),

                Map.entry(ModItems.BRIGANDINE, Manuscript.Types.BREASTPLATE),

                Map.entry(ModItems.BRIG_BREASTPLATE, Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.BRIG_BREASTPLATE_TASSETS, Manuscript.Types.BREASTPLATE),

                Map.entry(ModItems.PLATE_CUIRASS, Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.PLATE_CUIRASS_TASSETS, Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.MAXIMILLIAN_CUIRASS, Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.MAXIMILLIAN_CUIRASS_TASSETS, Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.XIIII_PLATE_CUIRASS, Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.XIIII_PLATE_CUIRASS_TASSETS, Manuscript.Types.BREASTPLATE),
                Map.entry(ModItems.XIIII_PLATE_BREASTPLATE, Manuscript.Types.BREASTPLATE),

                Map.entry(ModItems.RIM_GUARDS, Manuscript.Types.RIM_GUARDS),
                Map.entry(ModItems.BESAGEWS, Manuscript.Types.BESAGEWS),

                Map.entry(ModItems.BARBUTE, Manuscript.Types.HELMET),
                Map.entry(ModItems.BASCINET, Manuscript.Types.HELMET),
                Map.entry(ModItems.KETTLE_HELM, Manuscript.Types.HELMET),
                Map.entry(ModItems.NASAL_HELM, Manuscript.Types.HELMET),
                Map.entry(ModItems.VIKING_HELM, Manuscript.Types.HELMET),
                Map.entry(ModItems.BURGONET, Manuscript.Types.HELMET),

                Map.entry(ModItems.ARMET, Manuscript.Types.HELMET),
                Map.entry(ModItems.ARMET_2, Manuscript.Types.HELMET),
                Map.entry(ModItems.VISORED_BARBUTE, Manuscript.Types.HELMET),
                Map.entry(ModItems.HOUNDSKULL, Manuscript.Types.HELMET),
                Map.entry(ModItems.CAGE, Manuscript.Types.HELMET),
                Map.entry(ModItems.CAGE_2, Manuscript.Types.HELMET),
                Map.entry(ModItems.VISORED_BASCINET, Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_HELM, Manuscript.Types.HELMET),
                Map.entry(ModItems.SALLET, Manuscript.Types.HELMET),
                Map.entry(ModItems.BURGONET_FALLING_BUFFE, Manuscript.Types.HELMET),
                Map.entry(ModItems.CLOSE_HELM, Manuscript.Types.HELMET),

                Map.entry(ModItems.FROGMOUTH, Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_ARMET, Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_ARMET_2, Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_BASCINET, Manuscript.Types.HELMET),
                Map.entry(ModItems.GREAT_HOUNDSKUL_BASCINET, Manuscript.Types.HELMET),
                Map.entry(ModItems.MAXIMILLIAN_HELMET, Manuscript.Types.HELMET),
                Map.entry(ModItems.SAVOYARD, Manuscript.Types.HELMET),

                Map.entry(ModItems.GAUNTLET, Manuscript.Types.GAUNTLETS),
                Map.entry(ModItems.BRIGANDINE_HARNESS, Manuscript.Types.GAUNTLETS),
                Map.entry(ModItems.PLATE_HARNESS, Manuscript.Types.GAUNTLETS),

                Map.entry(ModItems.BRIGANDINE_CUISSES, Manuscript.Types.CHAUSSES),
                Map.entry(ModItems.PLATE_CUISSES, Manuscript.Types.CHAUSSES),

                Map.entry(ModItems.GREAVES, Manuscript.Types.GREAVES),

                Map.entry(ModItems.SABATONS, Manuscript.Types.BOOTS),

                Map.entry(ModItems.SURCOAT, Manuscript.Types.CLOTH),
                Map.entry(ModItems.SURCOAT_SLEEVELESS, Manuscript.Types.CLOTH),
                Map.entry(ModItems.CIVILIAN_SURCOAT, Manuscript.Types.CLOTH),
                Map.entry(ModItems.GIORNEA, Manuscript.Types.CLOTH),

                Map.entry(ModItems.CLOAK, Manuscript.Types.COAT),
                Map.entry(ModItems.TORN_CLOAK, Manuscript.Types.COAT),

                Map.entry(ModItems.HOOD, Manuscript.Types.COIF),
                Map.entry(ModItems.TORN_HOOD, Manuscript.Types.COIF),
                Map.entry(ModItems.JESTER_HOOD, Manuscript.Types.COIF),
                Map.entry(ModItems.HELMET_HOOD, Manuscript.Types.COIF),
                Map.entry(ModItems.HELMET_TORN_HOOD, Manuscript.Types.COIF),

                Map.entry(ModItems.LONGBOW, Manuscript.Types.LONGBOW),

                Map.entry(ModItems.HEAVY_CROSSBOW, Manuscript.Types.CROSSBOW),

                Map.entry(ModItems.ARQUEBUS, Manuscript.Types.ARQUEBUS),

                Map.entry(ModItems.HANDGONNE, Manuscript.Types.HANDGONNE),

                Map.entry(ModItems.SWALLOWTAIL_ARROW, Manuscript.Types.SWALLOWTAIL),
                Map.entry(ModItems.BODKIN_ARROW, Manuscript.Types.BODKIN),
                Map.entry(ModItems.BROADHEAD_ARROW, Manuscript.Types.BROAD),
                Map.entry(ModItems.CLOTH_ARROW, Manuscript.Types.CLOTH),

                Map.entry(ModItems.HORSE_BARDING, Manuscript.Types.HORSE)
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