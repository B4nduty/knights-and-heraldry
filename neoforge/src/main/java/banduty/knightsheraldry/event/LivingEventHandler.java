package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.stoneycore.util.data.entitydata.IEntityDataSaver;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.HashMap;
import java.util.UUID;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class LivingEventHandler {

    private static final HashMap<UUID, Integer> arrowTimers = new HashMap<>();
    private static final int SWALLOWTAIL_ARROW_COOLDOWN = 20 * 30;

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player) {
            handleSwallowtailArrowTimer(player);
        }
    }

    private static void handleSwallowtailArrowTimer(Player player) {
        int count = ((IEntityDataSaver) player).stoneycore$getPersistentData().getInt("swallowtailArrowCount");
        if (count < 0) return;

        int stuckArrows = player.getArrowCount();
        if (stuckArrows > 0) {
            UUID uuid = player.getUUID();
            int timer = arrowTimers.getOrDefault(uuid, 0);

            if (timer <= 0) {
                timer = SWALLOWTAIL_ARROW_COOLDOWN - (20 * stuckArrows);
            }

            timer--;

            if (timer <= 0) {
                ((IEntityDataSaver) player).stoneycore$getPersistentData().putInt("swallowtailArrowCount", count - 1);
            }

            arrowTimers.put(uuid, timer);
        }
    }
}