package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.item.khweapon.Lance;
import banduty.knightsheraldry.util.playerdata.PlayerVelocity;
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
        if (event.phase != TickEvent.Phase.START || !(event.player instanceof ServerPlayer player)) {
            return;
        }

        ItemStack lanceStack = getLanceStack(player);

        if (lanceStack != null && lanceStack.hasTag() && lanceStack.getTag().getBoolean("charged")) {
            float velocity = calculateVelocity(player);

            PlayerVelocity.updateSpeedHistory(player, velocity);
        }
    }

    private static ItemStack getLanceStack(Player player) {
        ItemStack mainHandStack = player.getMainHandItem();
        if (mainHandStack.getItem() instanceof Lance) return mainHandStack;

        ItemStack offHandStack = player.getOffhandItem();
        if (offHandStack.getItem() instanceof Lance) return offHandStack;

        return null;
    }

    private static float calculateVelocity(ServerPlayer player) {
        CompoundTag playerData = player.getPersistentData();

        double prevX = playerData.getDouble("lancePrevX");
        double prevZ = playerData.getDouble("lancePrevZ");

        Entity entity = player.getVehicle() != null ? player.getVehicle() : player;

        Vec3 delta = new Vec3(entity.getX() - prevX, 0, entity.getZ() - prevZ);

        playerData.putDouble("lancePrevX", entity.getX());
        playerData.putDouble("lancePrevZ", entity.getZ());

        return (float) delta.length();
    }
}