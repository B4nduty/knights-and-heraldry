package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.stoneycore.StoneyCore;
import banduty.stoneycore.datagen.NeoForgeModelProviderPlus;
import banduty.stoneycore.items.custom.manuscript.Manuscript;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.Map;

public class ModModelProvider extends NeoForgeModelProviderPlus {

    public ModModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KnightsHeraldry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // --- Basic Flat Items ---
        Item[] simpleFlat = {
                // Basic items
                KHItems.CHAPERON.get(), KHItems.BROADHEAD_ARROW.get(), KHItems.BODKIN_ARROW.get(),
                KHItems.SWALLOWTAIL_ARROW.get(), KHItems.HOOD.get(), KHItems.TORN_HOOD.get(),
                KHItems.HELMET_HOOD.get(), KHItems.HELMET_TORN_HOOD.get(), KHItems.CLOAK.get(),
                KHItems.TORN_CLOAK.get(), KHItems.CIVILIAN_SURCOAT.get(), KHItems.GIORNEA.get(),
                KHItems.AVENTAIL.get(), KHItems.BEVOR.get(), KHItems.DARK_BEVOR.get(),
                KHItems.GOLDEN_BEVOR.get(), KHItems.RIM_GUARDS.get(), KHItems.BESAGEWS.get(),
                KHItems.QUILTED_COIF.get(), KHItems.GAMBESON_BREECHES.get(), KHItems.GAMBESON_BOOTS.get(),
                KHItems.MAIL_COIF.get(), KHItems.HAUBERK.get(), KHItems.MAIL_BREECHES.get(),
                KHItems.MAIL_BOOTS.get(), KHItems.LANCE.get(),

                // Plumes
                KHItems.PLUME.get(), KHItems.TRI_PLUME.get(), KHItems.FLUFFY_PLUME.get(),

                // Deco items - Snakes
                KHItems.TEUTONIC_SNAKES.get(), KHItems.TEUTONIC_BLACK_SNAKES.get(),

                // Deco items - Horns
                KHItems.GOLD_HORNS.get(), KHItems.BLACK_HORNS.get(),

                // Deco items - Wings
                KHItems.TEUTONIC_GOLD_WINGS.get(), KHItems.TEUTONIC_BLACK_WINGS.get(),
                KHItems.TEUTONIC_WINGS_BALL_ENDS.get(), KHItems.TEUTONIC_WINGS_SHARP_ENDS.get(),

                // Deco items - Heraldic beasts
                KHItems.DRAGON.get(), KHItems.LION.get(), KHItems.SNAKE.get(),
                KHItems.UNICORN.get(), KHItems.STAG.get(), KHItems.BOAR.get(),
                KHItems.EAGLE.get(), KHItems.PEGASUS.get()
        };

        for (Item item : simpleFlat) simpleItem(item);

        registerSimpleItems();

        // --- Banner Patterns ---
        registerSurcoatWithBanner(KHItems.SURCOAT.get());
        registerSurcoatWithBanner(KHItems.SURCOAT_SLEEVELESS.get());

        // --- Dyeable / Special Items ---
        Item[] dyeable = {
                KHItems.BRIGANDINE_HARNESS.get(), KHItems.DARK_BRIGANDINE_HARNESS.get(), KHItems.GOLDEN_BRIGANDINE_HARNESS.get(),
                KHItems.BRIGANDINE_CUISSES.get(), KHItems.DARK_BRIGANDINE_CUISSES.get(), KHItems.GOLDEN_BRIGANDINE_CUISSES.get(),
                KHItems.JESTER_HOOD.get(), KHItems.HORSE_BARDING.get(), KHItems.DARK_HORSE_BARDING.get(),
                KHItems.GOLDEN_HORSE_BARDING.get(), KHItems.GILDED_CHAPERON.get(), KHItems.GAMBESON.get(),
                KHItems.WOODEN_LANCE.get(), KHItems.BRIGANDINE.get(), KHItems.BRIG_BREASTPLATE.get(), KHItems.BRIG_BREASTPLATE_TASSETS.get(),
                KHItems.DARK_BRIGANDINE.get(), KHItems.DARK_BRIG_BREASTPLATE.get(), KHItems.DARK_BRIG_BREASTPLATE_TASSETS.get(),
                KHItems.GOLDEN_BRIGANDINE.get(), KHItems.GOLDEN_BRIG_BREASTPLATE.get(), KHItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get(),
                KHItems.BRIGANDINE_SPAULDERS.get(), KHItems.DARK_BRIGANDINE_SPAULDERS.get(), KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(),
                KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(), KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get()
        };
        for (Item item : dyeable) dyeableItem(item);

