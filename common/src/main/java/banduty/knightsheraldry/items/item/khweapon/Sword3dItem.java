package banduty.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.client.SC3DRendererProvider;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class Sword3dItem extends SwordItem implements SC3DRendererProvider {
    public Sword3dItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
}
