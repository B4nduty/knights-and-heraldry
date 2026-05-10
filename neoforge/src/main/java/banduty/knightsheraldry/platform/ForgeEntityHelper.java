package banduty.knightsheraldry.platform;

import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.entity.custom.SCArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;

public class ForgeEntityHelper implements IEntityHelper {
    @Override
    public EntityType<? extends SCArrowEntity> getBodkinEntity() {
        return KHEntities.BODKIN_ARROW.get();
    }

    @Override
    public Item getBodkinItem() {
        return KHItems.BODKIN_ARROW.get();
    }

    @Override
    public EntityType<? extends SCArrowEntity> getBroadheadEntity() {
        return KHEntities.BROADHEAD_ARROW.get();
    }

    @Override
    public Item getBroadheadItem() {
        return KHItems.BROADHEAD_ARROW.get();
    }

    @Override
    public EntityType<? extends SCArrowEntity> getClothEntity() {
        return KHEntities.CLOTH_ARROW.get();
    }

    @Override
    public Item getClothItem() {
        return KHItems.CLOTH_ARROW.get();
    }

    @Override
    public EntityType<? extends SCArrowEntity> getSwallowtailEntity() {
        return KHEntities.SWALLOWTAIL_ARROW.get();
    }

    @Override
    public Item getSwallowtailItem() {
        return KHItems.SWALLOWTAIL_ARROW.get();
    }

    @Override
    public EntityType<? extends AbstractArrow> getWardartEntity() {
        return KHEntities.WARDART_PROJECTILE.get();
    }

    @Override
    public Item getWardartItem() {
        return KHItems.WARDART.get();
    }
}
