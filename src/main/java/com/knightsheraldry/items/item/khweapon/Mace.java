package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.item.SwordItem;

public class Mace extends SwordItem implements SCWeapon {
    public Mace(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                2.0d, //1st Distance
                2.4d, //2nd Distance
                2.9d, //3rd Distance
                3.4d, //4th Distance
                4.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                0.0F, //Slashing
                0.0F, //Piercing
                KnightsHeraldry.getConfig().getMaceDamageBludgeoning()
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
    public SCDamageCalculator.DamageType getOnlyDamageType() {
        return SCDamageCalculator.DamageType.BLUDGEONING;
    }
}
