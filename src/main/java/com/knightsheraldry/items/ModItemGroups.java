package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup KNIGHTS_AND_HERALDRY_TOOLS_AND_WEAPONS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(KnightsHeraldry.MOD_ID, "tools_and_weapons"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.knightsheraldry.title.tools_and_weapons"))
                    .icon(() -> new ItemStack(ModItems.SMITHING_HAMMER)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SMITHING_HAMMER);
                        entries.add(ModItems.WARSWORD);
                    })
                    .build());

    public static void registerItemGroups() {
        KnightsHeraldry.LOGGER.info("Registering Item Groups for " + KnightsHeraldry.MOD_ID);
    }
}
