package com.knightsheraldry.items.custom;

public class Sword extends KHWeapons {
    public Sword(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                // For activate Bludgeoning, you need KH_WEAPONS_BLUDGEONING Item Tag
                // If you want Piercing only, you need KH_WEAPONS_ONLY_PIERCING Item Tag
                1.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Slashing
                1.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Piercing
                1.0F, 4.0F, 6.0F, 4.0F, 2.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher or equal than its next value
                1.0d, //1st Distance
                2.0d, //2nd Distance
                2.3d, //3rd Distance
                2.5d, //4th Distance
                3.0d //5th Distance
        };
    }

    @Override
    public int getAnimation() {
        // How many animations has your item
        return 3;
    }

    @Override
    public int getPiercingAnimation() {
        // In which animation is Piercing Damage applied
        // Item needs to have Tag KH_WEAPONS_PIERCING
        return 3;
    }
}