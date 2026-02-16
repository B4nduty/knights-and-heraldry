package banduty.knightsheraldry.event;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.knightsheraldry.util.itemdata.ItemTooltipData;
import com.mojang.datafixers.util.Either;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = KnightsHeraldry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderTooltipHandlerGatherComponents {
    @SubscribeEvent
    public static void onGatherTooltipComponents(RenderTooltipEvent.GatherComponents event) {
        ItemStack stack = event.getItemStack();
        List<ItemStack> itemsToShow = new ArrayList<>();

        for (HelmetDeco helmetDeco : HelmetDeco.getValues()) {
            CompoundTag compoundTag = stack.getTag();
            String key = helmetDeco.getNbtKey();
            if (stack.is(helmetDeco.item()) || compoundTag == null || !compoundTag.contains(key)) continue;

            ItemStack itemStack = new ItemStack(helmetDeco.item());

            if (helmetDeco.color() == 2) {
                if (compoundTag.getCompound(key).contains("color1")) {
                    itemStack.getOrCreateTagElement(key).putInt("color1", compoundTag.getCompound(key).getInt("color1"));
                } else itemStack.getOrCreateTagElement(key).putInt("color1", -1);
                if (compoundTag.getCompound(key).contains("color2")) {
                    itemStack.getOrCreateTagElement(key).putInt("color2", compoundTag.getCompound(key).getInt("color2"));
                } else itemStack.getOrCreateTagElement(key).putInt("color2", -1);
            }

            if (helmetDeco.color() == 1) {
                itemStack.getOrCreateTagElement("display").putInt("color", compoundTag.getInt(key));
            }

            itemsToShow.add(itemStack);
        }

        if (!itemsToShow.isEmpty()) {
            event.getTooltipElements().add(Either.right(new ItemTooltipData(itemsToShow)));
        }
    }
}
