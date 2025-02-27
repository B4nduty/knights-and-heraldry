package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.item.SwordItem;

public class Rapier extends SwordItem implements SCWeapon {
    public Rapier(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                2.5d, //1st Distance
                2.9d, //2nd Distance
                3.4d, //3rd Distance
                4.0d, //4th Distance
                4.5d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                0.0F, //Slashing
                KnightsHeraldry.getConfig().getRapierDamagePiercing(),
                0.0F //Bludgeoning
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
        return SCDamageCalculator.DamageType.PIERCING;
    }
}