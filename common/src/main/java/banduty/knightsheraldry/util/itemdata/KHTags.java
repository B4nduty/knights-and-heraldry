package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public enum KHTags {
    REPAIR_KIT_WOOL("repair_kit/wool"),
    REPAIR_KIT_LEATHER("repair_kit/leather"),
    REPAIR_KIT_CHAIN("repair_kit/chain"),
    REPAIR_KIT_IRON("repair_kit/iron"),
    REPAIR_KIT_GOLD("repair_kit/gold");

    private final TagKey<Item> tag;

    KHTags(String name) {
        this.tag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, name));
    }

    public TagKey<Item> getTag() {
        return tag;
    }
}