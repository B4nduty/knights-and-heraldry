package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.FabricModelProviderPlus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModModelProvider extends FabricModelProviderPlus {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        registerItemWConditions(KHItems.MANUSCRIPT_DAGGER.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_SWORD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_AXE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_HAMMER.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_MACE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_HALBERD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_LONGSWORD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_GREATSWORD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_SPEAR.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_PITCHFORK.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_BARBUTE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_BASCINET.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_KETTLE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_NASAL.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_BURGONET.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_SALLET.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_MORION.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_ARMET.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_CAGE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_GREAT_HELMET.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_CLOSE_HELMET.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_FROGMOUTH.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_MAXIMILIAN.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_VISOR.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_FALLING_BUFFE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_BEVOR.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_AVENTAIL.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_CUIRASS.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_PLACKART.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_TASSETS.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_RIM_GUARDS.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_BESAGEWS.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_SPAULDERS.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_HARNESS.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_CUISSES.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_GREAVES.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_SABATONS.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_BARDING.get(), itemModelGenerators);

        registerItemWConditions(KHItems.MANUSCRIPT_SWALLOWTAIL.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_BODKIN.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_BROADHEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MANUSCRIPT_CLOTH.get(), itemModelGenerators);

        registerItemWConditions(KHItems.DAGGER_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.STILETTO_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.SWORD_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.FALCHION_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.RAPIER_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.AXE_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.HAMMER_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MACE_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.HALBERD_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BILLHOOK_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.LONGSWORD_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.GREATSWORD_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.SPEAR_HEAD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.PITCHFORK_HEAD.get(), itemModelGenerators);

        registerItemWConditions(KHItems.BARBUTE_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BASCINET_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.KETTLE_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.NASAL_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BURGONET_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.SALLET_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MORION_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.ARMET_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.CAGE_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.GREAT_HELMET_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.CLOSE_HELMET_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.FROGMOUTH_PIECE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MAXIMILIAN_PIECE.get(), itemModelGenerators);

        registerItemWConditions(KHItems.VISOR.get(), itemModelGenerators);
        registerItemWConditions(KHItems.FALLING_BUFFE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BEVOR.get(), itemModelGenerators);

        registerItemWConditions(KHItems.CUIRASS_PIECE.get(), itemModelGenerators);

        registerItemWConditions(KHItems.SPAULDERS_PIECE.get(), itemModelGenerators);

        registerItemWConditions(KHItems.HARNESS_PIECE.get(), itemModelGenerators);

        registerItemWConditions(KHItems.CHAPERON.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BROADHEAD_ARROW.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BODKIN_ARROW.get(), itemModelGenerators);
        registerItemWConditions(KHItems.SWALLOWTAIL_ARROW.get(), itemModelGenerators);
        registerItemWConditions(KHItems.HOOD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TORN_HOOD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.HELMET_HOOD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.HELMET_TORN_HOOD.get(), itemModelGenerators);
        registerItemWConditions(KHItems.CLOAK.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TORN_CLOAK.get(), itemModelGenerators);
        registerItemWConditions(KHItems.SURCOAT.get(), itemModelGenerators);
        registerItemWConditions(KHItems.SURCOAT_SLEEVELESS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.CIVILIAN_SURCOAT.get(), itemModelGenerators);
        registerItemWConditions(KHItems.GIORNEA.get(), itemModelGenerators);
        registerItemWConditions(KHItems.AVENTAIL.get(), itemModelGenerators);
        registerItemWConditions(KHItems.RIM_GUARDS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BESAGEWS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.QUILTED_COIF.get(), itemModelGenerators);
        registerItemWConditions(KHItems.GAMBESON_BREECHES.get(), itemModelGenerators);
        registerItemWConditions(KHItems.GAMBESON_BOOTS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MAIL_COIF.get(), itemModelGenerators);
        registerItemWConditions(KHItems.HAUBERK.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MAIL_BREECHES.get(), itemModelGenerators);
        registerItemWConditions(KHItems.MAIL_BOOTS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.LONGBOW.get(), itemModelGenerators);
        registerItemWConditions(KHItems.LANCE.get(), itemModelGenerators);

        registerItemWConditions(KHItems.PLUME.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TRI_PLUME.get(), itemModelGenerators);
        registerItemWConditions(KHItems.FLUFFY_PLUME.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TEUTONIC_SNAKES.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TEUTONIC_BLACK_SNAKES.get(), itemModelGenerators);
        registerItemWConditions(KHItems.GOLD_HORNS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BLACK_HORNS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TEUTONIC_GOLD_WINGS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TEUTONIC_BLACK_WINGS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TEUTONIC_WINGS_BALL_ENDS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.TEUTONIC_WINGS_SHARP_ENDS.get(), itemModelGenerators);
        registerItemWConditions(KHItems.DRAGON.get(), itemModelGenerators);
        registerItemWConditions(KHItems.LION.get(), itemModelGenerators);
        registerItemWConditions(KHItems.SNAKE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.UNICORN.get(), itemModelGenerators);
        registerItemWConditions(KHItems.STAG.get(), itemModelGenerators);
        registerItemWConditions(KHItems.BOAR.get(), itemModelGenerators);
        registerItemWConditions(KHItems.EAGLE.get(), itemModelGenerators);
        registerItemWConditions(KHItems.PEGASUS.get(), itemModelGenerators);

        registerSimpleItems(itemModelGenerators);

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

        registerDyeableItems(KHItems.LEATHER_GLOVES.get(), itemModelGenerators);
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
                KHItems.VISORLESS_MORION.get(), KHItems.DARK_VISORLESS_MORION.get(), KHItems.GOLDEN_VISORLESS_MORION.get(),
                KHItems.FROGMOUTH.get(), KHItems.DARK_FROGMOUTH.get(), KHItems.GOLDEN_FROGMOUTH.get(),
                KHItems.MAIL_GLOVES.get(),
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

        Item[] blackSallet = {
                KHItems.BLACK_SALLET.get(), KHItems.DARK_BLACK_SALLET.get(), KHItems.GOLDEN_BLACK_SALLET.get(),
                KHItems.BLACK_SALLET_BEVOR.get(), KHItems.DARK_BLACK_SALLET_BEVOR.get(),KHItems. GOLDEN_BLACK_SALLET_BEVOR.get(),
        };

        for (Item item : simpleHandheldItems) {
            registerItemWConditions(item, itemModelGenerators, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "broken"), 1));
        }

        for (Item item : openVisorHelmet) {
            registerItemWConditions(item, itemModelGenerators, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1));
        }

        for (Item item : blackSallet) {
            registerItemWConditions(item, itemModelGenerators, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1), new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "rat"), 1));
        }

        for (Item item : simpleArmor) {
            registerItemWConditions(item, itemModelGenerators);
        }
    }

    private void registerDyeableItems(Item item, ItemModelGenerators itemModelGenerators) {
        registerItemWConditions(item, itemModelGenerators, false, true);
    }
}