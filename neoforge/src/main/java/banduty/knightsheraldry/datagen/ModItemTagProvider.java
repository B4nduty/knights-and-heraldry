package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.armor.ArmorVariant;
import banduty.knightsheraldry.util.itemdata.KHTags;
import banduty.stoneycore.data.SCTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CompletableFuture.completedFuture(TagLookup.empty()), KnightsHeraldry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(SCTags.BROKEN_WEAPONS.getTag())
                .add(
                        KHItems.DAGGER.get(), KHItems.GILDED_DAGGER.get(), KHItems.STILETTO.get(), KHItems.GILDED_STILETTO.get(),
                        KHItems.RAPIER.get(), KHItems.GILDED_RAPIER.get(), KHItems.ESTOC.get(), KHItems.GILDED_ESTOC.get(),
                        KHItems.SWORD.get(), KHItems.GILDED_SWORD.get(), KHItems.V_SWORD.get(),  KHItems.GILDED_V_SWORD.get(),
                        KHItems.ARMING_SWORD.get(), KHItems.GILDED_ARMING_SWORD.get(),
                        KHItems.BASTARD_SWORD.get(), KHItems.GILDED_BASTARD_SWORD.get(), KHItems.AXE.get(),
                        KHItems.BROAD_AXE.get(), KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get(),
                        KHItems.MACE.get(), KHItems.SPIKED_MACE.get(), KHItems.HAMMER.get(),
                        KHItems.WAR_HAMMER.get(), KHItems.LONGSWORD.get(), KHItems.V_LONGSWORD.get(),
                        KHItems.FALCHION.get(), KHItems.SCIMITAR.get(), KHItems.PITCHFORK.get(),
                        KHItems.SPEAR.get(), KHItems.PIKE.get(), KHItems.BILLHOOK.get(),
                        KHItems.GLAIVE.get(), KHItems.CURVED_GLAIVE.get(), KHItems.HALBERD.get(),
                        KHItems.POLEAXE.get(), KHItems.POLEHAMMER.get(), KHItems.BEC_DE_CORBIN.get(),
                        KHItems.MORNING_STAR.get(), KHItems.BARDICHE.get(), KHItems.WARDART.get(),
                        KHItems.GREATSWORD.get(), KHItems.CLAYMORE.get(), KHItems.FLAMBERGE.get(),
                        KHItems.ZWEIHANDER.get(), KHItems.FLAIL.get(), KHItems.BALL_FLAIL.get()
                );

        tag(SCTags.WEAPONS_DAMAGE_BEHIND.getTag())
                .add(KHItems.DAGGER.get(), KHItems.GILDED_DAGGER.get());

        tag(SCTags.WEAPONS_IGNORES_ARMOR.getTag())
                .add(KHItems.STILETTO.get(), KHItems.GILDED_STILETTO.get());

        tag(SCTags.WEAPONS_DISABLE_SHIELD.getTag())
                .add(
                        KHItems.AXE.get(), KHItems.BROAD_AXE.get(),
                        KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get()
                );

        tag(SCTags.WEAPONS_BYPASS_BLOCK.getTag())
                .add(KHItems.FLAIL.get(), KHItems.BALL_FLAIL.get());

        tag(SCTags.WEAPONS_HARVEST.getTag())
                .add(KHItems.PITCHFORK.get());

        tag(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(KHItems.CLOAK.get(), KHItems.TORN_CLOAK.get());

        tag(SCTags.HIDE_NAME_TAG.getTag())
                .add(
                        KHItems.HOOD.get(), KHItems.TORN_HOOD.get(), KHItems.JESTER_HOOD.get(),
                        KHItems.HELMET_HOOD.get(), KHItems.HELMET_TORN_HOOD.get()
                );

        tag(ItemTags.DYEABLE)
                .add(KHItems.WOODEN_LANCE.get(), KHItems.QUILTED_COIF.get(), KHItems.GAMBESON.get(), KHItems.GAMBESON_BREECHES.get(),
                        KHItems.GAMBESON_BOOTS.get(), KHItems.ARMING_DOUBLET.get(), KHItems.ARMING_HOSE.get(),
                        KHItems.CLOAK.get(), KHItems.TORN_CLOAK.get(), KHItems.HOOD.get(), KHItems.TORN_HOOD.get(),
                        KHItems.JESTER_HOOD.get(), KHItems.HELMET_HOOD.get(), KHItems.HELMET_TORN_HOOD.get(),
                        KHItems.PLUME.get(), KHItems.TRI_PLUME.get(), KHItems.FLUFFY_PLUME.get(),
                        KHItems.CHAPERON.get(), KHItems.GILDED_CHAPERON.get(),
                        KHItems.LEATHER_GLOVES.get(),
                        KHItems.CIVILIAN_SURCOAT.get(), KHItems.GIORNEA.get(),
                        KHItems.MANUSCRIPT_BOOK.get())
                .add(itemArray(KHItems.BRIGANDINE_SPAULDERS.list()))
                .add(itemArray(KHItems.BRIGANDINE_HARNESS.list()))
                .add(itemArray(KHItems.BRIGANDINE_CUISSES.list()))
                .add(itemArray(KHItems.HORSE_BARDING.list()))
                .add(itemArray(KHItems.BRIGANDINE.list()));

        tag(KHTags.REPAIR_KIT_CHAIN.getTag())
                .add(KHItems.MAIL_COIF.get(), KHItems.HAUBERK.get(), KHItems.MAIL_BREECHES.get(), KHItems.MAIL_BOOTS.get(),
                        KHItems.MAIL_GLOVES.get())
                .add(itemArray(KHItems.MAIL_SPAULDERS.list()));

        tag(KHTags.REPAIR_KIT_GOLD.getTag())
                .add(
                        KHItems.MAIL_SPAULDERS.get(ArmorVariant.GOLDEN).get(), KHItems.BRIGANDINE_SPAULDERS.get(ArmorVariant.GOLDEN).get(),
                        KHItems.PLATE_SPAULDERS.get(ArmorVariant.GOLDEN).get(), KHItems.PAULDRONS.get(ArmorVariant.GOLDEN).get(),
                        KHItems.BRIGANDINE.get(ArmorVariant.GOLDEN).get(),
                        KHItems.PLATE_CUIRASS.get(ArmorVariant.GOLDEN).get(), KHItems.MAXIMILLIAN_CUIRASS.get(ArmorVariant.GOLDEN).get(),
                        KHItems.XIIII_PLATE_CUIRASS.get(ArmorVariant.GOLDEN).get(), KHItems.XIIII_PLATE_BREASTPLATE.get(ArmorVariant.GOLDEN).get(),
                        KHItems.PLACKART.get(ArmorVariant.GOLDEN).get(), KHItems.TASSETS.get(ArmorVariant.GOLDEN).get(),
                        KHItems.BARBUTE.get(ArmorVariant.GOLDEN).get(), KHItems.BASCINET.get(ArmorVariant.GOLDEN).get(),
                        KHItems.KETTLE_HELM.get(ArmorVariant.GOLDEN).get(), KHItems.NASAL_HELM.get(ArmorVariant.GOLDEN).get(),
                        KHItems.VIKING_HELM.get(ArmorVariant.GOLDEN).get(), KHItems.BURGONET.get(ArmorVariant.GOLDEN).get(),
                        KHItems.VISORLESS_SALLET.get(ArmorVariant.GOLDEN).get(), KHItems.MORION.get(ArmorVariant.GOLDEN).get(),
                        KHItems.ARMET.get(ArmorVariant.GOLDEN).get(), KHItems.ARMET_2.get(ArmorVariant.GOLDEN).get(),
                        KHItems.VISORED_BARBUTE.get(ArmorVariant.GOLDEN).get(), KHItems.HOUNDSKULL.get(ArmorVariant.GOLDEN).get(),
                        KHItems.CAGE.get(ArmorVariant.GOLDEN).get(), KHItems.VISORED_BASCINET.get(ArmorVariant.GOLDEN).get(),
                        KHItems.GREAT_HELM.get(ArmorVariant.GOLDEN).get(), KHItems.GREAT_HELM_2.get(ArmorVariant.GOLDEN).get(),
                        KHItems.SALLET.get(ArmorVariant.GOLDEN).get(), KHItems.BURGONET_FALLING_BUFFE.get(ArmorVariant.GOLDEN).get(),
                        KHItems.CLOSE_HELM.get(ArmorVariant.GOLDEN).get(), KHItems.BLACK_SALLET.get(ArmorVariant.GOLDEN).get(),
                        KHItems.VISORED_MORION.get(ArmorVariant.GOLDEN).get(), KHItems.SALLET_BEVOR.get(ArmorVariant.GOLDEN).get(),
                        KHItems.BLACK_SALLET_BEVOR.get(ArmorVariant.GOLDEN).get(), KHItems.FROGMOUTH.get(ArmorVariant.GOLDEN).get(),
                        KHItems.GREAT_ARMET.get(ArmorVariant.GOLDEN).get(), KHItems.GREAT_ARMET_2.get(ArmorVariant.GOLDEN).get(),
                        KHItems.GREAT_BASCINET.get(ArmorVariant.GOLDEN).get(), KHItems.GREAT_HOUNDSKUL_BASCINET.get(ArmorVariant.GOLDEN).get(),
                        KHItems.MAXIMILLIAN_HELMET.get(ArmorVariant.GOLDEN).get(), KHItems.SAVOYARD.get(ArmorVariant.GOLDEN).get(),
                        KHItems.ARAGONESE_SALLET.get(ArmorVariant.GOLDEN).get(), KHItems.GAUNTLET.get(ArmorVariant.GOLDEN).get(),
                        KHItems.BRIGANDINE_HARNESS.get(ArmorVariant.GOLDEN).get(), KHItems.PLATE_HARNESS.get(ArmorVariant.GOLDEN).get(),
                        KHItems.BRIGANDINE_CUISSES.get(ArmorVariant.GOLDEN).get(), KHItems.PLATE_CUISSES.get(ArmorVariant.GOLDEN).get(),
                        KHItems.GREAVES.get(ArmorVariant.GOLDEN).get(), KHItems.SABATONS.get(ArmorVariant.GOLDEN).get()
                );

        tag(KHTags.REPAIR_KIT_IRON.getTag())
                .add(itemArray(KHItems.PLATE_SPAULDERS.list()))
                .add(itemArray(KHItems.PAULDRONS.list()))
                .add(itemArray(KHItems.PLATE_CUIRASS.list()))
                .add(itemArray(KHItems.MAXIMILLIAN_CUIRASS.list()))
                .add(itemArray(KHItems.XIIII_PLATE_CUIRASS.list()))
                .add(itemArray(KHItems.XIIII_PLATE_BREASTPLATE.list()))
                .add(itemArray(KHItems.PLACKART.list()))
                .add(itemArray(KHItems.TASSETS.list()))
                .add(itemArray(KHItems.BARBUTE.list()))
                .add(itemArray(KHItems.BASCINET.list()))
                .add(itemArray(KHItems.KETTLE_HELM.list()))
                .add(itemArray(KHItems.NASAL_HELM.list()))
                .add(itemArray(KHItems.VIKING_HELM.list()))
                .add(itemArray(KHItems.BURGONET.list()))
                .add(itemArray(KHItems.VISORLESS_SALLET.list()))
                .add(itemArray(KHItems.MORION.list()))
                .add(itemArray(KHItems.ARMET.list()))
                .add(itemArray(KHItems.ARMET_2.list()))
                .add(itemArray(KHItems.VISORED_BARBUTE.list()))
                .add(itemArray(KHItems.HOUNDSKULL.list()))
                .add(itemArray(KHItems.CAGE.list()))
                .add(itemArray(KHItems.VISORED_BASCINET.list()))
                .add(itemArray(KHItems.GREAT_HELM.list()))
                .add(itemArray(KHItems.GREAT_HELM_2.list()))
                .add(itemArray(KHItems.SALLET.list()))
                .add(itemArray(KHItems.BURGONET_FALLING_BUFFE.list()))
                .add(itemArray(KHItems.CLOSE_HELM.list()))
                .add(itemArray(KHItems.BLACK_SALLET.list()))
                .add(itemArray(KHItems.VISORED_MORION.list()))
                .add(itemArray(KHItems.SALLET_BEVOR.list()))
                .add(itemArray(KHItems.BLACK_SALLET_BEVOR.list()))
                .add(itemArray(KHItems.FROGMOUTH.list()))
                .add(itemArray(KHItems.GREAT_ARMET.list()))
                .add(itemArray(KHItems.GREAT_ARMET_2.list()))
                .add(itemArray(KHItems.GREAT_BASCINET.list()))
                .add(itemArray(KHItems.GREAT_HOUNDSKUL_BASCINET.list()))
                .add(itemArray(KHItems.MAXIMILLIAN_HELMET.list()))
                .add(itemArray(KHItems.SAVOYARD.list()))
                .add(itemArray(KHItems.ARAGONESE_SALLET.list()))
                .add(itemArray(KHItems.GAUNTLET.list()))
                .add(itemArray(KHItems.PLATE_HARNESS.list()))
                .add(itemArray(KHItems.PLATE_CUISSES.list()))
                .add(itemArray(KHItems.GREAVES.list()))
                .add(itemArray(KHItems.SABATONS.list()))
                .add(KHItems.AVENTAIL.get())
                .add(KHItems.RIM_GUARDS.get())
                .add(KHItems.BESAGEWS.get());

        tag(KHTags.REPAIR_KIT_LEATHER.getTag())
                .add(KHItems.LEATHER_GLOVES.get());

        tag(KHTags.REPAIR_KIT_WOOL.getTag())
                .add(KHItems.QUILTED_COIF.get(), KHItems.GAMBESON.get(), KHItems.GAMBESON_BREECHES.get(), KHItems.GAMBESON_BOOTS.get())
                .add(itemArray(KHItems.BRIGANDINE_SPAULDERS.list()))
                .add(itemArray(KHItems.BRIGANDINE.list()))
                .add(itemArray(KHItems.BRIGANDINE_HARNESS.list()))
                .add(itemArray(KHItems.BRIGANDINE_CUISSES.list()));
    }

    private static Item[] itemArray(List<Supplier<Item>> suppliers) {
        return suppliers.stream().map(Supplier::get).toArray(Item[]::new);
    }
}
