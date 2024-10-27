package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.util.KHDamageCalculator;

public class LongSword extends KHWeapons {
    public LongSword(float attackSpeed, Settings settings, KHDamageCalculator.DamageType onlyDamageType) {
        super(attackSpeed, settings, onlyDamageType);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 7.0F, 10.5F, 7.0F, 3.5F, //Slashing
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Piercing
                0.0F, 5.0F, 7.5F, 5.0F, 2.5F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                1.0d, //1st Distance
                2.3d, //2nd Distance
                2.8d, //3rd Distance
                3.1d, //4th Distance
                4.0d  //5th Distance
        };
    }

    @Override
    public int getAnimation() {
        return 3;
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                2
        };
    }
}