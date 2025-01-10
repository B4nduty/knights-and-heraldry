package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.client.item.KHGeoRangeWeaponsModel;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.RangeWeaponConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
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

    /**
     * <p><b>Warning:</b>
     * This class is made for use of KnightsHeraldry, you can use it, but it isn't made to use by external people.
     * It is a class made only to reduce files and storage space.
     */
    public KHGeoRangeWeapons(Settings settings, KHDamageCalculator.DamageType damageType, int maxUseTime, float damage, float speed, RangeWeaponConfig.AmmoRequirement ammoRequirement, UseAction useAction, int rechargeTime, boolean needsFlintAndSteel, SoundEvent... soundEvents) {
        super(settings, damageType, maxUseTime, damage, speed, ammoRequirement, useAction, rechargeTime, needsFlintAndSteel, soundEvents);
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
        ItemStack itemStack = animationState.getData(DataTickets.ITEMSTACK);
        if (getWeaponState(itemStack).isShooting()) animationState.getController().setAnimation(RawAnimation.begin().then("shoot", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (getWeaponState(itemStack).isReloading()) animationState.getController().setAnimation(RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (getWeaponState(itemStack).isCharged()) animationState.getController().setAnimation(RawAnimation.begin().then("charged", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else animationState.getController().setAnimation(RawAnimation.begin().then("unloaded", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}