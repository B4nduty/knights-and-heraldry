package com.knightsheraldry.items.custom.item;

public class Poleaxe extends KHWeapons {
    public Poleaxe(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Slashing
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Piercing
                0.0F, 5.0F, 7.5F, 5.0F, 2.5F //Bludgeoning
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

    @Override
    public int getAnimation() {
        // How many animations has your item
        return 2;
    }

    @Override
    public int[] getPiercingAnimation() {
        // In which animation is Piercing Damage applied
        // Item needs to have Tag KH_WEAPONS_PIERCING
        // Max length of index is 2
        return new int[] {
                2
        };
    }
}