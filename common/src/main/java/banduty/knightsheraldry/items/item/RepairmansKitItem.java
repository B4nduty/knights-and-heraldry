package banduty.knightsheraldry.items.item;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.knightsheraldry.util.itemdata.KHTags;
import banduty.knightsheraldry.util.itemdata.RepairKitContents;
import banduty.stoneycore.data.SCDataComponents;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.items.custom.armor.underarmor.UnderArmorContents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static banduty.stoneycore.util.SCInventoryItemFinder.findUnderArmor;

public class RepairmansKitItem extends Item {
    public static final int MAX_PER_SLOT = 64;
    public static final float REPAIR_FRACTION = 0.40F;

    private static final ArmorItem.Type[] WORN_TYPES = {
            ArmorItem.Type.HELMET, ArmorItem.Type.CHESTPLATE, ArmorItem.Type.LEGGINGS, ArmorItem.Type.BOOTS
    };

    private static final Material[] MATERIALS = Material.values();

    private static final Material[] REPAIR_ORDER = {
            Material.GOLD, Material.WOOL, Material.LEATHER, Material.CHAIN, Material.IRON
    };
    private static final int MAX_TOTAL = MAX_PER_SLOT * MATERIALS.length;

    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    private static final int FULL_BAR_COLOR = Mth.color(1.0F, 0.33F, 0.33F);

    public RepairmansKitItem(Properties properties) {
        super(properties);
    }

    public enum Material {
        WOOL("wool", stack -> stack.is(ItemTags.WOOL), Items.WHITE_WOOL, KHTags.REPAIR_KIT_WOOL),
        LEATHER("leather", stack -> stack.is(Items.LEATHER), Items.LEATHER, KHTags.REPAIR_KIT_LEATHER),
        CHAIN("chain", stack -> stack.is(Items.CHAIN), Items.CHAIN, KHTags.REPAIR_KIT_CHAIN),
        IRON("iron", stack -> stack.is(Items.IRON_INGOT), Items.IRON_INGOT, KHTags.REPAIR_KIT_IRON),
        GOLD("gold", stack -> stack.is(Items.GOLD_INGOT), Items.GOLD_INGOT, KHTags.REPAIR_KIT_GOLD);

        private final String key;
        private final Predicate<ItemStack> matcher;
        private final Item representative;
        private final KHTags repairTag;

        Material(String key, Predicate<ItemStack> matcher, Item representative, KHTags repairTag) {
            this.key = key;
            this.matcher = matcher;
            this.representative = representative;
            this.repairTag = repairTag;
        }

        public static Material of(ItemStack stack) {
            if (stack.isEmpty()) return null;
            for (Material material : values()) {
                if (material.matcher.test(stack)) return material;
            }
            return null;
        }

        public static boolean isListed(ItemStack target) {
            for (Material material : values()) {
                if (target.is(material.repairTag.getTag())) return true;
            }
            return false;
        }

        public boolean canRepair(ItemStack target) {
            if (isListed(target)) return target.is(repairTag.getTag());
            return target.getItem().isValidRepairItem(target, new ItemStack(representative));
        }

        public ItemStack toStack(int count) {
            return new ItemStack(representative, count);
        }

        public Component label() {
            return Component.translatable("tooltip." + KnightsHeraldry.MOD_ID + ".repairmans_kit." + key);
        }
    }

    private static RepairKitContents readContents(ItemStack kit) {
        return kit.getOrDefault(KHDataComponents.REPAIR_KIT_CONTENTS.get(), RepairKitContents.EMPTY);
    }

    private static int[] readCounts(ItemStack kit) {
        RepairKitContents contents = readContents(kit);
        int[] counts = new int[MATERIALS.length];
        for (Material material : MATERIALS) {
            counts[material.ordinal()] = Mth.clamp(contents.get(material.ordinal()), 0, MAX_PER_SLOT);
        }
        return counts;
    }

    private static int readLast(ItemStack kit) {
        return readContents(kit).last();
    }

