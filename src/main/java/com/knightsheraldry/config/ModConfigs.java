package com.knightsheraldry.config;

import blue.endless.jankson.Comment;
import com.knightsheraldry.KnightsHeraldry;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = KnightsHeraldry.MOD_ID)
@Config(name = KnightsHeraldry.MOD_ID, wrapperName = "KHConfig")
public class ModConfigs {
    @Comment("Vanilla Weapons deals 0 Damage")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean getVanillaWeaponsDamage0 = false;

    @Comment("Use Stamina on or while Blocking")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean getBlocking = true;

    @Comment("Lance Weapons can Damage Tamed Entities")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean getDamageTamedEntities = false;

    @Comment("WarDart throw Cooldown")
    @RangeConstraint(min = 0, max = 180)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int wardartCooldown = 15;

    @Comment("Damage Indicator")
    public boolean getDamageIndicator = false;

    @Comment("Visored Helmet Overlay")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean getVisoredHelmet = true;

    @Sync(Option.SyncMode.NONE)
    @Comment("Low Stamina Indicator [Experimental]")
    public boolean getLowStaminaIndicator = true;

    @RestartRequired
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    @Comment("Muzzles Smoke Particles Time Active (In Seconds)")
    public int getMuzzlesSmokeParticlesTime = 60;

    @Sync(Option.SyncMode.NONE)
    @Comment("Parry [Experimental]")
    public boolean getParry = true;
}