        // --- Helmet Deco ---
        for (HelmetDeco deco : HelmetDeco.all()) {
            if (deco.item() != KHItems.TORSE.get()) simpleItem(deco.item());
        }

        // --- Manuscripts ---
        registerManuscriptsItems();
    }

    protected void registerSurcoatWithBanner(Item surcoatItem) {
        String path = BuiltInRegistries.ITEM.getKey(surcoatItem).getPath();

        String[] patterns = new String[]{"bl", "bo", "br", "bri", "bs", "bt", "bts", "cbo", "cr", "cre", "cs", "dls", "drs", "flo", "glb", "gra", "gru", "hh", "hhb", "ld", "ls", "lud", "mc", "moj", "mr", "ms", "pig", "rd", "rs", "rud", "sc", "sku", "ss", "tl", "tr", "ts", "tt", "tts", "vh", "vhr"};

        for (String pattern : patterns) {
            String modelName = "item/" + path + "/" + pattern;
            withExistingParent(modelName, "item/generated")
                    .texture("layer0", modLoc("item/" + path + "/" + pattern))
                    .renderType("minecraft:translucent");
        }

/*        withExistingParent(
                path,                    // this is "surcoat"
                ResourceLocation.tryBuild("minecraft", "builtin/entity")
        );*/
    }

    private void registerSimpleItems() {
        Item[] brokenItems = {
                KHItems.DAGGER.get(), KHItems.STILETTO.get(), KHItems.RAPIER.get(), KHItems.SWORD.get(),
                KHItems.V_SWORD.get(), KHItems.ARMING_SWORD.get(), KHItems.AXE.get(), KHItems.BROAD_AXE.get(),
                KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get(), KHItems.MACE.get(),
                KHItems.SPIKED_MACE.get(), KHItems.HAMMER.get(), KHItems.WAR_HAMMER.get(),
                KHItems.LONGSWORD.get(), KHItems.V_LONGSWORD.get(), KHItems.FALCHION.get(),
                KHItems.SCIMITAR.get(), KHItems.PITCHFORK.get(), KHItems.SPEAR.get(), KHItems.PIKE.get(),
                KHItems.BILLHOOK.get(), KHItems.GLAIVE.get(), KHItems.CURVED_GLAIVE.get(),
                KHItems.HALBERD.get(), KHItems.POLEAXE.get(), KHItems.POLEHAMMER.get(),
                KHItems.BEC_DE_CORBIN.get(), KHItems.MORNING_STAR.get(), KHItems.BARDICHE.get(),
                KHItems.GREATSWORD.get(), KHItems.CLAYMORE.get(), KHItems.FLAMBERGE.get(),
                KHItems.ZWEIHANDER.get(), KHItems.WARDART.get()
        };
        for (Item item : brokenItems) {
            registerItemWConditions(item, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "broken"), 1));
        }

        Item[] openVisorHelmets = {
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
                KHItems.SAVOYARD.get(), KHItems.DARK_SAVOYARD.get(), KHItems.GOLDEN_SAVOYARD.get()
        };
        for (Item item : openVisorHelmets)
            registerItemWConditions(item, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1));

        Item[] simpleArmor = {
                KHItems.MAIL_SPAULDERS.get(), KHItems.MAIL_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_MAIL_SPAULDERS.get(),
                KHItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get(), KHItems.PLATE_SPAULDERS.get(), KHItems.PLATE_SPAULDERS_BESAGEWS.get(),
                KHItems.PLATE_SPAULDERS_RIMMED.get(), KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS.get(),
                KHItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.DARK_PLATE_SPAULDERS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(),
                KHItems.GOLDEN_PLATE_SPAULDERS.get(), KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(),
                KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(),
                KHItems.PLATE_CUIRASS.get(), KHItems.GOLDEN_PLATE_CUIRASS.get(), KHItems.DARK_PLATE_CUIRASS.get(),
                KHItems.PLATE_CUIRASS_TASSETS.get(), KHItems.DARK_PLATE_CUIRASS_TASSETS.get(), KHItems.GOLDEN_PLATE_CUIRASS_TASSETS.get(),
                KHItems.MAXIMILLIAN_CUIRASS.get(), KHItems.DARK_MAXIMILLIAN_CUIRASS.get(), KHItems.GOLDEN_MAXIMILLIAN_CUIRASS.get(),
                KHItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), KHItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS.get(), KHItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS.get(),
                KHItems.XIIII_PLATE_CUIRASS.get(), KHItems.XIIII_PLATE_CUIRASS_TASSETS.get(), KHItems.XIIII_PLATE_BREASTPLATE.get(),
                KHItems.DARK_XIIII_PLATE_CUIRASS.get(), KHItems.DARK_XIIII_PLATE_CUIRASS_TASSETS.get(), KHItems.DARK_XIIII_PLATE_BREASTPLATE.get(),
                KHItems.GOLDEN_XIIII_PLATE_CUIRASS.get(), KHItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS.get(), KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get(),
                KHItems.GREAVES.get(), KHItems.DARK_GREAVES.get(), KHItems.GOLDEN_GREAVES.get(),
                KHItems.SABATONS.get(), KHItems.DARK_SABATONS.get(), KHItems.GOLDEN_SABATONS.get(),
                KHItems.BARBUTE.get(), KHItems.DARK_BARBUTE.get(), KHItems.GOLDEN_BARBUTE.get(),
                KHItems.BASCINET.get(), KHItems.DARK_BASCINET.get(), KHItems.GOLDEN_BASCINET.get(),
                KHItems.KETTLE_HELM.get(), KHItems.DARK_KETTLE_HELM.get(), KHItems.GOLDEN_KETTLE_HELM.get(),
                KHItems.NASAL_HELM.get(), KHItems.DARK_NASAL_HELM.get(), KHItems.GOLDEN_NASAL_HELM.get(),
                KHItems.VIKING_HELM.get(), KHItems.DARK_VIKING_HELM.get(), KHItems.GOLDEN_VIKING_HELM.get(),
                KHItems.BURGONET.get(), KHItems.DARK_BURGONET.get(), KHItems.GOLDEN_BURGONET.get(),
                KHItems.FROGMOUTH.get(), KHItems.DARK_FROGMOUTH.get(), KHItems.GOLDEN_FROGMOUTH.get(),
                KHItems.GAUNTLET.get(), KHItems.DARK_GAUNTLET.get(), KHItems.GOLDEN_GAUNTLET.get(),
                KHItems.PLATE_HARNESS.get(), KHItems.DARK_PLATE_HARNESS.get(), KHItems.GOLDEN_PLATE_HARNESS.get(),
                KHItems.PLATE_CUISSES.get(), KHItems.DARK_PLATE_CUISSES.get(), KHItems.GOLDEN_PLATE_CUISSES.get(),
                KHItems.BRIGANDINE.get(), KHItems.DARK_BRIGANDINE.get(), KHItems.GOLDEN_BRIGANDINE.get(),
                KHItems.BRIG_BREASTPLATE.get(), KHItems.DARK_BRIG_BREASTPLATE.get(), KHItems.GOLDEN_BRIG_BREASTPLATE.get(),
                KHItems.BRIG_BREASTPLATE_TASSETS.get(), KHItems.DARK_BRIG_BREASTPLATE_TASSETS.get(), KHItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get(),
                KHItems.GREAT_HELM.get(), KHItems.DARK_GREAT_HELM.get(), KHItems.GOLDEN_GREAT_HELM.get(),
                KHItems.GREAT_HELM_2.get(), KHItems.DARK_GREAT_HELM_2.get(), KHItems.GOLDEN_GREAT_HELM_2.get()
        };
        for (Item item : simpleArmor) simpleItem(item);
    }

    private void registerManuscriptsItems() {
        Map<Item, Manuscript.Types> itemTypesMap = new HashMap<>();
        itemTypesMap.put(KHItems.DAGGER.get(), Manuscript.Types.BROAD);
        itemTypesMap.put(KHItems.STILETTO.get(), Manuscript.Types.BROAD);
        itemTypesMap.put(KHItems.RAPIER.get(), Manuscript.Types.BROAD);
        itemTypesMap.put(KHItems.SWORD.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(KHItems.V_SWORD.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(KHItems.ARMING_SWORD.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(KHItems.AXE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.BROAD_AXE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.CROOKED_AXE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.STRAIGHT_CROOKED_AXE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.MACE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.SPIKED_MACE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.FLAIL.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.BALL_FLAIL.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.HAMMER.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.WAR_HAMMER.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.LONGSWORD.get(), Manuscript.Types.LONGSWORDS);
        itemTypesMap.put(KHItems.V_LONGSWORD.get(), Manuscript.Types.LONGSWORDS);
        itemTypesMap.put(KHItems.FALCHION.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(KHItems.SCIMITAR.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(KHItems.PITCHFORK.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.SPEAR.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.PIKE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.BILLHOOK.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.GLAIVE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.CURVED_GLAIVE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.HALBERD.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.LANCE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.WOODEN_LANCE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.POLEAXE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.POLEHAMMER.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.BEC_DE_CORBIN.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.MORNING_STAR.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.BARDICHE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(KHItems.GREATSWORD.get(), Manuscript.Types.GREATSWORDS);
        itemTypesMap.put(KHItems.CLAYMORE.get(), Manuscript.Types.GREATSWORDS);
        itemTypesMap.put(KHItems.FLAMBERGE.get(), Manuscript.Types.GREATSWORDS);
        itemTypesMap.put(KHItems.ZWEIHANDER.get(), Manuscript.Types.GREATSWORDS);
        itemTypesMap.put(KHItems.WARDART.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(KHItems.QUILTED_COIF.get(), Manuscript.Types.COIF);
        itemTypesMap.put(KHItems.GAMBESON.get(), Manuscript.Types.COAT);
        itemTypesMap.put(KHItems.GAMBESON_BREECHES.get(), Manuscript.Types.BREECHES);
        itemTypesMap.put(KHItems.GAMBESON_BOOTS.get(), Manuscript.Types.BOOTS);
        itemTypesMap.put(KHItems.MAIL_COIF.get(), Manuscript.Types.COIF);
        itemTypesMap.put(KHItems.HAUBERK.get(), Manuscript.Types.COAT);
        itemTypesMap.put(KHItems.MAIL_BREECHES.get(), Manuscript.Types.BREECHES);
        itemTypesMap.put(KHItems.MAIL_BOOTS.get(), Manuscript.Types.BOOTS);
        itemTypesMap.put(KHItems.MAIL_SPAULDERS.get(), Manuscript.Types.SPAULDERS);
        itemTypesMap.put(KHItems.BRIGANDINE_SPAULDERS.get(), Manuscript.Types.SPAULDERS);
        itemTypesMap.put(KHItems.PLATE_SPAULDERS.get(), Manuscript.Types.SPAULDERS);
        itemTypesMap.put(KHItems.BRIGANDINE.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.BRIG_BREASTPLATE.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.BRIG_BREASTPLATE_TASSETS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.PLATE_CUIRASS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.PLATE_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.MAXIMILLIAN_CUIRASS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.XIIII_PLATE_CUIRASS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.XIIII_PLATE_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.XIIII_PLATE_BREASTPLATE.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(KHItems.RIM_GUARDS.get(), Manuscript.Types.RIM_GUARDS);
        itemTypesMap.put(KHItems.BESAGEWS.get(), Manuscript.Types.BESAGEWS);
        itemTypesMap.put(KHItems.BARBUTE.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.BASCINET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.KETTLE_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.NASAL_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.VIKING_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.BURGONET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.ARMET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.ARMET_2.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.VISORED_BARBUTE.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.HOUNDSKULL.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.CAGE.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.VISORED_BASCINET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.GREAT_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.SALLET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.BURGONET_FALLING_BUFFE.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.CLOSE_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.FROGMOUTH.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.GREAT_ARMET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.GREAT_ARMET_2.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.GREAT_BASCINET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.GREAT_HOUNDSKUL_BASCINET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.MAXIMILLIAN_HELMET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.SAVOYARD.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(KHItems.GAUNTLET.get(), Manuscript.Types.GAUNTLETS);
        itemTypesMap.put(KHItems.BRIGANDINE_HARNESS.get(), Manuscript.Types.GAUNTLETS);
        itemTypesMap.put(KHItems.PLATE_HARNESS.get(), Manuscript.Types.GAUNTLETS);
        itemTypesMap.put(KHItems.BRIGANDINE_CUISSES.get(), Manuscript.Types.CHAUSSES);
        itemTypesMap.put(KHItems.PLATE_CUISSES.get(), Manuscript.Types.CHAUSSES);
        itemTypesMap.put(KHItems.GREAVES.get(), Manuscript.Types.GREAVES);
        itemTypesMap.put(KHItems.SABATONS.get(), Manuscript.Types.BOOTS);
        itemTypesMap.put(KHItems.SURCOAT.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(KHItems.SURCOAT_SLEEVELESS.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(KHItems.CIVILIAN_SURCOAT.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(KHItems.GIORNEA.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(KHItems.CLOAK.get(), Manuscript.Types.COAT);
        itemTypesMap.put(KHItems.TORN_CLOAK.get(), Manuscript.Types.COAT);
        itemTypesMap.put(KHItems.HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(KHItems.TORN_HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(KHItems.JESTER_HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(KHItems.HELMET_HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(KHItems.HELMET_TORN_HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(KHItems.LONGBOW.get(), Manuscript.Types.LONGBOW);
        itemTypesMap.put(KHItems.HEAVY_CROSSBOW.get(), Manuscript.Types.CROSSBOW);
        itemTypesMap.put(KHItems.ARQUEBUS.get(), Manuscript.Types.ARQUEBUS);
        itemTypesMap.put(KHItems.HANDGONNE.get(), Manuscript.Types.HANDGONNE);
        itemTypesMap.put(KHItems.SWALLOWTAIL_ARROW.get(), Manuscript.Types.SWALLOWTAIL);
        itemTypesMap.put(KHItems.BODKIN_ARROW.get(), Manuscript.Types.BODKIN);
        itemTypesMap.put(KHItems.BROADHEAD_ARROW.get(), Manuscript.Types.BROAD);
        itemTypesMap.put(KHItems.CLOTH_ARROW.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(KHItems.HORSE_BARDING.get(), Manuscript.Types.HORSE);

        for (Map.Entry<Item, Manuscript.Types> entry : itemTypesMap.entrySet()) {
            String path = BuiltInRegistries.ITEM.getKey(entry.getKey()).getPath();
            String manuscriptType = entry.getValue().name().toLowerCase();

            withExistingParent("manuscript_" + path, "item/generated")
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(StoneyCore.MOD_ID, "item/manuscript_" + manuscriptType));
        }
    }

    private void dyeableItem(Item item) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        withExistingParent(path, "item/generated")
                .texture("layer0", modLoc("item/" + path))
                .texture("layer1", modLoc("item/" + path + "_overlay"));
    }

    private void simpleItem(Item item) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        withExistingParent(path, "item/generated")
                .texture("layer0", modLoc("item/" + path));
    }
}