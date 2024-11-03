package com.knightsheraldry.util.itemdata;

import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.UseAction;

public record RangeWeaponConfig(
        DamageSettings damageSettings,
        AmmoRequirement ammoRequirement,
        SoundSettings soundSettings,
        UseAction useAction,
        int rechargeTime,
        boolean needsFlintAndSteel,
        int cooldown
) {
    public RangeWeaponConfig(
            DamageSettings damageSettings,
            AmmoRequirement ammoRequirement,
            SoundSettings soundSettings,
            UseAction useAction,
            int rechargeTime,
            boolean needsFlintAndSteel,
            int cooldown
    ) {
        this.damageSettings = damageSettings;
        this.ammoRequirement = (ammoRequirement != null) ? ammoRequirement : new AmmoRequirement(0, null, null, 0, null, null, 0, null, null);
        this.soundSettings = soundSettings;
        this.useAction = useAction;
        this.rechargeTime = rechargeTime;
        this.needsFlintAndSteel = needsFlintAndSteel;
        this.cooldown = cooldown;
    }

    public record DamageSettings(KHDamageCalculator.DamageType damageType, int maxUseTime, float damage, double blockRange) {
        public float velocity () {
            return (float) (blockRange() / 32f);
        }}

    public record AmmoRequirement(
            int amountFirstItem, Item firstItem, Item firstItem2nOption,
            int amountSecondItem, Item secondItem, Item secondItem2nOption,
            int amountThirdItem, Item thirdItem, Item thirdItem2nOption
    ) {}

    public record SoundSettings(SoundEvent... soundEvents) {}
}