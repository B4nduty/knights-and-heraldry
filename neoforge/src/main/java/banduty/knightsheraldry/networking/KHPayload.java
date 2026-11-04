package banduty.knightsheraldry.networking;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.combat.OffhandAttack;
import banduty.knightsheraldry.networking.payload.OffhandAttackC2SPacket;
import banduty.knightsheraldry.networking.payload.ToggleGripC2SPacket;
import banduty.knightsheraldry.networking.payload.VelocityS2CPacket;
import banduty.knightsheraldry.util.GripUtil;
import banduty.stoneycore.networking.payload.*;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class KHPayload {
    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1")
                .executesOn(HandlerThread.MAIN);

        registrar.playToClient(VelocityS2CPacket.TYPE, VelocityS2CPacket.STREAM_CODEC, VelocityS2CPacket::handle);
        registrar.playToServer(OffhandAttackC2SPacket.TYPE, OffhandAttackC2SPacket.STREAM_CODEC,
                (payload, context) -> OffhandAttack.handle((ServerPlayer) context.player(), payload));
        registrar.playToServer(ToggleGripC2SPacket.TYPE, ToggleGripC2SPacket.STREAM_CODEC,
                (payload, context) -> GripUtil.toggle((ServerPlayer) context.player()));
    }
}
