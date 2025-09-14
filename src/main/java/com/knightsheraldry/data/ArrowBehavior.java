package com.knightsheraldry.data;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ArrowBehavior {
    public Identifier targetItemId;

    public boolean requireOnFire = true;
    public int groundBurnTicks = 100;
    public double aoeRadius = 5.0D;

    public final List<EffectEntry> effects = new ArrayList<>();

    public final SmokeConfig smoke = new SmokeConfig();
    public final IgniteBlocksConfig igniteBlocks = new IgniteBlocksConfig();

    public static class EffectEntry {
        public Identifier id;
        public int duration = 100;
        public int amplifier = 0;
    }

    public static class SmokeConfig {
        public boolean enabled = true;
        public Identifier particle = new Identifier("minecraft", "campfire_cosy_smoke");
        public int count = 80;
        public double maxSpeed = 0.1D;
    }

    public static class IgniteBlocksConfig {
        public int radiusX = 2;
        public int radiusY = 2;
        public int radiusZ = 2;
        public float chance = 0.1f;
    }
}