package com.knightsheraldry.datagen;

import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WARSWORD, 1)
                .pattern("HC")
                .input('H', ModItems.SMITHING_HAMMER)
                .input('C', ModItems.WARSWORD)
                .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                .criterion(hasItem(ModItems.WARSWORD), conditionsFromItem(ModItems.WARSWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.WARSWORD)));
    }
}
