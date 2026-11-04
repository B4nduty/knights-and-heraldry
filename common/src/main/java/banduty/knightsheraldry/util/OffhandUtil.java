package banduty.knightsheraldry.util;

import banduty.knightsheraldry.util.itemdata.KHTags;
import banduty.stoneycore.combat.melee.ShieldUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public final class OffhandUtil {
    private OffhandUtil() {}

    /**
     * Weapons in the {@code knightsheraldry:two_handed} tag, and versatile weapons held in their two-handed grip,
     * are wielded with both hands, so the offhand item is hidden (and Better Combat treats the attack as
     * two-handed) unless the offhand holds a blocking shield.
     */
    public static boolean shouldHideOffhand(LivingEntity entity) {
        ItemStack mainHand = entity.getMainHandItem();
        return (mainHand.is(KHTags.TWO_HANDED.getTag()) || GripUtil.isTwoHandedGrip(mainHand))
                && !ShieldUtil.canBlock(entity.getOffhandItem());
    }
}
