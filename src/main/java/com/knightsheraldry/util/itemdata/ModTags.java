package com.knightsheraldry.util.itemdata;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public enum ModTags {
    DYES("dyes");

    private final TagKey<Item> tag;

    ModTags(String name) {
        this.tag = TagKey.create(Registries.ITEM, new ResourceLocation(KnightsHeraldry.MOD_ID, name));
    }

    public TagKey<Item> getTag() {
        return tag;
    }
}
