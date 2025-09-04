package com.knightsheraldry.util.itemdata;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public enum ModTags {
    DYES("dyes");

    private final TagKey<Item> tag;

    ModTags(String name) {
        this.tag = TagKey.of(RegistryKeys.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name));
    }

    public TagKey<Item> getTag() {
        return tag;
    }
}
