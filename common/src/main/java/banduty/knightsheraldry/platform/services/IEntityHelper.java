package banduty.knightsheraldry.platform.services;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;

public interface IEntityHelper {
    EntityType<? extends SCArrowEntity> getBodkinEntity();
    Item getBodkinItem();
    EntityType<? extends SCArrowEntity> getBroadheadEntity();
    Item getBroadheadItem();
    EntityType<? extends SCArrowEntity> getClothEntity();
    Item getClothItem();
    EntityType<? extends SCArrowEntity> getSwallowtailEntity();
    Item getSwallowtailItem();
    EntityType<? extends AbstractArrow> getWardartEntity();
    Item getWardartItem();
}
