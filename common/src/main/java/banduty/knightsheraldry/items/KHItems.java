package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.entity.custom.KHBodkinArrowEntity;
import banduty.knightsheraldry.entity.custom.KHBroadheadArrowEntity;
import banduty.knightsheraldry.entity.custom.KHClothArrowEntity;
import banduty.knightsheraldry.entity.custom.KHSwallowTailArrowEntity;
import banduty.knightsheraldry.items.armor.attachment.*;
import banduty.knightsheraldry.items.armor.deco.DecoItem;
import banduty.knightsheraldry.items.armor.deco.TwoLayerDyeableDeco;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.KHExtendedArrowItem;
import banduty.knightsheraldry.items.item.QuenchGenericItem;
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
import banduty.stoneycore.items.custom.hotiron.HotIron;
import banduty.stoneycore.items.custom.Manuscript;
import banduty.stoneycore.mobgear.SCMobGearRegistry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import org.joml.Vector3f;

import java.util.List;
import java.util.function.Supplier;

public interface KHItems {
    Supplier<Item> DAGGER = sword("dagger", -1.5F, 196);

    Supplier<Item> STILETTO = sword("stiletto", -1.4F, 157);

    Supplier<Item> RAPIER = sword("rapier", -2.2F, 209);

    Supplier<Item> SWORD = sword("sword", -2.4F, 326);
    Supplier<Item> V_SWORD = sword("v_sword", -2.4F, 326);
    Supplier<Item> ARMING_SWORD = sword("arming_sword", -2.4F, 326);

    Supplier<Item> AXE = axe("axe", -2.6F, 391);
    Supplier<Item> BROAD_AXE = axe("broad_axe", -2.6F, 391);
    Supplier<Item> CROOKED_AXE = axe("crooked_axe", -2.6F, 391);
    Supplier<Item> STRAIGHT_CROOKED_AXE = axe("straight_crooked_axe", -2.6F, 391);

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

