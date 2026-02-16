package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ComputeFovModifierHandler {
    @SubscribeEvent
    public static void onComputeFovModifier(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getUseItem();

        if (player.isUsingItem() && itemStack.is(ModItems.LONGBOW.get())) {
            int i = player.getUseItemRemainingTicks();
            float g = (float)i / 20.0f;
            g = g > 1.0f ? 1.0f : g * g;

            float newFovModifier = event.getFovModifier() * (1.0f - g * 0.15f);

            event.setNewFovModifier(newFovModifier);
        }
    }
}
