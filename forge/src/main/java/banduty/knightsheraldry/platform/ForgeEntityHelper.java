package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.entity.ModEntities;
import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.platform.services.IEntityHelper;
import banduty.stoneycore.entity.custom.SCArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;

public class ForgeEntityHelper implements IEntityHelper {
    @Override
    public EntityType<? extends SCArrowEntity> getBodkinEntity() {
        return ModEntities.BODKIN_ARROW.get();
    }

    @Override
    public Item getBodkinItem() {
        return ModItems.BODKIN_ARROW.get();
    }

    @Override
    public EntityType<? extends SCArrowEntity> getBroadheadEntity() {
        return ModEntities.BROADHEAD_ARROW.get();
    }

    @Override
    public Item getBroadheadItem() {
        return ModItems.BROADHEAD_ARROW.get();
    }

    @Override
    public EntityType<? extends SCArrowEntity> getClothEntity() {
        return ModEntities.CLOTH_ARROW.get();
    }

    @Override
    public Item getClothItem() {
        return ModItems.CLOTH_ARROW.get();
    }

    @Override
    public EntityType<? extends SCArrowEntity> getSwallowtailEntity() {
        return ModEntities.SWALLOWTAIL_ARROW.get();
    }

    @Override
    public Item getSwallowtailItem() {
        return ModItems.SWALLOWTAIL_ARROW.get();
    }

    @Override
    public EntityType<? extends AbstractArrow> getWardartEntity() {
        return ModEntities.WARDART_PROJECTILE.get();
    }

    @Override
    public Item getWardartItem() {
        return ModItems.WARDART.get();
    }
}
