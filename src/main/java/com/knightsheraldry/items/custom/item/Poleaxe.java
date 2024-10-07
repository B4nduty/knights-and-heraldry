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
                3.7d, //2nd Distance
                4.5d, //3rd Distance
                5.3d, //4th Distance
                6.0d  //5th Distance
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