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
                KHItems.JESTER_HOOD.get(), KHItems.GILDED_CHAPERON.get(), KHItems.GAMBESON.get(),
                KHItems.ARMING_DOUBLET.get(), KHItems.ARMING_HOSE.get(), KHItems.WOODEN_LANCE.get()
        };

        for (Supplier<Item> item : KHItems.BRIGANDINE_SPAULDERS.list()) {
            dyeableItem(item.get());
        }
        for (Supplier<Item> item : KHItems.BRIGANDINE.list()) {
            dyeableItem(item.get());
        }
        for (Supplier<Item> item : KHItems.BRIGANDINE_HARNESS.list()) {
            dyeableItem(item.get());
        }
        for (Supplier<Item> item : KHItems.BRIGANDINE_CUISSES.list()) {
            dyeableItem(item.get());
        }
        for (Supplier<Item> item : KHItems.HORSE_BARDING.list()) {
            dyeableItem(item.get());
        }
        for (Item item : dyeable) dyeableItem(item);

        simpleItem(KHItems.LEATHER_GLOVES.get());
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

        List<Supplier<Item>> openVisorHelmet = List.of(ArmorFamily.allFamilies(
                KHItems.ARMET,
                KHItems.ARMET_2,
                KHItems.VISORED_BARBUTE,
                KHItems.HOUNDSKULL,
                KHItems.CAGE,
                KHItems.VISORED_BASCINET,
                KHItems.SALLET,
                KHItems.BURGONET_FALLING_BUFFE,
                KHItems.CLOSE_HELM,
                KHItems.VISORED_MORION,
                KHItems.GREAT_ARMET,
                KHItems.GREAT_ARMET_2,
                KHItems.GREAT_BASCINET,
                KHItems.GREAT_HOUNDSKUL_BASCINET,
                KHItems.MAXIMILLIAN_HELMET,
                KHItems.SAVOYARD,
                KHItems.ARAGONESE_SALLET,
                KHItems.SALLET_BEVOR
        ));
        for (Supplier<Item> item : openVisorHelmet) {
            registerItemWConditions(item.get(), new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1));
        }

        List<Supplier<Item>> blackSallet = List.of(ArmorFamily.allFamilies(
                KHItems.BLACK_SALLET,
                KHItems.BLACK_SALLET_BEVOR
        ));
        for (Supplier<Item> item : blackSallet) {
            registerItemWConditions(item.get(),
                    new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1),
                    new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "rat"), 1));
        }

        registerItemWConditions(KHItems.MAIL_GLOVES.get());

        List<Supplier<Item>> simpleArmor = List.of(ArmorFamily.allFamilies(
                KHItems.MAIL_SPAULDERS,
                KHItems.PLATE_SPAULDERS,
                KHItems.PLATE_CUIRASS,
                KHItems.MAXIMILLIAN_CUIRASS,
                KHItems.XIIII_PLATE_CUIRASS,
                KHItems.XIIII_PLATE_BREASTPLATE,
                KHItems.PLACKART,
                KHItems.TASSETS,
                KHItems.GREAVES,
                KHItems.SABATONS,
                KHItems.BARBUTE,
                KHItems.BASCINET,
                KHItems.KETTLE_HELM,
                KHItems.NASAL_HELM,
                KHItems.VIKING_HELM,
                KHItems.BURGONET,
                KHItems.VISORLESS_SALLET,
                KHItems.MORION,
                KHItems.FROGMOUTH,
                KHItems.GAUNTLET,
                KHItems.PLATE_HARNESS,
                KHItems.PLATE_CUISSES,
                KHItems.GREAT_HELM,
                KHItems.GREAT_HELM_2
        ));
        for (Supplier<Item> item : simpleArmor) simpleItem(item.get());

        Item[] toolHeads = {
                KHItems.DAGGER_HEAD.get(),
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