    Supplier<Item> POLEAXE = axe("poleaxe", -2.8F, 391);

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
            () -> new SCUnderArmor(ModArmorMaterials.MAIL, ArmorItem.Type.HELMET, new Item.Properties().durability(512)));
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
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_VISORLESS_SALLET = registerItem("dark_visorless_sallet",
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_VISORLESS_SALLET = registerItem("golden_visorless_sallet",
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> MORION = registerItem("morion",
            () -> new KHMorionHelmet(new Item.Properties().stacksTo(1).durability(109), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_MORION = registerItem("dark_morion",
            () -> new KHMorionHelmet(new Item.Properties().stacksTo(1).durability(120), Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_MORION = registerItem("golden_morion",
            () -> new KHMorionHelmet(new Item.Properties().stacksTo(1).durability(131), Ingredient.of(Items.GOLD_INGOT)));

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
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_SALLET = registerItem("dark_sallet",
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_SALLET = registerItem("golden_sallet",
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));
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
    Supplier<Item> VISORED_MORION = registerItem("visored_morion",
            () -> new KHMorionHelmet(new Item.Properties().stacksTo(1).durability(172), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_VISORED_MORION = registerItem("dark_visored_morion",
            () -> new KHMorionHelmet(new Item.Properties().stacksTo(1).durability(189), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_VISORED_MORION = registerItem("golden_visored_morion",
            () -> new KHMorionHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.GOLD_INGOT)));

    Supplier<Item> SALLET_BEVOR = registerItem("sallet_bevor",
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_SALLET_BEVOR = registerItem("dark_sallet_bevor",
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(226), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_SALLET_BEVOR = registerItem("golden_sallet_bevor",
            () -> new KHSalletHelmet(new Item.Properties().stacksTo(1).durability(246), true, Ingredient.of(Items.GOLD_INGOT)));
    Supplier<Item> BLACK_SALLET_BEVOR = registerItem("black_sallet_bevor",
            () -> new KHBlackSalletHelmet(new Item.Properties().stacksTo(1).durability(206), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> DARK_BLACK_SALLET_BEVOR = registerItem("dark_black_sallet_bevor",
            () -> new KHBlackSalletHelmet(new Item.Properties().stacksTo(1).durability(226), true, Ingredient.of(Items.IRON_INGOT)));
    Supplier<Item> GOLDEN_BLACK_SALLET_BEVOR = registerItem("golden_black_sallet_bevor",
            () -> new KHBlackSalletHelmet(new Item.Properties().stacksTo(1).durability(246), true, Ingredient.of(Items.GOLD_INGOT)));

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

    Supplier<Item> LEATHER_GLOVES = registerItem("leather_gloves",
            () -> new KHGlove(new Item.Properties().stacksTo(1).durability(90), 0xFFA06440, Ingredient.of(Items.LEATHER)));
    Supplier<Item> MAIL_GLOVES = registerItem("mail_gloves",
            () -> new KHGlove(new Item.Properties().stacksTo(1).durability(100), Ingredient.of(Items.IRON_INGOT)));

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

    Supplier<Item> RIM_GUARDS = registerItem("rim_guards", () -> new QuenchGenericItem(new Item.Properties().stacksTo(1)));

    Supplier<Item> BESAGEWS = registerItem("besagews", () -> new QuenchGenericItem(new Item.Properties().stacksTo(1)));

    Supplier<Item> SURCOAT = registerItem("surcoat",
            () -> new KHSurcoatWBannerAttachment(new Item.Properties().stacksTo(1), true, -1, Ingredient.of(Items.LEATHER)));
    Supplier<Item> SURCOAT_SLEEVELESS = registerItem("surcoat_sleeveless",
            () -> new KHSurcoatWBannerAttachment(new Item.Properties().stacksTo(1), true, -1, Ingredient.of(Items.LEATHER)));
    Supplier<Item> CIVILIAN_SURCOAT = registerItem("civilian_surcoat",
            () -> new KHSurcoatAttachment(new Item.Properties().stacksTo(1), true, -1, Ingredient.of(Items.LEATHER)));
    Supplier<Item> GIORNEA = registerItem("giornea",
            () -> new KHSurcoatAttachment(new Item.Properties().stacksTo(1), true, -1, Ingredient.of(Items.LEATHER)));

    Supplier<Item> CLOAK = registerItem("cloak",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.CHESTPLATE, null));
    Supplier<Item> TORN_CLOAK = registerItem("torn_cloak",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.CHESTPLATE, null));

    Supplier<Item> HOOD = registerItem("hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.HELMET, new Vector3f(0f, -4f, 0f)));
    Supplier<Item> TORN_HOOD = registerItem("torn_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.HELMET, new Vector3f(0f, -4f, 0f)));
    Supplier<Item> JESTER_HOOD = registerItem("jester_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), true, ArmorItem.Type.HELMET, new Vector3f(0f, -4f, 0f)));
    Supplier<Item> HELMET_HOOD = registerItem("helmet_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.HELMET, new Vector3f(0f, -6f, 0f)));
    Supplier<Item> HELMET_TORN_HOOD = registerItem("helmet_torn_hood",
            () -> new KHCloak(new Item.Properties().stacksTo(1), ArmorItem.Type.HELMET, new Vector3f(0f, -6f, 0f)));

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

    Supplier<Item> CRAFTMAN_SPAWN_EGG = registerItem("craftman_spawn_egg",
            () -> new SpawnEggItem(KHEntities.CRAFTMAN.get(),
                    0x4C3224,
                    0xFFD700,
                    new Item.Properties()
            ));

    Supplier<Item> MANUSCRIPT_DAGGER = registerItem("manuscript_dagger", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_SWORD = registerItem("manuscript_sword", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_AXE = registerItem("manuscript_axe", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_HAMMER = registerItem("manuscript_hammer", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_MACE = registerItem("manuscript_mace", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_HALBERD = registerItem("manuscript_halberd", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_LONGSWORD = registerItem("manuscript_longsword", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_GREATSWORD = registerItem("manuscript_greatsword", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_SPEAR = registerItem("manuscript_spear", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_PITCHFORK = registerItem("manuscript_pitchfork", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_BARBUTE = registerItem("manuscript_barbute", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_BASCINET = registerItem("manuscript_bascinet", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_KETTLE = registerItem("manuscript_kettle", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_NASAL = registerItem("manuscript_nasal", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_BURGONET = registerItem("manuscript_burgonet", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_SALLET = registerItem("manuscript_sallet", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_MORION = registerItem("manuscript_morion", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_ARMET = registerItem("manuscript_armet", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_CAGE = registerItem("manuscript_cage", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_GREAT_HELMET = registerItem("manuscript_great_helmet", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_CLOSE_HELMET = registerItem("manuscript_close_helmet", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_FROGMOUTH = registerItem("manuscript_frogmouth", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_MAXIMILIAN = registerItem("manuscript_maximilian", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_VISOR = registerItem("manuscript_visor", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_FALLING_BUFFE = registerItem("manuscript_falling_buffe", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_BEVOR = registerItem("manuscript_bevor", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_AVENTAIL = registerItem("manuscript_aventail", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_CUIRASS = registerItem("manuscript_cuirass", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_PLACKART = registerItem("manuscript_plackart", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_TASSETS = registerItem("manuscript_tassets", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_RIM_GUARDS = registerItem("manuscript_rim_guards", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_BESAGEWS = registerItem("manuscript_besagews", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_SPAULDERS = registerItem("manuscript_spaulders", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_HARNESS = registerItem("manuscript_harness", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_CUISSES = registerItem("manuscript_cuisses", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_GREAVES = registerItem("manuscript_greaves", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_SABATONS = registerItem("manuscript_sabatons", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_BARDING = registerItem("manuscript_barding", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> MANUSCRIPT_SWALLOWTAIL = registerItem("manuscript_swallowtail", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_BODKIN = registerItem("manuscript_bodkin", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_BROADHEAD = registerItem("manuscript_broadhead", () -> new Manuscript(new Item.Properties().stacksTo(1)));
    Supplier<Item> MANUSCRIPT_CLOTH = registerItem("manuscript_cloth", () -> new Manuscript(new Item.Properties().stacksTo(1)));

    Supplier<Item> DAGGER_HEAD = registerItem("dagger_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> STILETTO_HEAD = registerItem("stiletto_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> SWORD_HEAD = registerItem("sword_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> FALCHION_HEAD = registerItem("falchion_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> RAPIER_HEAD = registerItem("rapier_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> AXE_HEAD = registerItem("axe_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> HAMMER_HEAD = registerItem("hammer_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> MACE_HEAD = registerItem("mace_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> HALBERD_HEAD = registerItem("halberd_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> BILLHOOK_HEAD = registerItem("billhook_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> LONGSWORD_HEAD = registerItem("longsword_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> GREATSWORD_HEAD = registerItem("greatsword_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> SPEAR_HEAD = registerItem("spear_head", () -> new HotIron(new Item.Properties(), false));
    Supplier<Item> PITCHFORK_HEAD = registerItem("pitchfork_head", () -> new HotIron(new Item.Properties(), false));

    Supplier<Item> BARBUTE_PIECE = registerItem("barbute_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> BASCINET_PIECE = registerItem("bascinet_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> KETTLE_PIECE = registerItem("kettle_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> NASAL_PIECE = registerItem("nasal_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> BURGONET_PIECE = registerItem("burgonet_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> SALLET_PIECE = registerItem("sallet_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> MORION_PIECE = registerItem("morion_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> ARMET_PIECE = registerItem("armet_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> CAGE_PIECE = registerItem("cage_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> GREAT_HELMET_PIECE = registerItem("great_helmet_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> CLOSE_HELMET_PIECE = registerItem("close_helmet_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> FROGMOUTH_PIECE = registerItem("frogmouth_piece", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> MAXIMILIAN_PIECE = registerItem("maximilian_piece", () -> new HotIron(new Item.Properties(), true));

    Supplier<Item> VISOR = registerItem("visor", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> FALLING_BUFFE = registerItem("falling_buffe", () -> new HotIron(new Item.Properties(), true));
    Supplier<Item> BEVOR = registerItem("bevor", () -> new HotIron(new Item.Properties(), true));

    Supplier<Item> CUIRASS_PIECE = registerItem("cuirass_piece", () -> new HotIron(new Item.Properties(), true));

    Supplier<Item> SPAULDERS_PIECE = registerItem("spaulders_piece", () -> new HotIron(new Item.Properties(), true));

    Supplier<Item> HARNESS_PIECE = registerItem("harness_piece", () -> new HotIron(new Item.Properties(), true));

    static Supplier<Item> sword(String id, float attackSpeed, int durability) {
        return registerItem(id, () -> new Sword3dItem(ModToolMaterials.WEAPONS,
                new Item.Properties().attributes(Sword3dItem.createAttributes(ModToolMaterials.WEAPONS, 1, attackSpeed))) {
            @Override
            public DataComponentMap components() {
                return DataComponentMap.builder()
                        .addAll(super.components())
                        .set(DataComponents.MAX_DAMAGE, durability)
                        .build();
            }
        });
    }

    static Supplier<Item> axe(String id, float attackSpeed, int durability) {
        return registerItem(id, () -> new Axe3dItem(ModToolMaterials.WEAPONS,
                new Item.Properties().attributes(Axe3dItem.createAttributes(ModToolMaterials.WEAPONS, 1, attackSpeed))) {
            @Override
            public DataComponentMap components() {
                return DataComponentMap.builder()
                        .addAll(super.components())
                        .set(DataComponents.MAX_DAMAGE, durability)
                        .build();
            }
        });
    }

    static Supplier<Item> deco(String id) {
        return registerItem(id, () -> new DecoItem(new Item.Properties().stacksTo(1)));
    }

    private static Supplier<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return Services.PLATFORM.register(BuiltInRegistries.ITEM, name, itemSupplier);
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
        registerMobGear();
    }

    private static void registerMobGear() {
        registerMobGearWeapons();
        registerMobGearBaseArmor();
        registerMobGearAttachments();
    }

    List<EntityType<?>> allowedMobs = List.of(
            EntityType.ZOMBIE,
            EntityType.HUSK,
            EntityType.DROWNED,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN
    );

    private static void registerMobGearWeapons() {
        registerWeapons(
                DAGGER, STILETTO, RAPIER,
                SWORD, V_SWORD, ARMING_SWORD,
                AXE, BROAD_AXE, CROOKED_AXE, STRAIGHT_CROOKED_AXE,
                MACE, SPIKED_MACE,
                FLAIL, BALL_FLAIL,
                HAMMER, WAR_HAMMER,
                LONGSWORD, V_LONGSWORD,
                FALCHION, SCIMITAR,
                PITCHFORK, SPEAR, PIKE,
                BILLHOOK,
                GLAIVE, CURVED_GLAIVE,
                HALBERD,
                POLEAXE, POLEHAMMER, BEC_DE_CORBIN,
                MORNING_STAR, BARDICHE,
                GREATSWORD, CLAYMORE, FLAMBERGE, ZWEIHANDER,
                WARDART
        );
    }

    @SafeVarargs
    static void registerWeapons(Supplier<? extends Item>... weapons) {
        for (Supplier<? extends Item> weapon : weapons) {
            SCMobGearRegistry.registerWeapon(weapon, allowedMobs);
        }
    }

    private static void registerMobGearBaseArmor() {
        SCMobGearRegistry.registerArmorSet(QUILTED_COIF, GAMBESON, GAMBESON_BREECHES, GAMBESON_BOOTS, allowedMobs);

        SCMobGearRegistry.registerArmorSet(MAIL_COIF, HAUBERK, MAIL_BREECHES, MAIL_BOOTS, allowedMobs);

        SCMobGearRegistry.registerArmor(EquipmentSlot.CHEST, ARMING_DOUBLET, allowedMobs);
        SCMobGearRegistry.registerArmor(EquipmentSlot.LEGS, ARMING_HOSE, allowedMobs);
    }

    private static void registerMobGearAttachments() {
        registerAttachments(EquipmentSlot.HEAD,
                BARBUTE, DARK_BARBUTE, GOLDEN_BARBUTE,
                BASCINET, DARK_BASCINET, GOLDEN_BASCINET,
                KETTLE_HELM, DARK_KETTLE_HELM, GOLDEN_KETTLE_HELM,
                NASAL_HELM, DARK_NASAL_HELM, GOLDEN_NASAL_HELM,
                VIKING_HELM, DARK_VIKING_HELM, GOLDEN_VIKING_HELM,
                BURGONET, DARK_BURGONET, GOLDEN_BURGONET,
                VISORLESS_SALLET, DARK_VISORLESS_SALLET, GOLDEN_VISORLESS_SALLET,
                MORION, DARK_MORION, GOLDEN_MORION,
                ARMET, DARK_ARMET, GOLDEN_ARMET,
                ARMET_2, DARK_ARMET_2, GOLDEN_ARMET_2,
                VISORED_BARBUTE, DARK_VISORED_BARBUTE, GOLDEN_VISORED_BARBUTE,
                HOUNDSKULL, DARK_HOUNDSKULL, GOLDEN_HOUNDSKULL,
                CAGE, DARK_CAGE, GOLDEN_CAGE,
                VISORED_BASCINET, DARK_VISORED_BASCINET, GOLDEN_VISORED_BASCINET,
                GREAT_HELM, DARK_GREAT_HELM, GOLDEN_GREAT_HELM,
                GREAT_HELM_2, DARK_GREAT_HELM_2, GOLDEN_GREAT_HELM_2,
                SALLET, DARK_SALLET, GOLDEN_SALLET,
                BURGONET_FALLING_BUFFE, DARK_BURGONET_FALLING_BUFFE, GOLDEN_BURGONET_FALLING_BUFFE,
                CLOSE_HELM, DARK_CLOSE_HELM, GOLDEN_CLOSE_HELM,
                BLACK_SALLET, DARK_BLACK_SALLET, GOLDEN_BLACK_SALLET,
                VISORED_MORION, DARK_VISORED_MORION, GOLDEN_VISORED_MORION,
                SALLET_BEVOR, DARK_SALLET_BEVOR, GOLDEN_SALLET_BEVOR,
                BLACK_SALLET_BEVOR, DARK_BLACK_SALLET_BEVOR, GOLDEN_BLACK_SALLET_BEVOR,
                FROGMOUTH, DARK_FROGMOUTH, GOLDEN_FROGMOUTH,
                GREAT_ARMET, DARK_GREAT_ARMET, GOLDEN_GREAT_ARMET,
                GREAT_ARMET_2, DARK_GREAT_ARMET_2, GOLDEN_GREAT_ARMET_2,
                GREAT_BASCINET, DARK_GREAT_BASCINET, GOLDEN_GREAT_BASCINET,
                GREAT_HOUNDSKUL_BASCINET, DARK_GREAT_HOUNDSKUL_BASCINET, GOLDEN_GREAT_HOUNDSKUL_BASCINET,
                MAXIMILLIAN_HELMET, DARK_MAXIMILLIAN_HELMET, GOLDEN_MAXIMILLIAN_HELMET,
                SAVOYARD, DARK_SAVOYARD, GOLDEN_SAVOYARD,
                ARAGONESE_SALLET, DARK_ARAGONESE_SALLET, GOLDEN_ARAGONESE_SALLET
        );

        registerAttachments(EquipmentSlot.CHEST,
                MAIL_SPAULDERS, MAIL_SPAULDERS_BESAGEWS, GOLDEN_MAIL_SPAULDERS, GOLDEN_MAIL_SPAULDERS_BESAGEWS,
                BRIGANDINE_SPAULDERS, BRIGANDINE_SPAULDERS_BESAGEWS,
                DARK_BRIGANDINE_SPAULDERS, DARK_BRIGANDINE_SPAULDERS_BESAGEWS,
                GOLDEN_BRIGANDINE_SPAULDERS, GOLDEN_BRIGANDINE_SPAULDERS_BESAGEWS,
                PLATE_SPAULDERS, PLATE_SPAULDERS_BESAGEWS, PLATE_SPAULDERS_RIMMED, PLATE_SPAULDERS_BESAGEWS_RIMMED,
                DARK_PLATE_SPAULDERS, DARK_PLATE_SPAULDERS_BESAGEWS, DARK_PLATE_SPAULDERS_RIMMED, DARK_PLATE_SPAULDERS_BESAGEWS_RIMMED,
                GOLDEN_PLATE_SPAULDERS, GOLDEN_PLATE_SPAULDERS_BESAGEWS, GOLDEN_PLATE_SPAULDERS_RIMMED, GOLDEN_PLATE_SPAULDERS_BESAGEWS_RIMMED,
                BRIGANDINE, DARK_BRIGANDINE, GOLDEN_BRIGANDINE,
                PLATE_CUIRASS, DARK_PLATE_CUIRASS, GOLDEN_PLATE_CUIRASS,
                MAXIMILLIAN_CUIRASS, DARK_MAXIMILLIAN_CUIRASS, GOLDEN_MAXIMILLIAN_CUIRASS,
                XIIII_PLATE_CUIRASS, DARK_XIIII_PLATE_CUIRASS, GOLDEN_XIIII_PLATE_CUIRASS,
                XIIII_PLATE_BREASTPLATE, DARK_XIIII_PLATE_BREASTPLATE, GOLDEN_XIIII_PLATE_BREASTPLATE,
                PLACKART, DARK_PLACKART, GOLDEN_PLACKART,
                TASSETS, DARK_TASSETS, GOLDEN_TASSETS,
                GAUNTLET, DARK_GAUNTLET, GOLDEN_GAUNTLET,
                BRIGANDINE_HARNESS, DARK_BRIGANDINE_HARNESS, GOLDEN_BRIGANDINE_HARNESS,
                PLATE_HARNESS, DARK_PLATE_HARNESS, GOLDEN_PLATE_HARNESS,
                AVENTAIL,
                CLOAK, TORN_CLOAK
        );

        registerAttachments(EquipmentSlot.LEGS,
                BRIGANDINE_CUISSES, DARK_BRIGANDINE_CUISSES, GOLDEN_BRIGANDINE_CUISSES,
                PLATE_CUISSES, DARK_PLATE_CUISSES, GOLDEN_PLATE_CUISSES,
                GREAVES, DARK_GREAVES, GOLDEN_GREAVES
        );

        registerAttachments(EquipmentSlot.FEET,
                SABATONS, DARK_SABATONS, GOLDEN_SABATONS
        );

        registerAttachments(EquipmentSlot.HEAD,
                HOOD, TORN_HOOD, JESTER_HOOD, HELMET_HOOD, HELMET_TORN_HOOD,
                CHAPERON, GILDED_CHAPERON
        );
        registerAttachments(EquipmentSlot.CHEST,
                LEATHER_GLOVES, MAIL_GLOVES,
                SURCOAT, SURCOAT_SLEEVELESS, CIVILIAN_SURCOAT, GIORNEA
        );
    }

    @SafeVarargs
    static void registerAttachments(EquipmentSlot slot, Supplier<? extends Item>... attachments) {
        for (Supplier<? extends Item> attachment : attachments) {
            SCMobGearRegistry.registerAttachment(slot, attachment, allowedMobs);
        }
    }
}