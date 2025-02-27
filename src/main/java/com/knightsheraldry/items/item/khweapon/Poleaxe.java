package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.minecraft.item.SwordItem;

public class Poleaxe extends SwordItem implements SCWeapon {
    public Poleaxe(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public int getAnimation() {
        return 2;
    }

    @Override
    public SCDamageCalculator.DamageType getOnlyDamageType() {
        return null;
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
                KnightsHeraldry.getConfig().getPoleAxeDamageSlashing(),
                KnightsHeraldry.getConfig().getPoleAxeDamagePiercing(),
                KnightsHeraldry.getConfig().getPoleAxeDamageBludgeoning()
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                2
        };
    }
}