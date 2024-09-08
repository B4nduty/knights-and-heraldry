package com.knightsheraldry.items.custom.item;

public class Hammer extends KHWeapons{
    public Hammer(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Piercing
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher or equal than its next value
                2.0d, //1st Distance
                2.2d, //2nd Distance
                2.5d, //3rd Distance
                2.8d, //4th Distance
                3.0d //5th Distance
        };
    }
}
