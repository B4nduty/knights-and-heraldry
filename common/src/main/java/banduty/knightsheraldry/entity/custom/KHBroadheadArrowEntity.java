package banduty.knightsheraldry.entity.custom;

import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.entity.custom.SCArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class KHBroadheadArrowEntity extends SCArrowEntity {
    public KHBroadheadArrowEntity(EntityType<? extends KHBroadheadArrowEntity> type, Level level) {
        super(type, null, level);
    }

    public KHBroadheadArrowEntity(LivingEntity shooter, Level level) {
        super(KHEntities.BROADHEAD_ARROW.get(), shooter, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(KHItems.BROADHEAD_ARROW.get());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(KHItems.BROADHEAD_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            scHitEntity(target, new ItemStack(KHItems.BROADHEAD_ARROW.get()), getBaseDamage());
        }
    }
}