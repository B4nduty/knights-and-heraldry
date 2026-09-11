package banduty.knightsheraldry.items;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.entity.custom.KHBodkinArrowEntity;
import banduty.knightsheraldry.entity.custom.KHBroadheadArrowEntity;
import banduty.knightsheraldry.entity.custom.KHClothArrowEntity;
import banduty.knightsheraldry.entity.custom.KHSwallowTailArrowEntity;
import banduty.knightsheraldry.items.armor.ArmorFamily;
import banduty.knightsheraldry.items.armor.ArmorVariant;
import banduty.knightsheraldry.items.armor.VariantCombo;
import banduty.knightsheraldry.items.armor.attachment.*;
import banduty.knightsheraldry.items.armor.deco.DecoBurnableItem;
import banduty.knightsheraldry.items.armor.deco.DecoItem;
import banduty.knightsheraldry.items.armor.deco.TwoLayerDyeableDeco;
import banduty.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import banduty.knightsheraldry.items.item.KHExtendedArrowItem;
import banduty.knightsheraldry.items.item.QuenchGenericItem;
import banduty.knightsheraldry.items.item.khammo.ClothArrow;
import banduty.knightsheraldry.items.item.khrangeweapon.Arquebus;
import banduty.knightsheraldry.items.item.khrangeweapon.Handgonne;
import banduty.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import banduty.knightsheraldry.items.item.khrangeweapon.Longbow;
import banduty.knightsheraldry.items.item.khweapon.*;
import banduty.knightsheraldry.items.item.khweapon.flail.Flail;
import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.combat.damagetype.SCDamageType;
import banduty.stoneycore.data.SCDataComponents;
import banduty.stoneycore.items.custom.Manuscript;
import banduty.stoneycore.items.custom.armor.underarmor.SCDyeableUnderArmor;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.items.custom.hotiron.HotIron;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.crafting.Ingredient;
import org.joml.Vector3f;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
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
            () -> new SCDyeableUnderArmor(ModArmorMaterials.ARMING, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(1075), 0xFFA06440));
    Supplier<Item> ARMING_HOSE = registerItem("arming_hose",
            () -> new SCDyeableUnderArmor(ModArmorMaterials.ARMING, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(896), 0xFFA06440));

    ArmorFamily MAIL_SPAULDERS = attachmentFamily("mail_spaulders", 96, KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.GOLDEN}, ArmorVariant.BESAGEWS);
    ArmorFamily BRIGANDINE_SPAULDERS = attachmentFamily(
            "brigandine_spaulders", 109,
            (props, ingredient) -> new KHChestplateAttachment(props, true, 0xFFA06440, Ingredient.of(Arrays.stream(ingredient.getItems()).findFirst().get().getItem(), Items.LEATHER)),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN},
            ArmorVariant.BESAGEWS
    );
    ArmorFamily PLATE_SPAULDERS = attachmentFamily(
            "plate_spaulders", 172,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN},
            ArmorVariant.BESAGEWS, ArmorVariant.RIMMED
    );

    ArmorFamily BRIGANDINE = attachmentFamily(
            "brigandine", 423,
            (props, ingredient) -> new KHChestplateAttachment(props, true, 0xFFA06440, Ingredient.of(Arrays.stream(ingredient.getItems()).findFirst().get().getItem(), Items.LEATHER)),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily PLATE_CUIRASS = attachmentFamily(
            "plate_cuirass", 650,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily MAXIMILLIAN_CUIRASS = attachmentFamily(
            "maximillian_cuirass", 650,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily XIIII_PLATE_CUIRASS = attachmentFamily(
            "xiiii_plate_cuirass", 650,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily XIIII_PLATE_BREASTPLATE = attachmentFamily(
            "xiiii_plate_breastplate", 650,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily PLACKART = attachmentFamily(
            "plackart", 430,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily TASSETS = attachmentFamily(
            "tassets", 220,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily BARBUTE = attachmentFamily(
            "barbute", 109,
            KHHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily BASCINET = attachmentFamily(
            "bascinet", 109,
            KHHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily KETTLE_HELM = attachmentFamily(
            "kettle_helm", 109,
            KHHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily NASAL_HELM = attachmentFamily(
            "nasal_helm", 109,
            KHHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily VIKING_HELM = attachmentFamily(
            "viking_helm", 109,
            KHHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily BURGONET = attachmentFamily(
            "burgonet", 109,
            KHHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily VISORLESS_SALLET = attachmentFamily(
            "visorless_sallet", 109,
            KHSalletHelmet::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily MORION = attachmentFamily(
            "morion", 109,
            KHMorionHelmet::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily ARMET = attachmentFamily(
            "armet", 172,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily ARMET_2 = attachmentFamily(
            "armet_2", 172,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily VISORED_BARBUTE = attachmentFamily(
            "visored_barbute", 172,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily HOUNDSKULL = attachmentFamily(
            "houndskull", 172,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily CAGE = attachmentFamily(
            "cage", 172,
            (properties, ingredient) -> new KHCageHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily VISORED_BASCINET = attachmentFamily(
            "visored_bascinet", 172,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily GREAT_HELM = attachmentFamily(
            "great_helm", 250,
            KHGreatHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily GREAT_HELM_2 = attachmentFamily(
            "great_helm_2", 250,
            KHGreatHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily SALLET = attachmentFamily(
            "sallet", 172,
            (properties, ingredient) -> new KHSalletHelmet(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily BURGONET_FALLING_BUFFE = attachmentFamily(
            "burgonet_falling_buffe", 172,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily CLOSE_HELM = attachmentFamily(
            "close_helm", 172,
            (properties, ingredient) -> new KHCloseHelmet(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily BLACK_SALLET = attachmentFamily(
            "black_sallet", 172,
            (properties, ingredient) -> new KHBlackSalletHelmet(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily VISORED_MORION = attachmentFamily(
            "visored_morion", 172,
            (properties, ingredient) -> new KHMorionHelmet(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily SALLET_BEVOR = attachmentFamily(
            "sallet_bevor", 206,
            (properties, ingredient) -> new KHSalletHelmet(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily BLACK_SALLET_BEVOR = attachmentFamily(
            "black_sallet_bevor", 206,
            (properties, ingredient) -> new KHBlackSalletHelmet(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily FROGMOUTH = attachmentFamily(
            "frogmouth", 354,
            KHHelmetAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily GREAT_ARMET = attachmentFamily(
            "great_armet", 234,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily GREAT_ARMET_2 = attachmentFamily(
            "great_armet_2", 234,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily GREAT_BASCINET = attachmentFamily(
            "great_bascinet", 234,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily GREAT_HOUNDSKUL_BASCINET = attachmentFamily(
            "great_houndskul_bascinet", 234,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily MAXIMILLIAN_HELMET = attachmentFamily(
            "maximillian_helmet", 234,
            (properties, ingredient) -> new KHHelmetAttachment(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily SAVOYARD = attachmentFamily(
            "savoyard", 234,
            (properties, ingredient) -> new KHSavoyard(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily ARAGONESE_SALLET = attachmentFamily(
            "aragonese_sallet", 234,
            (properties, ingredient) -> new KHAragoneseSalletHelmet(properties, true, ingredient),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    Supplier<Item> LEATHER_GLOVES = registerItem("leather_gloves",
            () -> new KHGlove(new Item.Properties().stacksTo(1).durability(90), 0xFFA06440, Ingredient.of(Items.LEATHER), true));
    Supplier<Item> MAIL_GLOVES = registerItem("mail_gloves",
            () -> new KHGlove(new Item.Properties().stacksTo(1).durability(100), Ingredient.of(Items.IRON_INGOT), true));

    ArmorFamily GAUNTLET = attachmentFamily(
            "gauntlet", 90,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily BRIGANDINE_HARNESS = attachmentFamily(
            "brigandine_harness", 100,
            (properties, ingredient) -> new KHChestplateAttachment(properties, true, 0xFFA06440, Ingredient.of(Arrays.stream(ingredient.getItems()).findFirst().get().getItem(), Items.LEATHER)),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily PLATE_HARNESS = attachmentFamily(
            "plate_harness", 116,
            KHChestplateAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily BRIGANDINE_CUISSES = attachmentFamily(
            "brigandine_cuisses", 187,
            (properties, ingredient) -> new KHLeggingsAttachment(properties, true, 0xFFA06440, Ingredient.of(Arrays.stream(ingredient.getItems()).findFirst().get().getItem(), Items.LEATHER)),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );
    ArmorFamily PLATE_CUISSES = attachmentFamily(
            "plate_cuisses", 203,
            KHLeggingsAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily GREAVES = attachmentFamily(
            "greaves", 90,
            KHLeggingsAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    ArmorFamily SABATONS = attachmentFamily(
            "sabatons", 90,
            KHBootsAttachment::new,
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

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
            () -> new Longbow(new Item.Properties().stacksTo(1).durability(666)));

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


    ArmorFamily HORSE_BARDING = attachmentFamily(
            "horse_barding", 300,
            (properties, ingredient) -> new HorseBardingArmorItem(properties),
            new ArmorVariant[]{ArmorVariant.PLAIN, ArmorVariant.DARK, ArmorVariant.GOLDEN}
    );

    Supplier<Item> PLUME = registerItem("plume",
            () -> new DecoItem(new Item.Properties().stacksTo(1)));
    Supplier<Item> TRI_PLUME = registerItem("tri_plume",
            () -> new DecoItem(new Item.Properties().stacksTo(1)));
    Supplier<Item> FLUFFY_PLUME = registerItem("fluffy_plume",
            () -> new DecoItem(new Item.Properties().stacksTo(1)));
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

    Supplier<Item> BARBUTE_PIECE = registerItem("barbute_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> BASCINET_PIECE = registerItem("bascinet_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> KETTLE_PIECE = registerItem("kettle_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> NASAL_PIECE = registerItem("nasal_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> BURGONET_PIECE = registerItem("burgonet_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> SALLET_PIECE = registerItem("sallet_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> MORION_PIECE = registerItem("morion_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> ARMET_PIECE = registerItem("armet_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> CAGE_PIECE = registerItem("cage_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> GREAT_HELMET_PIECE = registerItem("great_helmet_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> CLOSE_HELMET_PIECE = registerItem("close_helmet_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> FROGMOUTH_PIECE = registerItem("frogmouth_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> MAXIMILIAN_PIECE = registerItem("maximilian_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));

    Supplier<Item> VISOR = registerItem("visor", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> FALLING_BUFFE = registerItem("falling_buffe", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));
    Supplier<Item> BEVOR = registerItem("bevor", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));

    Supplier<Item> CUIRASS_PIECE = registerItem("cuirass_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));

    Supplier<Item> SPAULDERS_PIECE = registerItem("spaulders_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));

    Supplier<Item> HARNESS_PIECE = registerItem("harness_piece", () -> new HotIron(new Item.Properties().component(SCDataComponents.IGNITED.get(), true)));

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
        return registerItem(id, () -> new DecoBurnableItem(new Item.Properties().stacksTo(1)));
    }

    static ArmorFamily attachmentFamily(
            String baseName,
            int baseDurability,
            BiFunction<Item.Properties, Ingredient, Item> factory,
            ArmorVariant[] tiers,
            ArmorVariant... structuralToggles) {

        Map<VariantCombo, Supplier<Item>> map = new LinkedHashMap<>();
        for (ArmorVariant tier : tiers) {
            for (List<ArmorVariant> structural : ArmorVariant.powerSet(List.of(structuralToggles))) {

                String id = tier.id(baseName);
                int durability = tier.durability(baseDurability);
                for (ArmorVariant modifier : structural) {
                    id = modifier.id(id);
                    durability = modifier.durability(durability);
                }

                Item.Properties props = new Item.Properties().stacksTo(1).durability(durability);
                map.put(new VariantCombo(tier, structural),
                        registerItem(id, () -> factory.apply(props, tier.ingredient())));
            }
        }
        return new ArmorFamily(map, VariantCombo.of(tiers[0]));
    }

    private static Supplier<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return Services.PLATFORM.register(BuiltInRegistries.ITEM, name, itemSupplier);
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}