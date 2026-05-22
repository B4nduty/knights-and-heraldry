package banduty.knightsheraldry.items.item.khweapon.flail;

import banduty.knightsheraldry.client.item.weapon.FlailModel;
import banduty.knightsheraldry.client.item.weapon.FlailRenderer;
import banduty.knightsheraldry.items.ModToolMaterials;
import net.minecraft.world.item.SwordItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class Flail extends SwordItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public Flail(float attackSpeed, Properties properties) {
        super(ModToolMaterials.WEAPONS,
                properties.attributes(SwordItem.createAttributes(ModToolMaterials.WEAPONS, 1, attackSpeed)));
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<Flail> renderer;

            @Override
            public GeoItemRenderer<Flail> getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new FlailRenderer(new FlailModel());

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<Flail> animationState) {
        return FlailAnimation.clientPredicate(animationState);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}