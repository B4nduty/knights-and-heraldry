package com.knightsheraldry.util.itemdata;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public enum KHTags {
    /**
     * Armor that can deflect SwallowTail Arrow
     */
    DEFLECTIVE_ARMOR("deflective_armor");

    private final TagKey<Item> tag;

    KHTags(String name) {
        this.tag = TagKey.of(RegistryKeys.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name));
    }

    public TagKey<Item> getTag() {
        return tag;
    }
}