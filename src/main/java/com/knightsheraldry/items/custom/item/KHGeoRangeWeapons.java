package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.client.item.KHGeoRangeWeaponsModel;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.util.UseAction;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class KHGeoRangeWeapons extends KHRangeWeapons implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, float damage, double blockRange,
                             UseAction useAction, int maxUseTime) {
        super(settings, damageType, damage, blockRange, useAction, maxUseTime);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, float damage, double blockRange,
                             UseAction useAction, int maxUseTime, int animationTime) {
        super(settings, damageType, damage, blockRange, useAction, maxUseTime, animationTime);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoItemRenderer<KHGeoRangeWeapons> renderer;

            @Override
            public GeoItemRenderer<KHGeoRangeWeapons> getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new KHGeoRangeWeaponsModel());

                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<KHGeoRangeWeapons> animationState) {
        if (isReloading(animationState.getData(DataTickets.ITEMSTACK))) animationState.getController().setAnimation(RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (isCharged(animationState.getData(DataTickets.ITEMSTACK))) animationState.getController().setAnimation(RawAnimation.begin().then("charged", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (isShooting(animationState.getData(DataTickets.ITEMSTACK))) animationState.getController().setAnimation(RawAnimation.begin().then("shoot", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else animationState.getController().setAnimation(RawAnimation.begin().then("unloaded", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}