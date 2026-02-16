package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.*;
import banduty.knightsheraldry.items.armor.accessory.*;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.DyeableItems;
import banduty.knightsheraldry.items.item.KHExtendedArrowItem;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.items.item.khammo.ClothArrow;
import banduty.knightsheraldry.items.item.khrangeweapon.Arquebus;
import banduty.knightsheraldry.items.item.khrangeweapon.Handgonne;
import banduty.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import banduty.knightsheraldry.items.item.khweapon.*;
import banduty.stoneycore.items.armor.underarmor.SCDyeableUnderArmor;
import banduty.stoneycore.items.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.util.SCDamageCalculator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public interface ModItems {
    DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, KnightsHeraldry.MOD_ID);


    RegistryObject<Item> DAGGER = weapon("dagger", -2F, 196);

    RegistryObject<Item> STILETTO = weapon("stiletto", -2F, 157);

    RegistryObject<Item> RAPIER = weapon("rapier", -2.2F, 209);

    RegistryObject<Item> SWORD = weapon("sword", -2.4F, 326);
    RegistryObject<Item> V_SWORD = weapon("v_sword", -2.4F, 326);
    RegistryObject<Item> ARMING_SWORD = weapon("arming_sword", -2.4F, 326);

    RegistryObject<Item> AXE = registerItem("axe",
            () -> new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Properties().defaultDurability(391)));
    RegistryObject<Item> BROAD_AXE = registerItem("broad_axe",
            () -> new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Properties().defaultDurability(391)));
    RegistryObject<Item> CROOKED_AXE = registerItem("crooked_axe",
            () -> new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Properties().defaultDurability(391)));
    RegistryObject<Item> STRAIGHT_CROOKED_AXE = registerItem("straight_crooked_axe",
            () -> new AxeItem(ModToolMaterials.WEAPONS, 1, -2.6F, new Item.Properties().defaultDurability(391)));

    RegistryObject<Item> MACE = weapon("mace", -3.0F, 430);
    RegistryObject<Item> SPIKED_MACE = weapon("spiked_mace", -3.0F, 430);

    RegistryObject<Item> FLAIL = registerItem("flail",
            () -> new Flail(-2.8F, new Item.Properties().defaultDurability(261)));
    RegistryObject<Item> BALL_FLAIL = registerItem("ball_flail",
            () -> new Flail(-2.8F, new Item.Properties().defaultDurability(261)));

    RegistryObject<Item> HAMMER = weapon("hammer", -2.8F, 430);
    RegistryObject<Item> WAR_HAMMER = weapon("war_hammer", -2.8F, 430);

    RegistryObject<Item> LONGSWORD = weapon("longsword", -2.5F, 365);
    RegistryObject<Item> V_LONGSWORD = weapon("v_longsword", -2.5F, 365);

    RegistryObject<Item> FALCHION = weapon("falchion", -2.5F, 365);
    RegistryObject<Item> SCIMITAR = weapon("scimitar", -2.5F, 365);

    RegistryObject<Item> PITCHFORK = weapon("pitchfork", -2.6F, 235);

    RegistryObject<Item> SPEAR = weapon("spear", -2.4F, 235);

    RegistryObject<Item> PIKE = weapon("pike", -2.8F, 196);

    RegistryObject<Item> BILLHOOK = registerItem("billhook",
            () -> new Billhook(-2.8F, new Item.Properties().defaultDurability(391)));

    RegistryObject<Item> GLAIVE = weapon("glaive", -2.6F, 391);
    RegistryObject<Item> CURVED_GLAIVE = weapon("curved_glaive", -2.6F, 391);

    RegistryObject<Item> HALBERD = registerItem("halberd",
            () -> new Halberd(-2.8F, new Item.Properties().defaultDurability(391)));

    RegistryObject<Item> LANCE = registerItem("lance",
            () -> new Lance(-3.0F, new Item.Properties().defaultDurability(1), SCDamageCalculator.DamageType.PIERCING));
    RegistryObject<Item> WOODEN_LANCE = registerItem("wooden_lance",
            () -> new WoodenLance(-3.0F, new Item.Properties().defaultDurability(1), SCDamageCalculator.DamageType.PIERCING));

    RegistryObject<Item> POLEAXE = registerItem("poleaxe",
            () -> new AxeItem(ModToolMaterials.WEAPONS, 1, -2.8F, new Item.Properties().defaultDurability(391)));

    RegistryObject<Item> POLEHAMMER = weapon("polehammer", -2.8F, 391);
    RegistryObject<Item> BEC_DE_CORBIN = weapon("bec_de_corbin", -2.8F, 391);

    RegistryObject<Item> MORNING_STAR = weapon("morning_star", -3.1F, 391);

    RegistryObject<Item> BARDICHE = weapon("bardiche", -3.0F, 391);

    RegistryObject<Item> GREATSWORD = weapon("greatsword", -3.2F, 391);
    RegistryObject<Item> CLAYMORE = weapon("claymore", -3.2F, 391);
    RegistryObject<Item> FLAMBERGE = weapon("flamberge", -3.2F, 391);
    RegistryObject<Item> ZWEIHANDER = weapon("zweihander", -3.2F, 391);

    RegistryObject<Item> WARDART = registerItem("wardart",
            () -> new WarDart(-2.4F, new Item.Properties().defaultDurability(326)));


    RegistryObject<Item> QUILTED_COIF = registerItem("quilted_coif",
            () -> new SCDyeableUnderArmor(new Item.Properties(), ModArmorMaterials.GAMBESON, ArmorItem.Type.HELMET, 10511680));
    RegistryObject<Item> GAMBESON = registerItem("gambeson",
            () -> new SCDyeableUnderArmor(new Item.Properties(), ModArmorMaterials.GAMBESON, ArmorItem.Type.CHESTPLATE, 10511680));
    RegistryObject<Item> GAMBESON_BREECHES = registerItem("gambeson_breeches",
            () -> new SCDyeableUnderArmor(new Item.Properties(), ModArmorMaterials.GAMBESON, ArmorItem.Type.LEGGINGS, 10511680));
    RegistryObject<Item> GAMBESON_BOOTS = registerItem("gambeson_boots",
            () -> new SCDyeableUnderArmor(new Item.Properties(), ModArmorMaterials.GAMBESON, ArmorItem.Type.BOOTS, 10511680));

    RegistryObject<Item> MAIL_COIF = registerItem("mail_coif",
            () -> new SCUnderArmor(new Item.Properties(), ModArmorMaterials.MAIL, ArmorItem.Type.HELMET));
    RegistryObject<Item> HAUBERK = registerItem("hauberk",
            () -> new SCUnderArmor(new Item.Properties(), ModArmorMaterials.MAIL, ArmorItem.Type.CHESTPLATE));
    RegistryObject<Item> MAIL_BREECHES = registerItem("mail_breeches",
            () -> new SCUnderArmor(new Item.Properties(), ModArmorMaterials.MAIL, ArmorItem.Type.LEGGINGS));
    RegistryObject<Item> MAIL_BOOTS = registerItem("mail_boots",
            () -> new SCUnderArmor(new Item.Properties(), ModArmorMaterials.MAIL, ArmorItem.Type.BOOTS));

    RegistryObject<Item> MAIL_SPAULDERS = registerItem("mail_spaulders",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(96), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> MAIL_SPAULDERS_BESAGEWS = registerItem("mail_spaulders_besagews",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(96), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_MAIL_SPAULDERS = registerItem("golden_mail_spaulders",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(115), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GOLDEN_MAIL_SPAULDERS_BESAGEWS = registerItem("golden_mail_spaulders_besagews",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(115), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> BRIGANDINE_SPAULDERS = registerItem("brigandine_spaulders",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> BRIGANDINE_SPAULDERS_BESAGEWS = registerItem("brigandine_spaulders_besagews",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> DARK_BRIGANDINE_SPAULDERS = registerItem("dark_brigandine_spaulders",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> DARK_BRIGANDINE_SPAULDERS_BESAGEWS = registerItem("dark_brigandine_spaulders_besagews",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> GOLDEN_BRIGANDINE_SPAULDERS = registerItem("golden_brigandine_spaulders",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistryObject<Item> GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS = registerItem("golden_brigandine_spaulders_besagews",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistryObject<Item> PLATE_SPAULDERS = registerItem("plate_spaulders",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> PLATE_SPAULDERS_BESAGEWS = registerItem("plate_spaulders_besagews",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> PLATE_SPAULDERS_RIMMED = registerItem("plate_spaulders_rimmed",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> PLATE_SPAULDERS_BESAGEWS_RIMMED = registerItem("plate_spaulders_besagews_rimmed",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_PLATE_SPAULDERS = registerItem("dark_plate_spaulders",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_PLATE_SPAULDERS_BESAGEWS = registerItem("dark_plate_spaulders_besagews",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_PLATE_SPAULDERS_RIMMED = registerItem("dark_plate_spaulders_rimmed",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED = registerItem("dark_plate_spaulders_besagews_rimmed",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_PLATE_SPAULDERS = registerItem("golden_plate_spaulders",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GOLDEN_PLATE_SPAULDERS_BESAGEWS = registerItem("golden_plate_spaulders_besagews",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GOLDEN_PLATE_SPAULDERS_RIMMED = registerItem("golden_plate_spaulders_rimmed",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED = registerItem("golden_plate_spaulders_besagews_rimmed",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> BRIGANDINE = registerItem("brigandine",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(423), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> DARK_BRIGANDINE = registerItem("dark_brigandine",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(465), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> GOLDEN_BRIGANDINE = registerItem("golden_brigandine",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(508), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));

    RegistryObject<Item> BRIG_BREASTPLATE = registerItem("brig_breastplate",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(546), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> DARK_BRIG_BREASTPLATE = registerItem("dark_brig_breastplate",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(601), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> GOLDEN_BRIG_BREASTPLATE = registerItem("golden_brig_breastplate",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(655), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistryObject<Item> BRIG_BREASTPLATE_TASSETS = registerItem("brig_breastplate_tassets",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(546), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> DARK_BRIG_BREASTPLATE_TASSETS = registerItem("dark_brig_breastplate_tassets",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(601), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> GOLDEN_BRIG_BREASTPLATE_TASSETS = registerItem("golden_brig_breastplate_tassets",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(655), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));

    RegistryObject<Item> PLATE_CUIRASS = registerItem("plate_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_PLATE_CUIRASS = registerItem("dark_plate_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_PLATE_CUIRASS = registerItem("golden_plate_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> PLATE_CUIRASS_TASSETS = registerItem("plate_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_PLATE_CUIRASS_TASSETS = registerItem("dark_plate_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_PLATE_CUIRASS_TASSETS = registerItem("golden_plate_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> MAXIMILLIAN_CUIRASS = registerItem("maximillian_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_MAXIMILLIAN_CUIRASS = registerItem("dark_maximillian_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_MAXIMILLIAN_CUIRASS = registerItem("golden_maximillian_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> MAXIMILLIAN_CUIRASS_TASSETS = registerItem("maximillian_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_MAXIMILLIAN_CUIRASS_TASSETS = registerItem("dark_maximillian_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_MAXIMILLIAN_CUIRASS_TASSETS = registerItem("golden_maximillian_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> XIIII_PLATE_CUIRASS = registerItem("xiiii_plate_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_XIIII_PLATE_CUIRASS = registerItem("dark_xiiii_plate_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_XIIII_PLATE_CUIRASS = registerItem("golden_xiiii_plate_cuirass",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> XIIII_PLATE_CUIRASS_TASSETS = registerItem("xiiii_plate_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_XIIII_PLATE_CUIRASS_TASSETS = registerItem("dark_xiiii_plate_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_XIIII_PLATE_CUIRASS_TASSETS = registerItem("golden_xiiii_plate_cuirass_tassets",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> XIIII_PLATE_BREASTPLATE = registerItem("xiiii_plate_breastplate",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(650), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_XIIII_PLATE_BREASTPLATE = registerItem("dark_xiiii_plate_breastplate",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(715), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_XIIII_PLATE_BREASTPLATE = registerItem("golden_xiiii_plate_breastplate",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(780), Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> BARBUTE = registerItem("barbute",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_BARBUTE = registerItem("dark_barbute",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_BARBUTE = registerItem("golden_barbute",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> BASCINET = registerItem("bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_BASCINET = registerItem("dark_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_BASCINET = registerItem("golden_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> KETTLE_HELM = registerItem("kettle_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_KETTLE_HELM = registerItem("dark_kettle_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_KETTLE_HELM = registerItem("golden_kettle_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> NASAL_HELM = registerItem("nasal_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_NASAL_HELM = registerItem("dark_nasal_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_NASAL_HELM = registerItem("golden_nasal_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> VIKING_HELM = registerItem("viking_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_VIKING_HELM = registerItem("dark_viking_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_VIKING_HELM = registerItem("golden_viking_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> BURGONET = registerItem("burgonet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(109), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_BURGONET = registerItem("dark_burgonet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_BURGONET = registerItem("golden_burgonet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(131), Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> ARMET = registerItem("armet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_ARMET = registerItem("dark_armet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_ARMET = registerItem("golden_armet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> ARMET_2 = registerItem("armet_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_ARMET_2 = registerItem("dark_armet_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_ARMET_2 = registerItem("golden_armet_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> VISORED_BARBUTE = registerItem("visored_barbute",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_VISORED_BARBUTE = registerItem("dark_visored_barbute",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_VISORED_BARBUTE = registerItem("golden_visored_barbute",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> HOUNDSKULL = registerItem("houndskull",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_HOUNDSKULL = registerItem("dark_houndskull",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_HOUNDSKULL = registerItem("golden_houndskull",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> CAGE = registerItem("cage",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_CAGE = registerItem("dark_cage",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_CAGE = registerItem("golden_cage",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> CAGE_2 = registerItem("cage_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_CAGE_2 = registerItem("dark_cage_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_CAGE_2 = registerItem("golden_cage_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> VISORED_BASCINET = registerItem("visored_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_VISORED_BASCINET = registerItem("dark_visored_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_VISORED_BASCINET = registerItem("golden_visored_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GREAT_HELM = registerItem("great_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(250), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_GREAT_HELM = registerItem("dark_great_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(275), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_GREAT_HELM = registerItem("golden_great_helm",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(300), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GREAT_HELM_2 = registerItem("great_helm_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(250), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_GREAT_HELM_2 = registerItem("dark_great_helm_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(275), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_GREAT_HELM_2 = registerItem("golden_great_helm_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(300), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> SALLET = registerItem("sallet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_SALLET = registerItem("dark_sallet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_SALLET = registerItem("golden_sallet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> BURGONET_FALLING_BUFFE = registerItem("burgonet_falling_buffe",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_BURGONET_FALLING_BUFFE = registerItem("dark_burgonet_falling_buffe",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_BURGONET_FALLING_BUFFE = registerItem("golden_burgonet_falling_buffe",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> CLOSE_HELM = registerItem("close_helm",
            () -> new KHCloseHelmet(new Item.Properties().stacksTo(1).defaultDurability(172), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_CLOSE_HELM = registerItem("dark_close_helm",
            () -> new KHCloseHelmet(new Item.Properties().stacksTo(1).defaultDurability(189), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_CLOSE_HELM = registerItem("golden_close_helm",
            () -> new KHCloseHelmet(new Item.Properties().stacksTo(1).defaultDurability(206), true, Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> FROGMOUTH = registerItem("frogmouth",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(354), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_FROGMOUTH = registerItem("dark_frogmouth",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(389), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_FROGMOUTH = registerItem("golden_frogmouth",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(425), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GREAT_ARMET = registerItem("great_armet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_GREAT_ARMET = registerItem("dark_great_armet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_GREAT_ARMET = registerItem("golden_great_armet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GREAT_ARMET_2 = registerItem("great_armet_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_GREAT_ARMET_2 = registerItem("dark_great_armet_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_GREAT_ARMET_2 = registerItem("golden_great_armet_2",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GREAT_BASCINET = registerItem("great_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_GREAT_BASCINET = registerItem("dark_great_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_GREAT_BASCINET = registerItem("golden_great_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> GREAT_HOUNDSKUL_BASCINET = registerItem("great_houndskul_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_GREAT_HOUNDSKUL_BASCINET = registerItem("dark_great_houndskul_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_GREAT_HOUNDSKUL_BASCINET = registerItem("golden_great_houndskul_bascinet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> MAXIMILLIAN_HELMET = registerItem("maximillian_helmet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_MAXIMILLIAN_HELMET = registerItem("dark_maximillian_helmet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_MAXIMILLIAN_HELMET = registerItem("golden_maximillian_helmet",
            () -> new KHHelmetAccessory(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> SAVOYARD = registerItem("savoyard",
            () -> new KHSavoyard(new Item.Properties().stacksTo(1).defaultDurability(234), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_SAVOYARD = registerItem("dark_savoyard",
            () -> new KHSavoyard(new Item.Properties().stacksTo(1).defaultDurability(257), true, Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_SAVOYARD = registerItem("golden_savoyard",
            () -> new KHSavoyard(new Item.Properties().stacksTo(1).defaultDurability(281), true, Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> GAUNTLET = registerItem("gauntlet",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(90), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_GAUNTLET = registerItem("dark_gauntlet",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(99), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_GAUNTLET = registerItem("golden_gauntlet",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(108), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> BRIGANDINE_HARNESS = registerItem("brigandine_harness",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(100), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> DARK_BRIGANDINE_HARNESS = registerItem("dark_brigandine_harness",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(110), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> GOLDEN_BRIGANDINE_HARNESS = registerItem("golden_brigandine_harness",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistryObject<Item> PLATE_HARNESS = registerItem("plate_harness",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(116), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_PLATE_HARNESS = registerItem("dark_plate_harness",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(128), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_PLATE_HARNESS = registerItem("golden_plate_harness",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(139), Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> BRIGANDINE_CUISSES = registerItem("brigandine_cuisses",
            () -> new KHDyeableLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(187), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> DARK_BRIGANDINE_CUISSES = registerItem("dark_brigandine_cuisses",
            () -> new KHDyeableLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(206), true, 10511680, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    RegistryObject<Item> GOLDEN_BRIGANDINE_CUISSES = registerItem("golden_brigandine_cuisses",
            () -> new KHDyeableLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(224), true, 10511680, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    RegistryObject<Item> PLATE_CUISSES = registerItem("plate_cuisses",
            () -> new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(203), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_PLATE_CUISSES = registerItem("dark_plate_cuisses",
            () -> new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(223), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_PLATE_CUISSES = registerItem("golden_plate_cuisses",
            () -> new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(244), Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> GREAVES = registerItem("greaves",
            () -> new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(90), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_GREAVES = registerItem("dark_greaves",
            () -> new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(99), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_GREAVES = registerItem("golden_greaves",
            () -> new KHLeggingsAccessory(new Item.Properties().stacksTo(1).defaultDurability(108), Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> SABATONS = registerItem("sabatons",
            () -> new KHBootsAccessory(new Item.Properties().stacksTo(1).defaultDurability(90), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_SABATONS = registerItem("dark_sabatons",
            () -> new KHBootsAccessory(new Item.Properties().stacksTo(1).defaultDurability(99), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_SABATONS = registerItem("golden_sabatons",
            () -> new KHBootsAccessory(new Item.Properties().stacksTo(1).defaultDurability(108), Ingredient.of(Items.GOLD_INGOT)));

    RegistryObject<Item> BEVOR = registerItem("bevor",
            () -> new KHBevor(new Item.Properties().stacksTo(1).defaultDurability(100), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> DARK_BEVOR = registerItem("dark_bevor",
            () -> new KHBevor(new Item.Properties().stacksTo(1).defaultDurability(110), Ingredient.of(Items.IRON_INGOT)));
    RegistryObject<Item> GOLDEN_BEVOR = registerItem("golden_bevor",
            () -> new KHBevor(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.GOLD_INGOT)));
    RegistryObject<Item> AVENTAIL = registerItem("aventail",
            () -> new KHChestplateAccessory(new Item.Properties().stacksTo(1).defaultDurability(120), Ingredient.of(Items.IRON_INGOT)));

    RegistryObject<Item> RIM_GUARDS = registerItem("rim_guards", () -> new Item(new Item.Properties().stacksTo(1)));

    RegistryObject<Item> BESAGEWS = registerItem("besagews", () -> new Item(new Item.Properties().stacksTo(1)));

    RegistryObject<Item> SURCOAT = registerItem("surcoat",
            () -> new SurcoatItem(new Item.Properties().stacksTo(1), Ingredient.of(Items.LEATHER)));
    RegistryObject<Item> SURCOAT_SLEEVELESS = registerItem("surcoat_sleeveless",
            () -> new SurcoatItem(new Item.Properties().stacksTo(1), Ingredient.of(Items.LEATHER)));
    RegistryObject<Item> CIVILIAN_SURCOAT = registerItem("civilian_surcoat",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1), Ingredient.of(Items.LEATHER)));
    RegistryObject<Item> GIORNEA = registerItem("giornea",
            () -> new KHDyeableChestplateAccessory(new Item.Properties().stacksTo(1), Ingredient.of(Items.LEATHER)));

    RegistryObject<Item> CLOAK = registerItem("cloak",
            () -> new KHCloak(new Item.Properties().stacksTo(1)));
    RegistryObject<Item> TORN_CLOAK = registerItem("torn_cloak",
            () -> new KHCloak(new Item.Properties().stacksTo(1)));

    RegistryObject<Item> HOOD = registerItem("hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1)));
    RegistryObject<Item> TORN_HOOD = registerItem("torn_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1)));
    RegistryObject<Item> JESTER_HOOD = registerItem("jester_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), true));
    RegistryObject<Item> HELMET_HOOD = registerItem("helmet_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1)));
    RegistryObject<Item> HELMET_TORN_HOOD = registerItem("helmet_torn_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1)));

    RegistryObject<Item> LONGBOW = registerItem("longbow",
            () -> new Item(new Item.Properties().stacksTo(1).defaultDurability(666)));

    RegistryObject<Item> HEAVY_CROSSBOW = registerItem("heavy_crossbow",
            () -> new HeavyCrossbow(new Item.Properties().stacksTo(1).defaultDurability(666)));

    RegistryObject<Item> ARQUEBUS = registerItem("arquebus",
            () -> new Arquebus(new Item.Properties().stacksTo(1).defaultDurability(666)));

    RegistryObject<Item> HANDGONNE = registerItem("handgonne",
            () -> new Handgonne(new Item.Properties().stacksTo(1).defaultDurability(666)));

    RegistryObject<Item> SWALLOWTAIL_ARROW = registerItem("swallowtail_arrow",
            () -> new KHExtendedArrowItem(new Item.Properties(), KHSwallowTailArrowEntity::new));
    RegistryObject<Item> BODKIN_ARROW = registerItem("bodkin_arrow",
            () -> new KHExtendedArrowItem(new Item.Properties(), KHBodkinArrowEntity::new));
    RegistryObject<Item> BROADHEAD_ARROW = registerItem("broadhead_arrow",
            () -> new KHExtendedArrowItem(new Item.Properties(), KHBroadheadArrowEntity::new));
    RegistryObject<Item> CLOTH_ARROW = registerItem("cloth_arrow",
            () -> new ClothArrow(new Item.Properties(), KHClothArrowEntity::new));

    RegistryObject<Item> HORSE_BARDING = registerItem("horse_barding",
            () -> new HorseBardingArmorItem(7, new Item.Properties().stacksTo(1)));
    RegistryObject<Item> DARK_HORSE_BARDING = registerItem("dark_horse_barding",
            () -> new HorseBardingArmorItem(7, new Item.Properties().stacksTo(1)));
    RegistryObject<Item> GOLDEN_HORSE_BARDING = registerItem("golden_horse_barding",
            () -> new HorseBardingArmorItem(7, new Item.Properties().stacksTo(1)));

    RegistryObject<Item> PLUME = registerItem("plume",
            () -> new DyeableItems(new Item.Properties().stacksTo(1)));
    RegistryObject<Item> TRI_PLUME = registerItem("tri_plume",
            () -> new DyeableItems(new Item.Properties().stacksTo(1)));
    RegistryObject<Item> FLUFFY_PLUME = registerItem("fluffy_plume",
            () -> new DyeableItems(new Item.Properties().stacksTo(1)));
    RegistryObject<Item> TORSE = registerItem("torse",
            () -> new TwoLayerDyeableItem(new Item.Properties().stacksTo(1)));
    RegistryObject<Item> TEUTONIC_SNAKES = deco("teutonic_snakes");
    RegistryObject<Item> TEUTONIC_BLACK_SNAKES = deco("teutonic_black_snakes");
    RegistryObject<Item> GOLD_HORNS = deco("gold_horns");
    RegistryObject<Item> BLACK_HORNS = deco("black_horns");
    RegistryObject<Item> TEUTONIC_GOLD_WINGS = deco("teutonic_gold_wings");
    RegistryObject<Item> TEUTONIC_BLACK_WINGS = deco("teutonic_black_wings");
    RegistryObject<Item> TEUTONIC_WINGS_BALL_ENDS = deco("teutonic_wings_ball_ends");
    RegistryObject<Item> TEUTONIC_WINGS_SHARP_ENDS = deco("teutonic_wings_sharp_ends");
    RegistryObject<Item> DRAGON = deco("dragon");
    RegistryObject<Item> LION = deco("lion");
    RegistryObject<Item> SNAKE = deco("snake");
    RegistryObject<Item> UNICORN = deco("unicorn");
    RegistryObject<Item> STAG = deco("stag");
    RegistryObject<Item> BOAR = deco("boar");
    RegistryObject<Item> EAGLE = deco("eagle");
    RegistryObject<Item> PEGASUS = deco("pegasus");

    RegistryObject<Item> CHAPERON = registerItem("chaperon",
            () -> new KHChaperon(new Item.Properties().stacksTo(1), false));
    RegistryObject<Item> GILDED_CHAPERON = registerItem("gilded_chaperon",
            () -> new KHChaperon(new Item.Properties().stacksTo(1), true));

    static RegistryObject<Item> weapon(String id, float attackSpeed, int durability) {
        return registerItem(id, () -> new SwordItem(ModToolMaterials.WEAPONS, 1, attackSpeed, new Item.Properties().defaultDurability(durability)));
    }

    static RegistryObject<Item> deco(String id) {
        return registerItem(id, () -> new Item(new Item.Properties().stacksTo(1)));
    }

    private static <T extends Item> RegistryObject<T> registerItem(String id, Supplier<T> item) {
        return ITEMS.register(id, item);
    }

    static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
        KnightsHeraldry.LOG.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}