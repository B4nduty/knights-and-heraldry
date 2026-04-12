package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.item.khweapon.Lance;
import banduty.knightsheraldry.util.playerdata.PlayerVelocity;
import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTickHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        ItemStack lanceStack = getLanceStack(player);

        if (lanceStack.isEmpty()) return;

        CompoundTag tag = lanceStack.getTag();
        if (tag == null || !tag.getBoolean("charged")) return;

        float velocity = calculateVelocity(player);

        if (player instanceof IEntityDataSaver)
            PlayerVelocity.updateSpeedHistory((IEntityDataSaver) player, velocity);
    }

    private static ItemStack getLanceStack(Player player) {
        ItemStack mainHand = player.getMainHandItem();
        if (mainHand.getItem() instanceof Lance) return mainHand;

        ItemStack offHand = player.getOffhandItem();
        if (offHand.getItem() instanceof Lance) return offHand;

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