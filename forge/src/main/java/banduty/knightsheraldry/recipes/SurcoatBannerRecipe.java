package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.items.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class SurcoatBannerRecipe extends CustomRecipe {

    public SurcoatBannerRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack surcoat = ItemStack.EMPTY;
        ItemStack banner = ItemStack.EMPTY;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty()) continue;

            // Check if the item is either SURCOAT or SURCOAT_SLEEVELESS
            if (stack.is(ModItems.SURCOAT.get()) || stack.is(ModItems.SURCOAT_SLEEVELESS.get())) {
                if (!surcoat.isEmpty()) return false; // Only one surcoat
                surcoat = stack;
            } else if (stack.getItem() instanceof BannerItem) {
                if (!banner.isEmpty()) return false; // Only one banner
                banner = stack;
            } else {
                return false; // Unknown item
            }
        }

        return !surcoat.isEmpty() && !banner.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack surcoat = ItemStack.EMPTY;
        ItemStack banner = ItemStack.EMPTY;
        boolean isSleeveless = false;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.is(ModItems.SURCOAT.get()) || stack.is(ModItems.SURCOAT_SLEEVELESS.get())) {
                surcoat = stack;
                // Check if it's the sleeveless version
                isSleeveless = stack.is(ModItems.SURCOAT_SLEEVELESS.get());
            } else if (stack.getItem() instanceof BannerItem) {
                banner = stack;
            }
        }

        if (surcoat.isEmpty() || banner.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // Create result surcoat (preserve the type - sleeveless or regular)
        ItemStack result;
        if (isSleeveless) {
            result = new ItemStack(ModItems.SURCOAT_SLEEVELESS.get());
        } else {
            result = new ItemStack(ModItems.SURCOAT.get());
        }

        // Copy banner patterns to surcoat
        CompoundTag bannerTag = banner.getTag();
        if (bannerTag != null && bannerTag.contains("BlockEntityTag")) {
            CompoundTag blockEntityTag = bannerTag.getCompound("BlockEntityTag");
            if (blockEntityTag.contains("Patterns")) {
                // Create or get surcoat tag
                CompoundTag resultTag = result.getOrCreateTag();
                CompoundTag displayTag = resultTag.getCompound("display");

                // Copy the patterns to the surcoat
                displayTag.put("Patterns", blockEntityTag.getList("Patterns", 10));

                // Copy banner color if needed
                if (bannerTag.contains("color")) {
                    resultTag.putInt("color", bannerTag.getInt("color"));
                }

                resultTag.put("display", displayTag);
                result.setTag(resultTag);
            }
        }

        // Copy any other NBT from the original surcoat (like damage, enchantments, etc.)
        if (surcoat.hasTag()) {
            CompoundTag originalTag = surcoat.getTag();
            CompoundTag resultTag = result.getOrCreateTag();

            // Preserve existing tags except pattern-related ones
            for (String key : originalTag.getAllKeys()) {
                if (!key.equals("display") && !key.equals("color")) {
                    resultTag.put(key, originalTag.get(key));
                }
            }
        }

        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SURCOAT_BANNER.get();
    }
}