package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.recipe.TwoLayerDyeRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface ModRecipes {

    DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, KnightsHeraldry.MOD_ID);
    DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KnightsHeraldry.MOD_ID);

    RegistryObject<RecipeSerializer<HelmetDecoRecipe>> HELMET_DECO_SERIALIZER =
            RECIPE_SERIALIZERS.register("helmet_deco_crafting",
                    () -> new SimpleCraftingRecipeSerializer<>(HelmetDecoRecipe::new));
    RegistryObject<RecipeSerializer<TwoLayerDyeRecipe>> TWO_LAYER_DYEING_SERIALIZER =
            RECIPE_SERIALIZERS.register("two_layer_dyeing",
                    () -> new SimpleCraftingRecipeSerializer<>(TwoLayerDyeRecipe::new));
    RegistryObject<RecipeSerializer<SurcoatBannerRecipe>> SURCOAT_BANNER =
            RECIPE_SERIALIZERS.register("surcoat_banner",
                    () -> new SimpleCraftingRecipeSerializer<>(SurcoatBannerRecipe::new));

    static void register(IEventBus modEventBus) {
        RECIPE_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        KnightsHeraldry.LOG.info("Registered ModRecipes for " + KnightsHeraldry.MOD_ID);
    }
}
