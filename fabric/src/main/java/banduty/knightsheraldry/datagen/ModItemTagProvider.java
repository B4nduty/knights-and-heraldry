package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.util.itemdata.ModTags;
import banduty.stoneycore.util.data.itemdata.SCTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(SCTags.WEAPONS_3D.getTag())
                .add(KHItems.DAGGER.get(), KHItems.STILETTO.get(), KHItems.RAPIER.get(), KHItems.SWORD.get(),
                        KHItems.V_SWORD.get(), KHItems.ARMING_SWORD.get(), KHItems.AXE.get(), KHItems.BROAD_AXE.get(), KHItems.CROOKED_AXE.get(),
                        KHItems.STRAIGHT_CROOKED_AXE.get(), KHItems.MACE.get(), KHItems.SPIKED_MACE.get(), KHItems.HAMMER.get(),
                        KHItems.WAR_HAMMER.get(), KHItems.LONGSWORD.get(), KHItems.V_LONGSWORD.get(), KHItems.FALCHION.get(), KHItems.SCIMITAR.get(),
                        KHItems.PITCHFORK.get(), KHItems.SPEAR.get(), KHItems.PIKE.get(), KHItems.BILLHOOK.get(),
                        KHItems.GLAIVE.get(), KHItems.CURVED_GLAIVE.get(), KHItems.HALBERD.get(), KHItems.LANCE.get(), KHItems.WOODEN_LANCE.get(),
                        KHItems.POLEAXE.get(), KHItems.POLEHAMMER.get(), KHItems.BEC_DE_CORBIN.get(), KHItems.MORNING_STAR.get(),
                        KHItems.BARDICHE.get(), KHItems.WARDART.get(), KHItems.GREATSWORD.get(), KHItems.CLAYMORE.get(),
                        KHItems.FLAMBERGE.get(), KHItems.ZWEIHANDER.get(), KHItems.LONGBOW.get());

        getOrCreateTagBuilder(SCTags.BROKEN_WEAPONS.getTag())
                .add(KHItems.DAGGER.get(), KHItems.STILETTO.get(), KHItems.RAPIER.get(), KHItems.SWORD.get(),
                        KHItems.V_SWORD.get(), KHItems.ARMING_SWORD.get(), KHItems.AXE.get(), KHItems.BROAD_AXE.get(),
                        KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get(), KHItems.MACE.get(),
                        KHItems.SPIKED_MACE.get(), KHItems.HAMMER.get(), KHItems.WAR_HAMMER.get(), KHItems.LONGSWORD.get(),
                        KHItems.V_LONGSWORD.get(), KHItems.FALCHION.get(), KHItems.SCIMITAR.get(),
                        KHItems.PITCHFORK.get(), KHItems.SPEAR.get(), KHItems.PIKE.get(), KHItems.BILLHOOK.get(),
                        KHItems.GLAIVE.get(), KHItems.CURVED_GLAIVE.get(), KHItems.HALBERD.get(), KHItems.POLEAXE.get(),
                        KHItems.POLEHAMMER.get(), KHItems.BEC_DE_CORBIN.get(), KHItems.MORNING_STAR.get(),
                        KHItems.BARDICHE.get(), KHItems.WARDART.get(), KHItems.GREATSWORD.get(), KHItems.CLAYMORE.get(),
                        KHItems.FLAMBERGE.get(), KHItems.ZWEIHANDER.get(), KHItems.FLAIL.get(), KHItems.BALL_FLAIL.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_SHIELD.getTag())
                .add(KHItems.RAPIER.get(), KHItems.SWORD.get(), KHItems.V_SWORD.get(), KHItems.ARMING_SWORD.get(), KHItems.AXE.get(),
                        KHItems.BROAD_AXE.get(), KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get(), KHItems.MACE.get(),
                        KHItems.SPIKED_MACE.get(), KHItems.HAMMER.get(), KHItems.WAR_HAMMER.get(), KHItems.LONGSWORD.get(),
                        KHItems.V_LONGSWORD.get(), KHItems.FALCHION.get(), KHItems.SCIMITAR.get(), KHItems.PITCHFORK.get(),
                        KHItems.SPEAR.get(), KHItems.PIKE.get(), KHItems.BILLHOOK.get(), KHItems.GLAIVE.get(), KHItems.CURVED_GLAIVE.get(),
                        KHItems.HALBERD.get(), KHItems.POLEAXE.get(), KHItems.POLEHAMMER.get(), KHItems.BEC_DE_CORBIN.get(), KHItems.MORNING_STAR.get(),
                        KHItems.BARDICHE.get(), KHItems.GREATSWORD.get(), KHItems.CLAYMORE.get(), KHItems.FLAMBERGE.get(),
                        KHItems.ZWEIHANDER.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_DAMAGE_BEHIND.getTag())
                .add(KHItems.DAGGER.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_IGNORES_ARMOR.getTag())
                .add(KHItems.STILETTO.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_DISABLE_SHIELD.getTag())
                .add(KHItems.AXE.get(), KHItems.BROAD_AXE.get(), KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_BYPASS_BLOCK.getTag())
                .add(KHItems.FLAIL.get(), KHItems.BALL_FLAIL.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_HARVEST.getTag())
                .add(KHItems.PITCHFORK.get());

        getOrCreateTagBuilder(SCTags.GEO_2D_ITEMS.getTag())
                .add(KHItems.FLAIL.get(), KHItems.BALL_FLAIL.get(), KHItems.HEAVY_CROSSBOW.get(), KHItems.ARQUEBUS.get(), KHItems.HANDGONNE.get());

        getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(KHItems.CLOAK.get(), KHItems.TORN_CLOAK.get());

        getOrCreateTagBuilder(SCTags.BANNER_COMPATIBLE.getTag())
                .add(KHItems.SURCOAT.get(), KHItems.SURCOAT_SLEEVELESS.get());

        getOrCreateTagBuilder(SCTags.HIDE_NAME_TAG.getTag())
                .add(KHItems.HOOD.get(), KHItems.TORN_HOOD.get(), KHItems.JESTER_HOOD.get(), KHItems.HELMET_HOOD.get(), KHItems.HELMET_TORN_HOOD.get());

        getOrCreateTagBuilder(ModTags.DYES.getTag())
                .add(Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.LIGHT_BLUE_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_GRAY_DYE,
                        Items.LIME_DYE, Items.MAGENTA_DYE, Items.PINK_DYE, Items.ORANGE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE, Items.BROWN_DYE, Items.PURPLE_DYE);
    }
}