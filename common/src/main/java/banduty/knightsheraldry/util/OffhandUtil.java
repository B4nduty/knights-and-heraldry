package banduty.knightsheraldry.util;

import banduty.knightsheraldry.util.itemdata.KHTags;
import banduty.stoneycore.combat.melee.ShieldUtil;
import net.minecraft.world.entity.LivingEntity;

public final class OffhandUtil {
    private OffhandUtil() {}

    /**
     * Weapons in the {@code knightsheraldry:two_handed} tag are wielded with both hands, so the offhand item
     * is hidden (and Better Combat treats the attack as two-handed) unless the offhand holds a blocking shield.
     */
    public static boolean shouldHideOffhand(LivingEntity entity) {
        return entity.getMainHandItem().is(KHTags.TWO_HANDED.getTag())
                && !ShieldUtil.canBlock(entity.getOffhandItem());
    }
}
