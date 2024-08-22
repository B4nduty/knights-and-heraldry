package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SMITHING_HAMMER = registerItem("smithing_hammer",
            new SmithingHammer(new FabricItemSettings().maxCount(1)), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item DAGGER = registerItem("dagger",
            new Dagger(-2F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item STILETTO = registerItem("stiletto",
            new Stiletto(-2F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item RAPIER = registerItem("rapier",
            new Rapier(-2.2F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item SWORD = registerItem("sword",
            new Sword(-2.4F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item V_SWORD = registerItem("v_sword",
            new Sword(-2.4F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item ARMING_SWORD = registerItem("arming_sword",
            new Sword(-2.4F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item AXE = registerItem("axe",
            new Axe(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item BROAD_AXE = registerItem("broad_axe",
            new Axe(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item CROOKED_AXE = registerItem("crooked_axe",
            new Axe(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item STRAIGHT_CROOKED_AXE = registerItem("straight_crooked_axe",
            new Axe(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item MACE = registerItem("mace",
            new Mace(-3.0F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item SPIKED_MACE = registerItem("spiked_mace",
            new Mace(-3.0F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item FLAIL = registerItem("flail",
            new Flail(-2.8F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item BALL_FLAIL = registerItem("ball_flail",
            new Flail(-2.8F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item HAMMER = registerItem("hammer",
            new Hammer(-2.8F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item WAR_HAMMER = registerItem("war_hammer",
            new Hammer(-2.8F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item LONGSWORD = registerItem("longsword",
            new LongSword(-2.5F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item V_LONGSWORD = registerItem("v_longsword",
            new LongSword(-2.5F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item FALCHION = registerItem("falchion",
            new Falchion(-2.5F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item SCIMITAR = registerItem("scimitar",
            new Falchion(-2.5F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item KATANA = registerItem("katana",
            new Katana(-2.4F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item PITCHFORK = registerItem("pitchfork",
            new Pitchfork(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item SPEAR = registerItem("spear",
            new Spear(-2.4F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item PIKE = registerItem("pike",
            new Pike(-2.8F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item BILLHOOK = registerItem("billhook",
            new Billhook(-2.8F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item GLAIVE = registerItem("glaive",
            new Glaive(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item CURVED_GLAIVE = registerItem("curved_glaive",
            new Glaive(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item HALBERD = registerItem("halberd",
            new Halberd(-2.8F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item LANCE = registerItem("lance",
            new Lance(-3.0F, new FabricItemSettings().maxDamage(1)), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item WARSWORD = registerItem("warsword",
            new WarSword(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item WARSWORD_CLAYMORE = registerItem("warsword_claymore",
            new WarSword(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item WARSWORD_FLAMBERGE = registerItem("warsword_flamberge",
            new WarSword(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    public static final Item WARSWORD_ZWEIHANDER = registerItem("warsword_zweihander",
            new WarSword(-2.6F, new FabricItemSettings()), ModItemGroups.TOOLS_AND_WEAPONS);

    private static <T extends Item> T registerItem(String name, T item, RegistryKey<ItemGroup> itemGroupKey) {
        Registry.register(Registries.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name), item);
        addToItemGroup(item, itemGroupKey);
        return item;
    }

    private static void addToItemGroup(Item item, RegistryKey<ItemGroup> itemGroupKey) {
        ItemGroupEvents.modifyEntriesEvent(itemGroupKey).register(entries -> entries.add(new ItemStack(item)));
    }

    public static void registerModItems() {
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
