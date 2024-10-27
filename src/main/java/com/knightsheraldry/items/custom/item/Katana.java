package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.util.KHDamageCalculator;

public class Katana extends KHWeapons {
    public Katana(float attackSpeed, Settings settings, KHDamageCalculator.DamageType onlyDamageType) {
        super(attackSpeed, settings, onlyDamageType);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Slashing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                1.0d, //1st Distance
                2.3d, //2nd Distance
                2.8d, //3rd Distance
                3.3d, //4th Distance
                4.0d  //5th Distance
        };
    }
}
