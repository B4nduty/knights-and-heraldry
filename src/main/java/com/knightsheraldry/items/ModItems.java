package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item WEAPON_1 = registerItem("weapon_1",
            new SwordItem(ToolMaterials.WOOD, 1, -2.4F, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name), item);
    }

    public static void registerModItems() {
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
