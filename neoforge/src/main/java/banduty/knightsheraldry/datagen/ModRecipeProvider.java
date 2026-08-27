package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.CraftmanAnvilRecipeJsonBuilder;
import banduty.stoneycore.items.SCItems;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
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

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
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

        // Dagger Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.DAGGER_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_DAGGER.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.DAGGER.get(), finished(new ItemStack(KHItems.DAGGER_HEAD.get())), new ItemStack(Items.STICK));

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.STILETTO_HEAD.get(), unfinished(new ItemStack(KHItems.DAGGER_HEAD.get())));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.STILETTO.get(), finished(new ItemStack(KHItems.STILETTO_HEAD.get())), new ItemStack(Items.STICK));

        // Sword Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SWORD_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_SWORD.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.SWORD.get(), finished(new ItemStack(KHItems.SWORD_HEAD.get())), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.SWORD.get(), KHItems.V_SWORD.get(), KHItems.ARMING_SWORD.get());
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.GLAIVE.get(), finished(new ItemStack(KHItems.SWORD_HEAD.get())), new ItemStack(Items.STICK), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.GLAIVE.get(), KHItems.CURVED_GLAIVE.get());

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.FALCHION_HEAD.get(), unfinished(new ItemStack(KHItems.SWORD_HEAD.get())));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.FALCHION.get(), finished(new ItemStack(KHItems.FALCHION_HEAD.get())), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.FALCHION.get(), KHItems.SCIMITAR.get());

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.RAPIER_HEAD.get(), unfinished(new ItemStack(KHItems.FALCHION_HEAD.get())));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.RAPIER.get(), finished(new ItemStack(KHItems.RAPIER_HEAD.get())), new ItemStack(Items.STICK));

        // Axe Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.AXE_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_AXE.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.AXE.get(), finished(new ItemStack(KHItems.AXE_HEAD.get())), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.AXE.get(), KHItems.BROAD_AXE.get(), KHItems.CROOKED_AXE.get(), KHItems.STRAIGHT_CROOKED_AXE.get());
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BARDICHE.get(), finished(new ItemStack(KHItems.AXE_HEAD.get())), new ItemStack(Items.STICK), new ItemStack(Items.STICK));

        // Hammer Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.HAMMER_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_HAMMER.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.HAMMER.get(), finished(new ItemStack(KHItems.HAMMER_HEAD.get())), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.HAMMER.get(), KHItems.WAR_HAMMER.get());
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, KHItems.WAR_HAMMER.get(), KHItems.HAMMER.get());
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.POLEHAMMER.get(), finished(new ItemStack(KHItems.HAMMER_HEAD.get())), new ItemStack(Items.STICK), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.POLEHAMMER.get(), KHItems.BEC_DE_CORBIN.get());

        // Mace Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.MACE_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_MACE.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.MACE.get(), finished(new ItemStack(KHItems.MACE_HEAD.get())), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.MACE.get(), KHItems.SPIKED_MACE.get());
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.FLAIL.get(), finished(new ItemStack(KHItems.MACE_HEAD.get())), new ItemStack(Items.STICK), new ItemStack(Items.CHAIN));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.FLAIL.get(), KHItems.BALL_FLAIL.get());
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.MORNING_STAR.get(), finished(new ItemStack(KHItems.MACE_HEAD.get())), new ItemStack(Items.STICK), new ItemStack(Items.STICK));

        // Halberd Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.HALBERD_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_HALBERD.get()));
        CraftmanAnvilRecipeJsonBuilder.create(new ItemStack(KHItems.HALBERD.get()))
                .hitTimes(5)
                .chance(0.5f)
                .pattern("HSS")
                .defineFinished('H', new ItemStack(KHItems.HALBERD_HEAD.get()))
                .define('S', new ItemStack(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "craftmananvil/halberd_1"));
        CraftmanAnvilRecipeJsonBuilder.create(new ItemStack(KHItems.HALBERD.get()))
                .hitTimes(5)
                .chance(0.5f)
                .pattern("SSH")
                .defineFinished('H', new ItemStack(KHItems.HALBERD_HEAD.get()))
                .define('S', new ItemStack(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "craftmananvil/halberd_2"));
        CraftmanAnvilRecipeJsonBuilder.create(new ItemStack(KHItems.POLEAXE.get()))
                .hitTimes(5)
                .chance(0.5f)
                .pattern("H  ",
                        " SS")
                .defineFinished('H', new ItemStack(KHItems.HALBERD_HEAD.get()))
                .define('S', new ItemStack(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "craftmananvil/poleaxe_1"));
        CraftmanAnvilRecipeJsonBuilder.create(new ItemStack(KHItems.POLEAXE.get()))
                .hitTimes(5)
                .chance(0.5f)
                .pattern("  H",
                        "SS ")
                .defineFinished('H', new ItemStack(KHItems.HALBERD_HEAD.get()))
                .define('S', new ItemStack(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "craftmananvil/poleaxe_2"));
        CraftmanAnvilRecipeJsonBuilder.create(new ItemStack(KHItems.POLEAXE.get()))
                .hitTimes(5)
                .chance(0.5f)
                .pattern(" SS",
                        "H  ")
                .defineFinished('H', new ItemStack(KHItems.HALBERD_HEAD.get()))
                .define('S', new ItemStack(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "craftmananvil/poleaxe_3"));
        CraftmanAnvilRecipeJsonBuilder.create(new ItemStack(KHItems.POLEAXE.get()))
                .hitTimes(5)
                .chance(0.5f)
                .pattern("SS ",
                        "  H")
                .defineFinished('H', new ItemStack(KHItems.HALBERD_HEAD.get()))
                .define('S', new ItemStack(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "craftmananvil/poleaxe_4"));

        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BILLHOOK_HEAD.get(), unfinished(new ItemStack(KHItems.HALBERD_HEAD.get())));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BILLHOOK.get(), finished(new ItemStack(KHItems.BILLHOOK_HEAD.get())), new ItemStack(Items.STICK), new ItemStack(Items.STICK));

        // Longsword Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.LONGSWORD_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_LONGSWORD.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.LONGSWORD.get(), finished(new ItemStack(KHItems.LONGSWORD_HEAD.get())), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.LONGSWORD.get(), KHItems.V_LONGSWORD.get());

        // Greatsword Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.GREATSWORD_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_GREATSWORD.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREATSWORD.get(), finished(new ItemStack(KHItems.GREATSWORD_HEAD.get())), new ItemStack(Items.STICK));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.GREATSWORD.get(), KHItems.CLAYMORE.get(), KHItems.FLAMBERGE.get(), KHItems.ZWEIHANDER.get());

        // Spear Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SPEAR_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_SPEAR.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SPEAR.get(), finished(new ItemStack(KHItems.SPEAR_HEAD.get())), new ItemStack(Items.STICK), new ItemStack(Items.STICK));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.LANCE.get(), finished(new ItemStack(KHItems.SPEAR_HEAD.get())), ItemTags.LOGS);
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.PIKE.get(), finished(new ItemStack(KHItems.SPEAR_HEAD.get())), ItemTags.LOGS, ItemTags.LOGS);
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.WARDART.get(), finished(new ItemStack(KHItems.SPEAR_HEAD.get())),
                new ItemStack(Items.STICK), new ItemStack(Items.STICK), new ItemStack(Items.FEATHER), new ItemStack(Items.FEATHER));

        // Pitchfork Head
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.PITCHFORK_HEAD.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_PITCHFORK.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.PITCHFORK.get(), finished(new ItemStack(KHItems.PITCHFORK_HEAD.get())), new ItemStack(Items.STICK), new ItemStack(Items.STICK));

        // Visor
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.VISOR.get(), new ItemStack(SCItems.HOT_IRON.get()),
                new ItemStack(KHItems.MANUSCRIPT_VISOR.get()), new ItemStack(Items.SAND));

        // Bevor
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BEVOR.get(), new ItemStack(SCItems.HOT_IRON.get()),
                new ItemStack(KHItems.MANUSCRIPT_BEVOR.get()), new ItemStack(Items.SAND));

        // Falling Buffe
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.FALLING_BUFFE.get(), new ItemStack(SCItems.HOT_IRON.get()),
                new ItemStack(KHItems.MANUSCRIPT_FALLING_BUFFE.get()), new ItemStack(Items.SAND));

        // Barbute Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BARBUTE_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_BARBUTE.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BARBUTE.get(), new ItemStack(KHItems.BARBUTE_PIECE.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, KHItems.VISORED_BARBUTE.get(), unfinished(new ItemStack(KHItems.BARBUTE.get())), unfinished(new ItemStack(KHItems.VISOR.get())));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BARBUTE.get(), unfinished(new ItemStack(KHItems.BARBUTE.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_VISORED_BARBUTE.get(), unfinished(new ItemStack(KHItems.VISORED_BARBUTE.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BARBUTE.get(), unfinished(new ItemStack(KHItems.BARBUTE.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_VISORED_BARBUTE.get(), unfinished(new ItemStack(KHItems.VISORED_BARBUTE.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Bascinet Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BASCINET_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_BASCINET.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BASCINET.get(), new ItemStack(KHItems.BASCINET_PIECE.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, KHItems.VISORED_BASCINET.get(), unfinished(new ItemStack(KHItems.BASCINET.get())), unfinished(new ItemStack(KHItems.VISOR.get())));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREAT_BASCINET.get(), unfinished(new ItemStack(KHItems.VISORED_BASCINET.get())), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BASCINET.get(), unfinished(new ItemStack(KHItems.BASCINET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_VISORED_BASCINET.get(), unfinished(new ItemStack(KHItems.VISORED_BASCINET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_GREAT_BASCINET.get(), unfinished(new ItemStack(KHItems.GREAT_BASCINET.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BASCINET.get(), unfinished(new ItemStack(KHItems.BASCINET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_VISORED_BASCINET.get(), unfinished(new ItemStack(KHItems.VISORED_BASCINET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_GREAT_BASCINET.get(), unfinished(new ItemStack(KHItems.GREAT_BASCINET.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Kettle Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.KETTLE_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_KETTLE.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.KETTLE_HELM.get(), new ItemStack(KHItems.KETTLE_PIECE.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_KETTLE_HELM.get(), unfinished(new ItemStack(KHItems.KETTLE_HELM.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_KETTLE_HELM.get(), unfinished(new ItemStack(KHItems.KETTLE_HELM.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Nasal Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.NASAL_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_NASAL.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.NASAL_HELM.get(), new ItemStack(KHItems.NASAL_PIECE.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.VIKING_HELM.get(), new ItemStack(KHItems.NASAL_PIECE.get()), new ItemStack(Items.SAND), new ItemStack(SCItems.HOT_IRON.get()));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_NASAL_HELM.get(), unfinished(new ItemStack(KHItems.NASAL_HELM.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_VIKING_HELM.get(), unfinished(new ItemStack(KHItems.VIKING_HELM.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_NASAL_HELM.get(), unfinished(new ItemStack(KHItems.NASAL_HELM.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_VIKING_HELM.get(), unfinished(new ItemStack(KHItems.VIKING_HELM.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Burgonet Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BURGONET_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_BURGONET.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BURGONET.get(), new ItemStack(KHItems.BURGONET_PIECE.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BURGONET_FALLING_BUFFE.get(), unfinished(new ItemStack(KHItems.BURGONET.get())), unfinished(new ItemStack(KHItems.FALLING_BUFFE.get())));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BURGONET.get(), unfinished(new ItemStack(KHItems.BURGONET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BURGONET_FALLING_BUFFE.get(), unfinished(new ItemStack(KHItems.BURGONET_FALLING_BUFFE.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BURGONET.get(), unfinished(new ItemStack(KHItems.BURGONET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BURGONET_FALLING_BUFFE.get(), unfinished(new ItemStack(KHItems.BURGONET_FALLING_BUFFE.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Sallet Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SALLET_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_SALLET.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.VISORLESS_SALLET.get(), new ItemStack(KHItems.SALLET_PIECE.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, KHItems.SALLET.get(), unfinished(new ItemStack(KHItems.VISORLESS_SALLET.get())), unfinished(new ItemStack(KHItems.VISOR.get())));
        createCraftmanAnvilRecipe(exporter, 7, 0.5f, KHItems.SALLET_BEVOR.get(), unfinished(new ItemStack(KHItems.SALLET.get())), unfinished(new ItemStack(KHItems.BEVOR.get())));
        createCraftmanAnvilRecipe(exporter, 10, 0.25f, KHItems.ARAGONESE_SALLET.get(), unfinished(new ItemStack(KHItems.SALLET_BEVOR.get())), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, KHItems.BLACK_SALLET.get(), unfinished(new ItemStack(KHItems.VISORLESS_SALLET.get())), unfinished(new ItemStack(KHItems.VISOR.get())), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 7, 0.5f, KHItems.BLACK_SALLET_BEVOR.get(), unfinished(new ItemStack(KHItems.BLACK_SALLET.get())), unfinished(new ItemStack(KHItems.BEVOR.get())));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_VISORLESS_SALLET.get(), unfinished(new ItemStack(KHItems.VISORLESS_SALLET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_SALLET.get(), unfinished(new ItemStack(KHItems.SALLET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_SALLET_BEVOR.get(), unfinished(new ItemStack(KHItems.SALLET_BEVOR.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_ARAGONESE_SALLET.get(), unfinished(new ItemStack(KHItems.ARAGONESE_SALLET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BLACK_SALLET.get(), unfinished(new ItemStack(KHItems.BLACK_SALLET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BLACK_SALLET_BEVOR.get(), unfinished(new ItemStack(KHItems.BLACK_SALLET_BEVOR.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_VISORLESS_SALLET.get(), unfinished(new ItemStack(KHItems.VISORLESS_SALLET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_SALLET.get(), unfinished(new ItemStack(KHItems.SALLET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_SALLET_BEVOR.get(), unfinished(new ItemStack(KHItems.SALLET_BEVOR.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_ARAGONESE_SALLET.get(), unfinished(new ItemStack(KHItems.ARAGONESE_SALLET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BLACK_SALLET.get(), unfinished(new ItemStack(KHItems.BLACK_SALLET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BLACK_SALLET_BEVOR.get(), unfinished(new ItemStack(KHItems.BLACK_SALLET_BEVOR.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Morion Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.MORION_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_MORION.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.MORION.get(), new ItemStack(KHItems.MORION_PIECE.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.75f, KHItems.VISORED_MORION.get(), unfinished(new ItemStack(KHItems.MORION.get())), unfinished(new ItemStack(KHItems.VISOR.get())));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_MORION.get(), unfinished(new ItemStack(KHItems.MORION.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_VISORED_MORION.get(), unfinished(new ItemStack(KHItems.VISORED_MORION.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_MORION.get(), unfinished(new ItemStack(KHItems.MORION.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_VISORED_MORION.get(), unfinished(new ItemStack(KHItems.VISORED_MORION.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Armet Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.ARMET_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_ARMET.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.ARMET.get(), new ItemStack(KHItems.ARMET_PIECE.get()), new ItemStack(Items.SAND));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.ARMET.get(), KHItems.ARMET_2.get());
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREAT_ARMET.get(), unfinished(new ItemStack(KHItems.ARMET.get())), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREAT_ARMET_2.get(), unfinished(new ItemStack(KHItems.ARMET_2.get())), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.GREAT_ARMET.get(), KHItems.GREAT_ARMET_2.get());

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_ARMET.get(), unfinished(new ItemStack(KHItems.ARMET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_ARMET_2.get(), unfinished(new ItemStack(KHItems.ARMET_2.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.GOLDEN_ARMET.get(), KHItems.GOLDEN_ARMET_2.get());
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_GREAT_ARMET.get(), unfinished(new ItemStack(KHItems.GREAT_ARMET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_GREAT_ARMET_2.get(), unfinished(new ItemStack(KHItems.GREAT_ARMET_2.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.GOLDEN_GREAT_ARMET.get(), KHItems.GOLDEN_GREAT_ARMET_2.get());

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_ARMET.get(), unfinished(new ItemStack(KHItems.ARMET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_ARMET_2.get(), unfinished(new ItemStack(KHItems.ARMET_2.get())), new ItemStack(Items.HONEYCOMB, 4));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.DARK_ARMET.get(), KHItems.DARK_ARMET_2.get());
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_GREAT_ARMET.get(), unfinished(new ItemStack(KHItems.GREAT_ARMET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_GREAT_ARMET_2.get(), unfinished(new ItemStack(KHItems.GREAT_ARMET_2.get())), new ItemStack(Items.HONEYCOMB, 4));
        createWeaponCycle(exporter, 3, 1.0f, KHItems.DARK_GREAT_ARMET.get(), KHItems.DARK_GREAT_ARMET_2.get());

        // Cage Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.CAGE_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_CAGE.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.CAGE.get(), new ItemStack(KHItems.CAGE_PIECE.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_CAGE.get(), unfinished(new ItemStack(KHItems.CAGE.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_CAGE.get(), unfinished(new ItemStack(KHItems.CAGE.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Great Helmet Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.GREAT_HELMET_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_GREAT_HELMET.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.GREAT_HELM.get(), new ItemStack(KHItems.GREAT_HELMET_PIECE.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 3, 1.0f, KHItems.GREAT_HELM_2.get(), unfinished(new ItemStack(KHItems.GREAT_HELM.get())), new ItemStack(Items.GOLD_INGOT));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.HOUNDSKULL.get(), new ItemStack(KHItems.GREAT_HELMET_PIECE.get()), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.GREAT_HOUNDSKUL_BASCINET.get(), unfinished(new ItemStack(KHItems.HOUNDSKULL.get())), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_GREAT_HELM.get(), unfinished(new ItemStack(KHItems.GREAT_HELM.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_GREAT_HELM_2.get(), unfinished(new ItemStack(KHItems.GREAT_HELM_2.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_HOUNDSKULL.get(), unfinished(new ItemStack(KHItems.HOUNDSKULL.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_GREAT_HOUNDSKUL_BASCINET.get(), unfinished(new ItemStack(KHItems.GREAT_HOUNDSKUL_BASCINET.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_GREAT_HELM.get(), unfinished(new ItemStack(KHItems.GREAT_HELM.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_GREAT_HELM_2.get(), unfinished(new ItemStack(KHItems.GREAT_HELM_2.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_HOUNDSKULL.get(), unfinished(new ItemStack(KHItems.HOUNDSKULL.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_GREAT_HOUNDSKUL_BASCINET.get(), unfinished(new ItemStack(KHItems.GREAT_HOUNDSKUL_BASCINET.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Close Helmet Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.CLOSE_HELMET_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_CLOSE_HELMET.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.CLOSE_HELM.get(), new ItemStack(KHItems.CLOSE_HELMET_PIECE.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_CLOSE_HELM.get(), unfinished(new ItemStack(KHItems.CLOSE_HELM.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_CLOSE_HELM.get(), unfinished(new ItemStack(KHItems.CLOSE_HELM.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Frogmouth Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.FROGMOUTH_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_FROGMOUTH.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.FROGMOUTH.get(), new ItemStack(KHItems.FROGMOUTH_PIECE.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_FROGMOUTH.get(), unfinished(new ItemStack(KHItems.FROGMOUTH.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_FROGMOUTH.get(), unfinished(new ItemStack(KHItems.FROGMOUTH.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Maximilian Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.MAXIMILIAN_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_MAXIMILIAN.get()));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.MAXIMILLIAN_HELMET.get(), new ItemStack(KHItems.MAXIMILIAN_PIECE.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_MAXIMILLIAN_HELMET.get(), unfinished(new ItemStack(KHItems.MAXIMILLIAN_HELMET.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_MAXIMILLIAN_HELMET.get(), unfinished(new ItemStack(KHItems.MAXIMILLIAN_HELMET.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Aventail
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.AVENTAIL.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_AVENTAIL.get()), new ItemStack(Items.SAND));

        // Cuirass Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.CUIRASS_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_CUIRASS.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BRIGANDINE.get(), new ItemStack(KHItems.CUIRASS_PIECE.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.PLATE_CUIRASS.get(), new ItemStack(KHItems.CUIRASS_PIECE.get()), new ItemStack(SCItems.HOT_IRON.get()));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.MAXIMILLIAN_CUIRASS.get(), unfinished(new ItemStack(KHItems.PLATE_CUIRASS.get())));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.XIIII_PLATE_CUIRASS.get(), unfinished(new ItemStack(KHItems.MAXIMILLIAN_CUIRASS.get())));
        createCraftmanAnvilRecipe(exporter, 3, 0.75f, KHItems.XIIII_PLATE_BREASTPLATE.get(), new ItemStack(KHItems.CUIRASS_PIECE.get()));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BRIGANDINE.get(), unfinished(new ItemStack(KHItems.BRIGANDINE.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_PLATE_CUIRASS.get(), unfinished(new ItemStack(KHItems.PLATE_CUIRASS.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_MAXIMILLIAN_CUIRASS.get(), unfinished(new ItemStack(KHItems.MAXIMILLIAN_CUIRASS.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_XIIII_PLATE_CUIRASS.get(), unfinished(new ItemStack(KHItems.XIIII_PLATE_CUIRASS.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_XIIII_PLATE_BREASTPLATE.get(), unfinished(new ItemStack(KHItems.XIIII_PLATE_BREASTPLATE.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BRIGANDINE.get(), unfinished(new ItemStack(KHItems.BRIGANDINE.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_PLATE_CUIRASS.get(), unfinished(new ItemStack(KHItems.PLATE_CUIRASS.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_MAXIMILLIAN_CUIRASS.get(), unfinished(new ItemStack(KHItems.MAXIMILLIAN_CUIRASS.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_XIIII_PLATE_CUIRASS.get(), unfinished(new ItemStack(KHItems.XIIII_PLATE_CUIRASS.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_XIIII_PLATE_BREASTPLATE.get(), unfinished(new ItemStack(KHItems.XIIII_PLATE_BREASTPLATE.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Plackart
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.PLACKART.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_PLACKART.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_PLACKART.get(), unfinished(new ItemStack(KHItems.PLACKART.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_PLACKART.get(), unfinished(new ItemStack(KHItems.PLACKART.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Tassets
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.TASSETS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_TASSETS.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_TASSETS.get(), unfinished(new ItemStack(KHItems.TASSETS.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_TASSETS.get(), unfinished(new ItemStack(KHItems.TASSETS.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Rim Guards
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.RIM_GUARDS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_RIM_GUARDS.get()), new ItemStack(Items.SAND));

        // Besagews
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BESAGEWS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_BESAGEWS.get()), new ItemStack(Items.SAND));

        // Spaulders
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SPAULDERS_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_SPAULDERS.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.MAIL_SPAULDERS.get(), new ItemStack(KHItems.SPAULDERS_PIECE.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BRIGANDINE_SPAULDERS.get(), new ItemStack(KHItems.SPAULDERS_PIECE.get()), new ItemStack(Items.LEATHER));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.PLATE_SPAULDERS.get(), new ItemStack(KHItems.SPAULDERS_PIECE.get()), new ItemStack(SCItems.HOT_IRON.get()));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_MAIL_SPAULDERS.get(), unfinished(new ItemStack(KHItems.MAIL_SPAULDERS.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), unfinished(new ItemStack(KHItems.BRIGANDINE_SPAULDERS.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_PLATE_SPAULDERS.get(), unfinished(new ItemStack(KHItems.PLATE_SPAULDERS.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BRIGANDINE_SPAULDERS.get(), unfinished(new ItemStack(KHItems.BRIGANDINE_SPAULDERS.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_PLATE_SPAULDERS.get(), unfinished(new ItemStack(KHItems.PLATE_SPAULDERS.get())), new ItemStack(Items.HONEYCOMB, 4));

        createEasyRecipe(exporter, 3, 0.9f, KHItems.MAIL_SPAULDERS_BESAGEWS.get(), KHItems.MAIL_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.GOLDEN_MAIL_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_MAIL_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.BRIGANDINE_SPAULDERS_BESAGEWS.get(), KHItems.BRIGANDINE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.DARK_BRIGANDINE_SPAULDERS_BESAGEWS.get(), KHItems.DARK_BRIGANDINE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_BRIGANDINE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.PLATE_SPAULDERS_BESAGEWS.get(), KHItems.PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.DARK_PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.GOLDEN_PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.PLATE_SPAULDERS_RIMMED.get(), KHItems.PLATE_SPAULDERS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.DARK_PLATE_SPAULDERS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(), KHItems.GOLDEN_PLATE_SPAULDERS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.GOLDEN_PLATE_SPAULDERS.get(), KHItems.BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.PLATE_SPAULDERS_BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS.get(), KHItems.RIM_GUARDS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.PLATE_SPAULDERS_RIMMED.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.DARK_PLATE_SPAULDERS_RIMMED.get(), KHItems.BESAGEWS.get());
        createEasyRecipe(exporter, 3, 0.9f, KHItems.GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED.get(), KHItems.GOLDEN_PLATE_SPAULDERS_RIMMED.get(), KHItems.BESAGEWS.get());

        // Harness Piece
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.HARNESS_PIECE.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_HARNESS.get()));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.GAUNTLET.get(), new ItemStack(KHItems.HARNESS_PIECE.get()), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BRIGANDINE_HARNESS.get(), unfinished(new ItemStack(KHItems.GAUNTLET.get())), new ItemStack(Items.LEATHER), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 7, 0.25f, KHItems.PLATE_HARNESS.get(), unfinished(new ItemStack(KHItems.GAUNTLET.get())), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_GAUNTLET.get(), unfinished(new ItemStack(KHItems.GAUNTLET.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BRIGANDINE_HARNESS.get(), unfinished(new ItemStack(KHItems.BRIGANDINE_HARNESS.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_PLATE_HARNESS.get(), unfinished(new ItemStack(KHItems.PLATE_HARNESS.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_GAUNTLET.get(), unfinished(new ItemStack(KHItems.GAUNTLET.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BRIGANDINE_HARNESS.get(), unfinished(new ItemStack(KHItems.BRIGANDINE_HARNESS.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_PLATE_HARNESS.get(), unfinished(new ItemStack(KHItems.PLATE_HARNESS.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Cuisses
        createCraftmanAnvilRecipe(exporter, 3, 0.75f, KHItems.BRIGANDINE_CUISSES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_CUISSES.get()), new ItemStack(Items.LEATHER), new ItemStack(Items.SAND));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.PLATE_CUISSES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_CUISSES.get()), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_BRIGANDINE_CUISSES.get(), unfinished(new ItemStack(KHItems.BRIGANDINE_CUISSES.get())), new ItemStack(Items.GOLD_INGOT, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_PLATE_CUISSES.get(), unfinished(new ItemStack(KHItems.PLATE_CUISSES.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_BRIGANDINE_CUISSES.get(), unfinished(new ItemStack(KHItems.BRIGANDINE_CUISSES.get())), new ItemStack(Items.HONEYCOMB, 4));
        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_PLATE_CUISSES.get(), unfinished(new ItemStack(KHItems.PLATE_CUISSES.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Greaves
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.GREAVES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_GREAVES.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_GREAVES.get(), unfinished(new ItemStack(KHItems.GREAVES.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_GREAVES.get(), unfinished(new ItemStack(KHItems.GREAVES.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Sabatons
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SABATONS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_SABATONS.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_SABATONS.get(), unfinished(new ItemStack(KHItems.SABATONS.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_SABATONS.get(), unfinished(new ItemStack(KHItems.SABATONS.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Barding
        createCraftmanAnvilRecipe(exporter, 9, 0.25f, KHItems.HORSE_BARDING.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_BARDING.get()), new ItemStack(Items.SAND));

        createCraftmanAnvilRecipe(exporter, 7, 0.7f, KHItems.GOLDEN_HORSE_BARDING.get(), unfinished(new ItemStack(KHItems.HORSE_BARDING.get())), new ItemStack(Items.GOLD_INGOT, 4));

        createCraftmanAnvilRecipe(exporter, 7, 0.85f, KHItems.DARK_HORSE_BARDING.get(), unfinished(new ItemStack(KHItems.HORSE_BARDING.get())), new ItemStack(Items.HONEYCOMB, 4));

        // Swallowtail
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.SWALLOWTAIL_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_SWALLOWTAIL.get()), new ItemStack(Items.SAND), new ItemStack(Items.FEATHER), new ItemStack(Items.STICK));

        // Bodkin
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.BODKIN_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_BODKIN.get()), new ItemStack(Items.SAND), new ItemStack(Items.FEATHER), new ItemStack(Items.STICK));

        // Broadhead
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.BROADHEAD_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_BROADHEAD.get()), new ItemStack(Items.SAND), new ItemStack(Items.FEATHER), new ItemStack(Items.STICK));

        // Cloth
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.CLOTH_ARROW.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(KHItems.MANUSCRIPT_CLOTH.get()), new ItemStack(Items.SAND), new ItemStack(Items.FEATHER), new ItemStack(Items.STICK), ItemTags.WOOL);

        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.MAIL_COIF.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.HAUBERK.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.MAIL_BREECHES.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 5, 0.5f, KHItems.MAIL_BOOTS.get(), new ItemStack(SCItems.HOT_IRON.get()), new ItemStack(Items.CHAIN));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.LEATHER_GLOVES.get(), new ItemStack(Items.LEATHER, 2));
        createCraftmanAnvilRecipe(exporter, 3, 0.9f, KHItems.MAIL_GLOVES.get(), new ItemStack(SCItems.HOT_IRON.get(), 2));
    }

    private record Unfinished(ItemStack stack) {
    }

    private static Unfinished unfinished(ItemStack stack) {
        return new Unfinished(stack);
    }

    private record Finished(ItemStack stack) {
    }

    private static Finished finished(ItemStack stack) {
        return new Finished(stack);
    }

    private void createCraftmanAnvilRecipe(RecipeOutput exporter, int hitTime, float chance, Item output, Object... requiress) {
        createCraftmanAnvilRecipeIgnited(exporter, hitTime, chance, output, true, "", requiress);
    }

    private void createCraftmanAnvilRecipeIgnited(RecipeOutput exporter, int hitTime, float chance, Item output, boolean outputIgnited, String path, Object... requiress) {
        ItemStack resultStack = new ItemStack(output);
        if (outputIgnited) resultStack.set(SCDataComponents.IGNITED.get(), true);

        CraftmanAnvilRecipeJsonBuilder builder = CraftmanAnvilRecipeJsonBuilder.create(resultStack)
                .hitTimes(hitTime)
                .chance(chance);


        for (Object requires : requiress) {
            if (requires instanceof Unfinished(ItemStack itemStack)) {
                int count = itemStack.getCount();
                for (int i = 0; i < count; i++) {
                    ItemStack singleStack = itemStack.copy();
                    singleStack.setCount(1);
                    builder.requiresUnfinished(singleStack);
                }
            } else if (requires instanceof Finished(ItemStack itemStack)) {
                int count = itemStack.getCount();
                for (int i = 0; i < count; i++) {
                    ItemStack singleStack = itemStack.copy();
                    singleStack.setCount(1);
                    builder.requiresFinished(singleStack);
                }
            } else if (requires instanceof ItemStack itemStack) {
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
                builder.requires(itemTag, 1);
            } else {
                System.out.println("Unhandled type: " + requires.getClass().getName());
            }
        }

        if (path.isEmpty()) path = "craftmananvil/" + BuiltInRegistries.ITEM.getKey(output).getPath();

        builder.save(exporter, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, path));
    }

    private void createWeaponCycle(RecipeOutput exporter, int hitTime, float chance, Item... weapons) {
        for (int i = 0; i < weapons.length; i++) {
            Item current = weapons[i];
            Item next = weapons[(i + 1) % weapons.length];

            String path = "upgrade_" + BuiltInRegistries.ITEM.getKey(current).getPath() + "_to_" + BuiltInRegistries.ITEM.getKey(next).getPath();

            createCraftmanAnvilRecipeIgnited(exporter, hitTime, chance, next, false, path, current);
        }
    }

    private void createEasyRecipe(RecipeOutput exporter, int hitTime, float chance, Item finalItem, Item principal, Item... attachments) {
        StringBuilder recipeId = new StringBuilder(BuiltInRegistries.ITEM.getKey(finalItem).getPath());
        recipeId.append("_from_").append(BuiltInRegistries.ITEM.getKey(principal).getPath());
        for (Item attachment : attachments) {
            recipeId.append("_and_").append(BuiltInRegistries.ITEM.getKey(attachment).getPath());
        }

        Object[] requiress = new Object[attachments.length + 1];
        requiress[0] = principal;
        System.arraycopy(attachments, 0, requiress, 1, attachments.length);

        createCraftmanAnvilRecipeIgnited(exporter, hitTime, chance, finalItem, true, recipeId.toString(), requiress);
    }
}