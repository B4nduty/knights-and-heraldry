package com.knightsheraldry.items.custom;

public class Rapier extends KHWeapons {
    public Rapier(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                // For activate Bludgeoning, you need KH_WEAPONS_BLUDGEONING Item Tag
                // If you want Piercing only, you need KH_WEAPONS_ONLY_PIERCING Item Tag
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                1.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher or equal than its next value
                2.5d, //1st Distance
                2.8d, //2nd Distance
                3.0d, //3rd Distance
                3.5d, //4th Distance
                3.6d //5th Distance
        };
    }
}