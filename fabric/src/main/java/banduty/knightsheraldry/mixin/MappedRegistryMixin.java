package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.KHItemAliases;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Fabric has no registry alias API (NeoForge uses Registry#addAlias), so resolve removed item ids
 * from {@link KHItemAliases} on the item registry's by-name lookups, which is what ItemStack decoding goes through.
 */
@Mixin(MappedRegistry.class)
public abstract class MappedRegistryMixin<T> {
    @Shadow
    public abstract ResourceKey<? extends net.minecraft.core.Registry<T>> key();

    @ModifyVariable(method = {
            "get(Lnet/minecraft/resources/ResourceLocation;)Ljava/lang/Object;",
            "getHolder(Lnet/minecraft/resources/ResourceLocation;)Ljava/util/Optional;",
            "containsKey(Lnet/minecraft/resources/ResourceLocation;)Z"
    }, at = @At("HEAD"), argsOnly = true)
    private ResourceLocation knightsheraldry$resolveItemAlias(ResourceLocation location) {
        if (location == null || !isItemRegistry()) return location;
        return KHItemAliases.resolve(location);
    }

    @ModifyVariable(method = {
            "get(Lnet/minecraft/resources/ResourceKey;)Ljava/lang/Object;",
            "getHolder(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;",
            "containsKey(Lnet/minecraft/resources/ResourceKey;)Z"
    }, at = @At("HEAD"), argsOnly = true)
    private ResourceKey<T> knightsheraldry$resolveItemKeyAlias(ResourceKey<T> resourceKey) {
        if (resourceKey == null || !isItemRegistry()) return resourceKey;
        ResourceLocation resolved = KHItemAliases.resolve(resourceKey.location());
        return resolved == resourceKey.location() ? resourceKey : ResourceKey.create(key(), resolved);
    }

    private boolean isItemRegistry() {
        return Registries.ITEM.equals(key());
    }
}
