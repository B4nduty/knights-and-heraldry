package com.knightsheraldry.datagen;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SMITHING_HAMMER, 1)
                .pattern("IIN")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', Items.IRON_INGOT)
                .input('N', Items.IRON_NUGGET)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SMITHING_HAMMER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SWORD, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.ARMING_SWORD, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.ARMING_SWORD), conditionsFromItem(ModItems.ARMING_SWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SWORD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.V_SWORD, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.SWORD, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.SWORD), conditionsFromItem(ModItems.SWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.V_SWORD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ARMING_SWORD, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.V_SWORD, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.V_SWORD), conditionsFromItem(ModItems.V_SWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARMING_SWORD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AXE, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.STRAIGHT_CROOKED_AXE, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.STRAIGHT_CROOKED_AXE), conditionsFromItem(ModItems.STRAIGHT_CROOKED_AXE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AXE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BROAD_AXE, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.AXE, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.AXE), conditionsFromItem(ModItems.AXE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BROAD_AXE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CROOKED_AXE, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.BROAD_AXE, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.BROAD_AXE), conditionsFromItem(ModItems.BROAD_AXE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CROOKED_AXE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STRAIGHT_CROOKED_AXE, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.CROOKED_AXE, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.CROOKED_AXE), conditionsFromItem(ModItems.CROOKED_AXE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STRAIGHT_CROOKED_AXE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MACE, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.SPIKED_MACE, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.SPIKED_MACE), conditionsFromItem(ModItems.SPIKED_MACE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MACE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPIKED_MACE, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.MACE, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.MACE), conditionsFromItem(ModItems.MACE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SPIKED_MACE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLAIL, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.BALL_FLAIL, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.BALL_FLAIL), conditionsFromItem(ModItems.BALL_FLAIL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FLAIL)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BALL_FLAIL, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.FLAIL, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.FLAIL), conditionsFromItem(ModItems.FLAIL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BALL_FLAIL)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HAMMER, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.WAR_HAMMER, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.WAR_HAMMER), conditionsFromItem(ModItems.WAR_HAMMER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HAMMER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WAR_HAMMER, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.HAMMER, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.HAMMER), conditionsFromItem(ModItems.HAMMER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WAR_HAMMER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LONGSWORD, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.V_LONGSWORD, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.V_LONGSWORD), conditionsFromItem(ModItems.V_LONGSWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.LONGSWORD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.V_LONGSWORD, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.LONGSWORD, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.LONGSWORD), conditionsFromItem(ModItems.LONGSWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.V_LONGSWORD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FALCHION, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.SCIMITAR, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.SCIMITAR), conditionsFromItem(ModItems.SCIMITAR))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FALCHION)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCIMITAR, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.FALCHION, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.FALCHION), conditionsFromItem(ModItems.FALCHION))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SCIMITAR)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.POLEHAMMER, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.BEC_DE_CORBIN, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.BEC_DE_CORBIN), conditionsFromItem(ModItems.BEC_DE_CORBIN))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.POLEHAMMER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BEC_DE_CORBIN, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.POLEHAMMER, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.POLEHAMMER), conditionsFromItem(ModItems.POLEHAMMER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BEC_DE_CORBIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WARSWORD_CLAYMORE, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.WARSWORD, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.WARSWORD), conditionsFromItem(ModItems.WARSWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WARSWORD_CLAYMORE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WARSWORD_FLAMBERGE, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.WARSWORD_CLAYMORE, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.WARSWORD_CLAYMORE), conditionsFromItem(ModItems.WARSWORD_CLAYMORE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WARSWORD_FLAMBERGE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WARSWORD_ZWEIHANDER, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.WARSWORD_FLAMBERGE, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.WARSWORD_FLAMBERGE), conditionsFromItem(ModItems.WARSWORD_FLAMBERGE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WARSWORD_ZWEIHANDER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WARSWORD, 1)
                .input(ModItems.SMITHING_HAMMER, 1)
                .input(ModItems.WARSWORD_ZWEIHANDER, 1)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.WARSWORD_ZWEIHANDER), conditionsFromItem(ModItems.WARSWORD_ZWEIHANDER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WARSWORD)));

        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(ModItems.QUILTED_COIF),
                        Ingredient.ofItems(Items.NETHERITE_INGOT), RecipeCategory.MISC, ModItems.QUILTED_COIF)
                .criterion(hasItem(ModItems.QUILTED_COIF), conditionsFromItem(ModItems.QUILTED_COIF))
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, new Identifier("example_1"));
    }
}
