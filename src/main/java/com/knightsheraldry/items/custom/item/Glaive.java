package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.util.KHDamageCalculator;

public class Glaive extends KHWeapons {
    public Glaive(float attackSpeed, Settings settings, KHDamageCalculator.DamageType onlyDamageType) {
        super(attackSpeed, settings, onlyDamageType);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Slashing
                0.0F, 3.0F, 4.5F, 3.0F, 1.5F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                4.0d, //1st Distance
                4.7d, //2nd Distance
                5.5d, //3rd Distance
                6.3d, //4th Distance
                7.0d  //5th Distance
        };
    }

    @Override
    public int getAnimation() {
        return 3;
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                1, 2
        };
    }
}