    private static void writeCounts(ItemStack kit, int[] counts, int last) {
        int total = 0;
        List<Integer> list = new ArrayList<>(counts.length);
        for (int count : counts) {
            list.add(count);
            total += count;
        }
        if (total > 0) {
            kit.set(KHDataComponents.REPAIR_KIT_CONTENTS.get(), new RepairKitContents(list, last));
        } else {
            kit.remove(KHDataComponents.REPAIR_KIT_CONTENTS.get()); // empty kits carry no data
        }
    }

    public static int getCount(ItemStack kit, Material material) {
        return readCounts(kit)[material.ordinal()];
    }

    public static int getTotal(ItemStack kit) {
        int total = 0;
        for (int count : readCounts(kit)) total += count;
        return total;
    }

    private static int freeSpace(ItemStack kit, Material material) {
        return MAX_PER_SLOT - getCount(kit, material);
    }

    private static void add(ItemStack kit, Material material, int amount) {
        int[] counts = readCounts(kit);
        int i = material.ordinal();
        counts[i] = Math.min(MAX_PER_SLOT, counts[i] + amount);
        writeCounts(kit, counts, i);
    }

    private static void remove(ItemStack kit, Material material, int amount) {
        int[] counts = readCounts(kit);
        int last = readLast(kit);
        int i = material.ordinal();
        counts[i] = Math.max(0, counts[i] - amount);
        writeCounts(kit, counts, last);
    }

    private static Material pickExtractable(ItemStack kit) {
        int[] counts = readCounts(kit);
        int last = readLast(kit);
        if (last >= 0 && last < MATERIALS.length && counts[last] > 0) return MATERIALS[last];
        for (int i = MATERIALS.length - 1; i >= 0; i--) {
            if (counts[i] > 0) return MATERIALS[i];
        }
        return null;
    }

    private enum Outcome {
        NONE,
        MISSING,
        REPAIRED
    }

    private static Outcome repairOne(ItemStack kit, ItemStack target) {
        if (target.isEmpty() || !target.isDamageableItem() || !target.isDamaged()) return Outcome.NONE;

        int[] counts = readCounts(kit);
        boolean compatible = false;

        for (Material material : REPAIR_ORDER) {
            if (!material.canRepair(target)) continue;
            compatible = true;

            int i = material.ordinal();
            if (counts[i] <= 0) continue;

            int last = readLast(kit);
            counts[i]--;
            writeCounts(kit, counts, last);

            int heal = Math.max(1, Mth.ceil(target.getMaxDamage() * REPAIR_FRACTION));
            target.setDamageValue(Math.max(0, target.getDamageValue() - heal));
            return Outcome.REPAIRED;
        }
        return compatible ? Outcome.MISSING : Outcome.NONE;
    }

    private static Outcome repairWithAttachments(ItemStack kit, ItemStack target) {
        boolean missing = false;

        if (target.getItem() instanceof SCUnderArmor underArmor) {
            UnderArmorContents contents = target.get(SCDataComponents.UNDER_ARMOR_CONTENTS.get());
            if (contents != null && !contents.isEmpty()) {
                List<ItemStack> attachments = new ArrayList<>();
                for (ItemStack attachment : contents.attachments()) attachments.add(attachment.copy());

                for (ItemStack attachment : attachments) {
                    Outcome inner = repairOne(kit, attachment);
                    if (inner == Outcome.REPAIRED) {
                        target.set(SCDataComponents.UNDER_ARMOR_CONTENTS.get(), new UnderArmorContents(attachments));
                        underArmor.rebuildAttachmentAttributes(target);
                        return Outcome.REPAIRED;
                    }
                    if (inner == Outcome.MISSING) missing = true;
                }
            }
        }

        Outcome own = repairOne(kit, target);
        if (own == Outcome.REPAIRED) return own;
        return missing || own == Outcome.MISSING ? Outcome.MISSING : Outcome.NONE;
    }

    private static boolean report(Outcome outcome, Player player) {
        switch (outcome) {
            case REPAIRED -> {
                player.playSound(SoundEvents.ANVIL_USE, 0.5F, 0.9F + player.level().getRandom().nextFloat() * 0.3F);
                syncMenu(player);
                return true;
            }
            case MISSING -> {
                if (!player.level().isClientSide()) {
                    player.displayClientMessage(
                            Component.translatable("message." + KnightsHeraldry.MOD_ID + ".repairmans_kit.missing_materials")
                                    .withStyle(ChatFormatting.RED), true);
                }
                playFailSound(player);
                return true;
            }
            default -> {
                return false; // not something this kit can fix: let the click behave normally
            }
        }
    }

