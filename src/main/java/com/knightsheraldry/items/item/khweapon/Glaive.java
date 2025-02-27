package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.item.SwordItem;

public class Glaive extends SwordItem implements SCWeapon {
    public Glaive(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public int getAnimation() {
        return 3;
    }

    @Override
    public SCDamageCalculator.DamageType getOnlyDamageType() {
        return null;
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                4.0d, //1st Distance
                4.7d, //2nd Distance
                5.5d, //3rd Distance
                6.3d, //4th Distance
                7.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                KnightsHeraldry.getConfig().getGlaiveDamageSlashing(),
                KnightsHeraldry.getConfig().getGlaiveDamagePiercing(),
                0.0F //Bludgeoning
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                1, 2
        };
    }
}