package com.knightsheraldry.items.custom.item.khrangeweapon;

import com.knightsheraldry.client.item.HeavyCrossbowModel;
import com.knightsheraldry.items.custom.item.KHRangeWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.weaponutil.KHRangeWeaponUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class HeavyCrossbow extends Item implements KHRangeWeapon, GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public HeavyCrossbow(Settings settings) {
        super(settings);
    }

    @Override
    public @NotNull KHDamageCalculator.DamageType getDamageType() {
        return KHDamageCalculator.DamageType.PIERCING;
    }

    @Override
    public int maxUseTime() {
        return 72000;
    }

    @Override
    public float baseDamage() {
        return 16f;
    }

    @Override
    public float speed() {
        return 3f;
    }

    @Override
    public @Nullable AmmoRequirement ammoRequirement() {
        return null;
    }

    @Override
    public int rechargeTime() {
        return 5 * 20;
    }

    @Override
    public boolean needsFlintAndSteel() {
        return false;
    }

    @Override
    public @Nullable SoundEvent[] soundEvents() {
        return new SoundEvent[]{SoundEvents.ENTITY_ARROW_SHOOT};
    }

    @Override
    public UseAction useAction() {
        return UseAction.CROSSBOW;
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoItemRenderer<HeavyCrossbow> renderer;

            @Override
            public GeoItemRenderer<HeavyCrossbow> getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new HeavyCrossbowModel());

                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<HeavyCrossbow> animationState) {
        ItemStack itemStack = animationState.getData(DataTickets.ITEMSTACK);
        if (KHRangeWeaponUtil.getWeaponState(itemStack).isShooting()) animationState.getController().setAnimation(RawAnimation.begin().then("shoot", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (KHRangeWeaponUtil.getWeaponState(itemStack).isReloading()) animationState.getController().setAnimation(RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (KHRangeWeaponUtil.getWeaponState(itemStack).isCharged()) animationState.getController().setAnimation(RawAnimation.begin().then("charged", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else animationState.getController().setAnimation(RawAnimation.begin().then("unloaded", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
