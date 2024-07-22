package com.knightsheraldry.util;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> KH_WEAPONS = createTag("kh_weapons");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name));
        }
    }
}
