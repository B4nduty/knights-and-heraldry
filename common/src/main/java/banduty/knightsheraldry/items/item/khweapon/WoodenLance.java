package banduty.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.combat.damagetype.SCDamageType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class WoodenLance extends Lance {
    int defaultColor = 10511680;

    public WoodenLance(float attackSpeed, Properties properties, SCDamageType onlyDamageType) {
        super(attackSpeed, properties, onlyDamageType);
    }

    public int getColor(ItemStack stack) {
        DyedItemColor dyedItemColor = stack.get(DataComponents.DYED_COLOR);
        return dyedItemColor != null ? dyedItemColor.rgb() : this.defaultColor;
    }

    @Override
    public double getRange() {
        return 4.0d;
    }

    @Override
    public float getLanceDamage() {
        return 0.1f;
    }
}