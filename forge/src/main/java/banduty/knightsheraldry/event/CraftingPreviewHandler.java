package banduty.knightsheraldry.event;

import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.stoneycore.StoneyCore;
import banduty.stoneycore.event.custom.CraftingPreviewEvent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StoneyCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CraftingPreviewHandler {

    @SubscribeEvent
    public static void onCraftingPreview(CraftingPreviewEvent event) {

        ItemStack original = event.getItemResult();
        ServerPlayer player = event.getPlayer();
        CraftingContainer inventory = event.getInventory();

        if (original.isEmpty())
            return;

        ItemStack result = original.copy(); // ALWAYS copy in preview

        String itemPath = BuiltInRegistries.ITEM.getKey(result.getItem()).getPath();

        CompoundTag originalColors = backupColors(result, itemPath);

        boolean appliedNewColors = false;

        for (int i = 0; i < inventory.getContainerSize(); i++) {

            ItemStack ingredient = inventory.getItem(i);

            if (ingredient.isEmpty() || ingredient.getItem() == result.getItem())
                continue;

            if (applyDye(result, ingredient, itemPath)) {
                appliedNewColors = true;
                continue;
            }

            if (applyArmorUpgrade(result, ingredient))
                continue;

            if (applyHelmetDeco(result, ingredient))
                appliedNewColors = true;
        }

        if (result.getItem() instanceof TwoLayerDyeableItem
                && !appliedNewColors
                && originalColors != null
                && !originalColors.isEmpty()) {

            result.getOrCreateTag().put(itemPath, originalColors);
        }

        event.setResult(result);
    }

    private static CompoundTag backupColors(ItemStack stack, String itemPath) {
        if (!(stack.getItem() instanceof TwoLayerDyeableItem))
            return null;

        CompoundTag nbt = stack.getTag();

        if (nbt != null && nbt.contains(itemPath))
            return nbt.getCompound(itemPath).copy();

        return null;
    }

    private static boolean applyDye(ItemStack target, ItemStack ingredient, String itemPath) {

        if (!(ingredient.getItem() instanceof DyeItem dye)
                || !(target.getItem() instanceof TwoLayerDyeableItem))
            return false;

        CompoundTag nbt = target.getOrCreateTag();
        CompoundTag colors = nbt.getCompound(itemPath);

        if (colors.isEmpty())
            colors = new CompoundTag();

        int color = getColorFromComponents(
                dye.getDyeColor().getTextureDiffuseColors()
        );

        if (!colors.contains("color1"))
            colors.putInt("color1", color);
        else
            colors.putInt("color2", color);

        nbt.put(itemPath, colors);
        return true;
    }

    private static boolean applyArmorUpgrade(ItemStack target, ItemStack ingredient) {

        if (ingredient.getItem() == ModItems.RIM_GUARDS.get()
                && target.getItem() != ModItems.RIM_GUARDS.get()) {

            target.getOrCreateTag().putBoolean("rimmed", true);
            return true;
        }

        if (ingredient.getItem() == ModItems.BESAGEWS.get()
                && target.getItem() != ModItems.BESAGEWS.get()) {

            target.getOrCreateTag().putBoolean("besagews", true);
            return true;
        }

        return false;
    }

    private static boolean applyHelmetDeco(ItemStack target, ItemStack ingredient) {

        var decoOpt = HelmetDeco.getDecoFromItem(ingredient.getItem());
        if (decoOpt.isEmpty())
            return false;

        HelmetDeco deco = decoOpt.get();
        CompoundTag nbt = target.getOrCreateTag();

        HelmetDeco.getValues().stream()
                .filter(other -> other.group() == deco.group())
                .forEach(other -> nbt.remove(other.getNbtKey()));

        if (deco.color() == 2
                && ingredient.getItem() instanceof TwoLayerDyeableItem twoLayer) {

            CompoundTag decoTag = new CompoundTag();
            decoTag.putInt("color1", twoLayer.getColor1(ingredient));
            decoTag.putInt("color2", twoLayer.getColor2(ingredient));

            nbt.put(deco.getNbtKey(), decoTag);
            return true;
        }

        if (deco.color() == 1) {
            CompoundTag display = ingredient.getTagElement("display");
            int color = display != null && display.contains("color", 99)
                    ? display.getInt("color")
                    : 0xFFFFFF;

            nbt.putInt(deco.getNbtKey(), color);
        } else {
            nbt.putBoolean(deco.getNbtKey(), true);
        }

        return true;
    }

    private static int getColorFromComponents(float[] components) {
        int r = Math.round(components[0] * 255);
        int g = Math.round(components[1] * 255);
        int b = Math.round(components[2] * 255);
        return (r << 16) | (g << 8) | b;
    }
}