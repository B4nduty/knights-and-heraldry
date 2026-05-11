package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.util.TriState;

@EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RenderNameTagHandler {
    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player) {
            AccessoriesCapability.getOptionally(player).ifPresent(capability -> {
                for (SlotEntryReference equipped : capability.getAllEquipped()) {
                    ItemStack itemStack = equipped.stack();

                    if (isHidingNameHood(itemStack)) {
                        event.setCanRender(TriState.FALSE);
                        return;
                    }
                }
            });
        }
    }

    private static boolean isHidingNameHood(ItemStack stack) {
        return stack.is(KHItems.HOOD.get()) ||
                stack.is(KHItems.TORN_HOOD.get()) ||
                stack.is(KHItems.HELMET_HOOD.get()) ||
                stack.is(KHItems.HELMET_TORN_HOOD.get());
    }
}
