package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.util.KHDamageCalculator;

public class Sword extends KHWeapons {
    public Sword(float attackSpeed, Settings settings, KHDamageCalculator.DamageType onlyDamageType) {
        super(attackSpeed, settings, onlyDamageType);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Slashing
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Piercing
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                1.0d, //1st Distance
                2.2d, //2nd Distance
                2.7d, //3rd Distance
                3.2d, //4th Distance
                3.5d  //5th Distance
        };
    }

    @Override
    public int getAnimation() {
        return 3;
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                3
        };
    }
}