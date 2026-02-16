package banduty.knightsheraldry.entity.custom;

import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.entity.custom.SCArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class KHBroadheadArrowEntity extends SCArrowEntity {
    public KHBroadheadArrowEntity(LivingEntity shooter, Level level) {
        super(Services.ENTITY.getBroadheadEntity(), shooter, level);
    }

    public KHBroadheadArrowEntity(EntityType<KHBroadheadArrowEntity> khBroadheadArrowEntityEntityType, Level level) {
        super(khBroadheadArrowEntityEntityType, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Services.ENTITY.getBroadheadItem());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            scHitEntity(target, new ItemStack(Services.ENTITY.getBroadheadItem()), getBaseDamage());
        }
    }
}