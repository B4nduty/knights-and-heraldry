package com.knightsheraldry.particle;

import com.knightsheraldry.KnightsHeraldry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType MUZZLES_SMOKE_PARTICLE =
            registerParticle("muzzles_smoke_particle", FabricParticleTypes.simple());
    public static final DefaultParticleType MUZZLES_FLASH_PARTICLE =
            registerParticle("muzzles_flash_particle", FabricParticleTypes.simple());


    private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(KnightsHeraldry.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        KnightsHeraldry.LOGGER.info("Registering Particles for " + KnightsHeraldry.MOD_ID);
    }
}