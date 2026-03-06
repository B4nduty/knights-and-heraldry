package banduty.knightsheraldry;

import banduty.knightsheraldry.effect.ModEffects;
import banduty.knightsheraldry.entity.ModEntities;
import banduty.knightsheraldry.event.CanEquipHandler;
import banduty.knightsheraldry.event.StartTickHandler;
import banduty.knightsheraldry.event.UseItemHandler;
import banduty.knightsheraldry.items.ModItemGroups;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.recipes.ModRecipes;
import banduty.knightsheraldry.sounds.ModSounds;
import banduty.knightsheraldry.util.itemdata.KHHelmetDeco;
import io.wispforest.accessories.api.events.CanEquipCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class KnightsHeraldryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        KnightsHeraldry.init();

        ModEffects.registerEffects();
        ModEntities.registerEntities();
        ModItems.registerItems();
        ModItemGroups.registerItemGroups();
        ModSounds.registerSounds();
        KHHelmetDeco.registerHelmetDeco();
        ModRecipes.registerRecipes();

        CanEquipCallback.EVENT.register(new CanEquipHandler());
        ServerTickEvents.START_SERVER_TICK.register(new StartTickHandler());
        UseItemCallback.EVENT.register(new UseItemHandler());


    }
}
