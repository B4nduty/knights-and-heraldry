package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.stoneycore.StoneyCore;
import banduty.stoneycore.datagen.ForgeModelProviderPlus;
import banduty.stoneycore.items.manuscript.Manuscript;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.Map;

public class ModModelProvider extends ForgeModelProviderPlus {

    public ModModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KnightsHeraldry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // --- Basic Flat Items ---
        Item[] simpleFlat = {
                // Basic items
                ModItems.CHAPERON.get(), ModItems.BROADHEAD_ARROW.get(), ModItems.BODKIN_ARROW.get(),
                ModItems.SWALLOWTAIL_ARROW.get(), ModItems.HOOD.get(), ModItems.TORN_HOOD.get(),
                ModItems.HELMET_HOOD.get(), ModItems.HELMET_TORN_HOOD.get(), ModItems.CLOAK.get(),
                ModItems.TORN_CLOAK.get(), ModItems.CIVILIAN_SURCOAT.get(), ModItems.GIORNEA.get(),
                ModItems.AVENTAIL.get(), ModItems.BEVOR.get(), ModItems.DARK_BEVOR.get(),
                ModItems.GOLDEN_BEVOR.get(), ModItems.RIM_GUARDS.get(), ModItems.BESAGEWS.get(),
                ModItems.QUILTED_COIF.get(), ModItems.GAMBESON_BREECHES.get(), ModItems.GAMBESON_BOOTS.get(),
                ModItems.MAIL_COIF.get(), ModItems.HAUBERK.get(), ModItems.MAIL_BREECHES.get(),
                ModItems.MAIL_BOOTS.get(), ModItems.LANCE.get(),

                // Plumes
                ModItems.PLUME.get(), ModItems.TRI_PLUME.get(), ModItems.FLUFFY_PLUME.get(),

                // Deco items - Snakes
                ModItems.TEUTONIC_SNAKES.get(), ModItems.TEUTONIC_BLACK_SNAKES.get(),

                // Deco items - Horns
                ModItems.GOLD_HORNS.get(), ModItems.BLACK_HORNS.get(),

                // Deco items - Wings
                ModItems.TEUTONIC_GOLD_WINGS.get(), ModItems.TEUTONIC_BLACK_WINGS.get(),
                ModItems.TEUTONIC_WINGS_BALL_ENDS.get(), ModItems.TEUTONIC_WINGS_SHARP_ENDS.get(),

                // Deco items - Heraldic beasts
                ModItems.DRAGON.get(), ModItems.LION.get(), ModItems.SNAKE.get(),
                ModItems.UNICORN.get(), ModItems.STAG.get(), ModItems.BOAR.get(),
                ModItems.EAGLE.get(), ModItems.PEGASUS.get()
        };

        for (Item item : simpleFlat) simpleItem(item);

        registerSimpleItems();

        // --- Banner Patterns ---
        registerSurcoatWithBanner(ModItems.SURCOAT.get());
        registerSurcoatWithBanner(ModItems.SURCOAT_SLEEVELESS.get());

        // --- Dyeable / Special Items ---
        Item[] dyeable = {
                ModItems.BRIGANDINE_HARNESS.get(), ModItems.DARK_BRIGANDINE_HARNESS.get(), ModItems.GOLDEN_BRIGANDINE_HARNESS.get(),
                ModItems.BRIGANDINE_CUISSES.get(), ModItems.DARK_BRIGANDINE_CUISSES.get(), ModItems.GOLDEN_BRIGANDINE_CUISSES.get(),
                ModItems.JESTER_HOOD.get(), ModItems.HORSE_BARDING.get(), ModItems.DARK_HORSE_BARDING.get(),
                ModItems.GOLDEN_HORSE_BARDING.get(), ModItems.GILDED_CHAPERON.get(), ModItems.GAMBESON.get(),
                ModItems.WOODEN_LANCE.get(), ModItems.BRIGANDINE.get(), ModItems.BRIG_BREASTPLATE.get(), ModItems.BRIG_BREASTPLATE_TASSETS.get(),
                ModItems.DARK_BRIGANDINE.get(), ModItems.DARK_BRIG_BREASTPLATE.get(), ModItems.DARK_BRIG_BREASTPLATE_TASSETS.get(),
                ModItems.GOLDEN_BRIGANDINE.get(), ModItems.GOLDEN_BRIG_BREASTPLATE.get(), ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get(),
                ModItems.BRIGANDINE_SPAULDERS.get(), ModItems.DARK_BRIGANDINE_SPAULDERS.get(), ModItems.GOLDEN_BRIGANDINE_SPAULDERS.get(),
                ModItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(), ModItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(), ModItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get()
        };
        for (Item item : dyeable) dyeableItem(item);

