package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.FabricModelProviderPlus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class ModModelProvider extends FabricModelProviderPlus {
    private final CompletableFuture<HolderLookup.Provider> registriesFuture;
    public ModModelProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output);
        this.registriesFuture = registriesFuture;
    }
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        HolderLookup.Provider registries = registriesFuture.join();
        registerItemWConditions(KHItems.MANUSCRIPT_DAGGER.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_SWORD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_AXE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_HAMMER.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_MACE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_HALBERD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_LONGSWORD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_GREATSWORD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_SPEAR.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_PITCHFORK.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_BARBUTE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_BASCINET.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_KETTLE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_NASAL.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_BURGONET.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_SALLET.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_MORION.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_ARMET.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_CAGE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_GREAT_HELMET.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_CLOSE_HELMET.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_FROGMOUTH.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_MAXIMILIAN.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_VISOR.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_FALLING_BUFFE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_BEVOR.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_AVENTAIL.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_CUIRASS.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_PLACKART.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_TASSETS.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_RIM_GUARDS.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_BESAGEWS.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_SPAULDERS.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_HARNESS.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_CUISSES.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_GREAVES.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_SABATONS.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_BARDING.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.MANUSCRIPT_SWALLOWTAIL.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_BODKIN.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_BROADHEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MANUSCRIPT_CLOTH.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.DAGGER_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.STILETTO_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.SWORD_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.FALCHION_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.RAPIER_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.AXE_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.HAMMER_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MACE_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.HALBERD_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BILLHOOK_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.LONGSWORD_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.GREATSWORD_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.SPEAR_HEAD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.PITCHFORK_HEAD.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.BARBUTE_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BASCINET_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.KETTLE_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.NASAL_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BURGONET_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.SALLET_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MORION_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.ARMET_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.CAGE_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.GREAT_HELMET_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.CLOSE_HELMET_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.FROGMOUTH_PIECE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MAXIMILIAN_PIECE.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.VISOR.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.FALLING_BUFFE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BEVOR.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.CUIRASS_PIECE.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.SPAULDERS_PIECE.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.HARNESS_PIECE.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.CHAPERON.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BROADHEAD_ARROW.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BODKIN_ARROW.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.SWALLOWTAIL_ARROW.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.HOOD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TORN_HOOD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.HELMET_HOOD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.HELMET_TORN_HOOD.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.CLOAK.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TORN_CLOAK.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.SURCOAT.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.SURCOAT_SLEEVELESS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.CIVILIAN_SURCOAT.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.GIORNEA.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.AVENTAIL.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.RIM_GUARDS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BESAGEWS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.QUILTED_COIF.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.GAMBESON_BREECHES.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.GAMBESON_BOOTS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MAIL_COIF.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.HAUBERK.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MAIL_BREECHES.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.MAIL_BOOTS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.LONGBOW.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.LANCE.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.PLUME.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TRI_PLUME.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.FLUFFY_PLUME.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TEUTONIC_SNAKES.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TEUTONIC_BLACK_SNAKES.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.GOLD_HORNS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BLACK_HORNS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TEUTONIC_GOLD_WINGS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TEUTONIC_BLACK_WINGS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TEUTONIC_WINGS_BALL_ENDS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.TEUTONIC_WINGS_SHARP_ENDS.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.DRAGON.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.LION.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.SNAKE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.UNICORN.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.STAG.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.BOAR.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.EAGLE.get(), itemModelGenerators, registries);
        registerItemWConditions(KHItems.PEGASUS.get(), itemModelGenerators, registries);

        registerSimpleItems(itemModelGenerators, registries);

        // Dyeable Items
        registerDyeableItems(KHItems.BRIGANDINE_HARNESS.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.DARK_BRIGANDINE_HARNESS.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE_HARNESS.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.BRIGANDINE_CUISSES.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.DARK_BRIGANDINE_CUISSES.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE_CUISSES.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.JESTER_HOOD.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.HORSE_BARDING.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.DARK_HORSE_BARDING.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GOLDEN_HORSE_BARDING.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GILDED_CHAPERON.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GAMBESON.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.ARMING_DOUBLET.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.ARMING_HOSE.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.WOODEN_LANCE.get(), itemModelGenerators, registries);

        registerDyeableItems(KHItems.BRIGANDINE_SPAULDERS.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.DARK_BRIGANDINE_SPAULDERS.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get(), itemModelGenerators, registries);

        registerDyeableItems(KHItems.BRIGANDINE.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.DARK_BRIGANDINE.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GOLDEN_BRIGANDINE.get(), itemModelGenerators, registries);

        registerItemWConditions(KHItems.LEATHER_GLOVES.get(), itemModelGenerators, registries);
    }

    private void registerSimpleItems(ItemModelGenerators itemModelGenerators, HolderLookup.Provider registries) {
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
                KHItems.MORION.get(), KHItems.DARK_MORION.get(), KHItems.GOLDEN_MORION.get(),
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
            registerItemWConditions(item, itemModelGenerators, registries, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "broken"), 1));
        }

        for (Item item : openVisorHelmet) {
            registerItemWConditions(item, itemModelGenerators, registries, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1));
        }

        for (Item item : blackSallet) {
            registerItemWConditions(item, itemModelGenerators, registries, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1), new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "rat"), 1));
        }

        for (Item item : simpleArmor) {
            registerItemWConditions(item, itemModelGenerators, registries);
        }
    }

    private void registerDyeableItems(Item item, ItemModelGenerators itemModelGenerators, HolderLookup.Provider registries) {
        registerItemWConditions(item, itemModelGenerators, registries, false, true);
    }
}