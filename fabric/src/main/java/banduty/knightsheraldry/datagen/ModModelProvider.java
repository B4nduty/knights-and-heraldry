package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.armor.ArmorFamily;
import banduty.stoneycore.datagen.FabricModelProviderPlus;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

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
        registerDyeableItems(KHItems.JESTER_HOOD.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GILDED_CHAPERON.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.GAMBESON.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.ARMING_DOUBLET.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.ARMING_HOSE.get(), itemModelGenerators, registries);
        registerDyeableItems(KHItems.WOODEN_LANCE.get(), itemModelGenerators, registries);

        for (Supplier<Item> item : KHItems.BRIGANDINE_SPAULDERS.list()) {
            registerDyeableItems(item.get(), itemModelGenerators, registries);
        }
        for (Supplier<Item> item : KHItems.BRIGANDINE.list()) {
            registerDyeableItems(item.get(), itemModelGenerators, registries);
        }
        for (Supplier<Item> item : KHItems.BRIGANDINE_HARNESS.list()) {
            registerDyeableItems(item.get(), itemModelGenerators, registries);
        }
        for (Supplier<Item> item : KHItems.BRIGANDINE_CUISSES.list()) {
            registerDyeableItems(item.get(), itemModelGenerators, registries);
        }
        for (Supplier<Item> item : KHItems.HORSE_BARDING.list()) {
            registerDyeableItems(item.get(), itemModelGenerators, registries);
        }

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

        registerItemWConditions(KHItems.MAIL_GLOVES.get(), itemModelGenerators, registries);

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

        List<Supplier<Item>> blackSallet = List.of(ArmorFamily.allFamilies(
                KHItems.BLACK_SALLET,
                KHItems.BLACK_SALLET_BEVOR
        ));

        for (Item item : simpleHandheldItems) {
            registerItemWConditions(item, itemModelGenerators, registries, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "broken"), 1));
        }

        for (Supplier<Item> item : openVisorHelmet) {
            registerItemWConditions(item.get(), itemModelGenerators, registries, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1));
        }

        for (Supplier<Item> item : blackSallet) {
            registerItemWConditions(item.get(), itemModelGenerators, registries, new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "open"), 1), new OverrideCondition(ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "rat"), 1));
        }

        for (Supplier<Item> item : simpleArmor) {
            registerItemWConditions(item.get(), itemModelGenerators, registries);
        }
    }

    private void registerDyeableItems(Item item, ItemModelGenerators itemModelGenerators, HolderLookup.Provider registries) {
        registerItemWConditions(item, itemModelGenerators, registries, false, true);
    }
}