package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.stoneycore.datagen.BannerRecipeBuilder;
import banduty.stoneycore.datagen.CraftmanAnvilRecipeJsonBuilder;
import banduty.stoneycore.datagen.ManuscriptRecipeBuilder;
import banduty.stoneycore.items.SCItems;
import banduty.stoneycore.items.hotiron.HotIron;
import banduty.stoneycore.items.manuscript.Manuscript;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        createWeaponCycle(exporter, ModItems.ARMING_SWORD.get(), ModItems.SWORD.get(), ModItems.V_SWORD.get());
        createWeaponCycle(exporter, ModItems.STRAIGHT_CROOKED_AXE.get(), ModItems.AXE.get(), ModItems.BROAD_AXE.get(), ModItems.CROOKED_AXE.get());
        createWeaponCycle(exporter, ModItems.MACE.get(), ModItems.SPIKED_MACE.get());
        createWeaponCycle(exporter, ModItems.FLAIL.get(), ModItems.BALL_FLAIL.get());
        createWeaponCycle(exporter, ModItems.HAMMER.get(), ModItems.WAR_HAMMER.get());
        createWeaponCycle(exporter, ModItems.LONGSWORD.get(), ModItems.V_LONGSWORD.get());
        createWeaponCycle(exporter, ModItems.FALCHION.get(), ModItems.SCIMITAR.get());
        createWeaponCycle(exporter, ModItems.POLEHAMMER.get(), ModItems.BEC_DE_CORBIN.get());
        createWeaponCycle(exporter, ModItems.GREATSWORD.get(), ModItems.CLAYMORE.get(),
                ModItems.FLAMBERGE.get(), ModItems.ZWEIHANDER.get());

        createDecoRecipe(exporter, ModItems.BARBUTE.get());
        createDecoRecipe(exporter, ModItems.BASCINET.get());
        createDecoRecipe(exporter, ModItems.KETTLE_HELM.get());
        createDecoRecipe(exporter, ModItems.NASAL_HELM.get());
        createDecoRecipe(exporter, ModItems.VIKING_HELM.get());
        createDecoRecipe(exporter, ModItems.BURGONET.get());
        createDecoRecipe(exporter, ModItems.ARMET.get());
        createDecoRecipe(exporter, ModItems.ARMET_2.get());
        createDecoRecipe(exporter, ModItems.VISORED_BARBUTE.get());
        createDecoRecipe(exporter, ModItems.HOUNDSKULL.get());
        createDecoRecipe(exporter, ModItems.CAGE.get());
        createDecoRecipe(exporter, ModItems.CAGE_2.get());
        createDecoRecipe(exporter, ModItems.VISORED_BASCINET.get());
        createDecoRecipe(exporter, ModItems.GREAT_HELM.get());
        createDecoRecipe(exporter, ModItems.GREAT_HELM_2.get());
        createDecoRecipe(exporter, ModItems.SALLET.get());
        createDecoRecipe(exporter, ModItems.BURGONET_FALLING_BUFFE.get());
        createDecoRecipe(exporter, ModItems.CLOSE_HELM.get());
        createDecoRecipe(exporter, ModItems.FROGMOUTH.get());
        createDecoRecipe(exporter, ModItems.GREAT_ARMET.get());
        createDecoRecipe(exporter, ModItems.GREAT_ARMET_2.get());
        createDecoRecipe(exporter, ModItems.GREAT_BASCINET.get());
        createDecoRecipe(exporter, ModItems.GREAT_HOUNDSKUL_BASCINET.get());
        createDecoRecipe(exporter, ModItems.MAXIMILLIAN_HELMET.get());
        createDecoRecipe(exporter, ModItems.SAVOYARD.get());

        createDecoRecipe(exporter, ModItems.DARK_BARBUTE.get());
        createDecoRecipe(exporter, ModItems.DARK_BASCINET.get());
        createDecoRecipe(exporter, ModItems.DARK_KETTLE_HELM.get());
        createDecoRecipe(exporter, ModItems.DARK_NASAL_HELM.get());
        createDecoRecipe(exporter, ModItems.DARK_VIKING_HELM.get());
        createDecoRecipe(exporter, ModItems.DARK_BURGONET.get());
        createDecoRecipe(exporter, ModItems.DARK_ARMET.get());
        createDecoRecipe(exporter, ModItems.DARK_ARMET_2.get());
        createDecoRecipe(exporter, ModItems.DARK_VISORED_BARBUTE.get());
        createDecoRecipe(exporter, ModItems.DARK_HOUNDSKULL.get());
        createDecoRecipe(exporter, ModItems.DARK_CAGE.get());
        createDecoRecipe(exporter, ModItems.DARK_CAGE_2.get());
        createDecoRecipe(exporter, ModItems.DARK_VISORED_BASCINET.get());
        createDecoRecipe(exporter, ModItems.DARK_GREAT_HELM.get());
        createDecoRecipe(exporter, ModItems.DARK_GREAT_HELM_2.get());
        createDecoRecipe(exporter, ModItems.DARK_SALLET.get());
        createDecoRecipe(exporter, ModItems.DARK_CLOSE_HELM.get());
        createDecoRecipe(exporter, ModItems.DARK_BURGONET_FALLING_BUFFE.get());
        createDecoRecipe(exporter, ModItems.DARK_FROGMOUTH.get());
        createDecoRecipe(exporter, ModItems.DARK_GREAT_ARMET.get());
        createDecoRecipe(exporter, ModItems.DARK_GREAT_ARMET_2.get());
        createDecoRecipe(exporter, ModItems.DARK_GREAT_BASCINET.get());
        createDecoRecipe(exporter, ModItems.DARK_GREAT_HOUNDSKUL_BASCINET.get());
        createDecoRecipe(exporter, ModItems.DARK_MAXIMILLIAN_HELMET.get());
        createDecoRecipe(exporter, ModItems.DARK_SAVOYARD.get());

        createDecoRecipe(exporter, ModItems.GOLDEN_BARBUTE.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_BASCINET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_KETTLE_HELM.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_NASAL_HELM.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_VIKING_HELM.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_BURGONET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_ARMET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_ARMET_2.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_VISORED_BARBUTE.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_HOUNDSKULL.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_CAGE.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_CAGE_2.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_VISORED_BASCINET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_HELM.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_HELM_2.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_SALLET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_CLOSE_HELM.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_BURGONET_FALLING_BUFFE.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_FROGMOUTH.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_ARMET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_ARMET_2.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_BASCINET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_MAXIMILLIAN_HELMET.get());
        createDecoRecipe(exporter, ModItems.GOLDEN_SAVOYARD.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HORSE_BARDING.get(), 1)
                .requires(ModItems.HORSE_BARDING.get())
                .requires(ModItems.PLUME.get())
                .unlockedBy(getHasName(ModItems.HORSE_BARDING.get()), has(ModItems.HORSE_BARDING.get()))
                .unlockedBy(getHasName(ModItems.PLUME.get()), has(ModItems.PLUME.get()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID,
                        "deco/" + BuiltInRegistries.ITEM.getKey(ModItems.PLUME.get()).getPath() + "_" + BuiltInRegistries.ITEM.getKey(ModItems.HORSE_BARDING.get()).getPath()
                ));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_HORSE_BARDING.get(), 1)
                .requires(ModItems.DARK_HORSE_BARDING.get())
                .requires(ModItems.PLUME.get())
                .unlockedBy(getHasName(ModItems.DARK_HORSE_BARDING.get()), has(ModItems.DARK_HORSE_BARDING.get()))
                .unlockedBy(getHasName(ModItems.PLUME.get()), has(ModItems.PLUME.get()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID,
                        "deco/" + BuiltInRegistries.ITEM.getKey(ModItems.PLUME.get()).getPath() + "_" + BuiltInRegistries.ITEM.getKey(ModItems.DARK_HORSE_BARDING.get()).getPath()
                ));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GOLDEN_HORSE_BARDING.get(), 1)
                .requires(ModItems.GOLDEN_HORSE_BARDING.get())
                .requires(ModItems.PLUME.get())
                .unlockedBy(getHasName(ModItems.GOLDEN_HORSE_BARDING.get()), has(ModItems.GOLDEN_HORSE_BARDING.get()))
                .unlockedBy(getHasName(ModItems.PLUME.get()), has(ModItems.PLUME.get()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID,
                        "deco/" + BuiltInRegistries.ITEM.getKey(ModItems.PLUME.get()).getPath() + "_" + BuiltInRegistries.ITEM.getKey(ModItems.GOLDEN_HORSE_BARDING.get()).getPath()
                ));

        createEasyRecipe(exporter, ModItems.MAIL_SPAULDERS_BESAGEWS.get(), ModItems.MAIL_SPAULDERS.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get(), ModItems.GOLDEN_MAIL_SPAULDERS.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(), ModItems.BRIGANDINE_SPAULDERS.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(), ModItems.DARK_BRIGANDINE_SPAULDERS.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get(), ModItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_BESAGEWS.get(), ModItems.PLATE_SPAULDERS.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), ModItems.DARK_PLATE_SPAULDERS.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), ModItems.GOLDEN_PLATE_SPAULDERS.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_RIMMED.get(), ModItems.PLATE_SPAULDERS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_RIMMED.get(), ModItems.DARK_PLATE_SPAULDERS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(), ModItems.GOLDEN_PLATE_SPAULDERS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.PLATE_SPAULDERS.get(), ModItems.BESAGEWS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.DARK_PLATE_SPAULDERS.get(), ModItems.BESAGEWS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.GOLDEN_PLATE_SPAULDERS.get(), ModItems.BESAGEWS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.PLATE_SPAULDERS_BESAGEWS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), ModItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.PLATE_SPAULDERS_RIMMED.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.DARK_PLATE_SPAULDERS_RIMMED.get(), ModItems.BESAGEWS.get());
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(), ModItems.BESAGEWS.get());

  /*      ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SURCOAT.get(), 1)
                .requires(ModItems.SURCOAT.get())
                .requires(ItemTags.BANNERS)
                .unlockedBy(getHasName(ModItems.SURCOAT.get()), has(ModItems.SURCOAT.get()))
                .unlockedBy(getHasName(Items.WHITE_BANNER), has(Items.WHITE_BANNER))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, "surcoat_with_banner"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SURCOAT_SLEEVELESS.get(), 1)
                .requires(ModItems.SURCOAT_SLEEVELESS.get())
                .requires(ItemTags.BANNERS)
                .unlockedBy(getHasName(ModItems.SURCOAT_SLEEVELESS.get()), has(ModItems.SURCOAT_SLEEVELESS.get()))
                .unlockedBy(getHasName(Items.WHITE_BANNER), has(Items.WHITE_BANNER))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, "sleeveless_surcoat_with_banner"));
*/
       /* ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TORSE.get())
                .requires(ModItems.TORSE.get())
                .requires(ModTags.DYES.getTag())
                .unlockedBy(getHasName(ModItems.TORSE.get()), has(ModItems.TORSE.get()))
                .unlockedBy("has_" + ModTags.DYES.getTag(), has(ModTags.DYES.getTag()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, BuiltInRegistries.ITEM.getKey(ModItems.TORSE.get()).getPath() + "_dye"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TORSE.get())
                .requires(ModItems.TORSE.get())
                .requires(ModTags.DYES.getTag())
                .requires(ModTags.DYES.getTag())
                .unlockedBy(getHasName(ModItems.TORSE.get()), has(ModItems.TORSE.get()))
                .unlockedBy("has_" + ModTags.DYES.getTag(), has(ModTags.DYES.getTag()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, BuiltInRegistries.ITEM.getKey(ModItems.TORSE.get()).getPath() + "_dye_double"));
*/
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TRI_PLUME.get())
                .requires(ModItems.PLUME.get())
                .requires(ModItems.PLUME.get())
                .requires(ModItems.PLUME.get())
                .unlockedBy(getHasName(ModItems.PLUME.get()), has(ModItems.PLUME.get()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, getSimpleRecipeName(ModItems.TRI_PLUME.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLUFFY_PLUME.get())
                .requires(ModItems.PLUME.get())
                .requires(ModItems.PLUME.get())
                .requires(ModItems.PLUME.get())
                .requires(ModItems.PLUME.get())
                .requires(ModItems.PLUME.get())
                .unlockedBy(getHasName(ModItems.PLUME.get()), has(ModItems.PLUME.get()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, getSimpleRecipeName(ModItems.FLUFFY_PLUME.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLUFFY_PLUME.get())
                .requires(ModItems.TRI_PLUME.get())
                .requires(ModItems.PLUME.get())
                .requires(ModItems.PLUME.get())
                .unlockedBy(getHasName(ModItems.TRI_PLUME.get()), has(ModItems.TRI_PLUME.get()))
                .unlockedBy(getHasName(ModItems.PLUME.get()), has(ModItems.PLUME.get()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, getSimpleRecipeName(ModItems.FLUFFY_PLUME.get()) + "_2"));

        // Golden
        createSmithingRecipe(exporter, ModItems.GOLDEN_MAIL_SPAULDERS.get(), 7, 0.7f,
                new ItemStack(ModItems.MAIL_SPAULDERS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), 7, 0.7f,
                new ItemStack(ModItems.BRIGANDINE_SPAULDERS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS.get(), 7, 0.7f,
                new ItemStack(ModItems.PLATE_SPAULDERS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIGANDINE.get(), 7, 0.7f,
                new ItemStack(ModItems.BRIGANDINE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIG_BREASTPLATE.get(), 7, 0.7f,
                new ItemStack(ModItems.BRIG_BREASTPLATE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get(), 7, 0.7f,
                new ItemStack(ModItems.BRIG_BREASTPLATE_TASSETS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_CUIRASS.get(), 7, 0.7f,
                new ItemStack(ModItems.PLATE_CUIRASS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_CUIRASS_TASSETS.get(), 7, 0.7f,
                new ItemStack(ModItems.PLATE_CUIRASS_TASSETS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_MAXIMILLIAN_CUIRASS.get(), 7, 0.7f,
                new ItemStack(ModItems.MAXIMILLIAN_CUIRASS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS.get(), 7, 0.7f,
                new ItemStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_XIIII_PLATE_CUIRASS.get(), 7, 0.7f,
                new ItemStack(ModItems.XIIII_PLATE_CUIRASS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS.get(), 7, 0.7f,
                new ItemStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get(), 7, 0.7f,
                new ItemStack(ModItems.XIIII_PLATE_BREASTPLATE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BARBUTE.get(), 7, 0.7f,
                new ItemStack(ModItems.BARBUTE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BASCINET.get(), 7, 0.7f,
                new ItemStack(ModItems.BASCINET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_KETTLE_HELM.get(), 7, 0.7f,
                new ItemStack(ModItems.KETTLE_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_NASAL_HELM.get(), 7, 0.7f,
                new ItemStack(ModItems.NASAL_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_VIKING_HELM.get(), 7, 0.7f,
                new ItemStack(ModItems.VIKING_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BURGONET.get(), 7, 0.7f,
                new ItemStack(ModItems.BURGONET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_ARMET.get(), 7, 0.7f,
                new ItemStack(ModItems.ARMET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_ARMET_2.get(), 7, 0.7f,
                new ItemStack(ModItems.ARMET_2.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_VISORED_BARBUTE.get(), 7, 0.7f,
                new ItemStack(ModItems.VISORED_BARBUTE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_HOUNDSKULL.get(), 7, 0.7f,
                new ItemStack(ModItems.HOUNDSKULL.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_CAGE.get(), 7, 0.7f,
                new ItemStack(ModItems.CAGE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_CAGE_2.get(), 7, 0.7f,
                new ItemStack(ModItems.CAGE_2.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_VISORED_BASCINET.get(), 7, 0.7f,
                new ItemStack(ModItems.VISORED_BASCINET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_HELM.get(), 7, 0.7f,
                new ItemStack(ModItems.GREAT_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_HELM_2.get(), 7, 0.7f,
                new ItemStack(ModItems.GREAT_HELM_2.get()), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, ModItems.GOLDEN_FROGMOUTH.get(), 7, 0.7f,
                new ItemStack(ModItems.FROGMOUTH.get()), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_ARMET.get(), 7, 0.7f,
                new ItemStack(ModItems.GREAT_ARMET.get()), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_ARMET_2.get(), 7, 0.7f,
                new ItemStack(ModItems.GREAT_ARMET_2.get()), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, ModItems.GOLDEN_SALLET.get(), 7, 0.7f,
                new ItemStack(ModItems.SALLET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BURGONET_FALLING_BUFFE.get(), 7, 0.7f,
                new ItemStack(ModItems.BURGONET_FALLING_BUFFE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_CLOSE_HELM.get(), 7, 0.7f,
                new ItemStack(ModItems.CLOSE_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_BASCINET.get(), 7, 0.7f,
                new ItemStack(ModItems.GREAT_BASCINET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get(), 7, 0.7f,
                new ItemStack(ModItems.GREAT_HOUNDSKUL_BASCINET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_MAXIMILLIAN_HELMET.get(), 7, 0.7f,
                new ItemStack(ModItems.MAXIMILLIAN_HELMET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_SAVOYARD.get(), 7, 0.7f,
                new ItemStack(ModItems.SAVOYARD.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GAUNTLET.get(), 7, 0.7f,
                new ItemStack(ModItems.GAUNTLET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIGANDINE_HARNESS.get(), 7, 0.7f,
                new ItemStack(ModItems.BRIGANDINE_HARNESS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_HARNESS.get(), 7, 0.7f,
                new ItemStack(ModItems.PLATE_HARNESS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIGANDINE_CUISSES.get(), 7, 0.7f,
                new ItemStack(ModItems.BRIGANDINE_CUISSES.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_CUISSES.get(), 7, 0.7f,
                new ItemStack(ModItems.PLATE_CUISSES.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAVES.get(), 7, 0.7f,
                new ItemStack(ModItems.GREAVES.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_SABATONS.get(), 7, 0.7f,
                new ItemStack(ModItems.SABATONS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BEVOR.get(), 7, 0.7f,
                new ItemStack(ModItems.BEVOR.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_HORSE_BARDING.get(), 7, 0.7f,
                new ItemStack(ModItems.HORSE_BARDING.get()), new ItemStack(Items.GOLD_INGOT, 5));

        // Darkened
        createSmithingRecipe(exporter, ModItems.DARK_BRIGANDINE_SPAULDERS.get(), 7, 0.85f,
                new ItemStack(ModItems.BRIGANDINE_SPAULDERS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS.get(), 7, 0.85f,
                new ItemStack(ModItems.PLATE_SPAULDERS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIGANDINE.get(), 7, 0.85f,
                new ItemStack(ModItems.BRIGANDINE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIG_BREASTPLATE.get(), 7, 0.85f,
                new ItemStack(ModItems.BRIG_BREASTPLATE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIG_BREASTPLATE_TASSETS.get(), 7, 0.85f,
                new ItemStack(ModItems.BRIG_BREASTPLATE_TASSETS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_CUIRASS.get(), 7, 0.85f,
                new ItemStack(ModItems.PLATE_CUIRASS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_CUIRASS_TASSETS.get(), 7, 0.85f,
                new ItemStack(ModItems.PLATE_CUIRASS_TASSETS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_MAXIMILLIAN_CUIRASS.get(), 7, 0.85f,
                new ItemStack(ModItems.MAXIMILLIAN_CUIRASS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS.get(), 7, 0.85f,
                new ItemStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_XIIII_PLATE_CUIRASS.get(), 7, 0.85f,
                new ItemStack(ModItems.XIIII_PLATE_CUIRASS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_XIIII_PLATE_CUIRASS_TASSETS.get(), 7, 0.85f,
                new ItemStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_XIIII_PLATE_BREASTPLATE.get(), 7, 0.85f,
                new ItemStack(ModItems.XIIII_PLATE_BREASTPLATE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BARBUTE.get(), 7, 0.85f,
                new ItemStack(ModItems.BARBUTE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BASCINET.get(), 7, 0.85f,
                new ItemStack(ModItems.BASCINET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_KETTLE_HELM.get(), 7, 0.85f,
                new ItemStack(ModItems.KETTLE_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_NASAL_HELM.get(), 7, 0.85f,
                new ItemStack(ModItems.NASAL_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_VIKING_HELM.get(), 7, 0.85f,
                new ItemStack(ModItems.VIKING_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BURGONET.get(), 7, 0.85f,
                new ItemStack(ModItems.BURGONET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_ARMET.get(), 7, 0.85f,
                new ItemStack(ModItems.ARMET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_ARMET_2.get(), 7, 0.85f,
                new ItemStack(ModItems.ARMET_2.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_VISORED_BARBUTE.get(), 7, 0.85f,
                new ItemStack(ModItems.VISORED_BARBUTE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_HOUNDSKULL.get(), 7, 0.85f,
                new ItemStack(ModItems.HOUNDSKULL.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_CAGE.get(), 7, 0.85f,
                new ItemStack(ModItems.CAGE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_CAGE_2.get(), 7, 0.85f,
                new ItemStack(ModItems.CAGE_2.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_VISORED_BASCINET.get(), 7, 0.85f,
                new ItemStack(ModItems.VISORED_BASCINET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_HELM.get(), 7, 0.85f,
                new ItemStack(ModItems.GREAT_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_HELM_2.get(), 7, 0.85f,
                new ItemStack(ModItems.GREAT_HELM_2.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_SALLET.get(), 7, 0.85f,
                new ItemStack(ModItems.SALLET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BURGONET_FALLING_BUFFE.get(), 7, 0.85f,
                new ItemStack(ModItems.BURGONET_FALLING_BUFFE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_CLOSE_HELM.get(), 7, 0.85f,
                new ItemStack(ModItems.CLOSE_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_FROGMOUTH.get(), 7, 0.85f,
                new ItemStack(ModItems.FROGMOUTH.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_ARMET.get(), 7, 0.85f,
                new ItemStack(ModItems.GREAT_ARMET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_ARMET_2.get(), 7, 0.85f,
                new ItemStack(ModItems.GREAT_ARMET_2.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_BASCINET.get(), 7, 0.85f,
                new ItemStack(ModItems.GREAT_BASCINET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(), 7, 0.85f,
                new ItemStack(ModItems.GREAT_HOUNDSKUL_BASCINET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_MAXIMILLIAN_HELMET.get(), 7, 0.85f,
                new ItemStack(ModItems.MAXIMILLIAN_HELMET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_SAVOYARD.get(), 7, 0.85f,
                new ItemStack(ModItems.SAVOYARD.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GAUNTLET.get(), 7, 0.85f,
                new ItemStack(ModItems.GAUNTLET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIGANDINE_HARNESS.get(), 7, 0.85f,
                new ItemStack(ModItems.BRIGANDINE_HARNESS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_HARNESS.get(), 7, 0.85f,
                new ItemStack(ModItems.PLATE_HARNESS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIGANDINE_CUISSES.get(), 7, 0.85f,
                new ItemStack(ModItems.BRIGANDINE_CUISSES.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_CUISSES.get(), 7, 0.85f,
                new ItemStack(ModItems.PLATE_CUISSES.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAVES.get(), 7, 0.85f,
                new ItemStack(ModItems.GREAVES.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_SABATONS.get(), 7, 0.85f,
                new ItemStack(ModItems.SABATONS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BEVOR.get(), 7, 0.85f,
                new ItemStack(ModItems.BEVOR.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_HORSE_BARDING.get(), 7, 0.85f,
                new ItemStack(ModItems.HORSE_BARDING.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.DAGGER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.STILETTO.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.RAPIER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.SWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.V_SWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.ARMING_SWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.AXE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BROAD_AXE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.CROOKED_AXE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.STRAIGHT_CROOKED_AXE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.MACE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.SPIKED_MACE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.FLAIL.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BALL_FLAIL.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.HAMMER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.WAR_HAMMER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.LONGSWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.V_LONGSWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.FALCHION.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.SCIMITAR.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.PITCHFORK.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.SPEAR.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.PIKE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BILLHOOK.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.GLAIVE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.CURVED_GLAIVE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.HALBERD.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.LANCE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.LOGS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.POLEAXE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.POLEHAMMER.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.BEC_DE_CORBIN.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.MORNING_STAR.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BARDICHE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREATSWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.CLAYMORE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.FLAMBERGE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.ZWEIHANDER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.WARDART.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);

        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.MAIL_COIF.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.HAUBERK.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.MAIL_BREECHES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.MAIL_BOOTS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.MAIL_SPAULDERS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BRIGANDINE_SPAULDERS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.PLATE_SPAULDERS.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BRIGANDINE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 1.0f, ModItems.BRIG_BREASTPLATE.get(), new ItemStack(ModItems.BRIGANDINE.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, ModItems.BRIG_BREASTPLATE_TASSETS.get(), new ItemStack(ModItems.BRIG_BREASTPLATE.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.PLATE_CUIRASS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, ModItems.PLATE_CUIRASS_TASSETS.get(), new ItemStack(ModItems.PLATE_CUIRASS.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.MAXIMILLIAN_CUIRASS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, ModItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), new ItemStack(ModItems.MAXIMILLIAN_CUIRASS.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.XIIII_PLATE_CUIRASS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, ModItems.XIIII_PLATE_CUIRASS_TASSETS.get(), new ItemStack(ModItems.XIIII_PLATE_CUIRASS.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.XIIII_PLATE_BREASTPLATE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.RIM_GUARDS.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BESAGEWS.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BARBUTE.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BASCINET.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.KETTLE_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.NASAL_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.VIKING_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BURGONET.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.ARMET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.ARMET_2.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, ModItems.VISORED_BARBUTE.get(), new ItemStack(ModItems.BARBUTE.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.HOUNDSKULL.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.CAGE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.CAGE_2.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, ModItems.VISORED_BASCINET.get(), new ItemStack(ModItems.BASCINET.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.GREAT_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, false, 3, 1.0f, ModItems.GREAT_HELM_2.get(), false, new ItemStack(ModItems.GREAT_HELM.get()), new ItemStack(Items.GOLD_INGOT));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.SALLET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BURGONET_FALLING_BUFFE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.CLOSE_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.FROGMOUTH.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREAT_ARMET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREAT_ARMET_2.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREAT_BASCINET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREAT_HOUNDSKUL_BASCINET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.MAXIMILLIAN_HELMET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.GAUNTLET.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BRIGANDINE_HARNESS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.PLATE_HARNESS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BRIGANDINE_CUISSES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.PLATE_CUISSES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.GREAVES.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.SABATONS.get(), new ItemStack(SCItems.HOT_IRON.get()));

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.SWALLOWTAIL_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BODKIN_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BROADHEAD_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.CLOTH_ARROW.get(), ItemTags.WOOL, new ItemStack(Items.FEATHER));

        createCraftmanAnvilRecipe(exporter, 9, 0.25f, ModItems.HORSE_BARDING.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.IRON_HORSE_ARMOR));

        createBannerRecipe(exporter, ModItems.SURCOAT.get());
        createBannerRecipe(exporter, ModItems.SURCOAT_SLEEVELESS.get());
    }

    private void createCraftmanAnvilRecipe(Consumer<FinishedRecipe> exporter, boolean createManuscript, int hitTime, float chance, Item output, boolean outputHotIron, String path, Object... requiress) {
        ItemStack resultStack = new ItemStack(output);
        if (outputHotIron) resultStack = HotIron.createForStack(resultStack);

        CraftmanAnvilRecipeJsonBuilder builder = CraftmanAnvilRecipeJsonBuilder.create(resultStack)
                .hitTimes(hitTime)
                .chance(chance);

        if (createManuscript) {
            createManuscriptRecipe(exporter, output);
            builder.requires(Manuscript.createForStack(new ItemStack(output)));
        }

        for (Object requires : requiress) {
            if (requires instanceof ItemStack itemStack) {
                int count = itemStack.getCount();
                for (int i = 0; i < count; i++) {
                    ItemStack singleStack = itemStack.copy();
                    singleStack.setCount(1);
                    builder.requires(singleStack);
                }
            } else if (requires instanceof Item item) {
                builder.requires(new ItemStack(item));
            } else if (requires instanceof TagKey) {
                @SuppressWarnings("unchecked")
                TagKey<Item> itemTag = (TagKey<Item>) requires;
                builder.requires(itemTag);
            } else {
                System.out.println("Unhandled type: " + requires.getClass().getName());
            }
        }

        if (path.isEmpty()) path = "craftmananvil/" + BuiltInRegistries.ITEM.getKey(output).getPath();

        builder.save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, path));
    }

    private void createCraftmanAnvilRecipe(Consumer<FinishedRecipe> exporter, boolean createManuscript, int hitTime, float chance, Item output, boolean outputHotIron, Object... requiress) {
        createCraftmanAnvilRecipe(exporter, createManuscript, hitTime, chance, output, outputHotIron, "", requiress);
    }

    private void createCraftmanAnvilRecipe(Consumer<FinishedRecipe> exporter, int hitTime, float chance, Item output, Object... requiress) {
        createCraftmanAnvilRecipe(exporter, true, hitTime, chance, output, true, "", requiress);
    }

    private void createSmithingRecipe(Consumer<FinishedRecipe> exporter, Item output, int hitTime, float chance, Object... requiress) {
        createCraftmanAnvilRecipe(exporter, false, hitTime, chance, output, false, "", requiress);
    }

    private void createManuscriptRecipe(Consumer<FinishedRecipe> exporter, Item base) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.of(Items.PAPER));
        ingredients.add(Ingredient.of(SCItems.SMITHING_HAMMER.get()));
        ingredients.add(Ingredient.of(base));

        exporter.accept(new ManuscriptRecipeBuilder(
                new ResourceLocation(KnightsHeraldry.MOD_ID, "manuscript/" + getItemName(base)),
                SCItems.MANUSCRIPT.get(),
                ingredients
        ));
    }

    private void createBannerRecipe(Consumer<FinishedRecipe> exporter, Item targetItem) {
        NonNullList<Ingredient> ingredients = NonNullList.create();

        ingredients.add(Ingredient.of(net.minecraft.tags.ItemTags.BANNERS));

        ingredients.add(Ingredient.of(targetItem));

        exporter.accept(new BannerRecipeBuilder(
                new ResourceLocation(KnightsHeraldry.MOD_ID, "banner_pattern/" + getItemName(targetItem)),
                targetItem,
                ingredients,
                CraftingBookCategory.MISC
        ));
    }

    private void createDecoRecipe(Consumer<FinishedRecipe> exporter, Item helmet) {
        String helmetName = BuiltInRegistries.ITEM.getKey(helmet).getPath();

        for (HelmetDeco deco : HelmetDeco.getValues()) {
            NonNullList<Ingredient> ingredients = NonNullList.create();
            ingredients.add(Ingredient.of(helmet));
            ingredients.add(Ingredient.of(deco.item()));

            exporter.accept(new HelmetDecoRecipeBuilder(
                    new ResourceLocation(KnightsHeraldry.MOD_ID, "deco/" + helmetName + "_" + BuiltInRegistries.ITEM.getKey(deco.item()).getPath()),
                    ingredients
            ));
        }

        var group0 = HelmetDeco.getValues().stream().filter(d -> d.group() == 0).toList();
        var group1 = HelmetDeco.getValues().stream().filter(d -> d.group() == 1).toList();

        for (HelmetDeco deco0 : group0) {
            for (HelmetDeco deco1 : group1) {
                NonNullList<Ingredient> ingredients = NonNullList.create();
                ingredients.add(Ingredient.of(helmet));
                ingredients.add(Ingredient.of(deco0.item()));
                ingredients.add(Ingredient.of(deco1.item()));

                exporter.accept(new HelmetDecoRecipeBuilder(
                        new ResourceLocation(KnightsHeraldry.MOD_ID, "deco/" + helmetName + "_" +
                                BuiltInRegistries.ITEM.getKey(deco0.item()).getPath() + "_" +
                                BuiltInRegistries.ITEM.getKey(deco1.item()).getPath()),
                        ingredients
                ));
            }
        }
    }

    private void createWeaponCycle(Consumer<FinishedRecipe> exporter, Item... weapons) {
        for (int i = 0; i < weapons.length; i++) {
            Item current = weapons[i];
            Item next = weapons[(i + 1) % weapons.length];

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, next, 1)
                    .requires(SCItems.SMITHING_HAMMER.get())
                    .requires(current)
                    .unlockedBy(getHasName(SCItems.SMITHING_HAMMER.get()), has(SCItems.SMITHING_HAMMER.get()))
                    .unlockedBy(getHasName(current), has(current))
                    .save(exporter, new ResourceLocation(
                            KnightsHeraldry.MOD_ID,
                            "upgrade_" + BuiltInRegistries.ITEM.getKey(current).getPath() + "_to_" + BuiltInRegistries.ITEM.getKey(next).getPath()
                    ));
        }
    }

    private void createEasyRecipe(Consumer<FinishedRecipe> exporter, Item finalItem, Item principal, Item... attachments) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, finalItem, 1)
                .requires(principal)
                .unlockedBy(getHasName(principal), has(principal));

        for (Item attachment : attachments) {
            builder.requires(attachment);
            builder.unlockedBy(getHasName(attachment), has(attachment));
        }

        StringBuilder recipeId = new StringBuilder(BuiltInRegistries.ITEM.getKey(finalItem).getPath());
        recipeId.append("_from_").append(BuiltInRegistries.ITEM.getKey(principal).getPath());
        for (Item attachment : attachments) {
            recipeId.append("_and_").append(BuiltInRegistries.ITEM.getKey(attachment).getPath());
        }

        builder.save(exporter, new ResourceLocation(
                KnightsHeraldry.MOD_ID,
                recipeId.toString()
        ));
    }
}
