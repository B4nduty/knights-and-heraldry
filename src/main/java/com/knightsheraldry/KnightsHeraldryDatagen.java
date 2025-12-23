package com.knightsheraldry;

import com.knightsheraldry.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class KnightsHeraldryDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<ModAccessoriesDefinitionsProvider>) ModAccessoriesDefinitionsProvider::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<ModArmorDefinitionsProvider>) ModArmorDefinitionsProvider::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<ModWeaponDefinitionsProvider>) ModWeaponDefinitionsProvider::new);
    }
}
