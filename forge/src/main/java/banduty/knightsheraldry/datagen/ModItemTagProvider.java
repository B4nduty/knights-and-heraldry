package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.util.itemdata.ModTags;
import banduty.stoneycore.util.data.itemdata.SCTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CompletableFuture.completedFuture(TagLookup.empty()), KnightsHeraldry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(SCTags.WEAPONS_3D.getTag())
                .add(
                        ModItems.DAGGER.get(), ModItems.STILETTO.get(), ModItems.RAPIER.get(), ModItems.SWORD.get(),
                        ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get(), ModItems.AXE.get(), ModItems.BROAD_AXE.get(),
                        ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get(), ModItems.MACE.get(),
                        ModItems.SPIKED_MACE.get(), ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get(),
                        ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get(), ModItems.FALCHION.get(),
                        ModItems.SCIMITAR.get(), ModItems.PITCHFORK.get(), ModItems.SPEAR.get(), ModItems.PIKE.get(),
                        ModItems.BILLHOOK.get(), ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get(),
                        ModItems.HALBERD.get(), ModItems.LANCE.get(), ModItems.WOODEN_LANCE.get(),
                        ModItems.POLEAXE.get(), ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get(),
                        ModItems.MORNING_STAR.get(), ModItems.BARDICHE.get(), ModItems.WARDART.get(),
                        ModItems.GREATSWORD.get(), ModItems.CLAYMORE.get(), ModItems.FLAMBERGE.get(),
                        ModItems.ZWEIHANDER.get(), ModItems.LONGBOW.get()
                );

        tag(SCTags.BROKEN_WEAPONS.getTag())
                .add(
                        ModItems.DAGGER.get(), ModItems.STILETTO.get(), ModItems.RAPIER.get(), ModItems.SWORD.get(),
                        ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get(), ModItems.AXE.get(),
                        ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get(),
                        ModItems.MACE.get(), ModItems.SPIKED_MACE.get(), ModItems.HAMMER.get(),
                        ModItems.WAR_HAMMER.get(), ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get(),
                        ModItems.FALCHION.get(), ModItems.SCIMITAR.get(), ModItems.PITCHFORK.get(),
                        ModItems.SPEAR.get(), ModItems.PIKE.get(), ModItems.BILLHOOK.get(),
                        ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get(), ModItems.HALBERD.get(),
                        ModItems.POLEAXE.get(), ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get(),
                        ModItems.MORNING_STAR.get(), ModItems.BARDICHE.get(), ModItems.WARDART.get(),
                        ModItems.GREATSWORD.get(), ModItems.CLAYMORE.get(), ModItems.FLAMBERGE.get(),
                        ModItems.ZWEIHANDER.get(), ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get()
                );

        tag(SCTags.WEAPONS_SHIELD.getTag())
                .add(
                        ModItems.RAPIER.get(), ModItems.SWORD.get(), ModItems.V_SWORD.get(),
                        ModItems.ARMING_SWORD.get(), ModItems.AXE.get(), ModItems.BROAD_AXE.get(),
                        ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get(), ModItems.MACE.get(),
                        ModItems.SPIKED_MACE.get(), ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get(),
                        ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get(), ModItems.FALCHION.get(),
                        ModItems.SCIMITAR.get(), ModItems.PITCHFORK.get(), ModItems.SPEAR.get(),
                        ModItems.PIKE.get(), ModItems.BILLHOOK.get(), ModItems.GLAIVE.get(),
                        ModItems.CURVED_GLAIVE.get(), ModItems.HALBERD.get(), ModItems.POLEAXE.get(),
                        ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get(), ModItems.MORNING_STAR.get(),
                        ModItems.BARDICHE.get(), ModItems.GREATSWORD.get(), ModItems.CLAYMORE.get(),
                        ModItems.FLAMBERGE.get(), ModItems.ZWEIHANDER.get()
                );

        tag(SCTags.WEAPONS_DAMAGE_BEHIND.getTag())
                .add(ModItems.DAGGER.get());

        tag(SCTags.WEAPONS_IGNORES_ARMOR.getTag())
                .add(ModItems.STILETTO.get());

        tag(SCTags.WEAPONS_DISABLE_SHIELD.getTag())
                .add(
                        ModItems.AXE.get(), ModItems.BROAD_AXE.get(),
                        ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get()
                );

        tag(SCTags.WEAPONS_BYPASS_BLOCK.getTag())
                .add(ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get());

        tag(SCTags.WEAPONS_HARVEST.getTag())
                .add(ModItems.PITCHFORK.get());

        tag(SCTags.GEO_2D_ITEMS.getTag())
                .add(
                        ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get(),
                        ModItems.HEAVY_CROSSBOW.get(), ModItems.ARQUEBUS.get(), ModItems.HANDGONNE.get()
                );

        tag(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(ModItems.CLOAK.get(), ModItems.TORN_CLOAK.get());

        tag(SCTags.BANNER_COMPATIBLE.getTag())
                .add(ModItems.SURCOAT.get(), ModItems.SURCOAT_SLEEVELESS.get());

        tag(SCTags.HIDE_NAME_TAG.getTag())
                .add(
                        ModItems.HOOD.get(), ModItems.TORN_HOOD.get(), ModItems.JESTER_HOOD.get(),
                        ModItems.HELMET_HOOD.get(), ModItems.HELMET_TORN_HOOD.get()
                );

        tag(ModTags.DYES.getTag())
                .add(
                        Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.LIGHT_BLUE_DYE,
                        Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_GRAY_DYE,
                        Items.LIME_DYE, Items.MAGENTA_DYE, Items.PINK_DYE, Items.ORANGE_DYE,
                        Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE, Items.PURPLE_DYE
                );
    }
}
