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
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

public interface ModItems {
    DeferredRegister<Item> ITEMS = DeferredRegister.create(KnightsHeraldry.MOD_ID, Registries.ITEM);

    RegistrySupplier<Item> DAGGER = weapon("dagger", -2F, 196);

    RegistrySupplier<Item> STILETTO = weapon("stiletto", -2F, 157);

    RegistrySupplier<Item> RAPIER = weapon("rapier", -2.2F, 209);

    RegistrySupplier<Item> SWORD = weapon("sword", -2.4F, 326);
    RegistrySupplier<Item> V_SWORD = weapon("v_sword", -2.4F, 326);
    RegistrySupplier<Item> ARMING_SWORD = weapon("arming_sword", -2.4F, 326);

    RegistrySupplier<Item> AXE = ITEMS.register("axe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Properties().defaultDurability(391)));
    RegistrySupplier<Item> BROAD_AXE = ITEMS.register("broad_axe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Properties().defaultDurability(391)));
    RegistrySupplier<Item> CROOKED_AXE = ITEMS.register("crooked_axe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Properties().defaultDurability(391)));
    RegistrySupplier<Item> STRAIGHT_CROOKED_AXE = ITEMS.register("straight_crooked_axe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Properties().defaultDurability(391)));

    RegistrySupplier<Item> MACE = weapon("mace", -3.0F, 430);
    RegistrySupplier<Item> SPIKED_MACE = weapon("spiked_mace", -3.0F, 430);

    RegistrySupplier<Item> FLAIL = ITEMS.register("flail", () ->
            new Flail(-2.8F, new Item.Properties().defaultDurability(261)));
    RegistrySupplier<Item> BALL_FLAIL = ITEMS.register("ball_flail", () ->
            new Flail(-2.8F, new Item.Properties().defaultDurability(261)));

    RegistrySupplier<Item> HAMMER = weapon("hammer", -2.8F, 430);
    RegistrySupplier<Item> WAR_HAMMER = weapon("war_hammer", -2.8F, 430);

    RegistrySupplier<Item> LONGSWORD = weapon("longsword", -2.5F, 365);
    RegistrySupplier<Item> V_LONGSWORD = weapon("v_longsword", -2.5F, 365);

    RegistrySupplier<Item> FALCHION = weapon("falchion", -2.5F, 365);
    RegistrySupplier<Item> SCIMITAR = weapon("scimitar", -2.5F, 365);

    RegistrySupplier<Item> PITCHFORK = weapon("pitchfork", -2.6F, 235);

    RegistrySupplier<Item> SPEAR = weapon("spear", -2.4F, 235);

    RegistrySupplier<Item> PIKE = weapon("pike", -2.8F, 196);

    RegistrySupplier<Item> BILLHOOK = ITEMS.register("billhook", () ->
            new Billhook(-2.8F, new Item.Properties().defaultDurability(391)));

    RegistrySupplier<Item> GLAIVE = weapon("glaive", -2.6F, 391);
    RegistrySupplier<Item> CURVED_GLAIVE = weapon("curved_glaive", -2.6F, 391);

    RegistrySupplier<Item> HALBERD = ITEMS.register("halberd", () ->
            new Halberd(-2.8F, new Item.Properties().defaultDurability(391)));

    RegistrySupplier<Item> LANCE = ITEMS.register("lance", () ->
            new Lance(-3.0F, new Item.Properties().defaultDurability(1), SCDamageCalculator.DamageType.PIERCING));
    RegistrySupplier<Item> WOODEN_LANCE = ITEMS.register("wooden_lance", () ->
            new WoodenLance(-3.0F, new Item.Properties().defaultDurability(1), SCDamageCalculator.DamageType.PIERCING));

    RegistrySupplier<Item> POLEAXE = ITEMS.register("poleaxe", () ->
            new AxeItem(ModToolMaterials.WEAPONS, 1, -2.8F, new Item.Properties().defaultDurability(391)));

    RegistrySupplier<Item> POLEHAMMER = weapon("polehammer", -2.8F, 391);
    RegistrySupplier<Item> BEC_DE_CORBIN = weapon("bec_de_corbin", -2.8F, 391);

    RegistrySupplier<Item> MORNING_STAR = weapon("morning_star", -3.1F, 391);

    RegistrySupplier<Item> BARDICHE = weapon("bardiche", -3.0F, 391);

    RegistrySupplier<Item> GREATSWORD = weapon("greatsword", -3.2F, 391);
    RegistrySupplier<Item> CLAYMORE = weapon("claymore", -3.2F, 391);
    RegistrySupplier<Item> FLAMBERGE = weapon("flamberge", -3.2F, 391);
    RegistrySupplier<Item> ZWEIHANDER = weapon("zweihander", -3.2F, 391);

    RegistrySupplier<Item> WARDART = ITEMS.register("wardart", () ->
            new WarDart(-2.4F, new Item.Properties().defaultDurability(326)));


    RegistrySupplier<Item> QUILTED_COIF = ITEMS.register("quilted_coif", () ->
            new SCDyeableUnderArmor(new Item.Properties(), ModArmorMaterials.GAMBESON, ArmorItem.Type.HELMET, 10511680));
    RegistrySupplier<Item> GAMBESON = ITEMS.register("gambeson", () ->
            new SCDyeableUnderArmor(new Item.Properties(), ModArmorMaterials.GAMBESON, ArmorItem.Type.CHESTPLATE, 10511680));
    RegistrySupplier<Item> GAMBESON_BREECHES = ITEMS.register("gambeson_breeches", () ->
            new SCDyeableUnderArmor(new Item.Properties(), ModArmorMaterials.GAMBESON, ArmorItem.Type.LEGGINGS, 10511680));
    RegistrySupplier<Item> GAMBESON_BOOTS = ITEMS.register("gambeson_boots", () ->
            new SCDyeableUnderArmor(new Item.Properties(), ModArmorMaterials.GAMBESON, ArmorItem.Type.BOOTS, 10511680));

    RegistrySupplier<Item> MAIL_COIF = ITEMS.register("mail_coif", () ->
            new SCUnderArmor(new Item.Properties(), ModArmorMaterials.MAIL, ArmorItem.Type.HELMET));
    RegistrySupplier<Item> HAUBERK = ITEMS.register("hauberk", () ->
            new SCUnderArmor(new Item.Properties(), ModArmorMaterials.MAIL, ArmorItem.Type.CHESTPLATE));
    RegistrySupplier<Item> MAIL_BREECHES = ITEMS.register("mail_breeches", () ->
            new SCUnderArmor(new Item.Properties(), ModArmorMaterials.MAIL, ArmorItem.Type.LEGGINGS));
    RegistrySupplier<Item> MAIL_BOOTS = ITEMS.register("mail_boots", () ->
            new SCUnderArmor(new Item.Properties(), ModArmorMaterials.MAIL, ArmorItem.Type.BOOTS));

    RegistrySupplier<Item> MAIL_SPAULDERS = ITEMS.register("mail_spaulders", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(96), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_MAIL_SPAULDERS = ITEMS.register("golden_mail_spaulders", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(115), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> BRIGANDINE_SPAULDERS = ITEMS.register("brigandine_spaulders", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> DARK_BRIGANDINE_SPAULDERS = ITEMS.register("dark_brigandine_spaulders", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> GOLDEN_BRIGANDINE_SPAULDERS = ITEMS.register("golden_brigandine_spaulders", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> PLATE_SPAULDERS = ITEMS.register("plate_spaulders", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_PLATE_SPAULDERS = ITEMS.register("dark_plate_spaulders", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_PLATE_SPAULDERS = ITEMS.register("golden_plate_spaulders", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> BRIGANDINE = ITEMS.register("brigandine", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(423), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> DARK_BRIGANDINE = ITEMS.register("dark_brigandine", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(465), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> GOLDEN_BRIGANDINE = ITEMS.register("golden_brigandine", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(508), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));

    RegistrySupplier<Item> BRIG_BREASTPLATE = ITEMS.register("brig_breastplate", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(546), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> DARK_BRIG_BREASTPLATE = ITEMS.register("dark_brig_breastplate", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(601), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> GOLDEN_BRIG_BREASTPLATE = ITEMS.register("golden_brig_breastplate", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(655), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> BRIG_BREASTPLATE_TASSETS = ITEMS.register("brig_breastplate_tassets", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(546), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> DARK_BRIG_BREASTPLATE_TASSETS = ITEMS.register("dark_brig_breastplate_tassets", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(601), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> GOLDEN_BRIG_BREASTPLATE_TASSETS = ITEMS.register("golden_brig_breastplate_tassets", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(655), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));

    RegistrySupplier<Item> PLATE_CUIRASS = ITEMS.register("plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_PLATE_CUIRASS = ITEMS.register("dark_plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_PLATE_CUIRASS = ITEMS.register("golden_plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> PLATE_CUIRASS_TASSETS = ITEMS.register("plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_PLATE_CUIRASS_TASSETS = ITEMS.register("dark_plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_PLATE_CUIRASS_TASSETS = ITEMS.register("golden_plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> MAXIMILLIAN_CUIRASS = ITEMS.register("maximillian_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_MAXIMILLIAN_CUIRASS = ITEMS.register("dark_maximillian_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_MAXIMILLIAN_CUIRASS = ITEMS.register("golden_maximillian_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> MAXIMILLIAN_CUIRASS_TASSETS = ITEMS.register("maximillian_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_MAXIMILLIAN_CUIRASS_TASSETS = ITEMS.register("dark_maximillian_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS = ITEMS.register("golden_maximillian_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> XIIII_PLATE_CUIRASS = ITEMS.register("xiiii_plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_XIIII_PLATE_CUIRASS = ITEMS.register("dark_xiiii_plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_XIIII_PLATE_CUIRASS = ITEMS.register("golden_xiiii_plate_cuirass", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> XIIII_PLATE_CUIRASS_TASSETS = ITEMS.register("xiiii_plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_XIIII_PLATE_CUIRASS_TASSETS = ITEMS.register("dark_xiiii_plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_XIIII_PLATE_CUIRASS_TASSETS = ITEMS.register("golden_xiiii_plate_cuirass_tassets", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> XIIII_PLATE_BREASTPLATE = ITEMS.register("xiiii_plate_breastplate", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_XIIII_PLATE_BREASTPLATE = ITEMS.register("dark_xiiii_plate_breastplate", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_XIIII_PLATE_BREASTPLATE = ITEMS.register("golden_xiiii_plate_breastplate", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> BARBUTE = ITEMS.register("barbute", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_BARBUTE = ITEMS.register("dark_barbute", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_BARBUTE = ITEMS.register("golden_barbute", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> BASCINET = ITEMS.register("bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_BASCINET = ITEMS.register("dark_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_BASCINET = ITEMS.register("golden_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> KETTLE_HELM = ITEMS.register("kettle_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_KETTLE_HELM = ITEMS.register("dark_kettle_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_KETTLE_HELM = ITEMS.register("golden_kettle_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> NASAL_HELM = ITEMS.register("nasal_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_NASAL_HELM = ITEMS.register("dark_nasal_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_NASAL_HELM = ITEMS.register("golden_nasal_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> VIKING_HELM = ITEMS.register("viking_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_VIKING_HELM = ITEMS.register("dark_viking_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_VIKING_HELM = ITEMS.register("golden_viking_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> BURGONET = ITEMS.register("burgonet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_BURGONET = ITEMS.register("dark_burgonet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_BURGONET = ITEMS.register("golden_burgonet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> ARMET = ITEMS.register("armet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_ARMET = ITEMS.register("dark_armet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_ARMET = ITEMS.register("golden_armet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> ARMET_2 = ITEMS.register("armet_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_ARMET_2 = ITEMS.register("dark_armet_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_ARMET_2 = ITEMS.register("golden_armet_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> VISORED_BARBUTE = ITEMS.register("visored_barbute", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_VISORED_BARBUTE = ITEMS.register("dark_visored_barbute", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_VISORED_BARBUTE = ITEMS.register("golden_visored_barbute", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> HOUNDSKULL = ITEMS.register("houndskull", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_HOUNDSKULL = ITEMS.register("dark_houndskull", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_HOUNDSKULL = ITEMS.register("golden_houndskull", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> CAGE = ITEMS.register("cage", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_CAGE = ITEMS.register("dark_cage", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_CAGE = ITEMS.register("golden_cage", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> CAGE_2 = ITEMS.register("cage_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_CAGE_2 = ITEMS.register("dark_cage_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_CAGE_2 = ITEMS.register("golden_cage_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> VISORED_BASCINET = ITEMS.register("visored_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_VISORED_BASCINET = ITEMS.register("dark_visored_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_VISORED_BASCINET = ITEMS.register("golden_visored_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> GREAT_HELM = ITEMS.register("great_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(250), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_GREAT_HELM = ITEMS.register("dark_great_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(275), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_GREAT_HELM = ITEMS.register("golden_great_helm", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(300), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> GREAT_HELM_2 = ITEMS.register("great_helm_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(250), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_GREAT_HELM_2 = ITEMS.register("dark_great_helm_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(275), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_GREAT_HELM_2 = ITEMS.register("golden_great_helm_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(300), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> SALLET = ITEMS.register("sallet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_SALLET = ITEMS.register("dark_sallet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_SALLET = ITEMS.register("golden_sallet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> BURGONET_FALLING_BUFFE = ITEMS.register("burgonet_falling_buffe", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_BURGONET_FALLING_BUFFE = ITEMS.register("dark_burgonet_falling_buffe", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_BURGONET_FALLING_BUFFE = ITEMS.register("golden_burgonet_falling_buffe", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> CLOSE_HELM = ITEMS.register("close_helm", () ->
            new KHCloseHelmet(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_CLOSE_HELM = ITEMS.register("dark_close_helm", () ->
            new KHCloseHelmet(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_CLOSE_HELM = ITEMS.register("golden_close_helm", () ->
            new KHCloseHelmet(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> FROGMOUTH = ITEMS.register("frogmouth", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(354), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_FROGMOUTH = ITEMS.register("dark_frogmouth", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(389), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_FROGMOUTH = ITEMS.register("golden_frogmouth", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(425), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> GREAT_ARMET = ITEMS.register("great_armet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_GREAT_ARMET = ITEMS.register("dark_great_armet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_GREAT_ARMET = ITEMS.register("golden_great_armet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> GREAT_ARMET_2 = ITEMS.register("great_armet_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_GREAT_ARMET_2 = ITEMS.register("dark_great_armet_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_GREAT_ARMET_2 = ITEMS.register("golden_great_armet_2", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> GREAT_BASCINET = ITEMS.register("great_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_GREAT_BASCINET = ITEMS.register("dark_great_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_GREAT_BASCINET = ITEMS.register("golden_great_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> GREAT_HOUNDSKUL_BASCINET = ITEMS.register("great_houndskul_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_GREAT_HOUNDSKUL_BASCINET = ITEMS.register("dark_great_houndskul_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_GREAT_HOUNDSKUL_BASCINET = ITEMS.register("golden_great_houndskul_bascinet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> MAXIMILLIAN_HELMET = ITEMS.register("maximillian_helmet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_MAXIMILLIAN_HELMET = ITEMS.register("dark_maximillian_helmet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_MAXIMILLIAN_HELMET = ITEMS.register("golden_maximillian_helmet", () ->
            new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> SAVOYARD = ITEMS.register("savoyard", () ->
            new KHSavoyard(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_SAVOYARD = ITEMS.register("dark_savoyard", () ->
            new KHSavoyard(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_SAVOYARD = ITEMS.register("golden_savoyard", () ->
            new KHSavoyard(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> GAUNTLET = ITEMS.register("gauntlet", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(90), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_GAUNTLET = ITEMS.register("dark_gauntlet", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(99), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_GAUNTLET = ITEMS.register("golden_gauntlet", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(108), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> BRIGANDINE_HARNESS = ITEMS.register("brigandine_harness", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(100), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> DARK_BRIGANDINE_HARNESS = ITEMS.register("dark_brigandine_harness", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(110), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> GOLDEN_BRIGANDINE_HARNESS = ITEMS.register("golden_brigandine_harness", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> PLATE_HARNESS = ITEMS.register("plate_harness", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(116), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_PLATE_HARNESS = ITEMS.register("dark_plate_harness", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(128), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_PLATE_HARNESS = ITEMS.register("golden_plate_harness", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(139), Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> BRIGANDINE_CUISSES = ITEMS.register("brigandine_cuisses", () ->
            new KHDyeableLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(187), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> DARK_BRIGANDINE_CUISSES = ITEMS.register("dark_brigandine_cuisses", () ->
            new KHDyeableLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> GOLDEN_BRIGANDINE_CUISSES = ITEMS.register("golden_brigandine_cuisses", () ->
            new KHDyeableLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(224), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistrySupplier<Item> PLATE_CUISSES = ITEMS.register("plate_cuisses", () ->
            new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(203), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_PLATE_CUISSES = ITEMS.register("dark_plate_cuisses", () ->
            new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(223), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_PLATE_CUISSES = ITEMS.register("golden_plate_cuisses", () ->
            new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(244), Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> GREAVES = ITEMS.register("greaves", () ->
            new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(90), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_GREAVES = ITEMS.register("dark_greaves", () ->
            new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(99), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_GREAVES = ITEMS.register("golden_greaves", () ->
            new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(108), Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> SABATONS = ITEMS.register("sabatons", () ->
            new KHBootsAccessory(new Item.Properties().stacksTo(1).defaultDurability(90), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_SABATONS = ITEMS.register("dark_sabatons", () ->
            new KHBootsAccessory(new Item.Properties().stacksTo(1).defaultDurability(99), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_SABATONS = ITEMS.register("golden_sabatons", () ->
            new KHBootsAccessory(new Item.Properties().stacksTo(1).defaultDurability(108), Ingredient.of(Items.GOLD_INGOT)));

    RegistrySupplier<Item> BEVOR = ITEMS.register("bevor", () ->
            new KHBevor(new Item.Properties().stacksTo(1).defaultDurability(100), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> DARK_BEVOR = ITEMS.register("dark_bevor", () ->
            new KHBevor(new Item.Properties().stacksTo(1).defaultDurability(110), Ingredient.of(Items.IRON_INGOT)));
    RegistrySupplier<Item> GOLDEN_BEVOR = ITEMS.register("golden_bevor", () ->
            new KHBevor(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.GOLD_INGOT)));
    RegistrySupplier<Item> AVENTAIL = ITEMS.register("aventail", () ->
            new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));

    RegistrySupplier<Item> RIM_GUARDS = ITEMS.register("rim_guards", () -> new Item(new Item.Properties().stacksTo(1)));

    RegistrySupplier<Item> BESAGEWS = ITEMS.register("besagews", () -> new Item(new Item.Properties().stacksTo(1)));

    RegistrySupplier<Item> SURCOAT = ITEMS.register("surcoat", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1), Ingredient.of(Items.LEATHER)));
    RegistrySupplier<Item> SURCOAT_SLEEVELESS = ITEMS.register("surcoat_sleeveless", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1), Ingredient.of(Items.LEATHER)));
    RegistrySupplier<Item> CIVILIAN_SURCOAT = ITEMS.register("civilian_surcoat", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1), Ingredient.of(Items.LEATHER)));
    RegistrySupplier<Item> GIORNEA = ITEMS.register("giornea", () ->
            new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1), Ingredient.of(Items.LEATHER)));

    RegistrySupplier<Item> CLOAK = ITEMS.register("cloak", () ->
            new KHCloak(new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> TORN_CLOAK = ITEMS.register("torn_cloak", () ->
            new KHCloak(new Item.Properties().stacksTo(1)));

    RegistrySupplier<Item> HOOD = ITEMS.register("hood", () ->
            new KHCloak(new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> TORN_HOOD = ITEMS.register("torn_hood", () ->
            new KHCloak(new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> JESTER_HOOD = ITEMS.register("jester_hood", () ->
            new KHCloak(new Item.Properties().stacksTo(1), true));
    RegistrySupplier<Item> HELMET_HOOD = ITEMS.register("helmet_hood", () ->
            new KHCloak(new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> HELMET_TORN_HOOD = ITEMS.register("helmet_torn_hood", () ->
            new KHCloak(new Item.Properties().stacksTo(1)));

    RegistrySupplier<Item> LONGBOW = ITEMS.register("longbow", () ->
            new Item(new Item.Properties().stacksTo(1).defaultDurability(666)));

    RegistrySupplier<Item> HEAVY_CROSSBOW = ITEMS.register("heavy_crossbow", () ->
            new HeavyCrossbow(new Item.Properties().stacksTo(1).defaultDurability(666)));

    RegistrySupplier<Item> ARQUEBUS = ITEMS.register("arquebus", () ->
            new Arquebus(new Item.Properties().stacksTo(1).defaultDurability(666)));

    RegistrySupplier<Item> HANDGONNE = ITEMS.register("handgonne", () ->
            new Handgonne(new Item.Properties().stacksTo(1).defaultDurability(666)));

    RegistrySupplier<Item> SWALLOWTAIL_ARROW = ITEMS.register("swallowtail_arrow", () ->
            new KHExtendedArrowItem(new Item.Properties(), KHSwallowTailArrowEntity::new));
    RegistrySupplier<Item> BODKIN_ARROW = ITEMS.register("bodkin_arrow", () ->
            new KHExtendedArrowItem(new Item.Properties(), KHBodkinArrowEntity::new));
    RegistrySupplier<Item> BROADHEAD_ARROW = ITEMS.register("broadhead_arrow", () ->
            new KHExtendedArrowItem(new Item.Properties(), KHBroadheadArrowEntity::new));
    RegistrySupplier<Item> CLOTH_ARROW = ITEMS.register("cloth_arrow", () ->
            new ClothArrow(new Item.Properties(), KHClothArrowEntity::new));

    RegistrySupplier<Item> HORSE_BARDING = ITEMS.register("horse_barding", () ->
            new HorseBardingArmorItem(7, new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> DARK_HORSE_BARDING = ITEMS.register("dark_horse_barding", () ->
            new HorseBardingArmorItem(7, new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> GOLDEN_HORSE_BARDING = ITEMS.register("golden_horse_barding", () ->
            new HorseBardingArmorItem(7, new Item.Properties().stacksTo(1)));

    RegistrySupplier<Item> PLUME = ITEMS.register("plume", () ->
            new DyeableItems(new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> TRI_PLUME = ITEMS.register("tri_plume", () ->
            new DyeableItems(new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> FLUFFY_PLUME = ITEMS.register("fluffy_plume", () ->
            new DyeableItems(new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> TORSE = ITEMS.register("torse", () ->
            new TwoLayerDyeableItem(new Item.Properties().stacksTo(1)));
    RegistrySupplier<Item> TEUTONIC_SNAKES = deco("teutonic_snakes");
    RegistrySupplier<Item> TEUTONIC_BLACK_SNAKES = deco("teutonic_black_snakes");
    RegistrySupplier<Item> GOLD_HORNS = deco("gold_horns");
    RegistrySupplier<Item> BLACK_HORNS = deco("black_horns");
    RegistrySupplier<Item> TEUTONIC_GOLD_WINGS = deco("teutonic_gold_wings");
    RegistrySupplier<Item> TEUTONIC_BLACK_WINGS = deco("teutonic_black_wings");
    RegistrySupplier<Item> TEUTONIC_WINGS_BALL_ENDS = deco("teutonic_wings_ball_ends");
    RegistrySupplier<Item> TEUTONIC_WINGS_SHARP_ENDS = deco("teutonic_wings_sharp_ends");
    RegistrySupplier<Item> DRAGON = deco("dragon");
    RegistrySupplier<Item> LION = deco("lion");
    RegistrySupplier<Item> SNAKE = deco("snake");
    RegistrySupplier<Item> UNICORN = deco("unicorn");
    RegistrySupplier<Item> STAG = deco("stag");
    RegistrySupplier<Item> BOAR = deco("boar");
    RegistrySupplier<Item> EAGLE = deco("eagle");
    RegistrySupplier<Item> PEGASUS = deco("pegasus");

    RegistrySupplier<Item> CHAPERON = ITEMS.register("chaperon", () ->
            new KHChaperon(new Item.Properties().stacksTo(1), false));
    RegistrySupplier<Item> GILDED_CHAPERON = ITEMS.register("gilded_chaperon", () ->
            new KHChaperon(new Item.Properties().stacksTo(1), true));

    static RegistrySupplier<Item> weapon(String id, float attackSpeed, int durability) {
        return ITEMS.register(id, () -> new SwordItem(ModToolMaterials.WEAPONS, 1, attackSpeed, new Item.Properties().defaultDurability(durability)));
    }

    static RegistrySupplier<Item> deco(String id) {
        return ITEMS.register(id, () -> new Item(new Item.Properties().stacksTo(1)));
    }

    static void registerItems() {
        ITEMS.register();
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}