package com.knightsheraldry.datagen;

import banduty.stoneycore.items.SCItems;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
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

        createAventailHelmet(exporter, ModItems.ARMET.get());
        createAventailHelmet(exporter, ModItems.ARMET_2.get());
        createAventailHelmet(exporter, ModItems.VIKING_HELM.get());
        createAventailHelmet(exporter, ModItems.GREAT_HELM.get());
        createAventailHelmet(exporter, ModItems.GREAT_HELM_2.get());
        createAventailHelmet(exporter, ModItems.KETTLE_HELM.get());
        createAventailHelmet(exporter, ModItems.NASAL_HELM.get());
        createAventailHelmet(exporter, ModItems.SALLET.get());
        createAventailHelmet(exporter, ModItems.BARBUTE.get());
        createAventailHelmet(exporter, ModItems.BARBUTE_NO_VISOR.get());
        createAventailHelmet(exporter, ModItems.BASCINET.get());
        createAventailHelmet(exporter,  ModItems.BASCINET_NO_VISOR.get());
        createAventailHelmet(exporter, ModItems.CAGE.get());
        createAventailHelmet(exporter,  ModItems.CAGE_2.get());
        createAventailHelmet(exporter,  ModItems.FLAT_BASCINET.get());

        createPlumeRecipe(exporter,  ModItems.BARBUTE_NO_VISOR.get());
        createPlumeRecipe(exporter,  ModItems.BASCINET_NO_VISOR.get());
        createPlumeRecipe(exporter,  ModItems.KETTLE_HELM.get());
        createPlumeRecipe(exporter,  ModItems.NASAL_HELM.get());
        createPlumeRecipe(exporter,  ModItems.VIKING_HELM.get());
        createPlumeRecipe(exporter,  ModItems.ARMET.get());
        createPlumeRecipe(exporter,  ModItems.ARMET_2.get());
        createPlumeRecipe(exporter,  ModItems.BARBUTE.get());
        createPlumeRecipe(exporter,  ModItems.BASCINET.get());
        createPlumeRecipe(exporter,  ModItems.CAGE.get());
        createPlumeRecipe(exporter,  ModItems.CAGE_2.get());
        createPlumeRecipe(exporter,  ModItems.FLAT_BASCINET.get());
        createPlumeRecipe(exporter,  ModItems.GREAT_HELM.get());
        createPlumeRecipe(exporter,  ModItems.GREAT_HELM_2.get());
        createPlumeRecipe(exporter,  ModItems.SALLET.get());
        createPlumeRecipe(exporter,  ModItems.FROGMOUTH.get());
        createPlumeRecipe(exporter,  ModItems.GREAT_ARMET.get());
        createPlumeRecipe(exporter,  ModItems.GREAT_ARMET_2.get());
        createPlumeRecipe(exporter,  ModItems.GREAT_BASCINET.get());
        createPlumeRecipe(exporter,  ModItems.GREAT_HOUNDSKUL_BASCINET.get());
        createPlumeRecipe(exporter,  ModItems.MAXIMILLIAN_HELMET.get());
        createPlumeRecipe(exporter,  ModItems.HORSE_BARDING.get());

        createEasyRecipe(exporter, ModItems.PLATE_PAULDRON.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.MAIL_PAULDRON.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.BRIGANDINE_PAULDRON.get(), ModItems.RIM_GUARDS.get());
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
    }

    private void createAventailHelmet(Consumer<RecipeJsonProvider> exporter, Item helmet) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, helmet, 1)
                .input(helmet)
                .input(ModItems.AVENTAIL.get())
                .criterion(hasItem(helmet), conditionsFromItem(helmet))
                .criterion(hasItem(ModItems.AVENTAIL.get()), conditionsFromItem(ModItems.AVENTAIL.get()))
                .offerTo(exporter, new Identifier(
                        KnightsHeraldry.MOD_ID,
                        "aventail/" + Registries.ITEM.getId(helmet).getPath()
                ));
    }

    private void createPlumeRecipe(Consumer<RecipeJsonProvider> exporter, Item item) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, item, 1)
                .input(item)
                .input(ModItems.PLUME.get())
                .criterion(hasItem(item), conditionsFromItem(item))
                .criterion(hasItem(ModItems.PLUME.get()), conditionsFromItem(ModItems.PLUME.get()))
                .offerTo(exporter, new Identifier(
                        KnightsHeraldry.MOD_ID,
                        "plume/" + Registries.ITEM.getId(item).getPath()
                ));
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
