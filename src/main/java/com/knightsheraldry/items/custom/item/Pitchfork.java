package com.knightsheraldry.items.custom.item;

public class Pitchfork extends KHWeapons {
    public Pitchfork(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                0.0F, 5.0F, 7.5F, 5.0F, 2.5F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher or equal than its next value
                3.3d, //1st Distance
                3.5d, //2nd Distance
                3.7d, //3rd Distance
                3.8d, //4th Distance
                4.0d //5th Distance
        };
    }
}