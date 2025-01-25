package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.items.ModToolMaterials;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.item.SwordItem;

public class MorningStar extends SwordItem implements KHWeapon {
    public MorningStar(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                3.0d, //1st Distance
                3.7d, //2nd Distance
                4.5d, //3rd Distance
                5.3d, //4th Distance
                6.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Piercing
                0.0F, 8.0F, 12.0F, 8.0F, 4.0F //Bludgeoning
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[0];
    }

    @Override
    public int getAnimation() {
        return 0;
    }

    @Override
    public KHDamageCalculator.DamageType getOnlyDamageType() {
        return KHDamageCalculator.DamageType.BLUDGEONING;
    }
}