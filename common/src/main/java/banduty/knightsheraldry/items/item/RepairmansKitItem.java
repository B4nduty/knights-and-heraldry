package banduty.knightsheraldry.items.item;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.config.IKHConfig;
import banduty.knightsheraldry.platform.Services;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.knightsheraldry.util.itemdata.KHTags;
import banduty.knightsheraldry.util.itemdata.RepairKitContents;
import banduty.knightsheraldry.util.itemdata.RepairKitTooltip;
import banduty.stoneycore.data.SCDataComponents;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.items.custom.armor.underarmor.UnderArmorContents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static banduty.stoneycore.util.SCInventoryItemFinder.findUnderArmor;

public class RepairmansKitItem extends Item {
    public static final int EXTRACT_STACK_SIZE = 64;

    private static final ArmorItem.Type[] WORN_TYPES = {
            ArmorItem.Type.HELMET, ArmorItem.Type.CHESTPLATE, ArmorItem.Type.LEGGINGS, ArmorItem.Type.BOOTS
    };

    private static final Material[] MATERIALS = Material.values();

    private static final Material[] REPAIR_ORDER = {
            Material.GOLD, Material.WOOL, Material.LEATHER, Material.CHAIN, Material.IRON
    };

    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    private static final int FULL_BAR_COLOR = Mth.color(1.0F, 0.33F, 0.33F);

    private static IKHConfig config() {
        return Services.PLATFORM.getConfig();
    }

    public static int maxPerSlot() {
        return Mth.clamp(config().getRepairKitSlotCapacity(), 1, 999);
    }

    private static int maxTotal() {
        return maxPerSlot() * MATERIALS.length;
    }

    private static float repairFraction() {
        return Mth.clamp(config().getRepairKitRepairPercent(), 1, 100) / 100.0F;
    }

    private static int useCooldownTicks() {
        return Math.max(0, config().getRepairKitUseCooldown());
    }

    private static int everythingPenaltyTicks() {
        return Math.max(0, config().getRepairKitEverythingPenalty());
    }

    public RepairmansKitItem(Properties properties) {
        super(properties);
    }

    public enum Material {
        WOOL(stack -> stack.is(ItemTags.WOOL), Items.WHITE_WOOL, KHTags.REPAIR_KIT_WOOL,
                () -> SoundEvents.SHEEP_SHEAR),
        LEATHER(stack -> stack.is(Items.LEATHER), Items.LEATHER, KHTags.REPAIR_KIT_LEATHER,
                SoundEvents.ARMOR_EQUIP_LEATHER::value),
        CHAIN(stack -> stack.is(Items.CHAIN), Items.CHAIN, KHTags.REPAIR_KIT_CHAIN,
                SoundEvents.ARMOR_EQUIP_CHAIN::value),
        IRON(stack -> stack.is(Items.IRON_INGOT), Items.IRON_INGOT, KHTags.REPAIR_KIT_IRON,
                SoundEvents.ARMOR_EQUIP_IRON::value),
        GOLD(stack -> stack.is(Items.GOLD_INGOT), Items.GOLD_INGOT, KHTags.REPAIR_KIT_GOLD,
                SoundEvents.ARMOR_EQUIP_GOLD::value);

        private final Predicate<ItemStack> matcher;
        private final Item representative;
        private final KHTags repairTag;
        private final Supplier<SoundEvent> repairSound;

        Material(Predicate<ItemStack> matcher, Item representative, KHTags repairTag,
                 Supplier<SoundEvent> repairSound) {
            this.matcher = matcher;
            this.representative = representative;
            this.repairTag = repairTag;
            this.repairSound = repairSound;
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

        public SoundEvent repairSound() {
            return repairSound.get();
        }
    }

    private static RepairKitContents readContents(ItemStack kit) {
        return kit.getOrDefault(KHDataComponents.REPAIR_KIT_CONTENTS.get(), RepairKitContents.EMPTY);
    }

