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
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
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

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        createWeaponCycle(exporter, ModItems.ARMING_SWORD, ModItems.SWORD, ModItems.V_SWORD);
        createWeaponCycle(exporter, ModItems.STRAIGHT_CROOKED_AXE, ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE);
        createWeaponCycle(exporter, ModItems.MACE, ModItems.SPIKED_MACE);
        createWeaponCycle(exporter, ModItems.FLAIL, ModItems.BALL_FLAIL);
        createWeaponCycle(exporter, ModItems.HAMMER, ModItems.WAR_HAMMER);
        createWeaponCycle(exporter, ModItems.LONGSWORD, ModItems.V_LONGSWORD);
        createWeaponCycle(exporter, ModItems.FALCHION, ModItems.SCIMITAR);
        createWeaponCycle(exporter, ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN);
        createWeaponCycle(exporter, ModItems.GREATSWORD, ModItems.CLAYMORE,
                ModItems.FLAMBERGE, ModItems.ZWEIHANDER);

        createDecoRecipe(exporter, ModItems.BARBUTE);
        createDecoRecipe(exporter, ModItems.BASCINET);
        createDecoRecipe(exporter, ModItems.KETTLE_HELM);
        createDecoRecipe(exporter, ModItems.NASAL_HELM);
        createDecoRecipe(exporter, ModItems.VIKING_HELM);
        createDecoRecipe(exporter, ModItems.BURGONET);
        createDecoRecipe(exporter, ModItems.ARMET);
        createDecoRecipe(exporter, ModItems.ARMET_2);
        createDecoRecipe(exporter, ModItems.VISORED_BARBUTE);
        createDecoRecipe(exporter, ModItems.HOUNDSKULL);
        createDecoRecipe(exporter, ModItems.CAGE);
        createDecoRecipe(exporter, ModItems.VISORED_BASCINET);
        createDecoRecipe(exporter, ModItems.GREAT_HELM);
        createDecoRecipe(exporter, ModItems.GREAT_HELM_2);
        createDecoRecipe(exporter, ModItems.SALLET);
        createDecoRecipe(exporter, ModItems.BURGONET_FALLING_BUFFE);
        createDecoRecipe(exporter, ModItems.CLOSE_HELM);
        createDecoRecipe(exporter, ModItems.FROGMOUTH);
        createDecoRecipe(exporter, ModItems.GREAT_ARMET);
        createDecoRecipe(exporter, ModItems.GREAT_ARMET_2);
        createDecoRecipe(exporter, ModItems.GREAT_BASCINET);
        createDecoRecipe(exporter, ModItems.GREAT_HOUNDSKUL_BASCINET);
        createDecoRecipe(exporter, ModItems.MAXIMILLIAN_HELMET);
        createDecoRecipe(exporter, ModItems.SAVOYARD);

        createDecoRecipe(exporter, ModItems.DARK_BARBUTE);
        createDecoRecipe(exporter, ModItems.DARK_BASCINET);
        createDecoRecipe(exporter, ModItems.DARK_KETTLE_HELM);
        createDecoRecipe(exporter, ModItems.DARK_NASAL_HELM);
        createDecoRecipe(exporter, ModItems.DARK_VIKING_HELM);
        createDecoRecipe(exporter, ModItems.DARK_BURGONET);
        createDecoRecipe(exporter, ModItems.DARK_ARMET);
        createDecoRecipe(exporter, ModItems.DARK_ARMET_2);
        createDecoRecipe(exporter, ModItems.DARK_VISORED_BARBUTE);
        createDecoRecipe(exporter, ModItems.DARK_HOUNDSKULL);
        createDecoRecipe(exporter, ModItems.DARK_CAGE);
        createDecoRecipe(exporter, ModItems.DARK_VISORED_BASCINET);
        createDecoRecipe(exporter, ModItems.DARK_GREAT_HELM);
        createDecoRecipe(exporter, ModItems.DARK_GREAT_HELM_2);
        createDecoRecipe(exporter, ModItems.DARK_SALLET);
        createDecoRecipe(exporter, ModItems.DARK_CLOSE_HELM);
        createDecoRecipe(exporter, ModItems.DARK_BURGONET_FALLING_BUFFE);
        createDecoRecipe(exporter, ModItems.DARK_FROGMOUTH);
        createDecoRecipe(exporter, ModItems.DARK_GREAT_ARMET);
        createDecoRecipe(exporter, ModItems.DARK_GREAT_ARMET_2);
        createDecoRecipe(exporter, ModItems.DARK_GREAT_BASCINET);
        createDecoRecipe(exporter, ModItems.DARK_GREAT_HOUNDSKUL_BASCINET);
        createDecoRecipe(exporter, ModItems.DARK_MAXIMILLIAN_HELMET);
        createDecoRecipe(exporter, ModItems.DARK_SAVOYARD);

        createDecoRecipe(exporter, ModItems.GOLDEN_BARBUTE);
        createDecoRecipe(exporter, ModItems.GOLDEN_BASCINET);
        createDecoRecipe(exporter, ModItems.GOLDEN_KETTLE_HELM);
        createDecoRecipe(exporter, ModItems.GOLDEN_NASAL_HELM);
        createDecoRecipe(exporter, ModItems.GOLDEN_VIKING_HELM);
        createDecoRecipe(exporter, ModItems.GOLDEN_BURGONET);
        createDecoRecipe(exporter, ModItems.GOLDEN_ARMET);
        createDecoRecipe(exporter, ModItems.GOLDEN_ARMET_2);
        createDecoRecipe(exporter, ModItems.GOLDEN_VISORED_BARBUTE);
        createDecoRecipe(exporter, ModItems.GOLDEN_HOUNDSKULL);
        createDecoRecipe(exporter, ModItems.GOLDEN_CAGE);
        createDecoRecipe(exporter, ModItems.GOLDEN_VISORED_BASCINET);
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_HELM);
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_HELM_2);
        createDecoRecipe(exporter, ModItems.GOLDEN_SALLET);
        createDecoRecipe(exporter, ModItems.GOLDEN_CLOSE_HELM);
        createDecoRecipe(exporter, ModItems.GOLDEN_BURGONET_FALLING_BUFFE);
        createDecoRecipe(exporter, ModItems.GOLDEN_FROGMOUTH);
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_ARMET);
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_ARMET_2);
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_BASCINET);
        createDecoRecipe(exporter, ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET);
        createDecoRecipe(exporter, ModItems.GOLDEN_MAXIMILLIAN_HELMET);
        createDecoRecipe(exporter, ModItems.GOLDEN_SAVOYARD);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HORSE_BARDING, 1)
                .requires(ModItems.HORSE_BARDING)
                .requires(ModItems.PLUME)
                .unlockedBy(getHasName(ModItems.HORSE_BARDING), has(ModItems.HORSE_BARDING))
                .unlockedBy(getHasName(ModItems.PLUME), has(ModItems.PLUME))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID,
                        "deco/" + BuiltInRegistries.ITEM.getKey(ModItems.PLUME).getPath() + "_" + BuiltInRegistries.ITEM.getKey(ModItems.HORSE_BARDING).getPath()
                ));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_HORSE_BARDING, 1)
                .requires(ModItems.DARK_HORSE_BARDING)
                .requires(ModItems.PLUME)
                .unlockedBy(getHasName(ModItems.DARK_HORSE_BARDING), has(ModItems.DARK_HORSE_BARDING))
                .unlockedBy(getHasName(ModItems.PLUME), has(ModItems.PLUME))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID,
                        "deco/" + BuiltInRegistries.ITEM.getKey(ModItems.PLUME).getPath() + "_" + BuiltInRegistries.ITEM.getKey(ModItems.DARK_HORSE_BARDING).getPath()
                ));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GOLDEN_HORSE_BARDING, 1)
                .requires(ModItems.GOLDEN_HORSE_BARDING)
                .requires(ModItems.PLUME)
                .unlockedBy(getHasName(ModItems.GOLDEN_HORSE_BARDING), has(ModItems.GOLDEN_HORSE_BARDING))
                .unlockedBy(getHasName(ModItems.PLUME), has(ModItems.PLUME))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID,
                        "deco/" + BuiltInRegistries.ITEM.getKey(ModItems.PLUME).getPath() + "_" + BuiltInRegistries.ITEM.getKey(ModItems.GOLDEN_HORSE_BARDING).getPath()
                ));

        createEasyRecipe(exporter, ModItems.MAIL_SPAULDERS_BESAGEWS, ModItems.MAIL_SPAULDERS, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS, ModItems.GOLDEN_MAIL_SPAULDERS, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.BRIGANDINE_SPAULDERS_BESAGEWS, ModItems.BRIGANDINE_SPAULDERS, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS, ModItems.DARK_BRIGANDINE_SPAULDERS, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS, ModItems.GOLDEN_BRIGANDINE_SPAULDERS, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_BESAGEWS, ModItems.PLATE_SPAULDERS, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS, ModItems.DARK_PLATE_SPAULDERS, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS, ModItems.GOLDEN_PLATE_SPAULDERS, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_RIMMED, ModItems.PLATE_SPAULDERS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_RIMMED, ModItems.DARK_PLATE_SPAULDERS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED, ModItems.GOLDEN_PLATE_SPAULDERS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.PLATE_SPAULDERS, ModItems.BESAGEWS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.DARK_PLATE_SPAULDERS, ModItems.BESAGEWS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.GOLDEN_PLATE_SPAULDERS, ModItems.BESAGEWS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.PLATE_SPAULDERS_BESAGEWS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS, ModItems.RIM_GUARDS);
        createEasyRecipe(exporter, ModItems.PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.PLATE_SPAULDERS_RIMMED, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.DARK_PLATE_SPAULDERS_RIMMED, ModItems.BESAGEWS);
        createEasyRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED, ModItems.GOLDEN_PLATE_SPAULDERS_RIMMED, ModItems.BESAGEWS);

    /*    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SURCOAT, 1)
                .requires(ModItems.SURCOAT)
                .requires(ItemTags.BANNERS)
                .unlockedBy(getHasName(ModItems.SURCOAT), has(ModItems.SURCOAT))
                .unlockedBy(getHasName(Items.WHITE_BANNER), has(Items.WHITE_BANNER))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, "surcoat_with_banner"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SURCOAT_SLEEVELESS, 1)
                .requires(ModItems.SURCOAT_SLEEVELESS)
                .requires(ItemTags.BANNERS)
                .unlockedBy(getHasName(ModItems.SURCOAT_SLEEVELESS), has(ModItems.SURCOAT_SLEEVELESS))
                .unlockedBy(getHasName(Items.WHITE_BANNER), has(Items.WHITE_BANNER))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, "sleeveless_surcoat_with_banner"));*/

     /*   ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TORSE)
                .requires(ModItems.TORSE)
                .requires(ModTags.DYES.getTag())
                .unlockedBy(getHasName(ModItems.TORSE), has(ModItems.TORSE))
                .unlockedBy("has_" + ModTags.DYES.getTag(), has(ModTags.DYES.getTag()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, BuiltInRegistries.ITEM.getKey(ModItems.TORSE).getPath() + "_dye"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TORSE)
                .requires(ModItems.TORSE)
                .requires(ModTags.DYES.getTag())
                .requires(ModTags.DYES.getTag())
                .unlockedBy(getHasName(ModItems.TORSE), has(ModItems.TORSE))
                .unlockedBy("has_" + ModTags.DYES.getTag(), has(ModTags.DYES.getTag()))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, BuiltInRegistries.ITEM.getKey(ModItems.TORSE).getPath() + "_dye_double"));
*/
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TRI_PLUME)
                .requires(ModItems.PLUME)
                .requires(ModItems.PLUME)
                .requires(ModItems.PLUME)
                .unlockedBy(getHasName(ModItems.PLUME), has(ModItems.PLUME))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, getSimpleRecipeName(ModItems.TRI_PLUME)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLUFFY_PLUME)
                .requires(ModItems.PLUME)
                .requires(ModItems.PLUME)
                .requires(ModItems.PLUME)
                .requires(ModItems.PLUME)
                .requires(ModItems.PLUME)
                .unlockedBy(getHasName(ModItems.PLUME), has(ModItems.PLUME))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, getSimpleRecipeName(ModItems.FLUFFY_PLUME)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLUFFY_PLUME)
                .requires(ModItems.TRI_PLUME)
                .requires(ModItems.PLUME)
                .requires(ModItems.PLUME)
                .unlockedBy(getHasName(ModItems.TRI_PLUME), has(ModItems.TRI_PLUME))
                .unlockedBy(getHasName(ModItems.PLUME), has(ModItems.PLUME))
                .save(exporter, new ResourceLocation(KnightsHeraldry.MOD_ID, getSimpleRecipeName(ModItems.FLUFFY_PLUME) + "_2"));

        // Golden
        createSmithingRecipe(exporter, ModItems.GOLDEN_MAIL_SPAULDERS, 7, 0.7f,
                new ItemStack(ModItems.MAIL_SPAULDERS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIGANDINE_SPAULDERS, 7, 0.7f,
                new ItemStack(ModItems.BRIGANDINE_SPAULDERS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_SPAULDERS, 7, 0.7f,
                new ItemStack(ModItems.PLATE_SPAULDERS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIGANDINE, 7, 0.7f,
                new ItemStack(ModItems.BRIGANDINE), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIG_BREASTPLATE, 7, 0.7f,
                new ItemStack(ModItems.BRIG_BREASTPLATE), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIG_BREASTPLATE_TASSETS, 7, 0.7f,
                new ItemStack(ModItems.BRIG_BREASTPLATE_TASSETS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_CUIRASS, 7, 0.7f,
                new ItemStack(ModItems.PLATE_CUIRASS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_CUIRASS_TASSETS, 7, 0.7f,
                new ItemStack(ModItems.PLATE_CUIRASS_TASSETS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_MAXIMILLIAN_CUIRASS, 7, 0.7f,
                new ItemStack(ModItems.MAXIMILLIAN_CUIRASS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS, 7, 0.7f,
                new ItemStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_XIIII_PLATE_CUIRASS, 7, 0.7f,
                new ItemStack(ModItems.XIIII_PLATE_CUIRASS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_XIIII_PLATE_CUIRASS_TASSETS, 7, 0.7f,
                new ItemStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_XIIII_PLATE_BREASTPLATE, 7, 0.7f,
                new ItemStack(ModItems.XIIII_PLATE_BREASTPLATE), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BARBUTE, 7, 0.7f,
                new ItemStack(ModItems.BARBUTE), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BASCINET, 7, 0.7f,
                new ItemStack(ModItems.BASCINET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_KETTLE_HELM, 7, 0.7f,
                new ItemStack(ModItems.KETTLE_HELM), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_NASAL_HELM, 7, 0.7f,
                new ItemStack(ModItems.NASAL_HELM), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_VIKING_HELM, 7, 0.7f,
                new ItemStack(ModItems.VIKING_HELM), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BURGONET, 7, 0.7f,
                new ItemStack(ModItems.BURGONET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_ARMET, 7, 0.7f,
                new ItemStack(ModItems.ARMET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_ARMET_2, 7, 0.7f,
                new ItemStack(ModItems.ARMET_2), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_VISORED_BARBUTE, 7, 0.7f,
                new ItemStack(ModItems.VISORED_BARBUTE), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_HOUNDSKULL, 7, 0.7f,
                new ItemStack(ModItems.HOUNDSKULL), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_CAGE, 7, 0.7f,
                new ItemStack(ModItems.CAGE), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_VISORED_BASCINET, 7, 0.7f,
                new ItemStack(ModItems.VISORED_BASCINET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_HELM, 7, 0.7f,
                new ItemStack(ModItems.GREAT_HELM), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_HELM_2, 7, 0.7f,
                new ItemStack(ModItems.GREAT_HELM_2), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, ModItems.GOLDEN_FROGMOUTH, 7, 0.7f,
                new ItemStack(ModItems.FROGMOUTH), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_ARMET, 7, 0.7f,
                new ItemStack(ModItems.GREAT_ARMET), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_ARMET_2, 7, 0.7f,
                new ItemStack(ModItems.GREAT_ARMET_2), new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2));
        createSmithingRecipe(exporter, ModItems.GOLDEN_SALLET, 7, 0.7f,
                new ItemStack(ModItems.SALLET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BURGONET_FALLING_BUFFE, 7, 0.7f,
                new ItemStack(ModItems.BURGONET_FALLING_BUFFE), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_CLOSE_HELM, 7, 0.7f,
                new ItemStack(ModItems.CLOSE_HELM), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_BASCINET, 7, 0.7f,
                new ItemStack(ModItems.GREAT_BASCINET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET, 7, 0.7f,
                new ItemStack(ModItems.GREAT_HOUNDSKUL_BASCINET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_MAXIMILLIAN_HELMET, 7, 0.7f,
                new ItemStack(ModItems.MAXIMILLIAN_HELMET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_SAVOYARD, 7, 0.7f,
                new ItemStack(ModItems.SAVOYARD), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GAUNTLET, 7, 0.7f,
                new ItemStack(ModItems.GAUNTLET), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIGANDINE_HARNESS, 7, 0.7f,
                new ItemStack(ModItems.BRIGANDINE_HARNESS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_HARNESS, 7, 0.7f,
                new ItemStack(ModItems.PLATE_HARNESS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BRIGANDINE_CUISSES, 7, 0.7f,
                new ItemStack(ModItems.BRIGANDINE_CUISSES), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_PLATE_CUISSES, 7, 0.7f,
                new ItemStack(ModItems.PLATE_CUISSES), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_GREAVES, 7, 0.7f,
                new ItemStack(ModItems.GREAVES), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_SABATONS, 7, 0.7f,
                new ItemStack(ModItems.SABATONS), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_BEVOR, 7, 0.7f,
                new ItemStack(ModItems.BEVOR), new ItemStack(Items.GOLD_INGOT, 5));
        createSmithingRecipe(exporter, ModItems.GOLDEN_HORSE_BARDING, 7, 0.7f,
                new ItemStack(ModItems.HORSE_BARDING), new ItemStack(Items.GOLD_INGOT, 5));

        // Darkened
        createSmithingRecipe(exporter, ModItems.DARK_BRIGANDINE_SPAULDERS, 7, 0.85f,
                new ItemStack(ModItems.BRIGANDINE_SPAULDERS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_SPAULDERS, 7, 0.85f,
                new ItemStack(ModItems.PLATE_SPAULDERS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIGANDINE, 7, 0.85f,
                new ItemStack(ModItems.BRIGANDINE), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIG_BREASTPLATE, 7, 0.85f,
                new ItemStack(ModItems.BRIG_BREASTPLATE), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIG_BREASTPLATE_TASSETS, 7, 0.85f,
                new ItemStack(ModItems.BRIG_BREASTPLATE_TASSETS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_CUIRASS, 7, 0.85f,
                new ItemStack(ModItems.PLATE_CUIRASS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_CUIRASS_TASSETS, 7, 0.85f,
                new ItemStack(ModItems.PLATE_CUIRASS_TASSETS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_MAXIMILLIAN_CUIRASS, 7, 0.85f,
                new ItemStack(ModItems.MAXIMILLIAN_CUIRASS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_MAXIMILLIAN_CUIRASS_TASSETS, 7, 0.85f,
                new ItemStack(ModItems.MAXIMILLIAN_CUIRASS_TASSETS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_XIIII_PLATE_CUIRASS, 7, 0.85f,
                new ItemStack(ModItems.XIIII_PLATE_CUIRASS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_XIIII_PLATE_CUIRASS_TASSETS, 7, 0.85f,
                new ItemStack(ModItems.XIIII_PLATE_CUIRASS_TASSETS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_XIIII_PLATE_BREASTPLATE, 7, 0.85f,
                new ItemStack(ModItems.XIIII_PLATE_BREASTPLATE), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BARBUTE, 7, 0.85f,
                new ItemStack(ModItems.BARBUTE), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BASCINET, 7, 0.85f,
                new ItemStack(ModItems.BASCINET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_KETTLE_HELM, 7, 0.85f,
                new ItemStack(ModItems.KETTLE_HELM), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_NASAL_HELM, 7, 0.85f,
                new ItemStack(ModItems.NASAL_HELM), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_VIKING_HELM, 7, 0.85f,
                new ItemStack(ModItems.VIKING_HELM), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BURGONET, 7, 0.85f,
                new ItemStack(ModItems.BURGONET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_ARMET, 7, 0.85f,
                new ItemStack(ModItems.ARMET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_ARMET_2, 7, 0.85f,
                new ItemStack(ModItems.ARMET_2), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_VISORED_BARBUTE, 7, 0.85f,
                new ItemStack(ModItems.VISORED_BARBUTE), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_HOUNDSKULL, 7, 0.85f,
                new ItemStack(ModItems.HOUNDSKULL), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_CAGE, 7, 0.85f,
                new ItemStack(ModItems.CAGE), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_VISORED_BASCINET, 7, 0.85f,
                new ItemStack(ModItems.VISORED_BASCINET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_HELM, 7, 0.85f,
                new ItemStack(ModItems.GREAT_HELM), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_HELM_2, 7, 0.85f,
                new ItemStack(ModItems.GREAT_HELM_2), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_SALLET, 7, 0.85f,
                new ItemStack(ModItems.SALLET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BURGONET_FALLING_BUFFE, 7, 0.85f,
                new ItemStack(ModItems.BURGONET_FALLING_BUFFE), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_CLOSE_HELM, 7, 0.85f,
                new ItemStack(ModItems.CLOSE_HELM), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_FROGMOUTH, 7, 0.85f,
                new ItemStack(ModItems.FROGMOUTH), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_ARMET, 7, 0.85f,
                new ItemStack(ModItems.GREAT_ARMET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_ARMET_2, 7, 0.85f,
                new ItemStack(ModItems.GREAT_ARMET_2), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_BASCINET, 7, 0.85f,
                new ItemStack(ModItems.GREAT_BASCINET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAT_HOUNDSKUL_BASCINET, 7, 0.85f,
                new ItemStack(ModItems.GREAT_HOUNDSKUL_BASCINET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_MAXIMILLIAN_HELMET, 7, 0.85f,
                new ItemStack(ModItems.MAXIMILLIAN_HELMET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_SAVOYARD, 7, 0.85f,
                new ItemStack(ModItems.SAVOYARD), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GAUNTLET, 7, 0.85f,
                new ItemStack(ModItems.GAUNTLET), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIGANDINE_HARNESS, 7, 0.85f,
                new ItemStack(ModItems.BRIGANDINE_HARNESS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_HARNESS, 7, 0.85f,
                new ItemStack(ModItems.PLATE_HARNESS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BRIGANDINE_CUISSES, 7, 0.85f,
                new ItemStack(ModItems.BRIGANDINE_CUISSES), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_PLATE_CUISSES, 7, 0.85f,
                new ItemStack(ModItems.PLATE_CUISSES), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_GREAVES, 7, 0.85f,
                new ItemStack(ModItems.GREAVES), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_SABATONS, 7, 0.85f,
                new ItemStack(ModItems.SABATONS), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_BEVOR, 7, 0.85f,
                new ItemStack(ModItems.BEVOR), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));
        createSmithingRecipe(exporter, ModItems.DARK_HORSE_BARDING, 7, 0.85f,
                new ItemStack(ModItems.HORSE_BARDING), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.BONE_MEAL, 2), new ItemStack(Items.BASALT, 2));

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.DAGGER, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.STILETTO, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.RAPIER, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.SWORD, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.V_SWORD, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.ARMING_SWORD, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.AXE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BROAD_AXE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.CROOKED_AXE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.STRAIGHT_CROOKED_AXE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.MACE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.SPIKED_MACE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.FLAIL, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BALL_FLAIL, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.HAMMER, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.WAR_HAMMER, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.LONGSWORD, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.V_LONGSWORD, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.FALCHION, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.SCIMITAR, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.PITCHFORK, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.SPEAR, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.PIKE, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BILLHOOK, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.GLAIVE, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.CURVED_GLAIVE, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.HALBERD, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.LANCE, new ItemStack(SCItems.HOT_IRON), ItemTags.LOGS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.POLEAXE, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.POLEHAMMER, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.BEC_DE_CORBIN, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.MORNING_STAR, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BARDICHE, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREATSWORD, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.CLAYMORE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.FLAMBERGE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.ZWEIHANDER, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.WARDART, new ItemStack(SCItems.HOT_IRON), ItemTags.PLANKS);

        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.MAIL_COIF, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.HAUBERK, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.MAIL_BREECHES, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.MAIL_BOOTS, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.MAIL_SPAULDERS, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BRIGANDINE_SPAULDERS, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.PLATE_SPAULDERS, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BRIGANDINE, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 1.0f, ModItems.BRIG_BREASTPLATE, new ItemStack(ModItems.BRIGANDINE), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, ModItems.BRIG_BREASTPLATE_TASSETS, new ItemStack(ModItems.BRIG_BREASTPLATE), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.PLATE_CUIRASS, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, ModItems.PLATE_CUIRASS_TASSETS, new ItemStack(ModItems.PLATE_CUIRASS), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.MAXIMILLIAN_CUIRASS, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, ModItems.MAXIMILLIAN_CUIRASS_TASSETS, new ItemStack(ModItems.MAXIMILLIAN_CUIRASS), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.XIIII_PLATE_CUIRASS, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, ModItems.XIIII_PLATE_CUIRASS_TASSETS, new ItemStack(ModItems.XIIII_PLATE_CUIRASS), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.XIIII_PLATE_BREASTPLATE, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.RIM_GUARDS, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BESAGEWS, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BARBUTE, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BASCINET, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.KETTLE_HELM, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.NASAL_HELM, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.VIKING_HELM, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BURGONET, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.ARMET, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.ARMET_2, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, ModItems.VISORED_BARBUTE, new ItemStack(ModItems.BARBUTE), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.HOUNDSKULL, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.CAGE, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, ModItems.VISORED_BASCINET, new ItemStack(ModItems.BASCINET), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.GREAT_HELM, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, false, 3, 1.0f, ModItems.GREAT_HELM_2, false, new ItemStack(ModItems.GREAT_HELM), new ItemStack(Items.GOLD_INGOT));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.SALLET, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BURGONET_FALLING_BUFFE, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.CLOSE_HELM, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.FROGMOUTH, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREAT_ARMET, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREAT_ARMET_2, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREAT_BASCINET, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.GREAT_HOUNDSKUL_BASCINET, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.MAXIMILLIAN_HELMET, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.GAUNTLET, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BRIGANDINE_HARNESS, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.PLATE_HARNESS, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BRIGANDINE_CUISSES, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.PLATE_CUISSES, new ItemStack(SCItems.HOT_IRON), new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.GREAVES, new ItemStack(SCItems.HOT_IRON));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.SABATONS, new ItemStack(SCItems.HOT_IRON));

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.SWALLOWTAIL_ARROW, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, ModItems.BODKIN_ARROW, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, ModItems.BROADHEAD_ARROW, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.FEATHER));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, ModItems.CLOTH_ARROW, ItemTags.WOOL, new ItemStack(Items.FEATHER));

        createCraftmanAnvilRecipe(exporter, 9, 0.25f, ModItems.HORSE_BARDING, new ItemStack(SCItems.HOT_IRON), new ItemStack(Items.IRON_HORSE_ARMOR));

        createBannerRecipe(exporter, ModItems.SURCOAT);
        createBannerRecipe(exporter, ModItems.SURCOAT_SLEEVELESS);
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
        ingredients.add(Ingredient.of(SCItems.SMITHING_HAMMER));
        ingredients.add(Ingredient.of(base));

        exporter.accept(new ManuscriptRecipeBuilder(
                new ResourceLocation(KnightsHeraldry.MOD_ID, "manuscript/" + getItemName(base)),
                SCItems.MANUSCRIPT,
                ingredients
        ));
    }

    private void createBannerRecipe(Consumer<FinishedRecipe> exporter, Item targetItem) {

        NonNullList<Ingredient> ingredients = NonNullList.create();

        // Any banner
        ingredients.add(Ingredient.of(net.minecraft.tags.ItemTags.BANNERS));

        // The armor/surcoat/etc
        ingredients.add(Ingredient.of(targetItem));

        exporter.accept(new BannerRecipeBuilder(
                new ResourceLocation(KnightsHeraldry.MOD_ID,
                        "banner_pattern/" + getItemName(targetItem)),
                targetItem,
                ingredients,
                CraftingBookCategory.MISC,
                getItemName(targetItem)
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
                    .requires(SCItems.SMITHING_HAMMER)
                    .requires(current)
                    .unlockedBy(getHasName(SCItems.SMITHING_HAMMER), has(SCItems.SMITHING_HAMMER))
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