        // --- Helmet Deco ---
        for (Item deco : HelmetDeco.getRegisteredItems()) {
            if (deco != ModItems.TORSE.get()) simpleItem(deco);
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
                ModItems.DAGGER.get(), ModItems.STILETTO.get(), ModItems.RAPIER.get(), ModItems.SWORD.get(),
                ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get(), ModItems.AXE.get(), ModItems.BROAD_AXE.get(),
                ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get(), ModItems.MACE.get(),
                ModItems.SPIKED_MACE.get(), ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get(),
                ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get(), ModItems.FALCHION.get(),
                ModItems.SCIMITAR.get(), ModItems.PITCHFORK.get(), ModItems.SPEAR.get(), ModItems.PIKE.get(),
                ModItems.BILLHOOK.get(), ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get(),
                ModItems.HALBERD.get(), ModItems.POLEAXE.get(), ModItems.POLEHAMMER.get(),
                ModItems.BEC_DE_CORBIN.get(), ModItems.MORNING_STAR.get(), ModItems.BARDICHE.get(),
                ModItems.GREATSWORD.get(), ModItems.CLAYMORE.get(), ModItems.FLAMBERGE.get(),
                ModItems.ZWEIHANDER.get(), ModItems.WARDART.get()
        };
        for (Item item : brokenItems) {
            registerItemWConditions(item, new OverrideCondition(new ResourceLocation(KnightsHeraldry.MOD_ID, "broken"), 1));
        }

        Item[] openVisorHelmets = {
                ModItems.ARMET.get(), ModItems.DARK_ARMET.get(), ModItems.GOLDEN_ARMET.get(),
                ModItems.ARMET_2.get(), ModItems.DARK_ARMET_2.get(), ModItems.GOLDEN_ARMET_2.get(),
                ModItems.VISORED_BARBUTE.get(), ModItems.DARK_VISORED_BARBUTE.get(), ModItems.GOLDEN_VISORED_BARBUTE.get(),
                ModItems.HOUNDSKULL.get(), ModItems.DARK_HOUNDSKULL.get(), ModItems.GOLDEN_HOUNDSKULL.get(),
                ModItems.CAGE.get(), ModItems.DARK_CAGE.get(), ModItems.GOLDEN_CAGE.get(),
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
        for (Item item : openVisorHelmets)
            registerItemWConditions(item, new OverrideCondition(new ResourceLocation(KnightsHeraldry.MOD_ID, "open"), 1));

        Item[] simpleArmor = {
                ModItems.MAIL_SPAULDERS.get(), ModItems.MAIL_SPAULDERS_BESAGEWS.get(), ModItems.GOLDEN_MAIL_SPAULDERS.get(),
                ModItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get(), ModItems.PLATE_SPAULDERS.get(), ModItems.PLATE_SPAULDERS_BESAGEWS.get(),
                ModItems.PLATE_SPAULDERS_RIMMED.get(), ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.DARK_PLATE_SPAULDERS.get(),
                ModItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), ModItems.DARK_PLATE_SPAULDERS_RIMMED.get(), ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(),
                ModItems.GOLDEN_PLATE_SPAULDERS.get(), ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(),
                ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(),
                ModItems.PLATE_CUIRASS.get(), ModItems.GOLDEN_PLATE_CUIRASS.get(), ModItems.DARK_PLATE_CUIRASS.get(),
                ModItems.PLATE_CUIRASS_TASSETS.get(), ModItems.DARK_PLATE_CUIRASS_TASSETS.get(), ModItems.GOLDEN_PLATE_CUIRASS_TASSETS.get(),
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
        for (Item item : simpleArmor) simpleItem(item);
    }

