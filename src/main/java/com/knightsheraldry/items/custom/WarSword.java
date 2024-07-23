package com.knightsheraldry.items.custom;

public class WarSword extends KHWeaponsTemplate {
    public WarSword(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                1.0F, 8.0F, 12.0F, 8.0F, 4.0F, //Slashing
                1.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Piercing
                1.0F, 5.0F, 7.25F, 5.0F, 2.5F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher than it's next value
                1.0d, //1st Distance
                2.0d, //2nd Distance
                2.5d, //3rd Distance
                3.5d, //4th Distance
                4.0d //5th Distance
        };
    }
}