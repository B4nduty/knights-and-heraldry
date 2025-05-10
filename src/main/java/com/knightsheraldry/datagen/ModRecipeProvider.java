package com.knightsheraldry.datagen;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, "smithing_hammer"));

        createWeaponCycle(exporter, ModItems.ARMING_SWORD, ModItems.SWORD, ModItems.V_SWORD);
        createWeaponCycle(exporter, ModItems.STRAIGHT_CROOKED_AXE, ModItems.AXE, ModItems.BROAD_AXE, ModItems.CROOKED_AXE);
        createWeaponCycle(exporter, ModItems.MACE, ModItems.SPIKED_MACE);
        createWeaponCycle(exporter, ModItems.FLAIL, ModItems.BALL_FLAIL);
        createWeaponCycle(exporter, ModItems.HAMMER, ModItems.WAR_HAMMER);
        createWeaponCycle(exporter, ModItems.LONGSWORD, ModItems.V_LONGSWORD);
        createWeaponCycle(exporter, ModItems.FALCHION, ModItems.SCIMITAR);
        createWeaponCycle(exporter, ModItems.POLEHAMMER, ModItems.BEC_DE_CORBIN);
        createWeaponCycle(exporter, ModItems.WARSWORD, ModItems.WARSWORD_CLAYMORE,
                ModItems.WARSWORD_FLAMBERGE, ModItems.WARSWORD_ZWEIHANDER);

        createAventailHelmet(exporter, ModItems.ARMET);
        createAventailHelmet(exporter, ModItems.ARMET_2);
        createAventailHelmet(exporter, ModItems.VIKING_HELM);
        createAventailHelmet(exporter, ModItems.GREAT_HELM);
        createAventailHelmet(exporter, ModItems.GREAT_HELM_2);
        createAventailHelmet(exporter, ModItems.KETTLE_HELM);
        createAventailHelmet(exporter, ModItems.NASAL_HELM);
        createAventailHelmet(exporter, ModItems.SALLET);
        createAventailHelmet(exporter, ModItems.BARBUTE);
        createAventailHelmet(exporter, ModItems.BARBUTE_NO_VISOR);
        createAventailHelmet(exporter, ModItems.BASCINET);
        createAventailHelmet(exporter,  ModItems.BASCINET_NO_VISOR);
        createAventailHelmet(exporter, ModItems.CAGE);
        createAventailHelmet(exporter,  ModItems.CAGE_2);
        createAventailHelmet(exporter,  ModItems.FLAT_BASCINET);

        createPlumeRecipe(exporter,  ModItems.BARBUTE_NO_VISOR);
        createPlumeRecipe(exporter,  ModItems.BASCINET_NO_VISOR);
        createPlumeRecipe(exporter,  ModItems.KETTLE_HELM);
        createPlumeRecipe(exporter,  ModItems.NASAL_HELM);
        createPlumeRecipe(exporter,  ModItems.VIKING_HELM);
        createPlumeRecipe(exporter,  ModItems.ARMET);
        createPlumeRecipe(exporter,  ModItems.ARMET_2);
        createPlumeRecipe(exporter,  ModItems.BARBUTE);
        createPlumeRecipe(exporter,  ModItems.BASCINET);
        createPlumeRecipe(exporter,  ModItems.CAGE);
        createPlumeRecipe(exporter,  ModItems.CAGE_2);
        createPlumeRecipe(exporter,  ModItems.FLAT_BASCINET);
        createPlumeRecipe(exporter,  ModItems.GREAT_HELM);
        createPlumeRecipe(exporter,  ModItems.GREAT_HELM_2);
        createPlumeRecipe(exporter,  ModItems.SALLET);
        createPlumeRecipe(exporter,  ModItems.FROGMOUTH);
        createPlumeRecipe(exporter,  ModItems.GREAT_ARMET);
        createPlumeRecipe(exporter,  ModItems.GREAT_ARMET_2);
        createPlumeRecipe(exporter,  ModItems.GREAT_BASCINET);
        createPlumeRecipe(exporter,  ModItems.GREAT_HOUNDSKUL_BASCINET);
        createPlumeRecipe(exporter,  ModItems.MAXIMILLIAN_HELMET);
        createPlumeRecipe(exporter,  ModItems.HORSE_BARDING);

        createPauldronRecipe(exporter, ModItems.PLATE_PAULDRON, ModItems.RIM_GUARDS, "plate_pauldron_rim");
        createPauldronRecipe(exporter, ModItems.MAIL_PAULDRON, ModItems.BESAGEWS, "mail_pauldron_besagews");
        createPauldronRecipe(exporter, ModItems.BRIGANDINE_PAULDRON, ModItems.BESAGEWS, "brigandine_pauldron_besagews");
        createPauldronRecipe(exporter, ModItems.PLATE_PAULDRON, ModItems.BESAGEWS, "plate_pauldron_besagews");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SURCOAT, 1)
                .input(ModItems.SURCOAT)
                .input(ItemTags.BANNERS)
                .criterion(hasItem(ModItems.SURCOAT), conditionsFromItem(ModItems.SURCOAT))
                .criterion(hasItem(Items.WHITE_BANNER), conditionsFromItem(Items.WHITE_BANNER))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, "surcoat_with_banner"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SURCOAT_SLEEVELESS, 1)
                .input(ModItems.SURCOAT_SLEEVELESS)
                .input(ItemTags.BANNERS)
                .criterion(hasItem(ModItems.SURCOAT_SLEEVELESS), conditionsFromItem(ModItems.SURCOAT_SLEEVELESS))
                .criterion(hasItem(Items.WHITE_BANNER), conditionsFromItem(Items.WHITE_BANNER))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, "sleeveless_surcoat_with_banner"));
    }

    private void createAventailHelmet(Consumer<RecipeJsonProvider> exporter, Item helmet) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, helmet, 1)
                .input(helmet)
                .input(ModItems.AVENTAIL)
                .criterion(hasItem(helmet), conditionsFromItem(helmet))
                .criterion(hasItem(ModItems.AVENTAIL), conditionsFromItem(ModItems.AVENTAIL))
                .offerTo(exporter, new Identifier(
                        KnightsHeraldry.MOD_ID,
                        "aventail/" + Registries.ITEM.getId(helmet).getPath()
                ));
    }

    private void createPlumeRecipe(Consumer<RecipeJsonProvider> exporter, Item item) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, item, 1)
                .input(item)
                .input(ModItems.PLUME)
                .criterion(hasItem(item), conditionsFromItem(item))
                .criterion(hasItem(ModItems.PLUME), conditionsFromItem(ModItems.PLUME))
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
                    .input(ModItems.SMITHING_HAMMER)
                    .input(current)
                    .criterion(hasItem(ModItems.SMITHING_HAMMER), conditionsFromItem(ModItems.SMITHING_HAMMER))
                    .criterion(hasItem(current), conditionsFromItem(current))
                    .offerTo(exporter, new Identifier(
                            KnightsHeraldry.MOD_ID,
                            "upgrade_" + Registries.ITEM.getId(current).getPath() + "_to_" + Registries.ITEM.getId(next).getPath()
                    ));
        }
    }

    private void createPauldronRecipe(Consumer<RecipeJsonProvider> exporter, Item pauldron, Item attachment, String recipeName) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, pauldron, 1)
                .input(pauldron)
                .input(attachment)
                .criterion(hasItem(pauldron), conditionsFromItem(pauldron))
                .criterion(hasItem(attachment), conditionsFromItem(attachment))
                .offerTo(exporter, new Identifier(KnightsHeraldry.MOD_ID, recipeName));
    }
}
