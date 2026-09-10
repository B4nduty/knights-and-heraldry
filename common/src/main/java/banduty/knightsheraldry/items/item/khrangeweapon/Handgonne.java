package banduty.knightsheraldry.items.item.khrangeweapon;

import banduty.knightsheraldry.client.item.weapon.HandgonneModel;
import banduty.knightsheraldry.client.item.weapon.HandgonneRenderer;
import banduty.knightsheraldry.combat.weapon.IHeldWeaponAnimatable;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.stoneycore.items.client.SCIconRendererProvider;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.definitions.ArmorAttachmentDefinitionsStorage;
import banduty.stoneycore.definitions.WeaponDefinitionsStorage;
import banduty.stoneycore.combat.weapon.SCRangeWeaponUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class Handgonne extends Item implements GeoItem, SCIconRendererProvider, IHeldWeaponAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Map<Long, LivingEntity> fallbackLivingEntities = new ConcurrentHashMap<>();

    @Override
    public void trackHolder(ItemStack stack, ServerLevel serverLevel, LivingEntity holder) {
        long id = GeoItem.getOrAssignId(stack, serverLevel);
        fallbackLivingEntities.put(id, holder);
    }

    public Handgonne(Properties properties) {
        super(properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<Handgonne> renderer;

            @Override
            public GeoItemRenderer<Handgonne> getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new HandgonneRenderer(new HandgonneModel());

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<Handgonne> animationState) {
        ItemStack itemStack = animationState.getData(DataTickets.ITEMSTACK);
        AnimationController<Handgonne> controller = animationState.getController();

        long id = GeoItem.getId(itemStack);
        LivingEntity fallbackLivingEntity = fallbackLivingEntities.get(id);
        if (fallbackLivingEntity == null) return PlayState.STOP;

        if (SCRangeWeaponUtil.getWeaponState(itemStack).isShooting()) {
            controller.setAnimationSpeed(1.0);
            animationState.getController().setAnimation(RawAnimation.begin().then("shoot", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        else if (SCRangeWeaponUtil.getWeaponState(itemStack).isReloading()) {
            int rechargeTime = WeaponDefinitionsStorage.getData(itemStack).ranged().rechargeTime();
            for (ItemStack armorStack : fallbackLivingEntity.getArmorSlots()) {
                for (ItemStack attachment : SCUnderArmor.getArmorAttachments(armorStack)) {
                    rechargeTime += ArmorAttachmentDefinitionsStorage.getData(attachment).rechargeTime();
                }
            }
            controller.setAnimationSpeed((double) 300 / rechargeTime);
            animationState.getController().setAnimation(RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        else if (SCRangeWeaponUtil.getWeaponState(itemStack).isCharged()) {
            controller.setAnimationSpeed(1.0);
            animationState.getController().setAnimation(RawAnimation.begin().then("charged", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        else {
            controller.setAnimationSpeed(1.0);
            animationState.getController().setAnimation(RawAnimation.begin().then("unloaded", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public Component getName(ItemStack stack) {
        if (!stack.getOrDefault(KHDataComponents.PIGLIN.get(), false)) return super.getName(stack);
        return Component.translatable("item.stoneycore.golden_handgonne");
    }
}
