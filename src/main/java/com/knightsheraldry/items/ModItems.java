package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.Dagger;
import com.knightsheraldry.items.custom.SmithingHammer;
import com.knightsheraldry.items.custom.Stiletto;
import com.knightsheraldry.items.custom.WarSword;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SMITHING_HAMMER = registerItem("smithing_hammer",
            new SmithingHammer(new FabricItemSettings().maxCount(1)));

    public static final Item WARSWORD = registerItem("warsword",
            new WarSword(-2.6F, new FabricItemSettings()));

    public static final Item WARSWORD_CLAYMORE = registerItem("warsword_claymore",
            new WarSword(-2.6F, new FabricItemSettings()));

    public static final Item WARSWORD_FLAMBERGE = registerItem("warsword_flamberge",
            new WarSword(-2.6F, new FabricItemSettings()));

    public static final Item WARSWORD_ZWEIHANDER = registerItem("warsword_zweihander",
            new WarSword(-2.6F, new FabricItemSettings()));

    public static final Item DAGGER = registerItem("dagger",
            new Dagger(-2F, new FabricItemSettings()));

    public static final Item STILETTO = registerItem("stiletto",
            new Stiletto(-2F, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name), item);
    }

    public static void registerModItems() {
        KnightsHeraldry.LOGGER.info("Registering Mod Items for " + KnightsHeraldry.MOD_ID);
    }
}
