package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.SmithingHammer;
import com.knightsheraldry.items.custom.WarSword;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SMITHING_HAMMER = registerItem("smithing_hammer",
            new SmithingHammer(new FabricItemSettings().maxCount(1)));

    public static final Item WARSWORD = registerItem("warsword",
            new WarSword(ToolMaterials.IRON, 1, -2.6F, new FabricItemSettings().maxDamage(-1)));

    public static final Item WARSWORD_CLAYMORE = registerItem("warsword_claymore",
            new WarSword(ToolMaterials.IRON, 1, -2.6F, new FabricItemSettings().maxDamage(-1)));

    public static final Item WARSWORD_FLAMBERGE = registerItem("warsword_flamberge",
            new WarSword(ToolMaterials.IRON, 1, -2.6F, new FabricItemSettings().maxDamage(-1)));

    public static final Item WARSWORD_ZWEIHANDER = registerItem("warsword_zweihander",
            new WarSword(ToolMaterials.IRON, 1, -2.6F, new FabricItemSettings().maxDamage(-1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name), item);
    }

    public static void registerModItems() {
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
