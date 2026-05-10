package banduty.knightsheraldry;

import banduty.knightsheraldry.datagen.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KnightsHeraldryForgeDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new ModModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModAccessoriesDefinitionsProvider(packOutput));
        generator.addProvider(event.includeServer(), new ModArmorDefinitionsProvider(packOutput));
        generator.addProvider(event.includeServer(), new ModWeaponDefinitionsProvider(packOutput));
    }
}
