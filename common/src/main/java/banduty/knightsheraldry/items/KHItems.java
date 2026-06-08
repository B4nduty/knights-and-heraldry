package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.KHBodkinArrowEntity;
import banduty.knightsheraldry.entity.custom.KHBroadheadArrowEntity;
import banduty.knightsheraldry.entity.custom.KHClothArrowEntity;
import banduty.knightsheraldry.entity.custom.KHSwallowTailArrowEntity;
import banduty.knightsheraldry.items.armor.attachment.*;
import banduty.knightsheraldry.items.armor.deco.DecoItem;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.KHExtendedArrowItem;
import banduty.knightsheraldry.items.armor.deco.TwoLayerDyeableDeco;
import banduty.knightsheraldry.items.item.khammo.ClothArrow;
import banduty.knightsheraldry.items.item.khrangeweapon.Arquebus;
import banduty.knightsheraldry.items.item.khrangeweapon.Handgonne;
import banduty.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import banduty.knightsheraldry.items.item.khweapon.*;
import banduty.knightsheraldry.items.item.khweapon.flail.Flail;
import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.combat.damagetype.SCDamageType;
import banduty.stoneycore.items.custom.armor.underarmor.SCDyeableUnderArmor;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public interface KHItems {
    Supplier<Item> DAGGER = sword("dagger", -1.5F, 196);

    Supplier<Item> STILETTO = sword("stiletto", -1.4F, 157);

    Supplier<Item> RAPIER = sword("rapier", -2.2F, 209);

    Supplier<Item> SWORD = sword("sword", -2.4F, 326);
    Supplier<Item> V_SWORD = sword("v_sword", -2.4F, 326);
    Supplier<Item> ARMING_SWORD = sword("arming_sword", -2.4F, 326);

    Supplier<Item> AXE = axe("axe",-2.6F, 391);
    Supplier<Item> BROAD_AXE = axe("broad_axe", -2.6F, 391);
    Supplier<Item> CROOKED_AXE = axe("crooked_axe",-2.6F, 391);
    Supplier<Item> STRAIGHT_CROOKED_AXE = axe("straight_crooked_axe",-2.6F, 391);

    Supplier<Item> MACE = sword("mace", -2.6F, 430);
    Supplier<Item> SPIKED_MACE = sword("spiked_mace", -2.6F, 430);

    Supplier<Item> FLAIL = registerItem("flail",
            () -> new Flail(-2.8F, new Item.Properties().durability(261)));
    Supplier<Item> BALL_FLAIL = registerItem("ball_flail",
            () -> new Flail(-2.8F, new Item.Properties().durability(261)));

    Supplier<Item> HAMMER = sword("hammer", -2.8F, 430);
    Supplier<Item> WAR_HAMMER = sword("war_hammer", -2.8F, 430);

    Supplier<Item> LONGSWORD = sword("longsword", -2.5F, 365);
    Supplier<Item> V_LONGSWORD = sword("v_longsword", -2.5F, 365);

    Supplier<Item> FALCHION = sword("falchion", -2.2F, 365);
    Supplier<Item> SCIMITAR = sword("scimitar", -2.2F, 365);

    Supplier<Item> PITCHFORK = sword("pitchfork", -2.8F, 235);

    Supplier<Item> SPEAR = sword("spear", -2.6F, 235);

    Supplier<Item> PIKE = sword("pike", -2.8F, 196);

    Supplier<Item> BILLHOOK = registerItem("billhook",
            () -> new Billhook(-2.4F, new Item.Properties().durability(391)));

    Supplier<Item> GLAIVE = sword("glaive", -2.2F, 391);
    Supplier<Item> CURVED_GLAIVE = sword("curved_glaive", -2.2F, 391);

    Supplier<Item> HALBERD = registerItem("halberd",
            () -> new Halberd(-3F, new Item.Properties().durability(391)));

    Supplier<Item> LANCE = registerItem("lance",
            () -> new Lance(-3.0F, new Item.Properties().durability(1), SCDamageType.PIERCING));
    Supplier<Item> WOODEN_LANCE = registerItem("wooden_lance",
            () -> new WoodenLance(-3.0F, new Item.Properties().durability(1), SCDamageType.PIERCING));

    Supplier<Item> POLEAXE = axe("poleaxe",-2.8F, 391);

    Supplier<Item> POLEHAMMER = sword("polehammer", -2.8F, 391);
    Supplier<Item> BEC_DE_CORBIN = sword("bec_de_corbin", -2.8F, 391);

    Supplier<Item> MORNING_STAR = sword("morning_star", -3F, 391);

    Supplier<Item> BARDICHE = sword("bardiche", -3.0F, 391);

    Supplier<Item> GREATSWORD = sword("greatsword", -3.2F, 391);
    Supplier<Item> CLAYMORE = sword("claymore", -3.2F, 391);
    Supplier<Item> FLAMBERGE = sword("flamberge", -3.2F, 391);
    Supplier<Item> ZWEIHANDER = sword("zweihander", -3.2F, 391);

    Supplier<Item> WARDART = registerItem("wardart",
            () -> new WarDart(-2.6F, new Item.Properties().durability(326)));


    Supplier<Item> QUILTED_COIF = registerItem("quilted_coif",
            () -> new SCDyeableUnderArmor(ModArmorMaterials.GAMBESON, ArmorItem.Type.HELMET, new Item.Properties().durability(165), 0xFFA06440));
    Supplier<Item> GAMBESON = registerItem("gambeson",
            () -> new SCDyeableUnderArmor(ModArmorMaterials.GAMBESON, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(660), 0xFFA06440));
    Supplier<Item> GAMBESON_BREECHES = registerItem("gambeson_breeches",
            () -> new SCDyeableUnderArmor(ModArmorMaterials.GAMBESON, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(330), 0xFFA06440));
    Supplier<Item> GAMBESON_BOOTS = registerItem("gambeson_boots",
            () -> new SCDyeableUnderArmor(ModArmorMaterials.GAMBESON, ArmorItem.Type.BOOTS, new Item.Properties().durability(165), 0xFFA06440));

    Supplier<Item> MAIL_COIF = registerItem("mail_coif",
            () -> new SCUnderArmor(ModArmorMaterials.MAIL,  ArmorItem.Type.HELMET, new Item.Properties().durability(512)));
    Supplier<Item> HAUBERK = registerItem("hauberk",
            () -> new SCUnderArmor(ModArmorMaterials.MAIL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(1536)));
    Supplier<Item> MAIL_BREECHES = registerItem("mail_breeches",
            () -> new SCUnderArmor(ModArmorMaterials.MAIL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(1280)));
    Supplier<Item> MAIL_BOOTS = registerItem("mail_boots",
            () -> new SCUnderArmor(ModArmorMaterials.MAIL, ArmorItem.Type.BOOTS, new Item.Properties().durability(512)));

    Supplier<Item> ARMING_DOUBLET = registerItem("arming_doublet",
            () -> new SCDyeableUnderArmor(ModArmorMaterials.ARMING, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(1536), 0xFFA06440));
    Supplier<Item> ARMING_HOSE = registerItem("arming_hose",
            () -> new SCDyeableUnderArmor(ModArmorMaterials.ARMING, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(1280), 0xFFA06440));

    Supplier<Item> MAIL_SPAULDERS = registerItem("mail_spaulders",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(96), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> MAIL_SPAULDERS_BESAGEWS = registerItem("mail_spaulders_besagews",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(96), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_MAIL_SPAULDERS = registerItem("golden_mail_spaulders",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(115), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GOLDEN_MAIL_SPAULDERS_BESAGEWS = registerItem("golden_mail_spaulders_besagews",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(115), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> BRIGANDINE_SPAULDERS = registerItem("brigandine_spaulders",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(109), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> BRIGANDINE_SPAULDERS_BESAGEWS = registerItem("brigandine_spaulders_besagews",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(109), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> DARK_BRIGANDINE_SPAULDERS = registerItem("dark_brigandine_spaulders",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(120), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> DARK_BRIGANDINE_SPAULDERS_BESAGEWS = registerItem("dark_brigandine_spaulders_besagews",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(120), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> GOLDEN_BRIGANDINE_SPAULDERS = registerItem("golden_brigandine_spaulders",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(131), true, 0xFFA06440, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    Supplier<Item> GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS = registerItem("golden_brigandine_spaulders_besagews",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(131), true, 0xFFA06440, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    Supplier<Item> PLATE_SPAULDERS = registerItem("plate_spaulders",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(172), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> PLATE_SPAULDERS_BESAGEWS = registerItem("plate_spaulders_besagews",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(172), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> PLATE_SPAULDERS_RIMMED = registerItem("plate_spaulders_rimmed",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(172), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> PLATE_SPAULDERS_BESAGEWS_RIMMED = registerItem("plate_spaulders_besagews_rimmed",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(172), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_PLATE_SPAULDERS = registerItem("dark_plate_spaulders",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(189), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_PLATE_SPAULDERS_BESAGEWS = registerItem("dark_plate_spaulders_besagews",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(189), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_PLATE_SPAULDERS_RIMMED = registerItem("dark_plate_spaulders_rimmed",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(189), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED = registerItem("dark_plate_spaulders_besagews_rimmed",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(189), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_PLATE_SPAULDERS = registerItem("golden_plate_spaulders",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(206), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GOLDEN_PLATE_SPAULDERS_BESAGEWS = registerItem("golden_plate_spaulders_besagews",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(206), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GOLDEN_PLATE_SPAULDERS_RIMMED = registerItem("golden_plate_spaulders_rimmed",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(206), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED = registerItem("golden_plate_spaulders_besagews_rimmed",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(206), Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> BRIGANDINE = registerItem("brigandine",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(423), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> DARK_BRIGANDINE = registerItem("dark_brigandine",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(465), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> GOLDEN_BRIGANDINE = registerItem("golden_brigandine",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(508), true, 0xFFA06440, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));

    Supplier<Item> PLATE_CUIRASS = registerItem("plate_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(650), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_PLATE_CUIRASS = registerItem("dark_plate_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(715), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_PLATE_CUIRASS = registerItem("golden_plate_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(780), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> MAXIMILLIAN_CUIRASS = registerItem("maximillian_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(650), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_MAXIMILLIAN_CUIRASS = registerItem("dark_maximillian_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(715), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_MAXIMILLIAN_CUIRASS = registerItem("golden_maximillian_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(780), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> XIIII_PLATE_CUIRASS = registerItem("xiiii_plate_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(650), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_XIIII_PLATE_CUIRASS = registerItem("dark_xiiii_plate_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(715), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_XIIII_PLATE_CUIRASS = registerItem("golden_xiiii_plate_cuirass",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(780), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> XIIII_PLATE_BREASTPLATE = registerItem("xiiii_plate_breastplate",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(650), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_XIIII_PLATE_BREASTPLATE = registerItem("dark_xiiii_plate_breastplate",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(715), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_XIIII_PLATE_BREASTPLATE = registerItem("golden_xiiii_plate_breastplate",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(780), Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> PLACKART = registerItem("plackart",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(430), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_PLACKART = registerItem("dark_plackart",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(473), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_PLACKART = registerItem("golden_plackart",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(516), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> TASSETS = registerItem("tassets",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(220), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_TASSETS = registerItem("dark_tassets",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(242), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_TASSETS = registerItem("golden_tassets",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(264), Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> BARBUTE = registerItem("barbute",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_BARBUTE = registerItem("dark_barbute",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_BARBUTE = registerItem("golden_barbute",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> BASCINET = registerItem("bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_BASCINET = registerItem("dark_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_BASCINET = registerItem("golden_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> KETTLE_HELM = registerItem("kettle_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_KETTLE_HELM = registerItem("dark_kettle_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_KETTLE_HELM = registerItem("golden_kettle_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> NASAL_HELM = registerItem("nasal_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_NASAL_HELM = registerItem("dark_nasal_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_NASAL_HELM = registerItem("golden_nasal_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> VIKING_HELM = registerItem("viking_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_VIKING_HELM = registerItem("dark_viking_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_VIKING_HELM = registerItem("golden_viking_helm",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> BURGONET = registerItem("burgonet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_BURGONET = registerItem("dark_burgonet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_BURGONET = registerItem("golden_burgonet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> VISORLESS_SALLET = registerItem("visorless_sallet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_VISORLESS_SALLET = registerItem("dark_visorless_sallet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_VISORLESS_SALLET = registerItem("golden_visorless_sallet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> ARMET = registerItem("armet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_ARMET = registerItem("dark_armet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_ARMET = registerItem("golden_armet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> ARMET_2 = registerItem("armet_2",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_ARMET_2 = registerItem("dark_armet_2",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_ARMET_2 = registerItem("golden_armet_2",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> VISORED_BARBUTE = registerItem("visored_barbute",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_VISORED_BARBUTE = registerItem("dark_visored_barbute",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_VISORED_BARBUTE = registerItem("golden_visored_barbute",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> HOUNDSKULL = registerItem("houndskull",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_HOUNDSKULL = registerItem("dark_houndskull",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_HOUNDSKULL = registerItem("golden_houndskull",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> CAGE = registerItem("cage",
            () -> new KHCageHelmetAttachment(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_CAGE = registerItem("dark_cage",
            () -> new KHCageHelmetAttachment(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_CAGE = registerItem("golden_cage",
            () -> new KHCageHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> VISORED_BASCINET = registerItem("visored_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_VISORED_BASCINET = registerItem("dark_visored_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_VISORED_BASCINET = registerItem("golden_visored_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GREAT_HELM = registerItem("great_helm",
            () -> new KHGreatHelmetAttachment(new Item.Properties().stacksTo(1).durability(250), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_GREAT_HELM = registerItem("dark_great_helm",
            () -> new KHGreatHelmetAttachment(new Item.Properties().stacksTo(1).durability(275), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_GREAT_HELM = registerItem("golden_great_helm",
            () -> new KHGreatHelmetAttachment(new Item.Properties().stacksTo(1).durability(300), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GREAT_HELM_2 = registerItem("great_helm_2",
            () -> new KHGreatHelmetAttachment(new Item.Properties().stacksTo(1).durability(250), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_GREAT_HELM_2 = registerItem("dark_great_helm_2",
            () -> new KHGreatHelmetAttachment(new Item.Properties().stacksTo(1).durability(275), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_GREAT_HELM_2 = registerItem("golden_great_helm_2",
            () -> new KHGreatHelmetAttachment(new Item.Properties().stacksTo(1).durability(300), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> SALLET = registerItem("sallet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_SALLET = registerItem("dark_sallet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_SALLET = registerItem("golden_sallet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> BURGONET_FALLING_BUFFE = registerItem("burgonet_falling_buffe",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_BURGONET_FALLING_BUFFE = registerItem("dark_burgonet_falling_buffe",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_BURGONET_FALLING_BUFFE = registerItem("golden_burgonet_falling_buffe",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> CLOSE_HELM = registerItem("close_helm",
            () -> new KHCloseHelmet(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_CLOSE_HELM = registerItem("dark_close_helm",
            () -> new KHCloseHelmet(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_CLOSE_HELM = registerItem("golden_close_helm",
            () -> new KHCloseHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> BLACK_SALLET = registerItem("black_sallet",
            () -> new KHBlackSalletHelmet(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_BLACK_SALLET = registerItem("dark_black_sallet",
            () -> new KHBlackSalletHelmet(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_BLACK_SALLET = registerItem("golden_black_sallet",
            () -> new KHBlackSalletHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> SALLET_BEVOR = registerItem("sallet_bevor",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_SALLET_BEVOR = registerItem("dark_sallet_bevor",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_SALLET_BEVOR = registerItem("golden_sallet_bevor",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> FROGMOUTH = registerItem("frogmouth",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(354), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_FROGMOUTH = registerItem("dark_frogmouth",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(389), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_FROGMOUTH = registerItem("golden_frogmouth",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(425), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GREAT_ARMET = registerItem("great_armet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(234), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_GREAT_ARMET = registerItem("dark_great_armet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(257), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_GREAT_ARMET = registerItem("golden_great_armet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GREAT_ARMET_2 = registerItem("great_armet_2",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(234), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_GREAT_ARMET_2 = registerItem("dark_great_armet_2",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(257), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_GREAT_ARMET_2 = registerItem("golden_great_armet_2",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GREAT_BASCINET = registerItem("great_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(234), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_GREAT_BASCINET = registerItem("dark_great_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(257), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_GREAT_BASCINET = registerItem("golden_great_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> GREAT_HOUNDSKUL_BASCINET = registerItem("great_houndskul_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(234), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_GREAT_HOUNDSKUL_BASCINET = registerItem("dark_great_houndskul_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(257), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_GREAT_HOUNDSKUL_BASCINET = registerItem("golden_great_houndskul_bascinet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> MAXIMILLIAN_HELMET = registerItem("maximillian_helmet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(234), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_MAXIMILLIAN_HELMET = registerItem("dark_maximillian_helmet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(257), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_MAXIMILLIAN_HELMET = registerItem("golden_maximillian_helmet",
            () -> new KHHelmetAttachment(new Item.Properties().stacksTo(1).durability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> SAVOYARD = registerItem("savoyard",
            () -> new KHSavoyard(new Item.Properties().stacksTo(1).durability(234), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_SAVOYARD = registerItem("dark_savoyard",
            () -> new KHSavoyard(new Item.Properties().stacksTo(1).durability(257), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_SAVOYARD = registerItem("golden_savoyard",
            () -> new KHSavoyard(new Item.Properties().stacksTo(1).durability(281), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> ARAGONESE_SALLET = registerItem("aragonese_sallet",
            () -> new KHAragoneseSalletHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_ARAGONESE_SALLET = registerItem("dark_aragonese_sallet",
            () -> new KHAragoneseSalletHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_ARAGONESE_SALLET = registerItem("golden_aragonese_sallet",
            () -> new KHAragoneseSalletHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> GAUNTLET = registerItem("gauntlet",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(90), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_GAUNTLET = registerItem("dark_gauntlet",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(99), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_GAUNTLET = registerItem("golden_gauntlet",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(108), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> BRIGANDINE_HARNESS = registerItem("brigandine_harness",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(100), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> DARK_BRIGANDINE_HARNESS = registerItem("dark_brigandine_harness",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(110), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> GOLDEN_BRIGANDINE_HARNESS = registerItem("golden_brigandine_harness",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(120), true, 0xFFA06440, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    Supplier<Item> PLATE_HARNESS = registerItem("plate_harness",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(116), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_PLATE_HARNESS = registerItem("dark_plate_harness",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(128), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_PLATE_HARNESS = registerItem("golden_plate_harness",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(139), Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> BRIGANDINE_CUISSES = registerItem("brigandine_cuisses",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(187), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> DARK_BRIGANDINE_CUISSES = registerItem("dark_brigandine_cuisses",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(206), true, 0xFFA06440, Ingredient.of(Items.IRON_INGOT, Items.LEATHER)));
    Supplier<Item> GOLDEN_BRIGANDINE_CUISSES = registerItem("golden_brigandine_cuisses",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(224), true, 0xFFA06440, Ingredient.of(Items.GOLD_INGOT, Items.LEATHER)));
    Supplier<Item> PLATE_CUISSES = registerItem("plate_cuisses",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(203), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_PLATE_CUISSES = registerItem("dark_plate_cuisses",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(223), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_PLATE_CUISSES = registerItem("golden_plate_cuisses",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(244), Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> GREAVES = registerItem("greaves",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(90), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_GREAVES = registerItem("dark_greaves",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(99), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_GREAVES = registerItem("golden_greaves",
            () -> new KHLeggingsAttachment(new Item.Properties().stacksTo(1).durability(108), Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> SABATONS = registerItem("sabatons",
            () -> new KHBootsAttachment(new Item.Properties().stacksTo(1).durability(90), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_SABATONS = registerItem("dark_sabatons",
            () -> new KHBootsAttachment(new Item.Properties().stacksTo(1).durability(99), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_SABATONS = registerItem("golden_sabatons",
            () -> new KHBootsAttachment(new Item.Properties().stacksTo(1).durability(108), Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> AVENTAIL = registerItem("aventail",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));

    Supplier<Item> RIM_GUARDS = registerItem("rim_guards", () -> new Item(new Item.Properties().stacksTo(1)));

    Supplier<Item> BESAGEWS = registerItem("besagews", () -> new Item(new Item.Properties().stacksTo(1)));

    Supplier<Item> SURCOAT = registerItem("surcoat",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1), true, -1, Ingredient.of(Items.LEATHER)));
    Supplier<Item> SURCOAT_SLEEVELESS = registerItem("surcoat_sleeveless",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1), true, -1, Ingredient.of(Items.LEATHER)));
    Supplier<Item> CIVILIAN_SURCOAT = registerItem("civilian_surcoat",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1), true, -1, Ingredient.of(Items.LEATHER)));
    Supplier<Item> GIORNEA = registerItem("giornea",
            () -> new KHChestplateAttachment(new Item.Properties().stacksTo(1), true, -1, Ingredient.of(Items.LEATHER)));

    Supplier<Item> CLOAK = registerItem("cloak",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.CHESTPLATE));
    Supplier<Item> TORN_CLOAK = registerItem("torn_cloak",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.CHESTPLATE));

    Supplier<Item> HOOD = registerItem("hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.HELMET));
    Supplier<Item> TORN_HOOD = registerItem("torn_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.HELMET));
    Supplier<Item> JESTER_HOOD = registerItem("jester_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), true, ArmorItem.Type.HELMET));
    Supplier<Item> HELMET_HOOD = registerItem("helmet_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.HELMET));
    Supplier<Item> HELMET_TORN_HOOD = registerItem("helmet_torn_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.HELMET));

    Supplier<Item> LONGBOW = registerItem("longbow",
            () -> new Item(new Item.Properties().stacksTo(1).durability(666)));

    Supplier<Item> HEAVY_CROSSBOW = registerItem("heavy_crossbow",
            () -> new HeavyCrossbow(new Item.Properties().stacksTo(1).durability(666)));

    Supplier<Item> ARQUEBUS = registerItem("arquebus",
            () -> new Arquebus(new Item.Properties().stacksTo(1).durability(666)));

    Supplier<Item> HANDGONNE = registerItem("handgonne",
            () -> new Handgonne(new Item.Properties().stacksTo(1).durability(666)));

    Supplier<Item> SWALLOWTAIL_ARROW = registerItem("swallowtail_arrow",
            () -> new KHExtendedArrowItem(new Item.Properties(), KHSwallowTailArrowEntity::new));
    Supplier<Item> BODKIN_ARROW = registerItem("bodkin_arrow",
            () -> new KHExtendedArrowItem(new Item.Properties(), KHBodkinArrowEntity::new));
    Supplier<Item> BROADHEAD_ARROW = registerItem("broadhead_arrow",
            () -> new KHExtendedArrowItem(new Item.Properties(), KHBroadheadArrowEntity::new));
    Supplier<Item> CLOTH_ARROW = registerItem("cloth_arrow",
            () -> new ClothArrow(new Item.Properties(), KHClothArrowEntity::new));

    Supplier<Item> HORSE_BARDING = registerItem("horse_barding",
            () -> new HorseBardingArmorItem(new Item.Properties().stacksTo(1)));
    Supplier<Item> DARK_HORSE_BARDING = registerItem("dark_horse_barding",
            () -> new HorseBardingArmorItem(new Item.Properties().stacksTo(1)));
    Supplier<Item> GOLDEN_HORSE_BARDING = registerItem("golden_horse_barding",
            () -> new HorseBardingArmorItem(new Item.Properties().stacksTo(1)));

    Supplier<Item> PLUME = deco("plume");
    Supplier<Item> TRI_PLUME = deco("tri_plume");
    Supplier<Item> FLUFFY_PLUME = deco("fluffy_plume");
    Supplier<Item> TORSE = registerItem("torse",
            () -> new TwoLayerDyeableDeco(new Item.Properties().stacksTo(1)));
    Supplier<Item> TEUTONIC_SNAKES = deco("teutonic_snakes");
    Supplier<Item> TEUTONIC_BLACK_SNAKES = deco("teutonic_black_snakes");
    Supplier<Item> GOLD_HORNS = deco("gold_horns");
    Supplier<Item> BLACK_HORNS = deco("black_horns");
    Supplier<Item> TEUTONIC_GOLD_WINGS = deco("teutonic_gold_wings");
    Supplier<Item> TEUTONIC_BLACK_WINGS = deco("teutonic_black_wings");
    Supplier<Item> TEUTONIC_WINGS_BALL_ENDS = deco("teutonic_wings_ball_ends");
    Supplier<Item> TEUTONIC_WINGS_SHARP_ENDS = deco("teutonic_wings_sharp_ends");
    Supplier<Item> DRAGON = deco("dragon");
    Supplier<Item> LION = deco("lion");
    Supplier<Item> SNAKE = deco("snake");
    Supplier<Item> UNICORN = deco("unicorn");
    Supplier<Item> STAG = deco("stag");
    Supplier<Item> BOAR = deco("boar");
    Supplier<Item> EAGLE = deco("eagle");
    Supplier<Item> PEGASUS = deco("pegasus");

    Supplier<Item> CHAPERON = registerItem("chaperon",
            () -> new KHChaperon(new Item.Properties().stacksTo(1), false));
    Supplier<Item> GILDED_CHAPERON = registerItem("gilded_chaperon",
            () -> new KHChaperon(new Item.Properties().stacksTo(1), true));

    static Supplier<Item> sword(String id, float attackSpeed, int durability) {
        return registerItem(id, () -> new SwordItem(ModToolMaterials.WEAPONS, new Item.Properties().attributes(SwordItem.createAttributes(ModToolMaterials.WEAPONS, 1, attackSpeed)).durability(durability)));
    }
    static Supplier<Item> axe(String id, float attackSpeed, int durability) {
        return registerItem(id, () -> new AxeItem(ModToolMaterials.WEAPONS, new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterials.WEAPONS, 1, attackSpeed)).durability(durability)));
    }

    static Supplier<Item> deco(String id) {
        return registerItem(id, () -> new DecoItem(new Item.Properties().stacksTo(1)));
    }

    private static Supplier<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return Services.PLATFORM.register(BuiltInRegistries.ITEM, name, itemSupplier);
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}