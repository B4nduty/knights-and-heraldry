package banduty.knightsheraldry.util.itemdata;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.items.item.khrangeweapon.HeavyCrossbow;
import banduty.stoneycore.util.definitionsloader.WeaponDefinitionsStorage;
import banduty.stoneycore.util.weaponutil.SCRangeWeaponUtil;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class ModModelPredicates {
    public static void registerModelPredicates(Item item) {
        registerEasterEggPredicates(item);
        registerArmorPredicates(item);
        registerWeaponPredicates(item);
        registerBowPredicates(item);
    }

    private static void registerBowPredicates(Item item) {
        if (item instanceof HeavyCrossbow) {
            ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID, "pulling"), (stack, world, entity, seed) ->
                    SCRangeWeaponUtil.getWeaponState(stack).isReloading() || SCRangeWeaponUtil.getWeaponState(stack).isCharged() ? 1.0F : 0.0F);

            ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"pull"), (stack, world, entity, seed) -> {
                if (entity == null || !WeaponDefinitionsStorage.isRanged(stack)) {
                    return 0.0F;
                } else if (SCRangeWeaponUtil.getWeaponState(stack).isCharged()) {
                    return 1.0F;
                } else {
                    return (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F / WeaponDefinitionsStorage.getData(stack).ranged().rechargeTime();
                }
            });
            return;
        }

        ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"pulling"), (stack, world, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
    }

    private static void registerEasterEggPredicates(Item item) {
        if (item == ModItems.LONGBOW.get()) ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"longbow_xxxl"), (stack, world, entity, seed) ->
                Objects.equals(stack.getDisplayName(), Component.translatable("item.knightsheraldry.easter_egg.longbow_xxxl").getString()) ? 1.0F : 0.0F);
    }

    private static void registerArmorPredicates(Item item) {
        ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"open"),
                (stack, world, entity, seed) -> stack.hasTag() &&
                        stack.getTag().getBoolean("visorOpen") ? 1.0F : 0.0F);
    }

    private static void registerWeaponPredicates(Item item) {
        ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"charged"),
                (stack, world, entity, seed) -> entity != null
                        && (entity.getMainHandItem() == stack || entity.getOffhandItem() == stack) &&
                        stack.hasTag() && stack.getTag().getBoolean("charged") ? 1.0F : 0.0F);
        ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
        ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"bludgeoning"),
                (stack, world, entity, seed) -> stack.hasTag() &&
                        stack.getTag().getBoolean("bludgeoning") ? 1.0F : 0.0F);
        ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"ignited"),
                (stack, world, entity, seed) -> stack.hasTag() &&
                        stack.getTag().getBoolean("ignited") ? 1.0F : 0.0F);
        ItemProperties.register(item, new ResourceLocation(KnightsHeraldry.MOD_ID,"extinguished"),
                (stack, world, entity, seed) -> stack.hasTag() &&
                        stack.getTag().getBoolean("extinguished") ? 1.0F : 0.0F);
    }
}