    private void registerManuscriptsItems() {
        Map<Item, Manuscript.Types> itemTypesMap = new HashMap<>();
        itemTypesMap.put(ModItems.DAGGER.get(), Manuscript.Types.BROAD);
        itemTypesMap.put(ModItems.STILETTO.get(), Manuscript.Types.BROAD);
        itemTypesMap.put(ModItems.RAPIER.get(), Manuscript.Types.BROAD);
        itemTypesMap.put(ModItems.SWORD.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(ModItems.V_SWORD.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(ModItems.ARMING_SWORD.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(ModItems.AXE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.BROAD_AXE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.CROOKED_AXE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.STRAIGHT_CROOKED_AXE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.MACE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.SPIKED_MACE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.FLAIL.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.BALL_FLAIL.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.HAMMER.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.WAR_HAMMER.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.LONGSWORD.get(), Manuscript.Types.LONGSWORDS);
        itemTypesMap.put(ModItems.V_LONGSWORD.get(), Manuscript.Types.LONGSWORDS);
        itemTypesMap.put(ModItems.FALCHION.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(ModItems.SCIMITAR.get(), Manuscript.Types.SWORDS);
        itemTypesMap.put(ModItems.PITCHFORK.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.SPEAR.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.PIKE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.BILLHOOK.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.GLAIVE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.CURVED_GLAIVE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.HALBERD.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.LANCE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.WOODEN_LANCE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.POLEAXE.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.POLEHAMMER.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.BEC_DE_CORBIN.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.MORNING_STAR.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.BARDICHE.get(), Manuscript.Types.SHORT_HAFTED);
        itemTypesMap.put(ModItems.GREATSWORD.get(), Manuscript.Types.GREATSWORDS);
        itemTypesMap.put(ModItems.CLAYMORE.get(), Manuscript.Types.GREATSWORDS);
        itemTypesMap.put(ModItems.FLAMBERGE.get(), Manuscript.Types.GREATSWORDS);
        itemTypesMap.put(ModItems.ZWEIHANDER.get(), Manuscript.Types.GREATSWORDS);
        itemTypesMap.put(ModItems.WARDART.get(), Manuscript.Types.LONG_HAFTED);
        itemTypesMap.put(ModItems.QUILTED_COIF.get(), Manuscript.Types.COIF);
        itemTypesMap.put(ModItems.GAMBESON.get(), Manuscript.Types.COAT);
        itemTypesMap.put(ModItems.GAMBESON_BREECHES.get(), Manuscript.Types.BREECHES);
        itemTypesMap.put(ModItems.GAMBESON_BOOTS.get(), Manuscript.Types.BOOTS);
        itemTypesMap.put(ModItems.MAIL_COIF.get(), Manuscript.Types.COIF);
        itemTypesMap.put(ModItems.HAUBERK.get(), Manuscript.Types.COAT);
        itemTypesMap.put(ModItems.MAIL_BREECHES.get(), Manuscript.Types.BREECHES);
        itemTypesMap.put(ModItems.MAIL_BOOTS.get(), Manuscript.Types.BOOTS);
        itemTypesMap.put(ModItems.MAIL_SPAULDERS.get(), Manuscript.Types.SPAULDERS);
        itemTypesMap.put(ModItems.BRIGANDINE_SPAULDERS.get(), Manuscript.Types.SPAULDERS);
        itemTypesMap.put(ModItems.PLATE_SPAULDERS.get(), Manuscript.Types.SPAULDERS);
        itemTypesMap.put(ModItems.BRIGANDINE.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.BRIG_BREASTPLATE.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.BRIG_BREASTPLATE_TASSETS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.PLATE_CUIRASS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.PLATE_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.MAXIMILLIAN_CUIRASS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.XIIII_PLATE_CUIRASS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.XIIII_PLATE_BREASTPLATE.get(), Manuscript.Types.BREASTPLATE);
        itemTypesMap.put(ModItems.RIM_GUARDS.get(), Manuscript.Types.RIM_GUARDS);
        itemTypesMap.put(ModItems.BESAGEWS.get(), Manuscript.Types.BESAGEWS);
        itemTypesMap.put(ModItems.BARBUTE.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.BASCINET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.KETTLE_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.NASAL_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.VIKING_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.BURGONET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.ARMET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.ARMET_2.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.VISORED_BARBUTE.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.HOUNDSKULL.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.CAGE.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.VISORED_BASCINET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.GREAT_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.SALLET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.BURGONET_FALLING_BUFFE.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.CLOSE_HELM.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.FROGMOUTH.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.GREAT_ARMET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.GREAT_ARMET_2.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.GREAT_BASCINET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.GREAT_HOUNDSKUL_BASCINET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.MAXIMILLIAN_HELMET.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.SAVOYARD.get(), Manuscript.Types.HELMET);
        itemTypesMap.put(ModItems.GAUNTLET.get(), Manuscript.Types.GAUNTLETS);
        itemTypesMap.put(ModItems.BRIGANDINE_HARNESS.get(), Manuscript.Types.GAUNTLETS);
        itemTypesMap.put(ModItems.PLATE_HARNESS.get(), Manuscript.Types.GAUNTLETS);
        itemTypesMap.put(ModItems.BRIGANDINE_CUISSES.get(), Manuscript.Types.CHAUSSES);
        itemTypesMap.put(ModItems.PLATE_CUISSES.get(), Manuscript.Types.CHAUSSES);
        itemTypesMap.put(ModItems.GREAVES.get(), Manuscript.Types.GREAVES);
        itemTypesMap.put(ModItems.SABATONS.get(), Manuscript.Types.BOOTS);
        itemTypesMap.put(ModItems.SURCOAT.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(ModItems.SURCOAT_SLEEVELESS.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(ModItems.CIVILIAN_SURCOAT.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(ModItems.GIORNEA.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(ModItems.CLOAK.get(), Manuscript.Types.COAT);
        itemTypesMap.put(ModItems.TORN_CLOAK.get(), Manuscript.Types.COAT);
        itemTypesMap.put(ModItems.HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(ModItems.TORN_HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(ModItems.JESTER_HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(ModItems.HELMET_HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(ModItems.HELMET_TORN_HOOD.get(), Manuscript.Types.COIF);
        itemTypesMap.put(ModItems.LONGBOW.get(), Manuscript.Types.LONGBOW);
        itemTypesMap.put(ModItems.HEAVY_CROSSBOW.get(), Manuscript.Types.CROSSBOW);
        itemTypesMap.put(ModItems.ARQUEBUS.get(), Manuscript.Types.ARQUEBUS);
        itemTypesMap.put(ModItems.HANDGONNE.get(), Manuscript.Types.HANDGONNE);
        itemTypesMap.put(ModItems.SWALLOWTAIL_ARROW.get(), Manuscript.Types.SWALLOWTAIL);
        itemTypesMap.put(ModItems.BODKIN_ARROW.get(), Manuscript.Types.BODKIN);
        itemTypesMap.put(ModItems.BROADHEAD_ARROW.get(), Manuscript.Types.BROAD);
        itemTypesMap.put(ModItems.CLOTH_ARROW.get(), Manuscript.Types.CLOTH);
        itemTypesMap.put(ModItems.HORSE_BARDING.get(), Manuscript.Types.HORSE);

        for (Map.Entry<Item, Manuscript.Types> entry : itemTypesMap.entrySet()) {
            String path = BuiltInRegistries.ITEM.getKey(entry.getKey()).getPath();
            String manuscriptType = entry.getValue().name().toLowerCase();

            withExistingParent("manuscript_" + path, "item/generated")
                    .texture("layer0", new ResourceLocation(StoneyCore.MOD_ID, "item/manuscript_" + manuscriptType));
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