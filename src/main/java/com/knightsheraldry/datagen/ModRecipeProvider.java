package com.knightsheraldry.datagen;

import banduty.stoneycore.items.SCItems;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import com.knightsheraldry.util.itemdata.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        createWeaponCycle(exporter, ModItems.ARMING_SWORD.get(), ModItems.SWORD.get(), ModItems.V_SWORD.get());
        createWeaponCycle(exporter, ModItems.STRAIGHT_CROOKED_AXE.get(), ModItems.AXE.get(), ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get());
        createWeaponCycle(exporter, ModItems.MACE.get(), ModItems.SPIKED_MACE.get());
        createWeaponCycle(exporter, ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get());
        createWeaponCycle(exporter, ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get());
        createWeaponCycle(exporter, ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get());
        createWeaponCycle(exporter, ModItems.FALCHION.get(), ModItems.SCIMITAR.get());
        createWeaponCycle(exporter, ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get());
        createWeaponCycle(exporter, ModItems.WARSWORD.get(), ModItems.WARSWORD_CLAYMORE.get(),
                ModItems.WARSWORD_FLAMBERGE.get(), ModItems.WARSWORD_ZWEIHANDER.get());

        createDecoRecipe(exporter,  ModItems.BARBUTE_NO_VISOR.get());
        createDecoRecipe(exporter,  ModItems.BASCINET_NO_VISOR.get());
        createDecoRecipe(exporter,  ModItems.KETTLE_HELM.get());
        createDecoRecipe(exporter,  ModItems.NASAL_HELM.get());
        createDecoRecipe(exporter,  ModItems.VIKING_HELM.get());
        createDecoRecipe(exporter,  ModItems.ARMET.get());
        createDecoRecipe(exporter,  ModItems.ARMET_2.get());
        createDecoRecipe(exporter,  ModItems.BARBUTE.get());
        createDecoRecipe(exporter,  ModItems.BASCINET.get());
        createDecoRecipe(exporter,  ModItems.CAGE.get());
        createDecoRecipe(exporter,  ModItems.CAGE_2.get());
        createDecoRecipe(exporter,  ModItems.FLAT_BASCINET.get());
        createDecoRecipe(exporter,  ModItems.GREAT_HELM.get());
        createDecoRecipe(exporter,  ModItems.GREAT_HELM_2.get());
        createDecoRecipe(exporter,  ModItems.SALLET.get());
        createDecoRecipe(exporter,  ModItems.FROGMOUTH.get());
        createDecoRecipe(exporter,  ModItems.GREAT_ARMET.get());
        createDecoRecipe(exporter,  ModItems.GREAT_ARMET_2.get());
        createDecoRecipe(exporter,  ModItems.GREAT_BASCINET.get());
        createDecoRecipe(exporter,  ModItems.GREAT_HOUNDSKUL_BASCINET.get());
        createDecoRecipe(exporter,  ModItems.MAXIMILLIAN_HELMET.get());

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HORSE_BARDING.get(), 1)
                .input(ModItems.HORSE_BARDING.get())
                .input(ModItems.PLUME.get())
                .criterion(hasItem(ModItems.HORSE_BARDING.get()), conditionsFromItem(ModItems.HORSE_BARDING.get()))
                .criterion(hasItem(ModItems.PLUME.get()), conditionsFromItem(ModItems.PLUME.get()))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID,
                        "deco/" + Registries.ITEM.getId(ModItems.PLUME.get()).getPath() + "_" + Registries.ITEM.getId(ModItems.HORSE_BARDING.get()).getPath()
                ));

        createEasyRecipe(exporter, ModItems.PLATE_PAULDRON.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.MAIL_PAULDRON.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.BRIGANDINE_PAULDRON.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.PLATE_PAULDRON.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.MAIL_PAULDRON.get(), ModItems.BESAGEWS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.BRIGANDINE_PAULDRON.get(), ModItems.BESAGEWS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.PLATE_PAULDRON.get(), ModItems.BESAGEWS.get(), ModItems.RIM_GUARDS.get());

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SURCOAT.get(), 1)
                .input(ModItems.SURCOAT.get())
                .input(ItemTags.BANNERS)
                .criterion(hasItem(ModItems.SURCOAT.get()), conditionsFromItem(ModItems.SURCOAT.get()))
                .criterion(hasItem(Items.WHITE_BANNER), conditionsFromItem(Items.WHITE_BANNER))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, "surcoat_with_banner"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SURCOAT_SLEEVELESS.get(), 1)
                .input(ModItems.SURCOAT_SLEEVELESS.get())
                .input(ItemTags.BANNERS)
                .criterion(hasItem(ModItems.SURCOAT_SLEEVELESS.get()), conditionsFromItem(ModItems.SURCOAT_SLEEVELESS.get()))
                .criterion(hasItem(Items.WHITE_BANNER), conditionsFromItem(Items.WHITE_BANNER))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, "sleeveless_surcoat_with_banner"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JOUSTING_BAND.get())
                .input(ModItems.JOUSTING_BAND.get())
                .input(ModTags.DYES.getTag())
                .criterion(hasItem(ModItems.JOUSTING_BAND.get()), conditionsFromItem(ModItems.JOUSTING_BAND.get()))
                .criterion("has_" + ModTags.DYES.getTag(), conditionsFromTag(ModTags.DYES.getTag()))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, "jousting_band_dye"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JOUSTING_BAND.get())
                .input(ModItems.JOUSTING_BAND.get())
                .input(ModTags.DYES.getTag())
                .input(ModTags.DYES.getTag())
                .criterion(hasItem(ModItems.JOUSTING_BAND.get()), conditionsFromItem(ModItems.JOUSTING_BAND.get()))
                .criterion("has_" + ModTags.DYES.getTag(), conditionsFromTag(ModTags.DYES.getTag()))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, "jousting_band_dye_double"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TRI_PLUME.get())
                .input(ModItems.PLUME.get())
                .input(ModItems.PLUME.get())
                .input(ModItems.PLUME.get())
                .criterion(hasItem(ModItems.PLUME.get()), conditionsFromItem(ModItems.PLUME.get()))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, getRecipeName(ModItems.TRI_PLUME.get())));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUFFY_PLUME.get())
                .input(ModItems.PLUME.get())
                .input(ModItems.PLUME.get())
                .input(ModItems.PLUME.get())
                .input(ModItems.PLUME.get())
                .input(ModItems.PLUME.get())
                .criterion(hasItem(ModItems.PLUME.get()), conditionsFromItem(ModItems.PLUME.get()))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, getRecipeName(ModItems.FLUFFY_PLUME.get())));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUFFY_PLUME.get())
                .input(ModItems.TRI_PLUME.get())
                .input(ModItems.PLUME.get())
                .input(ModItems.PLUME.get())
                .criterion(hasItem(ModItems.TRI_PLUME.get()), conditionsFromItem(ModItems.TRI_PLUME.get()))
                .criterion(hasItem(ModItems.PLUME.get()), conditionsFromItem(ModItems.PLUME.get()))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, getRecipeName(ModItems.FLUFFY_PLUME.get()) + "_2"));
    }

    private static void createDecoRecipe(Consumer<RecipeJsonProvider> exporter, Item helmet) {
        // Single deco recipes
        for (HelmetDeco deco : HelmetDeco.HELMET_DECO.values()) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, helmet, 1)
                    .input(helmet)
                    .input(deco.item())
                    .criterion(hasItem(helmet), conditionsFromItem(helmet))
                    .criterion(hasItem(deco.item()), conditionsFromItem(deco.item()))
                    .offerTo(exporter, new Identifier(
                            KnightsHeraldry.MOD_ID,
                            "deco/" + Registries.ITEM.getId(deco.item()).getPath()
                                    + "_" + Registries.ITEM.getId(helmet).getPath()
                    ));
        }

        // Grouped deco recipes: helmet + deco(group 0) + deco(group 1)
        for (Item deco0 : HelmetDeco.getDecoGroup(0)) {
            for (Item deco1 : HelmetDeco.getDecoGroup(1)) {
                ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, helmet, 1)
                        .input(helmet)
                        .input(deco0)
                        .input(deco1)
                        .criterion(hasItem(helmet), conditionsFromItem(helmet))
                        .criterion(hasItem(deco0), conditionsFromItem(deco0))
                        .criterion(hasItem(deco1), conditionsFromItem(deco1));

                builder.offerTo(exporter, new Identifier(
                        KnightsHeraldry.MOD_ID,
                        "deco/" + Registries.ITEM.getId(deco0).getPath()
                                + "_" + Registries.ITEM.getId(deco1).getPath()
                                + "_" + Registries.ITEM.getId(helmet).getPath()
                ));
            }
        }
    }

    private void createWeaponCycle(Consumer<RecipeJsonProvider> exporter, Item... weapons) {
        for (int i = 0; i < weapons.length; i++) {
            Item current = weapons[i];
            Item next = weapons[(i + 1) % weapons.length];

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, next, 1)
                    .input(SCItems.SMITHING_HAMMER.get())
                    .input(current)
                    .criterion(hasItem(SCItems.SMITHING_HAMMER.get()), conditionsFromItem(SCItems.SMITHING_HAMMER.get()))
                    .criterion(hasItem(current), conditionsFromItem(current))
                    .offerTo(exporter, new Identifier(
                            KnightsHeraldry.MOD_ID,
                            "upgrade_" + Registries.ITEM.getId(current).getPath() + "_to_" + Registries.ITEM.getId(next).getPath()
                    ));
        }
    }

    private void createEasyRecipe(Consumer<RecipeJsonProvider> exporter, Item principal, Item... attachments) {
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, principal, 1)
                .input(principal)
                .criterion(hasItem(principal), conditionsFromItem(principal));

        for (Item attachment : attachments) {
            builder.input(attachment)
                    .criterion(hasItem(attachment), conditionsFromItem(attachment));
        }

        StringBuilder recipeId = new StringBuilder(Registries.ITEM.getId(principal).getPath());
        for (Item attachment : attachments) {
            recipeId.append("_").append(Registries.ITEM.getId(attachment).getPath());
        }

        builder.offerTo(exporter, new Identifier(
                KnightsHeraldry.MOD_ID,
                recipeId.toString()
        ));
    }
}
