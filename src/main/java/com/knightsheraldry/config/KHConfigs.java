package com.knightsheraldry.config;

import blue.endless.jankson.Comment;
import com.knightsheraldry.KnightsHeraldry;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = KnightsHeraldry.MOD_ID)
@Config(name = KnightsHeraldry.MOD_ID, wrapperName = "KHConfig")
public class KHConfigs {
    @SectionHeader("lance")
    @Comment("Lance Cooldown")
    @RangeConstraint(min = 0, max = 180)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int getLanceCooldown = 30;
    @Comment("Damage Tamed Entities")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean getDamageTamedEntities = false;

    @SectionHeader("warDart")
    @Comment("WarDart throw Cooldown")
    @RangeConstraint(min = 0, max = 180)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int getWardartCooldown = 15;
}