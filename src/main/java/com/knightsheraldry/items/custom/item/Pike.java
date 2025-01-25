package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.items.ModToolMaterials;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.item.SwordItem;

public class Pike extends SwordItem implements KHWeapon {
    public Pike(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                5.5d, //1st Distance
                6.2d, //2nd Distance
                7.0d, //3rd Distance
                7.8d, //4th Distance
                8.5d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[0];
    }

    @Override
    public int getAnimation() {
        return 0;
    }

    @Override
    public KHDamageCalculator.DamageType getOnlyDamageType() {
        return KHDamageCalculator.DamageType.PIERCING;
    }
}