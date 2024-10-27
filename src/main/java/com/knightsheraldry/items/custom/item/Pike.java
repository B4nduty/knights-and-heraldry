package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.util.KHDamageCalculator;

public class Pike extends KHWeapons {
    public Pike(float attackSpeed, Settings settings, KHDamageCalculator.DamageType onlyDamageType) {
        super(attackSpeed, settings, onlyDamageType);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                5.5d, //1st Distance
                6.2d, //2nd Distance
                7.0d, //3rd Distance
                7.8d, //4th Distance
                8.5d  //5th Distance
        };
    }
}