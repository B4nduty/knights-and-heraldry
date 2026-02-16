package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.KnightsHeraldry;
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

    static void register(IEventBus modEventBus) {
        RECIPE_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        KnightsHeraldry.LOG.info("Registered ModRecipes for " + KnightsHeraldry.MOD_ID);
    }
}
