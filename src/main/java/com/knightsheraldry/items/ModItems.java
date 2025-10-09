package com.knightsheraldry.items;

import banduty.stoneycore.items.armor.underarmor.SCDyeableUnderArmor;
import banduty.stoneycore.items.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.KHBodkinArrowEntity;
import com.knightsheraldry.entity.custom.KHBroadheadArrowEntity;
import com.knightsheraldry.entity.custom.KHClothArrowEntity;
import com.knightsheraldry.entity.custom.KHSwallowTailArrowEntity;
import com.knightsheraldry.items.armor.accessory.*;
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import com.knightsheraldry.items.item.DyeableItems;
import com.knightsheraldry.items.item.KHExtendedArrowItem;
import com.knightsheraldry.items.item.TwoLayerDyeableItem;
import com.knightsheraldry.items.item.khammo.ClothArrow;
import com.knightsheraldry.items.item.khrangeweapon.Arquebus;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import com.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import com.knightsheraldry.items.item.khweapon.*;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(KnightsHeraldry.MOD_ID, RegistryKeys.ITEM);

    public static final RegistrySupplier<Item> DAGGER = ITEMS.register("dagger", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2F, new Item.Settings().maxDamage(151)));

    public static final RegistrySupplier<Item> STILETTO = ITEMS.register("stiletto", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2F, new Item.Settings().maxDamage(121)));

    public static final RegistrySupplier<Item> RAPIER = ITEMS.register("rapier", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.2F, new Item.Settings().maxDamage(161)));

    public static final RegistrySupplier<Item> SWORD = ITEMS.register("sword", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.4F, new Item.Settings().maxDamage(251)));
    public static final RegistrySupplier<Item> V_SWORD = ITEMS.register("v_sword", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.4F, new Item.Settings().maxDamage(251)));
    public static final RegistrySupplier<Item> ARMING_SWORD = ITEMS.register("arming_sword", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.4F, new Item.Settings().maxDamage(251)));

    public static final RegistrySupplier<Item> AXE = ITEMS.register("axe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Settings().maxDamage(301)));
    public static final RegistrySupplier<Item> BROAD_AXE = ITEMS.register("broad_axe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Settings().maxDamage(301)));
    public static final RegistrySupplier<Item> CROOKED_AXE = ITEMS.register("crooked_axe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Settings().maxDamage(301)));
    public static final RegistrySupplier<Item> STRAIGHT_CROOKED_AXE = ITEMS.register("straight_crooked_axe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> MACE = ITEMS.register("mace", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.0F, new Item.Settings().maxDamage(331)));
    public static final RegistrySupplier<Item> SPIKED_MACE = ITEMS.register("spiked_mace", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.0F, new Item.Settings().maxDamage(331)));

    public static final RegistrySupplier<Item> FLAIL = ITEMS.register("flail", () ->
            new Flail(-2.8F, new Item.Settings().maxDamage(201)));
    public static final RegistrySupplier<Item> BALL_FLAIL = ITEMS.register("ball_flail", () ->
            new Flail(-2.8F, new Item.Settings().maxDamage(201)));

    public static final RegistrySupplier<Item> HAMMER = ITEMS.register("hammer", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new Item.Settings().maxDamage(331)));
    public static final RegistrySupplier<Item> WAR_HAMMER = ITEMS.register("war_hammer", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new Item.Settings().maxDamage(331)));

    public static final RegistrySupplier<Item> LONGSWORD = ITEMS.register("longsword", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.5F, new Item.Settings().maxDamage(281)));
    public static final RegistrySupplier<Item> V_LONGSWORD = ITEMS.register("v_longsword", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.5F, new Item.Settings().maxDamage(281)));

    public static final RegistrySupplier<Item> FALCHION = ITEMS.register("falchion", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.5F, new Item.Settings().maxDamage(281)));
    public static final RegistrySupplier<Item> SCIMITAR = ITEMS.register("scimitar", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.5F, new Item.Settings().maxDamage(281)));

    public static final RegistrySupplier<Item> PITCHFORK = ITEMS.register("pitchfork", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Settings().maxDamage(181)));

    public static final RegistrySupplier<Item> SPEAR = ITEMS.register("spear", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.4F, new Item.Settings().maxDamage(181)));

    public static final RegistrySupplier<Item> PIKE = ITEMS.register("pike", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new Item.Settings().maxDamage(151)));

    public static final RegistrySupplier<Item> BILLHOOK = ITEMS.register("billhook", () ->
            new Billhook(-2.8F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> GLAIVE = ITEMS.register("glaive", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Settings().maxDamage(301)));
    public static final RegistrySupplier<Item> CURVED_GLAIVE = ITEMS.register("curved_glaive", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> HALBERD = ITEMS.register("halberd", () ->
            new Halberd(-2.8F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> LANCE = ITEMS.register("lance", () ->
            new Lance(-3.0F, new Item.Settings().maxDamage(1), SCDamageCalculator.DamageType.PIERCING));
    public static final RegistrySupplier<Item> WOODEN_LANCE = ITEMS.register("wooden_lance", () ->
            new WoodenLance(-3.0F, new Item.Settings().maxDamage(1), SCDamageCalculator.DamageType.PIERCING));

    public static final RegistrySupplier<Item> POLEAXE = ITEMS.register("poleaxe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.8F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> POLEHAMMER = ITEMS.register("polehammer", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new Item.Settings().maxDamage(301)));
    public static final RegistrySupplier<Item> BEC_DE_CORBIN = ITEMS.register("bec_de_corbin", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -2.8F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> MORNING_STAR = ITEMS.register("morning_star", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.1F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> BARDICHE = ITEMS.register("bardiche", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.0F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> WARSWORD = ITEMS.register("warsword", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.2F, new Item.Settings().maxDamage(301)));
    public static final RegistrySupplier<Item> WARSWORD_CLAYMORE = ITEMS.register("warsword_claymore", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.2F, new Item.Settings().maxDamage(301)));
    public static final RegistrySupplier<Item> WARSWORD_FLAMBERGE = ITEMS.register("warsword_flamberge", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.2F, new Item.Settings().maxDamage(301)));
    public static final RegistrySupplier<Item> WARSWORD_ZWEIHANDER = ITEMS.register("warsword_zweihander", () ->
            new SwordItem(ModToolMaterials.WEAPONS, 1, -3.2F, new Item.Settings().maxDamage(301)));

    public static final RegistrySupplier<Item> WARDART = ITEMS.register("wardart", () ->
            new WarDart(-2.4F, new Item.Settings().maxDamage(251)));


    public static final RegistrySupplier<Item> QUILTED_COIF = ITEMS.register("quilted_coif", () ->
            new SCDyeableUnderArmor(new Item.Settings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.HELMET, 10511680));
    public static final RegistrySupplier<Item> GAMBESON = ITEMS.register("gambeson", () ->
            new SCDyeableUnderArmor(new Item.Settings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.CHESTPLATE, 10511680));
    public static final RegistrySupplier<Item> GAMBESON_BREECHES = ITEMS.register("gambeson_breeches", () ->
            new SCDyeableUnderArmor(new Item.Settings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.LEGGINGS, 10511680));
    public static final RegistrySupplier<Item> GAMBESON_BOOTS = ITEMS.register("gambeson_boots", () ->
            new SCDyeableUnderArmor(new Item.Settings(), ModArmorMaterials.GAMBESON, ArmorItem.Type.BOOTS, 10511680));

    public static final RegistrySupplier<Item> MAIL_COIF = ITEMS.register("mail_coif", () ->
            new SCUnderArmor(new Item.Settings(), ModArmorMaterials.MAIL, ArmorItem.Type.HELMET));
    public static final RegistrySupplier<Item> HAUBERK = ITEMS.register("hauberk", () ->
            new SCUnderArmor(new Item.Settings(), ModArmorMaterials.MAIL, ArmorItem.Type.CHESTPLATE));
    public static final RegistrySupplier<Item> MAIL_BREECHES = ITEMS.register("mail_breeches", () ->
            new SCUnderArmor(new Item.Settings(), ModArmorMaterials.MAIL, ArmorItem.Type.LEGGINGS));
    public static final RegistrySupplier<Item> MAIL_BOOTS = ITEMS.register("mail_boots", () ->
            new SCUnderArmor(new Item.Settings(), ModArmorMaterials.MAIL, ArmorItem.Type.BOOTS));

    public static final RegistrySupplier<Item> MAIL_PAULDRON = ITEMS.register("mail_pauldron", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(74), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> BRIGANDINE_PAULDRON = ITEMS.register("brigandine_pauldron", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(84), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> DARK_BRIGANDINE_PAULDRON = ITEMS.register("dark_brigandine_pauldron", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(84), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> PLATE_PAULDRON = ITEMS.register("plate_pauldron", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(132), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_PLATE_PAULDRON = ITEMS.register("dark_plate_pauldron", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(132), Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> BRIGANDINE = ITEMS.register("brigandine", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(325), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> DARK_BRIGANDINE = ITEMS.register("dark_brigandine", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(325), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));

    public static final RegistrySupplier<Item> BRIG_BREASTPLATE = ITEMS.register("brig_breastplate", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(420), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> DARK_BRIG_BREASTPLATE = ITEMS.register("dark_brig_breastplate", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(420), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> BRIG_BREASTPLATE_TASSETS = ITEMS.register("brig_breastplate_tassets", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(420), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> DARK_BRIG_BREASTPLATE_TASSETS = ITEMS.register("dark_brig_breastplate_tassets", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(420), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));

    public static final RegistrySupplier<Item> PLATE_CUIRASS = ITEMS.register("plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_PLATE_CUIRASS = ITEMS.register("dark_plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> PLATE_CUIRASS_TASSETS = ITEMS.register("plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_PLATE_CUIRASS_TASSETS = ITEMS.register("dark_plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> MAXIMILLIAN_CUIRASS = ITEMS.register("maximillian_cuirass", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_MAXIMILLIAN_CUIRASS = ITEMS.register("dark_maximillian_cuirass", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> MAXIMILLIAN_CUIRASS_TASSETS = ITEMS.register("maximillian_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_MAXIMILLIAN_CUIRASS_TASSETS = ITEMS.register("dark_maximillian_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> XIIII_PLATE_CUIRASS = ITEMS.register("xiiii_plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_XIIII_PLATE_CUIRASS = ITEMS.register("dark_xiiii_plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> XIIII_PLATE_CUIRASS_TASSETS = ITEMS.register("xiiii_plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_XIIII_PLATE_CUIRASS_TASSETS = ITEMS.register("dark_xiiii_plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> XIIII_PLATE_BREASTPLATE = ITEMS.register("xiiii_plate_breastplate", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_XIIII_PLATE_BREASTPLATE = ITEMS.register("dark_xiiii_plate_breastplate", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(500), Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> BARBUTE_NO_VISOR = ITEMS.register("barbute_no_visor", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_BARBUTE_NO_VISOR = ITEMS.register("dark_barbute_no_visor", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> BASCINET_NO_VISOR = ITEMS.register("bascinet_no_visor", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_BASCINET_NO_VISOR = ITEMS.register("dark_bascinet_no_visor", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> KETTLE_HELM = ITEMS.register("kettle_helm", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_KETTLE_HELM = ITEMS.register("dark_kettle_helm", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> NASAL_HELM = ITEMS.register("nasal_helm", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_NASAL_HELM = ITEMS.register("dark_nasal_helm", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> VIKING_HELM = ITEMS.register("viking_helm", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_VIKING_HELM = ITEMS.register("dark_viking_helm", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(84), Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> ARMET = ITEMS.register("armet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_ARMET = ITEMS.register("dark_armet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> ARMET_2 = ITEMS.register("armet_2", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_ARMET_2 = ITEMS.register("dark_armet_2", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> BARBUTE = ITEMS.register("barbute", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_BARBUTE = ITEMS.register("dark_barbute", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> BASCINET = ITEMS.register("bascinet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_BASCINET = ITEMS.register("dark_bascinet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> CAGE = ITEMS.register("cage", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_CAGE = ITEMS.register("dark_cage", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> CAGE_2 = ITEMS.register("cage_2", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_CAGE_2 = ITEMS.register("dark_cage_2", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> FLAT_BASCINET = ITEMS.register("flat_bascinet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_FLAT_BASCINET = ITEMS.register("dark_flat_bascinet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> GREAT_HELM = ITEMS.register("great_helm", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(192), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_GREAT_HELM = ITEMS.register("dark_great_helm", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(192), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> GREAT_HELM_2 = ITEMS.register("great_helm_2", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(192), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_GREAT_HELM_2 = ITEMS.register("dark_great_helm_2", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(192), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> SALLET = ITEMS.register("sallet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_SALLET = ITEMS.register("dark_sallet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(132), true, Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> FROGMOUTH = ITEMS.register("frogmouth", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(272), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_FROGMOUTH = ITEMS.register("dark_frogmouth", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(272), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> GREAT_ARMET = ITEMS.register("great_armet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_GREAT_ARMET = ITEMS.register("dark_great_armet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> GREAT_ARMET_2 = ITEMS.register("great_armet_2", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_GREAT_ARMET_2 = ITEMS.register("dark_great_armet_2", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> GREAT_BASCINET = ITEMS.register("great_bascinet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_GREAT_BASCINET = ITEMS.register("dark_great_bascinet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> GREAT_HOUNDSKUL_BASCINET = ITEMS.register("great_houndskul_bascinet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_GREAT_HOUNDSKUL_BASCINET = ITEMS.register("dark_great_houndskul_bascinet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> MAXIMILLIAN_HELMET = ITEMS.register("maximillian_helmet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_MAXIMILLIAN_HELMET = ITEMS.register("dark_maximillian_helmet", () ->
            new KHHelmetAccessory(new Item.Settings().maxCount(1).maxDamage(180), true, Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> GAUNTLET = ITEMS.register("gauntlet", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(69), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_GAUNTLET = ITEMS.register("dark_gauntlet", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(69), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> BRIGANDINE_REREBRACE = ITEMS.register("brigandine_rerebrace", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(77), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> DARK_BRIGANDINE_REREBRACE = ITEMS.register("dark_brigandine_rerebrace", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(77), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> PLATE_REREBRACE = ITEMS.register("plate_rerebrace", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(89), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_PLATE_REREBRACE = ITEMS.register("dark_plate_rerebrace", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(89), Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> BRIGANDINE_CHAUSSES = ITEMS.register("brigandine_chausses", () ->
            new KHDyeableLeggingsAccessory(new Item.Settings().maxCount(1).maxDamage(144), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> DARK_BRIGANDINE_CHAUSSES = ITEMS.register("dark_brigandine_chausses", () ->
            new KHDyeableLeggingsAccessory(new Item.Settings().maxCount(1).maxDamage(144), true, 10511680, Ingredient.ofItems(Items.IRON_INGOT, Items.LEATHER)));
    public static final RegistrySupplier<Item> PLATE_CUISSES = ITEMS.register("plate_cuisses", () ->
            new KHLeggingsAccessory(new Item.Settings().maxCount(1).maxDamage(156), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_PLATE_CUISSES = ITEMS.register("dark_plate_cuisses", () ->
            new KHLeggingsAccessory(new Item.Settings().maxCount(1).maxDamage(156), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> GREAVES = ITEMS.register("greaves", () ->
            new KHLeggingsAccessory(new Item.Settings().maxCount(1).maxDamage(69), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_GREAVES = ITEMS.register("dark_greaves", () ->
            new KHLeggingsAccessory(new Item.Settings().maxCount(1).maxDamage(69), Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> SABATONS = ITEMS.register("sabatons", () ->
            new KHBootsAccessory(new Item.Settings().maxCount(1).maxDamage(69), Ingredient.ofItems(Items.IRON_INGOT)));
    public static final RegistrySupplier<Item> DARK_SABATONS = ITEMS.register("dark_sabatons", () ->
            new KHBootsAccessory(new Item.Settings().maxCount(1).maxDamage(69), Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> AVENTAIL = ITEMS.register("aventail", () ->
            new KHChestplateAccessory(new Item.Settings().maxCount(1).maxDamage(92), Ingredient.ofItems(Items.IRON_INGOT)));

    public static final RegistrySupplier<Item> RIM_GUARDS = ITEMS.register("rim_guards", () ->
            new Item(new Item.Settings().maxCount(1)));

    public static final RegistrySupplier<Item> BESAGEWS = ITEMS.register("besagews", () ->
            new Item(new Item.Settings().maxCount(1)));

    public static final RegistrySupplier<Item> SURCOAT = ITEMS.register("surcoat", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1), Ingredient.ofItems(Items.LEATHER)));
    public static final RegistrySupplier<Item> SURCOAT_SLEEVELESS = ITEMS.register("surcoat_sleeveless", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1), Ingredient.ofItems(Items.LEATHER)));
    public static final RegistrySupplier<Item> CIVILIAN_SURCOAT = ITEMS.register("civilian_surcoat", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1), Ingredient.ofItems(Items.LEATHER)));
    public static final RegistrySupplier<Item> GIORNEA = ITEMS.register("giornea", () ->
            new KHDyeableChestplateAccessory(new Item.Settings().maxCount(1), Ingredient.ofItems(Items.LEATHER)));

    public static final RegistrySupplier<Item> CLOAK = ITEMS.register("cloak", () ->
            new KHCloak(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TORN_CLOAK = ITEMS.register("torn_cloak", () ->
            new KHCloak(new Item.Settings().maxCount(1)));

    public static final RegistrySupplier<Item> HOOD = ITEMS.register("hood", () ->
            new KHCloak(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TORN_HOOD = ITEMS.register("torn_hood", () ->
            new KHCloak(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> JESTER_HOOD = ITEMS.register("jester_hood", () ->
            new KHCloak(new Item.Settings().maxCount(1), true));
    public static final RegistrySupplier<Item> HELMET_HOOD = ITEMS.register("helmet_hood", () ->
            new KHCloak(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> HELMET_TORN_HOOD = ITEMS.register("helmet_torn_hood", () ->
            new KHCloak(new Item.Settings().maxCount(1)));

    public static final RegistrySupplier<Item> LONGBOW = ITEMS.register("longbow", () ->
            new Item(new Item.Settings().maxCount(1).maxDamage(512)));

    public static final RegistrySupplier<Item> HEAVY_CROSSBOW = ITEMS.register("heavy_crossbow", () ->
            new HeavyCrossbow(new Item.Settings().maxCount(1).maxDamage(512)));

    public static final RegistrySupplier<Item> ARQUEBUS = ITEMS.register("arquebus", () ->
            new Arquebus(new Item.Settings().maxCount(1).maxDamage(512)));

    public static final RegistrySupplier<Item> HANDGONNE = ITEMS.register("handgonne", () ->
            new Handgonne(new Item.Settings().maxCount(1).maxDamage(512)));

    public static final RegistrySupplier<Item> SWALLOWTAIL_ARROW = ITEMS.register("swallowtail_arrow", () ->
            new KHExtendedArrowItem(new Item.Settings(), KHSwallowTailArrowEntity::new));
    public static final RegistrySupplier<Item> BODKIN_ARROW = ITEMS.register("bodkin_arrow", () ->
            new KHExtendedArrowItem(new Item.Settings(), KHBodkinArrowEntity::new));
    public static final RegistrySupplier<Item> BROADHEAD_ARROW = ITEMS.register("broadhead_arrow", () ->
            new KHExtendedArrowItem(new Item.Settings(), KHBroadheadArrowEntity::new));
    public static final RegistrySupplier<Item> CLOTH_ARROW = ITEMS.register("cloth_arrow", () ->
            new ClothArrow(new Item.Settings().maxCount(6), KHClothArrowEntity::new));

    public static final RegistrySupplier<Item> HORSE_BARDING = ITEMS.register("horse_barding", () ->
            new HorseBardingArmorItem(7, new Item.Settings().maxCount(1)));

    public static final RegistrySupplier<Item> DARK_HORSE_BARDING = ITEMS.register("dark_horse_barding", () ->
            new HorseBardingArmorItem(7, new Item.Settings().maxCount(1)));

    public static final RegistrySupplier<Item> PLUME = ITEMS.register("plume", () ->
            new DyeableItems(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TRI_PLUME = ITEMS.register("tri_plume", () ->
            new DyeableItems(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> FLUFFY_PLUME = ITEMS.register("fluffy_plume", () ->
            new DyeableItems(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> JOUSTING_BAND = ITEMS.register("jousting_band", () ->
            new TwoLayerDyeableItem(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TEUTONIC_SNAKES = ITEMS.register("teutonic_snakes", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TEUTONIC_BLACK_SNAKES = ITEMS.register("teutonic_black_snakes", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> GOLD_HORNS = ITEMS.register("gold_horns", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> BLACK_HORNS = ITEMS.register("black_horns", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TEUTONIC_GOLD_WINGS = ITEMS.register("teutonic_gold_wings", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TEUTONIC_BLACK_WINGS = ITEMS.register("teutonic_black_wings", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TEUTONIC_WINGS_BALL_ENDS = ITEMS.register("teutonic_wings_ball_ends", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> TEUTONIC_WINGS_SHARP_ENDS = ITEMS.register("teutonic_wings_sharp_ends", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> DRAGON = ITEMS.register("dragon", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> LION = ITEMS.register("lion", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> SNAKE = ITEMS.register("snake", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> UNICORN = ITEMS.register("unicorn", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> STAG = ITEMS.register("stag", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> BOAR = ITEMS.register("boar", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> EAGLE = ITEMS.register("eagle", () ->
            new Item(new Item.Settings().maxCount(1)));
    public static final RegistrySupplier<Item> PEGASUS = ITEMS.register("pegasus", () ->
            new Item(new Item.Settings().maxCount(1)));

    public static final RegistrySupplier<Item> CHAPERON = ITEMS.register("chaperon", () ->
            new KHChaperon(new Item.Settings().maxCount(1), false));
    public static final RegistrySupplier<Item> GILDED_CHAPERON = ITEMS.register("gilded_chaperon", () ->
            new KHChaperon(new Item.Settings().maxCount(1), true));

    public static final RegistrySupplier<Item> DARKENED_ARMOR_TEMPLATE = ITEMS.register("darkened_armor_template", () ->
            new Item(new Item.Settings()));

    public static void registerItems() {
        ITEMS.register();
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
