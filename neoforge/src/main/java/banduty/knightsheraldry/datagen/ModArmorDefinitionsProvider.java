package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.DefinitionsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;

public class ModArmorDefinitionsProvider extends DefinitionsProvider.Armor {
    public ModArmorDefinitionsProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void generateDefinitions(ArmorConsumer consumer) {
        // Gambeson Set
        consumer.accept(KHItems.QUILTED_COIF.get(), Builder.create()
                        .weight(0.5)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.005, getAllArrows())
                        .build()
        );
        consumer.accept(KHItems.GAMBESON.get(), Builder.create()
                        .weight(5.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.025, getAllArrows())
                        .build()
        );
        consumer.accept(KHItems.GAMBESON_BREECHES.get(), Builder.create()
                        .weight(2.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.01, getAllArrows())
                        .build()
        );
        consumer.accept(KHItems.GAMBESON_BOOTS.get(), Builder.create()
                        .weight(1.0)
                        .damageResistance(0.04, 0.1, 0)
                        .deflectChance(0.002, getAllArrows())
                        .build()
        );

        // Mail Set
        consumer.accept(KHItems.MAIL_COIF.get(), Builder.create()
                        .weight(2.5)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.04, getAllArrows())
                        .build()
        );
        consumer.accept(KHItems.HAUBERK.get(), Builder.create()
                        .weight(10.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.07, getAllArrows())
                        .build()
        );
        consumer.accept(KHItems.MAIL_BREECHES.get(), Builder.create()
                        .weight(8.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.05, getAllArrows())
                        .build()
        );
        consumer.accept(KHItems.MAIL_BOOTS.get(), Builder.create()
                        .weight(3.0)
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.02, getAllArrows())
                        .build()
        );

        // Horse Armor
        consumer.accept(KHItems.HORSE_BARDING.get(), Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6, getAllArrows())
                        .build()
        );
        consumer.accept(KHItems.DARK_HORSE_BARDING.get(), Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6, getAllArrows())
                        .build()
        );
        consumer.accept(KHItems.GOLDEN_HORSE_BARDING.get(), Builder.create()
                        .damageResistance(0.1, 0.04, 0)
                        .deflectChance(0.6, getAllArrows())
                        .build()
        );
    }

    protected EntityType<?>[] getAllArrows() {
        return new EntityType<?>[] {
                EntityType.ARROW,
                KHEntities.BODKIN_ARROW.get(),
                KHEntities.BROADHEAD_ARROW.get(),
                KHEntities.CLOTH_ARROW.get(),
                KHEntities.SWALLOWTAIL_ARROW.get()
        };
    }
}
