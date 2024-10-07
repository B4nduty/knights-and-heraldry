package com.knightsheraldry.items.custom.item;

public class LongSword extends KHWeapons {
    public LongSword(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 7.0F, 10.5F, 7.0F, 3.5F, //Slashing
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Piercing
                0.0F, 5.0F, 7.5F, 5.0F, 2.5F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher than its next value
                1.0d, //1st Distance
                2.3d, //2nd Distance
                2.8d, //3rd Distance
                3.1d, //4th Distance
                4.0d  //5th Distance
        };
    }

    @Override
    public int getAnimation() {
        // How many animations has your item
        return 3;
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