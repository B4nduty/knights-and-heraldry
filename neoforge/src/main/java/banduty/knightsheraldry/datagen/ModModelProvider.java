package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.armor.ArmorFamily;
import banduty.stoneycore.datagen.NeoForgeModelProviderPlus;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Supplier;

public class ModModelProvider extends NeoForgeModelProviderPlus {

    public ModModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KnightsHeraldry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // --- Basic Flat Items ---
        Item[] simpleFlat = {
                KHItems.MANUSCRIPT_DAGGER.get(),
                KHItems.MANUSCRIPT_SWORD.get(),
                KHItems.MANUSCRIPT_AXE.get(),
                KHItems.MANUSCRIPT_HAMMER.get(),
                KHItems.MANUSCRIPT_MACE.get(),
                KHItems.MANUSCRIPT_HALBERD.get(),
                KHItems.MANUSCRIPT_LONGSWORD.get(),
                KHItems.MANUSCRIPT_GREATSWORD.get(),
                KHItems.MANUSCRIPT_SPEAR.get(),
                KHItems.MANUSCRIPT_PITCHFORK.get(),

                KHItems.MANUSCRIPT_BARBUTE.get(),
                KHItems.MANUSCRIPT_BASCINET.get(),
                KHItems.MANUSCRIPT_KETTLE.get(),
                KHItems.MANUSCRIPT_NASAL.get(),
                KHItems.MANUSCRIPT_BURGONET.get(),
                KHItems.MANUSCRIPT_SALLET.get(),
                KHItems.MANUSCRIPT_MORION.get(),
                KHItems.MANUSCRIPT_ARMET.get(),
                KHItems.MANUSCRIPT_CAGE.get(),
                KHItems.MANUSCRIPT_GREAT_HELMET.get(),
                KHItems.MANUSCRIPT_CLOSE_HELMET.get(),
                KHItems.MANUSCRIPT_FROGMOUTH.get(),
                KHItems.MANUSCRIPT_MAXIMILIAN.get(),

                KHItems.MANUSCRIPT_VISOR.get(),
                KHItems.MANUSCRIPT_FALLING_BUFFE.get(),
                KHItems.MANUSCRIPT_BEVOR.get(),
                KHItems.MANUSCRIPT_AVENTAIL.get(),
                KHItems.MANUSCRIPT_CUIRASS.get(),
                KHItems.MANUSCRIPT_PLACKART.get(),
                KHItems.MANUSCRIPT_TASSETS.get(),
                KHItems.MANUSCRIPT_RIM_GUARDS.get(),
                KHItems.MANUSCRIPT_BESAGEWS.get(),
                KHItems.MANUSCRIPT_SPAULDERS.get(),
                KHItems.MANUSCRIPT_HARNESS.get(),
                KHItems.MANUSCRIPT_CUISSES.get(),
                KHItems.MANUSCRIPT_GREAVES.get(),
                KHItems.MANUSCRIPT_SABATONS.get(),
                KHItems.MANUSCRIPT_BARDING.get(),

                KHItems.MANUSCRIPT_SWALLOWTAIL.get(),
                KHItems.MANUSCRIPT_BODKIN.get(),
                KHItems.MANUSCRIPT_BROADHEAD.get(),
                KHItems.MANUSCRIPT_CLOTH.get(),

                KHItems.BARBUTE_PIECE.get(),
                KHItems.BASCINET_PIECE.get(),
                KHItems.KETTLE_PIECE.get(),
                KHItems.NASAL_PIECE.get(),
                KHItems.BURGONET_PIECE.get(),
                KHItems.SALLET_PIECE.get(),
                KHItems.MORION_PIECE.get(),
                KHItems.ARMET_PIECE.get(),
                KHItems.CAGE_PIECE.get(),
                KHItems.GREAT_HELMET_PIECE.get(),
                KHItems.CLOSE_HELMET_PIECE.get(),
                KHItems.FROGMOUTH_PIECE.get(),
                KHItems.MAXIMILIAN_PIECE.get(),

                KHItems.VISOR.get(),
                KHItems.FALLING_BUFFE.get(),
                KHItems.BEVOR.get(),

                KHItems.CUIRASS_PIECE.get(),
                KHItems.SPAULDERS_PIECE.get(),
                KHItems.HARNESS_PIECE.get(),

                // Basic items
                KHItems.CHAPERON.get(), KHItems.BROADHEAD_ARROW.get(), KHItems.BODKIN_ARROW.get(),
                KHItems.SWALLOWTAIL_ARROW.get(), KHItems.HOOD.get(), KHItems.TORN_HOOD.get(),
                KHItems.HELMET_HOOD.get(), KHItems.HELMET_TORN_HOOD.get(), KHItems.CLOAK.get(),
                KHItems.TORN_CLOAK.get(), KHItems.CIVILIAN_SURCOAT.get(), KHItems.GIORNEA.get(),
                KHItems.AVENTAIL.get(), KHItems.RIM_GUARDS.get(), KHItems.BESAGEWS.get(),
                KHItems.QUILTED_COIF.get(), KHItems.GAMBESON_BREECHES.get(), KHItems.GAMBESON_BOOTS.get(),
                KHItems.MAIL_COIF.get(), KHItems.HAUBERK.get(), KHItems.MAIL_BREECHES.get(),
                KHItems.MAIL_BOOTS.get(), KHItems.LONGBOW.get(), KHItems.LANCE.get(),
                KHItems.SURCOAT.get(), KHItems.SURCOAT_SLEEVELESS.get(),

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

        // --- Dyeable / Special Items ---
        Item[] dyeable = {
                KHItems.BRIGANDINE_HARNESS.get(), KHItems.DARK_BRIGANDINE_HARNESS.get(), KHItems.GOLDEN_BRIGANDINE_HARNESS.get(),
                KHItems.BRIGANDINE_CUISSES.get(), KHItems.DARK_BRIGANDINE_CUISSES.get(), KHItems.GOLDEN_BRIGANDINE_CUISSES.get(),
                KHItems.JESTER_HOOD.get(), KHItems.HORSE_BARDING.get(), KHItems.DARK_HORSE_BARDING.get(),
                KHItems.GOLDEN_HORSE_BARDING.get(), KHItems.GILDED_CHAPERON.get(), KHItems.GAMBESON.get(),
                KHItems.ARMING_DOUBLET.get(), KHItems.ARMING_HOSE.get(),
                KHItems.WOODEN_LANCE.get()
        };

        for (Supplier<Item> item : KHItems.BRIGANDINE_SPAULDERS.list()) {
            dyeableItem(item.get());
        }
        for (Supplier<Item> item : KHItems.BRIGANDINE.list()) {
            dyeableItem(item.get());
        }
        for (Item item : dyeable) dyeableItem(item);
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
                KHItems.VISORED_MORION.get(), KHItems.DARK_VISORED_MORION.get(), KHItems.GOLDEN_VISORED_MORION.get(),
                KHItems.GREAT_ARMET.get(), KHItems.DARK_GREAT_ARMET.get(), KHItems.GOLDEN_GREAT_ARMET.get(),
                KHItems.GREAT_ARMET_2.get(), KHItems.DARK_GREAT_ARMET_2.get(), KHItems.GOLDEN_GREAT_ARMET_2.get(),
                KHItems.GREAT_BASCINET.get(), KHItems.DARK_GREAT_BASCINET.get(), KHItems.GOLDEN_GREAT_BASCINET.get(),
                KHItems.GREAT_HOUNDSKUL_BASCINET.get(), KHItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(), KHItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get(),
                KHItems.MAXIMILLIAN_HELMET.get(), KHItems.DARK_MAXIMILLIAN_HELMET.get(), KHItems.GOLDEN_MAXIMILLIAN_HELMET.get(),
                KHItems.SAVOYARD.get(), KHItems.DARK_SAVOYARD.get(), KHItems.GOLDEN_SAVOYARD.get(),
                KHItems.ARAGONESE_SALLET.get(), KHItems.DARK_ARAGONESE_SALLET.get(), KHItems.GOLDEN_ARAGONESE_SALLET.get(),
                KHItems.SALLET_BEVOR.get(), KHItems.DARK_SALLET_BEVOR.get(), KHItems.GOLDEN_SALLET_BEVOR.get()
        };
        for (Item item : openVisorHelmets)
            registerItemWConditions(item, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1));

        Item[] blackSallet = {
                KHItems.BLACK_SALLET.get(), KHItems.DARK_BLACK_SALLET.get(), KHItems.GOLDEN_BLACK_SALLET.get(),
                KHItems.BLACK_SALLET_BEVOR.get(), KHItems.DARK_BLACK_SALLET_BEVOR.get(), KHItems.GOLDEN_BLACK_SALLET_BEVOR.get()
        };
        for (Item item : blackSallet)
            registerItemWConditions(item,
                    new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1),
                    new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "rat"), 1));

        List<Supplier<Item>> simpleArmorA = List.of(ArmorFamily.allFamilies(
                KHItems.MAIL_SPAULDERS,
                KHItems.PLATE_SPAULDERS
        ).toArray(new Supplier[0]));
        List<Supplier<Item>> simpleArmor = List.of(
                KHItems.PLATE_CUIRASS, KHItems.GOLDEN_PLATE_CUIRASS, KHItems.DARK_PLATE_CUIRASS,
                KHItems.MAXIMILLIAN_CUIRASS, KHItems.DARK_MAXIMILLIAN_CUIRASS, KHItems.GOLDEN_MAXIMILLIAN_CUIRASS,
                KHItems.XIIII_PLATE_CUIRASS, KHItems.XIIII_PLATE_BREASTPLATE,
                KHItems.PLACKART, KHItems.DARK_PLACKART, KHItems.GOLDEN_PLACKART,
                KHItems.TASSETS, KHItems.DARK_TASSETS, KHItems.GOLDEN_TASSETS,
                KHItems.DARK_XIIII_PLATE_CUIRASS, KHItems.DARK_XIIII_PLATE_BREASTPLATE,
                KHItems.GOLDEN_XIIII_PLATE_CUIRASS, KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE,
                KHItems.GREAVES, KHItems.DARK_GREAVES, KHItems.GOLDEN_GREAVES,
                KHItems.SABATONS, KHItems.DARK_SABATONS, KHItems.GOLDEN_SABATONS,
                KHItems.BARBUTE, KHItems.DARK_BARBUTE, KHItems.GOLDEN_BARBUTE,
                KHItems.BASCINET, KHItems.DARK_BASCINET, KHItems.GOLDEN_BASCINET,
                KHItems.KETTLE_HELM, KHItems.DARK_KETTLE_HELM, KHItems.GOLDEN_KETTLE_HELM,
                KHItems.NASAL_HELM, KHItems.DARK_NASAL_HELM, KHItems.GOLDEN_NASAL_HELM,
                KHItems.VIKING_HELM, KHItems.DARK_VIKING_HELM, KHItems.GOLDEN_VIKING_HELM,
                KHItems.BURGONET, KHItems.DARK_BURGONET, KHItems.GOLDEN_BURGONET,
                KHItems.VISORLESS_SALLET, KHItems.DARK_VISORLESS_SALLET, KHItems.GOLDEN_VISORLESS_SALLET,
                KHItems.MORION, KHItems.DARK_MORION, KHItems.GOLDEN_MORION,
                KHItems.FROGMOUTH, KHItems.DARK_FROGMOUTH, KHItems.GOLDEN_FROGMOUTH,
                KHItems.LEATHER_GLOVES, KHItems.MAIL_GLOVES,
                KHItems.GAUNTLET, KHItems.DARK_GAUNTLET, KHItems.GOLDEN_GAUNTLET,
                KHItems.PLATE_HARNESS, KHItems.DARK_PLATE_HARNESS, KHItems.GOLDEN_PLATE_HARNESS,
                KHItems.PLATE_CUISSES, KHItems.DARK_PLATE_CUISSES, KHItems.GOLDEN_PLATE_CUISSES,
                KHItems.GREAT_HELM, KHItems.DARK_GREAT_HELM, KHItems.GOLDEN_GREAT_HELM,
                KHItems.GREAT_HELM_2, KHItems.DARK_GREAT_HELM_2, KHItems.GOLDEN_GREAT_HELM_2
        );
        for (Supplier<Item> item : simpleArmorA) simpleItem(item.get());
        for (Supplier<Item> item : simpleArmor) simpleItem(item.get());

        Item[] toolHeads = {KHItems.DAGGER_HEAD.get(),
                KHItems.STILETTO_HEAD.get(),
                KHItems.SWORD_HEAD.get(),
                KHItems.FALCHION_HEAD.get(),
                KHItems.RAPIER_HEAD.get(),
                KHItems.AXE_HEAD.get(),
                KHItems.HAMMER_HEAD.get(),
                KHItems.MACE_HEAD.get(),
                KHItems.HALBERD_HEAD.get(),
                KHItems.BILLHOOK_HEAD.get(),
                KHItems.LONGSWORD_HEAD.get(),
                KHItems.GREATSWORD_HEAD.get(),
                KHItems.SPEAR_HEAD.get(),
                KHItems.PITCHFORK_HEAD.get()
        };
        for (Item item : toolHeads) registerItemWConditions(item);

    }

    private void dyeableItem(Item item) {
        registerItemWConditions(item, false, true);
    }

    private void simpleItem(Item item) {
        registerItemWConditions(item);
    }
}