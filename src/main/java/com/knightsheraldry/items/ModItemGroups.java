package com.knightsheraldry.items;

import com.knightsheraldry.KnightsHeraldry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> TOOLS_AND_WEAPONS = registerItemGroup("tools_and_weapons", () -> new ItemStack(ModItems.SMITHING_HAMMER));

    private static RegistryKey<ItemGroup> registerItemGroup(String name, Supplier<ItemStack> iconSupplier) {
        Text displayName = Text.translatable("itemgroup.knightsheraldry.%s".formatted(name));
        ItemGroup itemGroup = FabricItemGroup.builder()
                .icon(iconSupplier)
                .displayName(displayName)
                .build();
        RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(KnightsHeraldry.MOD_ID, name));
        Registry.register(Registries.ITEM_GROUP, key, itemGroup);
        return key;
    }

    public static void registerItemGroups() {
        KnightsHeraldry.LOGGER.info("Registering Item Groups for " + KnightsHeraldry.MOD_ID);
    }
}