    private static int[] readCounts(ItemStack kit) {
        RepairKitContents contents = readContents(kit);
        int[] counts = new int[MATERIALS.length];
        for (Material material : MATERIALS) {
            counts[material.ordinal()] = Math.max(0, contents.get(material.ordinal()));
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
        return Math.max(0, maxPerSlot() - getCount(kit, material));
    }

    private static void add(ItemStack kit, Material material, int amount) {
        int[] counts = readCounts(kit);
        int i = material.ordinal();
        counts[i] += amount;
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

    private record Result(Outcome outcome, Material material) {
        static final Result NO_RESULT = new Result(Outcome.NONE, null);
        static final Result MISSING_RESULT = new Result(Outcome.MISSING, null);

        static Result repaired(Material material) {
            return new Result(Outcome.REPAIRED, material);
        }
    }

    private static Result repairOne(ItemStack kit, ItemStack target) {
        if (target.isEmpty() || !target.isDamageableItem() || !target.isDamaged()) return Result.NO_RESULT;

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

            int heal = Math.max(1, Mth.ceil(target.getMaxDamage() * repairFraction()));
            target.setDamageValue(Math.max(0, target.getDamageValue() - heal));
            return Result.repaired(material);
        }
        return compatible ? Result.MISSING_RESULT : Result.NO_RESULT;
    }

    private static Result repairWithAttachments(ItemStack kit, ItemStack target) {
        boolean missing = false;

        if (target.getItem() instanceof SCUnderArmor underArmor) {
            UnderArmorContents contents = target.get(SCDataComponents.UNDER_ARMOR_CONTENTS.get());
            if (contents != null && !contents.isEmpty()) {
                List<ItemStack> attachments = new ArrayList<>();
                for (ItemStack attachment : contents.attachments()) attachments.add(attachment.copy());

                for (ItemStack attachment : attachments) {
                    Result inner = repairOne(kit, attachment);
                    if (inner.outcome() == Outcome.REPAIRED) {
                        target.set(SCDataComponents.UNDER_ARMOR_CONTENTS.get(), new UnderArmorContents(attachments));
                        underArmor.rebuildAttachmentAttributes(target);
                        return inner;
                    }
                    if (inner.outcome() == Outcome.MISSING) missing = true;
                }
            }
        }

        Result own = repairOne(kit, target);
        if (own.outcome() == Outcome.REPAIRED) return own;
        return missing || own.outcome() == Outcome.MISSING ? Result.MISSING_RESULT : Result.NO_RESULT;
    }

    private static boolean report(Result result, Player player) {
        switch (result.outcome()) {
            case REPAIRED -> {
                player.playSound(result.material().repairSound(), 0.9F,
                        0.9F + player.level().getRandom().nextFloat() * 0.2F);
                syncMenu(player);
                return true;
            }
            case MISSING -> {
                if (!player.level().isClientSide()) {
                    player.displayClientMessage(
                            Component.translatable("text.message." + KnightsHeraldry.MOD_ID + ".repairmans_kit.missing_materials")
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
        if (player.getCooldowns().isOnCooldown(kit.getItem())) {
            if (repairWithAttachments(kit.copy(), target.copy()).outcome() == Outcome.NONE) return false;
            playFailSound(player);
            return true;
        }
        return report(repairWithAttachments(kit, target), player);
    }

    private static final class Tally {
        int items;
        boolean missing;
        final EnumSet<Material> used = EnumSet.noneOf(Material.class);
    }

    private static boolean repairFully(ItemStack kit, ItemStack stack, Tally tally) {
        boolean changed = false;
        while (true) {
            Result result = repairOne(kit, stack);
            if (result.outcome() == Outcome.REPAIRED) {
                changed = true;
                tally.used.add(result.material());
                continue;
            }
            if (result.outcome() == Outcome.MISSING) tally.missing = true;
            break;
        }
        if (changed) tally.items++;
        return changed;
    }

    private static void repairEverythingOn(ItemStack kit, ItemStack target, Tally tally) {
        if (target.getItem() instanceof SCUnderArmor underArmor) {
            UnderArmorContents contents = target.get(SCDataComponents.UNDER_ARMOR_CONTENTS.get());
            if (contents != null && !contents.isEmpty()) {
                List<ItemStack> attachments = new ArrayList<>();
                for (ItemStack attachment : contents.attachments()) attachments.add(attachment.copy());

                boolean any = false;
                for (ItemStack attachment : attachments) {
                    any |= repairFully(kit, attachment, tally);
                }
                if (any) {
                    target.set(SCDataComponents.UNDER_ARMOR_CONTENTS.get(), new UnderArmorContents(attachments));
                    underArmor.rebuildAttachmentAttributes(target);
                }
            }
        }
        repairFully(kit, target, tally);
    }

    private static volatile long clientCooldownEnd = 0L;

    public static long getClientCooldownEnd() {
        return clientCooldownEnd;
    }

    private static void startCooldown(Player player, ItemStack kit, int ticks) {
        player.getCooldowns().addCooldown(kit.getItem(), ticks);
        if (player.level().isClientSide()) {
            clientCooldownEnd = player.level().getGameTime() + ticks;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack kit = player.getItemInHand(hand);
        return player.isShiftKeyDown() ? useOnEverything(level, player, kit) : useOnce(level, player, kit);
    }

    private InteractionResultHolder<ItemStack> useOnce(Level level, Player player, ItemStack kit) {
        Result best = Result.NO_RESULT;
        for (ArmorItem.Type type : WORN_TYPES) {
            ItemStack underArmor = findUnderArmor(player, type);
            if (underArmor.isEmpty() || !(underArmor.getItem() instanceof SCUnderArmor)) continue;

            Result result = repairWithAttachments(kit, underArmor);
            if (result.outcome() == Outcome.REPAIRED) {
                best = result;
                break;
            }
            if (result.outcome() == Outcome.MISSING) best = result;
        }

        if (report(best, player)) {
            if (best.outcome() == Outcome.REPAIRED) startCooldown(player, kit, useCooldownTicks());
            return InteractionResultHolder.sidedSuccess(kit, level.isClientSide());
        }
        return InteractionResultHolder.pass(kit);
    }

    private InteractionResultHolder<ItemStack> useOnEverything(Level level, Player player, ItemStack kit) {
        Tally tally = new Tally();
        for (ArmorItem.Type type : WORN_TYPES) {
            ItemStack underArmor = findUnderArmor(player, type);
            if (underArmor.isEmpty() || !(underArmor.getItem() instanceof SCUnderArmor)) continue;
            repairEverythingOn(kit, underArmor, tally);
        }

        if (tally.items > 0) {
            for (Material material : tally.used) {
                player.playSound(material.repairSound(), 0.9F, 0.9F + level.getRandom().nextFloat() * 0.2F);
            }
            syncMenu(player);
            long total = useCooldownTicks() + (long) everythingPenaltyTicks() * tally.items;
            startCooldown(player, kit, (int) Math.min(Integer.MAX_VALUE, total));
            return InteractionResultHolder.sidedSuccess(kit, level.isClientSide());
        }
        if (tally.missing) {
            report(Result.MISSING_RESULT, player);
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
                int amount = Math.min(getCount(kit, material), EXTRACT_STACK_SIZE);
                ItemStack leftover = slot.safeInsert(material.toStack(amount));
                int moved = amount - leftover.getCount();
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
            int amount = Math.min(getCount(kit, material), EXTRACT_STACK_SIZE);
            access.set(material.toStack(amount));
            remove(kit, material, amount);
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
        return Math.min(1 + 12 * getTotal(stack) / maxTotal(), 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return getTotal(stack) >= maxTotal() ? FULL_BAR_COLOR : BAR_COLOR;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new RepairKitTooltip(readContents(stack)));
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