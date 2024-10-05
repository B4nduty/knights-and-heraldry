package com.knightsheraldry.util;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class KHTags {
    public static class Weapon {
        public static final TagKey<Item> KH_WEAPONS = createTag("kh_weapons");
        public static final TagKey<Item> KH_WEAPONS_3D = createTag("kh_weapons_3d");
        public static final TagKey<Item> KH_WEAPONS_SHIELD = createTag("kh_weapons_shield");
        public static final TagKey<Item> KH_WEAPONS_DAMAGE_BEHIND = createTag("kh_weapons_damage_behind");
        public static final TagKey<Item> KH_WEAPONS_IGNORES_ARMOR = createTag("kh_weapons_ignores_armor");
        public static final TagKey<Item> KH_WEAPONS_BLUDGEONING = createTag("kh_weapons_bludgeoning");
        public static final TagKey<Item> KH_WEAPONS_ONLY_BLUDGEONING = createTag("kh_weapons_only_bludgeoning");
        public static final TagKey<Item> KH_WEAPONS_PIERCING = createTag("kh_weapons_piercing");
        public static final TagKey<Item> KH_WEAPONS_ONLY_PIERCING = createTag("kh_weapons_only_piercing");
        public static final TagKey<Item> KH_WEAPONS_DISABLE_SHIELD = createTag("kh_weapons_disable_shield");
        public static final TagKey<Item> KH_WEAPONS_BYPASS_BLOCK = createTag("kh_weapons_bypass_block");
        public static final TagKey<Item> KH_WEAPONS_BLUDGEONING_TO_PIERCING = createTag("kh_weapons_bludgeoning_to_piercing");
        public static final TagKey<Item> KH_WEAPONS_HARVEST = createTag("kh_weapons_harvest");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name));
        }
    }

    public static class Armors {
        public static final TagKey<Item> KH_UNDER_ARMORS = createTag("kh_under_armors");
        public static final TagKey<Item> KH_ALWAYS_WEARABLE = createTag("kh_always_wearable");
        public static final TagKey<Item> VISORED_HELMET = createTag("visored_helmet");
        public static final TagKey<Item> KH_DEFLECTIVE_ARMOR = createTag("kh_deflective_armor");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name));
        }
    }
}