    private static boolean tryRepair(ItemStack kit, ItemStack target, Player player) {
        return report(repairWithAttachments(kit, target), player);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack kit = player.getItemInHand(hand);

        Outcome best = Outcome.NONE;
        for (ArmorItem.Type type : WORN_TYPES) {
            ItemStack underArmor = findUnderArmor(player, type);
            if (underArmor.isEmpty() || !(underArmor.getItem() instanceof SCUnderArmor)) continue;

            Outcome outcome = repairWithAttachments(kit, underArmor);
            if (outcome == Outcome.REPAIRED) {
                best = outcome;
                break;
            }
            if (outcome == Outcome.MISSING) best = outcome;
        }

        if (report(best, player)) {
            return InteractionResultHolder.sidedSuccess(kit, level.isClientSide());
        }
        return InteractionResultHolder.pass(kit);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack kit, Slot slot, ClickAction action, Player player) {
        ItemStack target = slot.getItem();

        if (action == ClickAction.PRIMARY) {
            if (target.isEmpty() || !slot.allowModification(player)) return false;
            Material material = Material.of(target);
            if (material == null) return false;

            int amount = Math.min(freeSpace(kit, material), target.getCount());
            if (amount <= 0) {
                playFailSound(player);
                return true;
            }
            ItemStack taken = slot.safeTake(amount, amount, player);
            if (taken.isEmpty()) return false;
            add(kit, material, taken.getCount());
            playInsertSound(player);
            syncMenu(player);
            return true;
        }

        if (action == ClickAction.SECONDARY) {
            if (target.isEmpty()) {
                // take material out into the empty slot
                Material material = pickExtractable(kit);
                if (material == null) return false;
                int count = getCount(kit, material);
                ItemStack leftover = slot.safeInsert(material.toStack(count));
                int moved = count - leftover.getCount();
                if (moved <= 0) return false;
                remove(kit, material, moved);
                playRemoveSound(player);
                syncMenu(player);
                return true;
            }
            // right-click the item to repair
            if (!slot.allowModification(player)) return false;
            return tryRepair(kit, target, player);
        }

        return false;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack kit, ItemStack other, Slot slot, ClickAction action,
                                            Player player, SlotAccess access) {
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) return false;

        if (other.isEmpty()) {
            Material material = pickExtractable(kit);
            if (material == null) return false;
            int count = getCount(kit, material);
            access.set(material.toStack(count));
            remove(kit, material, count);
            playRemoveSound(player);
            syncMenu(player);
            return true;
        }

        Material material = Material.of(other);
        if (material != null) {
            int amount = Math.min(freeSpace(kit, material), other.getCount());
            if (amount <= 0) {
                playFailSound(player);
                return true;
            }
            other.shrink(amount);
            add(kit, material, amount);
            playInsertSound(player);
            syncMenu(player);
            return true;
        }

        // damaged item on the cursor, right-click the kit
        return tryRepair(kit, other, player);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getTotal(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.min(1 + 12 * getTotal(stack) / MAX_TOTAL, 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return getTotal(stack) >= MAX_TOTAL ? FULL_BAR_COLOR : BAR_COLOR;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int[] counts = readCounts(stack);
        for (Material material : MATERIALS) {
            int count = counts[material.ordinal()];
            tooltip.add(material.label().copy().withStyle(ChatFormatting.GRAY)
                    .append(Component.literal(": ").withStyle(ChatFormatting.GRAY))
                    .append(Component.literal(count + "/" + MAX_PER_SLOT)
                            .withStyle(count == 0 ? ChatFormatting.DARK_GRAY : ChatFormatting.WHITE)));
        }
    }

    private static void syncMenu(Player player) {
        AbstractContainerMenu menu = player.containerMenu;
        menu.slotsChanged(player.getInventory());
    }

    private static void playInsertSound(Player player) {
        player.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
    }

    private static void playRemoveSound(Player player) {
        player.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
    }

    private static void playFailSound(Player player) {
        player.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 1.0F, 1.0F);
    }
}