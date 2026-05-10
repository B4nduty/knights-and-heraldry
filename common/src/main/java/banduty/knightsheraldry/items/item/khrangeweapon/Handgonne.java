package banduty.knightsheraldry.items.item.khrangeweapon;

import banduty.knightsheraldry.client.item.HandgonneModel;
import banduty.stoneycore.util.weaponutil.SCRangeWeaponUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class Handgonne extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public Handgonne(Properties properties) {
        super(properties);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<Handgonne> renderer;

            @Override
            public GeoItemRenderer<Handgonne> getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new GeoItemRenderer<>(new HandgonneModel());

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
        if (SCRangeWeaponUtil.getWeaponState(itemStack).isShooting()) animationState.getController().setAnimation(RawAnimation.begin().then("shoot", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (SCRangeWeaponUtil.getWeaponState(itemStack).isReloading()) animationState.getController().setAnimation(RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else if (SCRangeWeaponUtil.getWeaponState(itemStack).isCharged()) animationState.getController().setAnimation(RawAnimation.begin().then("charged", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else animationState.getController().setAnimation(RawAnimation.begin().then("unloaded", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
