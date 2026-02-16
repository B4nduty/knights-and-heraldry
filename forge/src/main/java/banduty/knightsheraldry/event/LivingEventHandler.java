package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.stoneycore.util.data.keys.NBTDataHelper;
import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import banduty.stoneycore.util.data.playerdata.PDKeys;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingEventHandler {

    private static final HashMap<UUID, Integer> arrowTimers = new HashMap<>();
    private static final int SWALLOWTAIL_ARROW_COOLDOWN = 20 * 30;

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            handleSwallowtailArrowTimer(player);
        }
    }

    private static void handleSwallowtailArrowTimer(Player player) {
        int count = NBTDataHelper.get((IEntityDataSaver) player, PDKeys.SWALLOWTAIL_ARROW_COUNT, 0);
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
                NBTDataHelper.set((IEntityDataSaver) player, PDKeys.SWALLOWTAIL_ARROW_COUNT, count - 1);
            }

            arrowTimers.put(uuid, timer);
        }
    }
}