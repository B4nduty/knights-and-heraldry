package banduty.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.client.SC3DRendererProvider;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class Sword3dItem extends SwordItem implements SC3DRendererProvider {
    /** Max durability overriding the tier's uses, or 0 to keep the tier's. */
    private final int durability;

    public Sword3dItem(Tier tier, Properties properties) {
        this(tier, properties, 0);
    }

    /**
     * TieredItem always overwrites {@code Properties#durability} with the tier's uses, so a weapon-specific
     * durability has to be passed here and is applied through {@link #components()} instead.
     */
    public Sword3dItem(Tier tier, Properties properties, int durability) {
        super(tier, properties);
        this.durability = durability;
    }

    @Override
    public DataComponentMap components() {
        return withDurability(super.components(), durability);
    }

    /** {@code base} with its max damage replaced by {@code durability} (unchanged when durability is 0). */
    public static DataComponentMap withDurability(DataComponentMap base, int durability) {
        if (durability <= 0) return base;
        return DataComponentMap.builder()
                .addAll(base)
                .set(DataComponents.MAX_DAMAGE, durability)
                .build();
    }
}
