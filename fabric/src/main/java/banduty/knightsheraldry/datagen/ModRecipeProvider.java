package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.CraftmanAnvilRecipeJsonBuilder;
import banduty.stoneycore.datagen.ManuscriptRecipeBuilder;
import banduty.stoneycore.items.SCItems;
import banduty.stoneycore.items.custom.hotiron.HotIron;
import banduty.stoneycore.items.custom.manuscript.Manuscript;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        createWeaponCycle(exporter, KHItems.ARMING_SWORD.get(), KHItems.SWORD.get(), KHItems.V_SWORD.get());
        createWeaponCycle(exporter, KHItems.STRAIGHT_CROOKED_AXE.get(), KHItems.AXE.get(), KHItems.BROAD_AXE.get(), KHItems.CROOKED_AXE.get());
        createWeaponCycle(exporter, KHItems.MACE.get(), KHItems.SPIKED_MACE.get());
        createWeaponCycle(exporter, KHItems.FLAIL.get(), KHItems.BALL_FLAIL.get());
        createWeaponCycle(exporter, KHItems.HAMMER.get(), KHItems.WAR_HAMMER.get());
        createWeaponCycle(exporter, KHItems.LONGSWORD.get(), KHItems.V_LONGSWORD.get());
        createWeaponCycle(exporter, KHItems.FALCHION.get(), KHItems.SCIMITAR.get());
        createWeaponCycle(exporter, KHItems.POLEHAMMER.get(), KHItems.BEC_DE_CORBIN.get());
        createWeaponCycle(exporter, KHItems.GREATSWORD.get(), KHItems.CLAYMORE.get(),
                KHItems.FLAMBERGE.get(), KHItems.ZWEIHANDER.get());

        createEasyRecipe(exporter, KHItems.MAIL_SPAULDERS_BESAGEWS.get(), KHItems.MAIL_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_MAIL_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(), KHItems.BRIGANDINE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(), KHItems.DARK_BRIGANDINE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.PLATE_SPAULDERS_BESAGEWS.get(), KHItems.PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.DARK_PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.PLATE_SPAULDERS_RIMMED.get(), KHItems.PLATE_SPAULDERS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.DARK_PLATE_SPAULDERS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(), KHItems.GOLDEN_PLATE_SPAULDERS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.GOLDEN_PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.PLATE_SPAULDERS_BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.PLATE_SPAULDERS_RIMMED.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS_RIMMED.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(), KHItems.BESAGEWS.get());

    /*    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KHItems.SURCOAT, 1.get())
                .requires(KHItems.SURCOAT.get())
                .requires(ItemTags.BANNERS)
                .unlockedBy(getHasName(KHItems.SURCOAT), has(KHItems.SURCOAT))
                .unlockedBy(getHasName(Items.WHITE_BANNER), has(Items.WHITE_BANNER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "surcoat_with_banner"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KHItems.SURCOAT_SLEEVELESS, 1.get())
                .requires(KHItems.SURCOAT_SLEEVELESS.get())
                .requires(ItemTags.BANNERS)
                .unlockedBy(getHasName(KHItems.SURCOAT_SLEEVELESS), has(KHItems.SURCOAT_SLEEVELESS))
                .unlockedBy(getHasName(Items.WHITE_BANNER), has(Items.WHITE_BANNER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "sleeveless_surcoat_with_banner"));*/

     /*   ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KHItems.TORSE.get())
                .requires(KHItems.TORSE.get())
                .requires(ModTags.DYES.getTag())
                .unlockedBy(getHasName(KHItems.TORSE), has(KHItems.TORSE))
                .unlockedBy("has_" + ModTags.DYES.getTag(), has(ModTags.DYES.getTag()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, BuiltInRegistries.ITEM.getKey(KHItems.TORSE).getPath() + "_dye"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KHItems.TORSE.get())
                .requires(KHItems.TORSE.get())
                .requires(ModTags.DYES.getTag().get())
                .requires(ModTags.DYES.getTag())
                .unlockedBy(getHasName(KHItems.TORSE), has(KHItems.TORSE))
                .unlockedBy("has_" + ModTags.DYES.getTag(), has(ModTags.DYES.getTag()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, BuiltInRegistries.ITEM.getKey(KHItems.TORSE).getPath() + "_dye_double"));
*/
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KHItems.TRI_PLUME.get())
                .requires(KHItems.PLUME.get())
                .requires(KHItems.PLUME.get())
                .requires(KHItems.PLUME.get())
                .unlockedBy(getHasName(KHItems.PLUME.get()), has(KHItems.PLUME.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, getSimpleRecipeName(KHItems.TRI_PLUME.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KHItems.FLUFFY_PLUME.get())
                .requires(KHItems.PLUME.get())
                .requires(KHItems.PLUME.get())
                .requires(KHItems.PLUME.get())
                .requires(KHItems.PLUME.get())
                .requires(KHItems.PLUME.get())
                .unlockedBy(getHasName(KHItems.PLUME.get()), has(KHItems.PLUME.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, getSimpleRecipeName(KHItems.FLUFFY_PLUME.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, KHItems.FLUFFY_PLUME.get())
                .requires(KHItems.TRI_PLUME.get())
                .requires(KHItems.PLUME.get())
                .requires(KHItems.PLUME.get())
                .unlockedBy(getHasName(KHItems.TRI_PLUME.get()), has(KHItems.TRI_PLUME.get()))
                .unlockedBy(getHasName(KHItems.PLUME.get()), has(KHItems.PLUME.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, getSimpleRecipeName(KHItems.FLUFFY_PLUME.get()) + "_2"));

        // Golden
        createSmithingRecipe(exporter, KHItems.GOLDEN_MAIL_SPAULDERS.get(), 7, 0.7f,
                new ItemStack(KHItems.MAIL_SPAULDERS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), 7, 0.7f,
                new ItemStack(KHItems.BRIGANDINE_SPAULDERS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_PLATE_SPAULDERS.get(), 7, 0.7f,
                new ItemStack(KHItems.PLATE_SPAULDERS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BRIGANDINE.get(), 7, 0.7f,
                new ItemStack(KHItems.BRIGANDINE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BRIG_BREASTPLATE.get(), 7, 0.7f,
                new ItemStack(KHItems.BRIG_BREASTPLATE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BRIG_BREASTPLATE_TASSETS.get(), 7, 0.7f,
                new ItemStack(KHItems.BRIG_BREASTPLATE_TASSETS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_PLATE_CUIRASS.get(), 7, 0.7f,
                new ItemStack(KHItems.PLATE_CUIRASS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_PLATE_CUIRASS_TASSETS.get(), 7, 0.7f,
                new ItemStack(KHItems.PLATE_CUIRASS_TASSETS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_MAXIMILLIAN_CUIRASS.get(), 7, 0.7f,
                new ItemStack(KHItems.MAXIMILLIAN_CUIRASS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS.get(), 7, 0.7f,
                new ItemStack(KHItems.MAXIMILLIAN_CUIRASS_TASSETS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_XIIII_PLATE_CUIRASS.get(), 7, 0.7f,
                new ItemStack(KHItems.XIIII_PLATE_CUIRASS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS.get(), 7, 0.7f,
                new ItemStack(KHItems.XIIII_PLATE_CUIRASS_TASSETS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get(), 7, 0.7f,
                new ItemStack(KHItems.XIIII_PLATE_BREASTPLATE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BARBUTE.get(), 7, 0.7f,
                new ItemStack(KHItems.BARBUTE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BASCINET.get(), 7, 0.7f,
                new ItemStack(KHItems.BASCINET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_KETTLE_HELM.get(), 7, 0.7f,
                new ItemStack(KHItems.KETTLE_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_NASAL_HELM.get(), 7, 0.7f,
                new ItemStack(KHItems.NASAL_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_VIKING_HELM.get(), 7, 0.7f,
                new ItemStack(KHItems.VIKING_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BURGONET.get(), 7, 0.7f,
                new ItemStack(KHItems.BURGONET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_ARMET.get(), 7, 0.7f,
                new ItemStack(KHItems.ARMET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_ARMET_2.get(), 7, 0.7f,
                new ItemStack(KHItems.ARMET_2.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_VISORED_BARBUTE.get(), 7, 0.7f,
                new ItemStack(KHItems.VISORED_BARBUTE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_HOUNDSKULL.get(), 7, 0.7f,
                new ItemStack(KHItems.HOUNDSKULL.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_CAGE.get(), 7, 0.7f,
                new ItemStack(KHItems.CAGE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_VISORED_BASCINET.get(), 7, 0.7f,
                new ItemStack(KHItems.VISORED_BASCINET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_GREAT_HELM.get(), 7, 0.7f,
                new ItemStack(KHItems.GREAT_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_GREAT_HELM_2.get(), 7, 0.7f,
                new ItemStack(KHItems.GREAT_HELM_2.get()), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, KHItems.GOLDEN_FROGMOUTH.get(), 7, 0.7f,
                new ItemStack(KHItems.FROGMOUTH.get()), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, KHItems.GOLDEN_GREAT_ARMET.get(), 7, 0.7f,
                new ItemStack(KHItems.GREAT_ARMET.get()), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, KHItems.GOLDEN_GREAT_ARMET_2.get(), 7, 0.7f,
                new ItemStack(KHItems.GREAT_ARMET_2.get()), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, KHItems.GOLDEN_SALLET.get(), 7, 0.7f,
                new ItemStack(KHItems.SALLET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BURGONET_FALLING_BUFFE.get(), 7, 0.7f,
                new ItemStack(KHItems.BURGONET_FALLING_BUFFE.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_CLOSE_HELM.get(), 7, 0.7f,
                new ItemStack(KHItems.CLOSE_HELM.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_GREAT_BASCINET.get(), 7, 0.7f,
                new ItemStack(KHItems.GREAT_BASCINET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get(), 7, 0.7f,
                new ItemStack(KHItems.GREAT_HOUNDSKUL_BASCINET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_MAXIMILLIAN_HELMET.get(), 7, 0.7f,
                new ItemStack(KHItems.MAXIMILLIAN_HELMET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_SAVOYARD.get(), 7, 0.7f,
                new ItemStack(KHItems.SAVOYARD.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_GAUNTLET.get(), 7, 0.7f,
                new ItemStack(KHItems.GAUNTLET.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BRIGANDINE_HARNESS.get(), 7, 0.7f,
                new ItemStack(KHItems.BRIGANDINE_HARNESS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_PLATE_HARNESS.get(), 7, 0.7f,
                new ItemStack(KHItems.PLATE_HARNESS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BRIGANDINE_CUISSES.get(), 7, 0.7f,
                new ItemStack(KHItems.BRIGANDINE_CUISSES.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_PLATE_CUISSES.get(), 7, 0.7f,
                new ItemStack(KHItems.PLATE_CUISSES.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_GREAVES.get(), 7, 0.7f,
                new ItemStack(KHItems.GREAVES.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_SABATONS.get(), 7, 0.7f,
                new ItemStack(KHItems.SABATONS.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_BEVOR.get(), 7, 0.7f,
                new ItemStack(KHItems.BEVOR.get()), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, KHItems.GOLDEN_HORSE_BARDING.get(), 7, 0.7f,
                new ItemStack(KHItems.HORSE_BARDING.get()), new ItemStack(Items.GOLD_INGOT, 5));

        // Darkened
        createSmithingRecipe(exporter, KHItems.DARK_BRIGANDINE_SPAULDERS.get(), 7, 0.85f,
                new ItemStack(KHItems.BRIGANDINE_SPAULDERS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_PLATE_SPAULDERS.get(), 7, 0.85f,
                new ItemStack(KHItems.PLATE_SPAULDERS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BRIGANDINE.get(), 7, 0.85f,
                new ItemStack(KHItems.BRIGANDINE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BRIG_BREASTPLATE.get(), 7, 0.85f,
                new ItemStack(KHItems.BRIG_BREASTPLATE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BRIG_BREASTPLATE_TASSETS.get(), 7, 0.85f,
                new ItemStack(KHItems.BRIG_BREASTPLATE_TASSETS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_PLATE_CUIRASS.get(), 7, 0.85f,
                new ItemStack(KHItems.PLATE_CUIRASS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_PLATE_CUIRASS_TASSETS.get(), 7, 0.85f,
                new ItemStack(KHItems.PLATE_CUIRASS_TASSETS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_MAXIMILLIAN_CUIRASS.get(), 7, 0.85f,
                new ItemStack(KHItems.MAXIMILLIAN_CUIRASS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS.get(), 7, 0.85f,
                new ItemStack(KHItems.MAXIMILLIAN_CUIRASS_TASSETS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_XIIII_PLATE_CUIRASS.get(), 7, 0.85f,
                new ItemStack(KHItems.XIIII_PLATE_CUIRASS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_XIIII_PLATE_CUIRASS_TASSETS.get(), 7, 0.85f,
                new ItemStack(KHItems.XIIII_PLATE_CUIRASS_TASSETS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_XIIII_PLATE_BREASTPLATE.get(), 7, 0.85f,
                new ItemStack(KHItems.XIIII_PLATE_BREASTPLATE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BARBUTE.get(), 7, 0.85f,
                new ItemStack(KHItems.BARBUTE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BASCINET.get(), 7, 0.85f,
                new ItemStack(KHItems.BASCINET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_KETTLE_HELM.get(), 7, 0.85f,
                new ItemStack(KHItems.KETTLE_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_NASAL_HELM.get(), 7, 0.85f,
                new ItemStack(KHItems.NASAL_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_VIKING_HELM.get(), 7, 0.85f,
                new ItemStack(KHItems.VIKING_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BURGONET.get(), 7, 0.85f,
                new ItemStack(KHItems.BURGONET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_ARMET.get(), 7, 0.85f,
                new ItemStack(KHItems.ARMET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_ARMET_2.get(), 7, 0.85f,
                new ItemStack(KHItems.ARMET_2.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_VISORED_BARBUTE.get(), 7, 0.85f,
                new ItemStack(KHItems.VISORED_BARBUTE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_HOUNDSKULL.get(), 7, 0.85f,
                new ItemStack(KHItems.HOUNDSKULL.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_CAGE.get(), 7, 0.85f,
                new ItemStack(KHItems.CAGE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_VISORED_BASCINET.get(), 7, 0.85f,
                new ItemStack(KHItems.VISORED_BASCINET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_GREAT_HELM.get(), 7, 0.85f,
                new ItemStack(KHItems.GREAT_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_GREAT_HELM_2.get(), 7, 0.85f,
                new ItemStack(KHItems.GREAT_HELM_2.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_SALLET.get(), 7, 0.85f,
                new ItemStack(KHItems.SALLET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BURGONET_FALLING_BUFFE.get(), 7, 0.85f,
                new ItemStack(KHItems.BURGONET_FALLING_BUFFE.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_CLOSE_HELM.get(), 7, 0.85f,
                new ItemStack(KHItems.CLOSE_HELM.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_FROGMOUTH.get(), 7, 0.85f,
                new ItemStack(KHItems.FROGMOUTH.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_GREAT_ARMET.get(), 7, 0.85f,
                new ItemStack(KHItems.GREAT_ARMET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_GREAT_ARMET_2.get(), 7, 0.85f,
                new ItemStack(KHItems.GREAT_ARMET_2.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_GREAT_BASCINET.get(), 7, 0.85f,
                new ItemStack(KHItems.GREAT_BASCINET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(), 7, 0.85f,
                new ItemStack(KHItems.GREAT_HOUNDSKUL_BASCINET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_MAXIMILLIAN_HELMET.get(), 7, 0.85f,
                new ItemStack(KHItems.MAXIMILLIAN_HELMET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_SAVOYARD.get(), 7, 0.85f,
                new ItemStack(KHItems.SAVOYARD.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_GAUNTLET.get(), 7, 0.85f,
                new ItemStack(KHItems.GAUNTLET.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BRIGANDINE_HARNESS.get(), 7, 0.85f,
                new ItemStack(KHItems.BRIGANDINE_HARNESS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_PLATE_HARNESS.get(), 7, 0.85f,
                new ItemStack(KHItems.PLATE_HARNESS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BRIGANDINE_CUISSES.get(), 7, 0.85f,
                new ItemStack(KHItems.BRIGANDINE_CUISSES.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_PLATE_CUISSES.get(), 7, 0.85f,
                new ItemStack(KHItems.PLATE_CUISSES.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_GREAVES.get(), 7, 0.85f,
                new ItemStack(KHItems.GREAVES.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_SABATONS.get(), 7, 0.85f,
                new ItemStack(KHItems.SABATONS.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_BEVOR.get(), 7, 0.85f,
                new ItemStack(KHItems.BEVOR.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, KHItems.DARK_HORSE_BARDING.get(), 7, 0.85f,
                new ItemStack(KHItems.HORSE_BARDING.get()), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.DAGGER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.STILETTO.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.RAPIER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.SWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.V_SWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.ARMING_SWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.AXE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BROAD_AXE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.CROOKED_AXE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.STRAIGHT_CROOKED_AXE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.MACE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.SPIKED_MACE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.FLAIL.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BALL_FLAIL.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.HAMMER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.WAR_HAMMER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.LONGSWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.V_LONGSWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.FALCHION.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.SCIMITAR.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.PITCHFORK.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SPEAR.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.PIKE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BILLHOOK.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.GLAIVE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.CURVED_GLAIVE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.HALBERD.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.LANCE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.LOGS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.POLEAXE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.POLEHAMMER.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.BEC_DE_CORBIN.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.MORNING_STAR.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BARDICHE.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREATSWORD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.CLAYMORE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.FLAMBERGE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.ZWEIHANDER.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.WARDART.get(), new ItemStack(SCItems.HOT_IRON.get()), ItemTags.PLANKS);

        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.MAIL_COIF.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.HAUBERK.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.MAIL_BREECHES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.MAIL_BOOTS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.MAIL_SPAULDERS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BRIGANDINE_SPAULDERS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.PLATE_SPAULDERS.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BRIGANDINE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 1.0f, KHItems.BRIG_BREASTPLATE.get(), new ItemStack(KHItems.BRIGANDINE.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, KHItems.BRIG_BREASTPLATE_TASSETS.get(), new ItemStack(KHItems.BRIG_BREASTPLATE.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.PLATE_CUIRASS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, KHItems.PLATE_CUIRASS_TASSETS.get(), new ItemStack(KHItems.PLATE_CUIRASS.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.MAXIMILLIAN_CUIRASS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, KHItems.MAXIMILLIAN_CUIRASS_TASSETS.get(), new ItemStack(KHItems.MAXIMILLIAN_CUIRASS.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.XIIII_PLATE_CUIRASS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, KHItems.XIIII_PLATE_CUIRASS_TASSETS.get(), new ItemStack(KHItems.XIIII_PLATE_CUIRASS.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.XIIII_PLATE_BREASTPLATE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.RIM_GUARDS.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BESAGEWS.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BARBUTE.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BASCINET.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.KETTLE_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.NASAL_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.VIKING_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BURGONET.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.ARMET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.ARMET_2.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, KHItems.VISORED_BARBUTE.get(), new ItemStack(KHItems.BARBUTE.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.HOUNDSKULL.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.CAGE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, KHItems.VISORED_BASCINET.get(), new ItemStack(KHItems.BASCINET.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.GREAT_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, false, 3, 1.0f, KHItems.GREAT_HELM_2.get(), false, new ItemStack(KHItems.GREAT_HELM.get()), new ItemStack(Items.GOLD_INGOT));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.SALLET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BURGONET_FALLING_BUFFE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.CLOSE_HELM.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.FROGMOUTH.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREAT_ARMET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREAT_ARMET_2.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREAT_BASCINET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREAT_HOUNDSKUL_BASCINET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.MAXIMILLIAN_HELMET.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.GAUNTLET.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BRIGANDINE_HARNESS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.PLATE_HARNESS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BRIGANDINE_CUISSES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.PLATE_CUISSES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.GREAVES.get(), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SABATONS.get(), new ItemStack(SCItems.HOT_IRON.get()));

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SWALLOWTAIL_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BODKIN_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BROADHEAD_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.CLOTH_ARROW.get(), ItemTags.WOOL, new ItemStack(Items.FEATHER));

        createCraftmanAnvilRecipe(exporter, 9, 0.25f, KHItems.HORSE_BARDING.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.IRON_HORSE_ARMOR));

        createBannerRecipe(exporter, KHItems.SURCOAT.get());
        createBannerRecipe(exporter, KHItems.SURCOAT_SLEEVELESS.get());
    }

    private void createCraftmanAnvilRecipe(RecipeOutput exporter, boolean createManuscript, int hitTime, float chance, Item output, boolean outputHotIron, String path, Object... requiress) {
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
            switch (requires) {
                case ItemStack itemStack -> {
                    int count = itemStack.getCount();
                    for (int i = 0; i < count; i++) {
                        ItemStack singleStack = itemStack.copy();
                        singleStack.setCount(1);
                        builder.requires(singleStack);
                    }
                }
                case Item item -> builder.requires(new ItemStack(item));
                case TagKey tagKey -> {
                    @SuppressWarnings("unchecked")
                    TagKey<Item> itemTag = (TagKey<Item>) requires;
                    builder.requires(itemTag, 1);
                }
                default -> System.out.println("Unhandled type: " + requires.getClass().getName());
            }
        }

        if (path.isEmpty()) path = "craftmananvil/" + BuiltInRegistries.ITEM.getKey(output).getPath();

        builder.save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, path));
    }

    private void createCraftmanAnvilRecipe(RecipeOutput exporter, boolean createManuscript, int hitTime, float chance, Item output, boolean outputHotIron, Object... requiress) {
        createCraftmanAnvilRecipe(exporter, createManuscript, hitTime, chance, output, outputHotIron, "", requiress);
    }

    private void createCraftmanAnvilRecipe(RecipeOutput exporter, int hitTime, float chance, Item output, Object... requiress) {
        createCraftmanAnvilRecipe(exporter, true, hitTime, chance, output, true, "", requiress);
    }

    private void createSmithingRecipe(RecipeOutput exporter, Item output, int hitTime, float chance, Object... requiress) {
        createCraftmanAnvilRecipe(exporter, false, hitTime, chance, output, false, "", requiress);
    }

    private void createManuscriptRecipe(RecipeOutput exporter, Item base) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.of(Items.PAPER));
        ingredients.add(Ingredient.of(SCItems.SMITHING_HAMMER.get()));
        ingredients.add(Ingredient.of(base));

        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(
                KnightsHeraldry.MOD_ID,
                "manuscript/" + getItemName(base)
        );

        new ManuscriptRecipeBuilder(SCItems.MANUSCRIPT.get(), ingredients)
                .unlockedBy(getHasName(base), has(base))
                .save(exporter, recipeId);
    }

    private void createBannerRecipe(RecipeOutput exporter, Item targetItem) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, targetItem)
                .requires(ItemTags.BANNERS)
                .requires(targetItem)
                .unlockedBy("has_item", RecipeProvider.has(targetItem))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "banner_pattern/" + getItemName(targetItem)));
    }

    private void createWeaponCycle(RecipeOutput exporter, Item... weapons) {
        for (int i = 0; i < weapons.length; i++) {
            Item current = weapons[i];
            Item next = weapons[(i + 1) % weapons.length];

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, next, 1)
                    .requires(SCItems.SMITHING_HAMMER.get())
                    .requires(current)
                    .unlockedBy(getHasName(SCItems.SMITHING_HAMMER.get()), has(SCItems.SMITHING_HAMMER.get()))
                    .unlockedBy(getHasName(current), has(current))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(
                            KnightsHeraldry.MOD_ID,
                            "upgrade_" + BuiltInRegistries.ITEM.getKey(current).getPath() + "_to_" + BuiltInRegistries.ITEM.getKey(next).getPath()
                    ));
        }
    }

    private void createEasyRecipe(RecipeOutput exporter, Item finalItem, Item principal, Item... attachments) {
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

        builder.save(exporter, ResourceLocation.fromNamespaceAndPath(
                KnightsHeraldry.MOD_ID,
                recipeId.toString()
        ));
    }
}
