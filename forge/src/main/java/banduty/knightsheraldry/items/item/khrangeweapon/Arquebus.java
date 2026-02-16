package banduty.knightsheraldry.items.item.khrangeweapon;

import banduty.knightsheraldry.client.item.ArquebusRenderer;
import banduty.stoneycore.util.weaponutil.SCRangeWeaponUtil;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class Arquebus extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public Arquebus(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private ArquebusRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new ArquebusRenderer();
                }
                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<Arquebus> animationState) {
        ItemStack itemStack = animationState.getData(DataTickets.ITEMSTACK);

        String animName = "unloaded";
        if (SCRangeWeaponUtil.getWeaponState(itemStack).isShooting()) animName = "shoot";
        else if (SCRangeWeaponUtil.getWeaponState(itemStack).isReloading()) animName = "reload";
        else if (SCRangeWeaponUtil.getWeaponState(itemStack).isCharged()) animName = "charged";

        animationState.getController().setAnimation(RawAnimation.begin().then(animName, Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}