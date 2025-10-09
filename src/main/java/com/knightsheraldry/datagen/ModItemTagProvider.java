package com.knightsheraldry.datagen;

import banduty.stoneycore.util.data.itemdata.SCTags;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.itemdata.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(SCTags.WEAPONS_3D.getTag())
            .add(ModItems.DAGGER.get(), ModItems.STILETTO.get(), ModItems.RAPIER.get(), ModItems.SWORD.get(),
                    ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get(), ModItems.AXE.get(), ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(),
                    ModItems.STRAIGHT_CROOKED_AXE.get(), ModItems.MACE.get(), ModItems.SPIKED_MACE.get(), ModItems.HAMMER.get(),
                    ModItems.WAR_HAMMER.get(), ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get(), ModItems.FALCHION.get(), ModItems.SCIMITAR.get(),
                    ModItems.PITCHFORK.get(), ModItems.SPEAR.get(), ModItems.PIKE.get(), ModItems.BILLHOOK.get(),
                    ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get(), ModItems.HALBERD.get(), ModItems.LANCE.get(), ModItems.WOODEN_LANCE.get(),
                    ModItems.POLEAXE.get(), ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get(), ModItems.MORNING_STAR.get(),
                    ModItems.BARDICHE.get(),  ModItems.WARDART.get(), ModItems.WARSWORD.get(), ModItems.WARSWORD_CLAYMORE.get(),
                    ModItems.WARSWORD_FLAMBERGE.get(), ModItems.WARSWORD_ZWEIHANDER.get(), ModItems.LONGBOW.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_SHIELD.getTag())
                .add(ModItems.RAPIER.get(), ModItems.SWORD.get(), ModItems.V_SWORD.get(), ModItems.ARMING_SWORD.get(), ModItems.AXE.get(),
                        ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get(), ModItems.MACE.get(),
                        ModItems.SPIKED_MACE.get(), ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get(), ModItems.LONGSWORD.get(),
                        ModItems.V_LONGSWORD.get(), ModItems.FALCHION.get(), ModItems.SCIMITAR.get(), ModItems.PITCHFORK.get(),
                        ModItems.SPEAR.get(), ModItems.PIKE.get(), ModItems.BILLHOOK.get(), ModItems.GLAIVE.get(), ModItems.CURVED_GLAIVE.get(),
                        ModItems.HALBERD.get(), ModItems.POLEAXE.get(), ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get(), ModItems.MORNING_STAR.get(),
                        ModItems.BARDICHE.get(), ModItems.WARSWORD.get(), ModItems.WARSWORD_CLAYMORE.get(), ModItems.WARSWORD_FLAMBERGE.get(),
                        ModItems.WARSWORD_ZWEIHANDER.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_DAMAGE_BEHIND.getTag())
                .add(ModItems.DAGGER.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_IGNORES_ARMOR.getTag())
                .add(ModItems.STILETTO.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_DISABLE_SHIELD.getTag())
                .add(ModItems.AXE.get(), ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get(), ModItems.STRAIGHT_CROOKED_AXE.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_BYPASS_BLOCK.getTag())
                .add(ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get());

        getOrCreateTagBuilder(SCTags.WEAPONS_HARVEST.getTag())
                .add(ModItems.PITCHFORK.get());

        getOrCreateTagBuilder(SCTags.GEO_2D_ITEMS.getTag())
                .add(ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get(), ModItems.HEAVY_CROSSBOW.get(), ModItems.ARQUEBUS.get(), ModItems.HANDGONNE.get());

        getOrCreateTagBuilder(SCTags.VISORED_HELMET.getTag())
                .add(ModItems.ARMET.get(), ModItems.ARMET_2.get(), ModItems.BARBUTE.get(), ModItems.BASCINET.get(), ModItems.CAGE.get(),
                        ModItems.CAGE_2.get(), ModItems.FLAT_BASCINET.get(), ModItems.GREAT_HELM.get(), ModItems.GREAT_HELM_2.get(),
                        ModItems.SALLET.get(), ModItems.FROGMOUTH.get(), ModItems.GREAT_ARMET.get(), ModItems.GREAT_ARMET_2.get(),
                        ModItems.GREAT_BASCINET.get(), ModItems.GREAT_HOUNDSKUL_BASCINET.get(), ModItems.MAXIMILLIAN_HELMET.get(),
                        ModItems.DARK_ARMET.get(), ModItems.DARK_ARMET_2.get(), ModItems.DARK_BARBUTE.get(), ModItems.DARK_BASCINET.get(), ModItems.DARK_CAGE.get(),
                        ModItems.DARK_CAGE_2.get(), ModItems.DARK_FLAT_BASCINET.get(), ModItems.DARK_GREAT_HELM.get(), ModItems.DARK_GREAT_HELM_2.get(),
                        ModItems.DARK_SALLET.get(), ModItems.DARK_FROGMOUTH.get(), ModItems.DARK_GREAT_ARMET.get(), ModItems.DARK_GREAT_ARMET_2.get(),
                        ModItems.DARK_GREAT_BASCINET.get(), ModItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(), ModItems.DARK_MAXIMILLIAN_HELMET.get());

        getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(ModItems.CLOAK.get(), ModItems.TORN_CLOAK.get());

        getOrCreateTagBuilder(SCTags.BANNER_COMPATIBLE.getTag())
                .add(ModItems.SURCOAT.get(), ModItems.SURCOAT_SLEEVELESS.get());

        getOrCreateTagBuilder(SCTags.HIDE_NAME_TAG.getTag())
                .add(ModItems.HOOD.get(), ModItems.TORN_HOOD.get(), ModItems.JESTER_HOOD.get(), ModItems.HELMET_HOOD.get(), ModItems.HELMET_TORN_HOOD.get());

        getOrCreateTagBuilder(ModTags.DYES.getTag())
                .add(Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.LIGHT_BLUE_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_GRAY_DYE,
                        Items.LIME_DYE, Items.MAGENTA_DYE, Items.PINK_DYE, Items.ORANGE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE, Items.BROWN_DYE);
    }
}