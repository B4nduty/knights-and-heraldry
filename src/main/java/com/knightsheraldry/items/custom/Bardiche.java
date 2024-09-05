package com.knightsheraldry.items.custom;

public class Bardiche extends KHWeapons {
    public Bardiche(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 8.0F, 12.0F, 8.0F, 4.0F, //Slashing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher or equal than its next value
                3.0d, //1st Distance
                3.2d, //2nd Distance
                3.5d, //3rd Distance
                3.8d, //4th Distance
                4.0d //5th Distance
        };
    }
}
