package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.item.khweapon.Lance;
import banduty.knightsheraldry.util.playerdata.PlayerVelocity;
import banduty.stoneycore.util.data.entitydata.IEntityDataSaver;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerTickHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ItemStack lanceStack = getLanceStack(player);

        if (lanceStack.isEmpty()) return;

        if (Boolean.FALSE.equals(lanceStack.get(SCDataComponents.CHARGED.get()))) return;

        float velocity = calculateVelocity(player);

        if (player instanceof IEntityDataSaver dataSaver)
            PlayerVelocity.updateSpeedHistory(dataSaver, velocity);
    }

    private static ItemStack getLanceStack(Player player) {
        if (player.getMainHandItem().getItem() instanceof Lance) return player.getMainHandItem();
        if (player.getOffhandItem().getItem() instanceof Lance) return player.getOffhandItem();

        return ItemStack.EMPTY;
    }

    private static float calculateVelocity(ServerPlayer player) {
        CompoundTag playerData = ((IEntityDataSaver) player).stoneycore$getPersistentData();

        double prevX = playerData.getDouble("lancePrevX");
        double prevZ = playerData.getDouble("lancePrevZ");

        Entity entity = player.getVehicle();
        if (entity == null) entity = player;

        Vec3 delta = new Vec3(entity.getX() - prevX, 0, entity.getZ() - prevZ);

        playerData.putDouble("lancePrevX", entity.getX());
        playerData.putDouble("lancePrevZ", entity.getZ());

        return (float) delta.length();
    }
}