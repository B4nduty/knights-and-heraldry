package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
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
            ItemStack itemStack = player.getItemBySlot(EquipmentSlot.HEAD);
            for (ItemStack armorAttachments : SCUnderArmor.getArmorAttachments(itemStack)) {
                if (armorAttachments.getItem() == KHItems.HOOD.get() || armorAttachments.getItem() == KHItems.TORN_HOOD.get() ||
                        armorAttachments.getItem() == KHItems.HELMET_HOOD.get() || armorAttachments.getItem() == KHItems.HELMET_TORN_HOOD.get()) {

                    event.setCanRender(TriState.FALSE);
                    return;
                }
            }
        }
    }
}
