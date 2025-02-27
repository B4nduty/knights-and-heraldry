package com.knightsheraldry.config;

import blue.endless.jankson.Comment;
import com.knightsheraldry.KnightsHeraldry;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = KnightsHeraldry.MOD_ID)
@Config(name = KnightsHeraldry.MOD_ID, wrapperName = "KHConfig")
public class KHConfigs {
    @SectionHeader("dagger")
    @Comment("Dagger Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getDaggerDamageSlashing = 9.0F;
    @Comment("Dagger Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getDaggerDamagePiercing = 4.5F;

    @SectionHeader("stiletto")
    @Comment("Stiletto Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getStilettoDamagePiercing = 9.0F;

    @SectionHeader("rapier")
    @Comment("Rapier Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getRapierDamagePiercing = 9.0F;

    @SectionHeader("sword")
    @Comment("Sword Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getSwordDamageSlashing = 9.0F;
    @Comment("Sword Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getSwordDamagePiercing = 6.0F;
    @Comment("Sword Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getSwordDamageBludgeoning = 6.0F;

    @SectionHeader("axe")
    @Comment("Axe Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getAxeDamageSlashing = 12.0F;

    @SectionHeader("mace")
    @Comment("Mace Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getMaceDamageBludgeoning = 12.0F;

    @SectionHeader("flail")
    @Comment("Flail Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getFlailDamageBludgeoning = 7.5F;

    @SectionHeader("hammer")
    @Comment("Hammer Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getHammerDamagePiercing = 6.0F;
    @Comment("Hammer Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getHammerDamageBludgeoning = 9.0F;

    @SectionHeader("longSword")
    @Comment("LongSword Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getLongSwordDamageSlashing = 10.5F;
    @Comment("LongSword Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getLongSwordDamagePiercing = 6.0F;
    @Comment("LongSword Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getLongSwordDamageBludgeoning = 7.5F;

    @SectionHeader("falchion")
    @Comment("Falchion Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getFalchionDamageSlashing = 12.0F;

    @SectionHeader("pitchfork")
    @Comment("Pitchfork Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getPitchforkDamagePiercing = 7.5F;

    @SectionHeader("spear")
    @Comment("Spear Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getSpearDamagePiercing = 9.0F;

    @SectionHeader("pike")
    @Comment("Pike Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getPikeDamagePiercing = 9.0F;

    @SectionHeader("billhook")
    @Comment("Billhook Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getBillhookDamageSlashing = 6.0F;
    @Comment("Billhook Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getBillhookDamagePiercing = 9.0F;

    @SectionHeader("glaive")
    @Comment("Glaive Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getGlaiveDamageSlashing = 9.0F;
    @Comment("Glaive Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getGlaiveDamagePiercing = 4.5F;

    @SectionHeader("halberd")
    @Comment("Halberd Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getHalberdDamageSlashing = 9.0F;
    @Comment("Halberd Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getHalberdDamagePiercing = 10.5F;

    @SectionHeader("lance")
    @Comment("Lance Cooldown")
    @RangeConstraint(min = 0, max = 180)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int getLanceCooldown = 30;
    @Comment("Damage Tamed Entities")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean getDamageTamedEntities = false;

    @SectionHeader("poleAxe")
    @Comment("PoleAxe Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getPoleAxeDamageSlashing = 9.0F;
    @Comment("PoleAxe Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getPoleAxeDamagePiercing = 6.0F;
    @Comment("PoleAxe Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getPoleAxeDamageBludgeoning = 7.5F;

    @SectionHeader("poleHammer")
    @Comment("PoleHammer Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getPoleHammerDamagePiercing = 7.5F;
    @Comment("PoleHammer Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getPoleHammerDamageBludgeoning = 10.5F;

    @SectionHeader("morningStar")
    @Comment("MorningStar Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getMorningStarDamageBludgeoning = 12.0F;

    @SectionHeader("bardiche")
    @Comment("Bardiche Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getBardicheDamageSlashing = 12.0F;

    @SectionHeader("warSword")
    @Comment("WarSword Damage Slashing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getWarSwordDamageSlashing = 12.0F;
    @Comment("WarSword Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getWarSwordDamagePiercing = 9.0F;
    @Comment("WarSword Damage Bludgeoning")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getWarSwordDamageBludgeoning = 7.5F;

    @SectionHeader("warDart")
    @Comment("WarDart Damage Piercing")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float getWarDartDamagePiercing = 13.5F;

    @Comment("WarDart throw Cooldown")
    @RangeConstraint(min = 0, max = 180)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int getWardartCooldown = 15;
}