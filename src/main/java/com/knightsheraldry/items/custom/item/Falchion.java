package com.knightsheraldry.items.custom.item;

public class Falchion extends KHWeapons {
    public Falchion(float attackSpeed, Settings settings) {
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
                1.0d, //1st Distance
                1.7d, //2nd Distance
                2.3d, //3rd Distance
                2.5d, //4th Distance
                3.0d //5th Distance
        };
    }
}
