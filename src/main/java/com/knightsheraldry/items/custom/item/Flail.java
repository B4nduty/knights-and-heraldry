package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.client.item.FlailModel;
import com.knightsheraldry.items.ModToolMaterials;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.SwordItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Flail extends SwordItem implements GeoItem, KHWeapon {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    public Flail(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoItemRenderer<Flail> renderer;

            @Override
            public GeoItemRenderer<Flail> getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new FlailModel());

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

    private PlayState predicate(AnimationState<Flail> animationState) {
        RawAnimation animation;
        final var client = MinecraftClient.getInstance();
        if (client.options.attackKey.isPressed()) {
            animation = RawAnimation.begin().then("attack", Animation.LoopType.LOOP);
        } else {
            animation = RawAnimation.begin().then("standby", Animation.LoopType.LOOP);
        }

        animationState.getController().setAnimation(animation);

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                2.0d, //1st Distance
                2.4d, //2nd Distance
                2.9d, //3rd Distance
                3.4d, //4th Distance
                4.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Piercing
                0.0F, 5.0F, 7.5F, 5.0F, 2.5F //Bludgeoning
        };
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[0];
    }

    @Override
    public int getAnimation() {
        return 0;
    }

    @Override
    public KHDamageCalculator.DamageType getOnlyDamageType() {
        return KHDamageCalculator.DamageType.BLUDGEONING;
    }
}