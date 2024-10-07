package com.knightsheraldry.items.custom.item;

public class Rapier extends KHWeapons {
    public Rapier(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
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
                // Values cannot be higher or equal than its next value
                2.5d, //1st Distance
                2.9d, //2nd Distance
                3.4d, //3rd Distance
                4.0d, //4th Distance
                4.5d  //5th Distance
        };
    }
}