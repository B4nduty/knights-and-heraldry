package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.util.GripUtil;
import net.bettercombat.api.AttributesContainer;
import net.bettercombat.api.WeaponAttributes;
import net.bettercombat.logic.WeaponRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Versatile weapons held in their two-handed grip use the weapon attributes file
 * {@code <namespace>:<item>_two_handed} (e.g. {@code knightsheraldry:estoc_two_handed}) instead of the item's own,
 * so Better Combat uses two-handed animations, pose and moveset. Better Combat resolves every file in
 * {@code weapon_attributes} into {@code containers} (and syncs them to clients), even ones not matching an item.
 */
@Mixin(value = WeaponRegistry.class, remap = false)
public class WeaponRegistryMixin {
    @Shadow
    static Map<ResourceLocation, AttributesContainer> containers;

    @Unique
    private static final Map<Item, ResourceLocation> knightsheraldry$twoHandedIds = new ConcurrentHashMap<>();

    // Mapping-agnostic selector for the getAttributes(ItemStack) overload (class_1799 in Fabric production,
    // ItemStack in dev / on NeoForge); getAttributes(ResourceLocation) has the same shape, so match the type.
    @Inject(method = "name=/^getAttributes$/ desc=/^\\(L(net\\/minecraft\\/class_1799|net\\/minecraft\\/world\\/item\\/ItemStack);\\)/",
            at = @At("HEAD"), cancellable = true)
    private static void kh$twoHandedGrip(ItemStack stack, CallbackInfoReturnable<WeaponAttributes> cir) {
        if (stack == null || containers == null || !GripUtil.isTwoHandedGrip(stack)) return;

        ResourceLocation id = knightsheraldry$twoHandedIds.computeIfAbsent(stack.getItem(), item -> {
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
            return ResourceLocation.fromNamespaceAndPath(key.getNamespace(), key.getPath() + "_two_handed");
        });

        AttributesContainer container = containers.get(id);
        if (container != null && container.attributes() != null) cir.setReturnValue(container.attributes());
    }
}
