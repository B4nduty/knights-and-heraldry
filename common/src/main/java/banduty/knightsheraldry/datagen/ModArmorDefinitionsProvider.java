package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.datagen.DefinitionsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModArmorDefinitionsProvider extends DefinitionsProvider.Armor {
    public ModArmorDefinitionsProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void generateDefinitions(ArmorConsumer biConsumer) {
        // Gambeson Set
        biConsumer.accept(KHItems.QUILTED_COIF.get(), Builder.create()
                .weight(0.5)
                .deflect(0.005, 0.05, 0.0)
                .build()
        );
        biConsumer.accept(KHItems.GAMBESON.get(), Builder.create()
                .weight(5.0)
                .deflect(0.025, 0.25, 0.0)
                .build()
        );
        biConsumer.accept(KHItems.GAMBESON_BREECHES.get(), Builder.create()
                .weight(2.0)
                .deflect(0.01, 0.1, 0.0)
                .build()
        );
        biConsumer.accept(KHItems.GAMBESON_BOOTS.get(), Builder.create()
                .weight(1.0)
                .deflect(0.002, 0.02, 0.0)
                .build()
        );

        // Arming Set
        biConsumer.accept(KHItems.ARMING_DOUBLET.get(), Builder.create()
                .weight(7.5)
                .deflect(0.05, 0.05, 0.0)
                .build()
        );
        biConsumer.accept(KHItems.ARMING_HOSE.get(), Builder.create()
                .weight(5.0)
                .deflect(0.03, 0.03, 0.0)
                .build()
        );

        // Mail Set
        biConsumer.accept(KHItems.MAIL_COIF.get(), Builder.create()
                .weight(2.5)
                .deflect(0.04, 0.02, 0.0)
                .build()
        );
        biConsumer.accept(KHItems.HAUBERK.get(), Builder.create()
                .weight(10.0)
                .deflect(0.07, 0.03, 0.0)
                .build()
        );
        biConsumer.accept(KHItems.MAIL_BREECHES.get(), Builder.create()
                .weight(8.0)
                .deflect(0.05, 0.02, 0.0)
                .build()
        );
        biConsumer.accept(KHItems.MAIL_BOOTS.get(), Builder.create()
                .weight(3.0)
                .deflect(0.02, 0.01, 0.0)
                .build()
        );

        // Horse Armor
        for (Supplier<Item> item : KHItems.HORSE_BARDING.list()) {
            biConsumer.accept(item.get(), Builder.create()
                    .deflect(0.8, 0.4, 0)
                    .build()
            );
        }
    }
}