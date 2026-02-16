package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderNameTagHandler {
    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player) {
            AccessoriesCapability.getOptionally(player).ifPresent(capability -> {
                for (SlotEntryReference equipped : capability.getAllEquipped()) {
                    ItemStack itemStack = equipped.stack();

                    if (isHidingNameHood(itemStack)) {
                        event.setResult(Event.Result.DENY);
                        return;
                    }
                }
            });
        }
    }

    private static boolean isHidingNameHood(ItemStack stack) {
        return stack.is(ModItems.HOOD.get()) ||
                stack.is(ModItems.TORN_HOOD.get()) ||
                stack.is(ModItems.HELMET_HOOD.get()) ||
                stack.is(ModItems.HELMET_TORN_HOOD.get());
    }
}
