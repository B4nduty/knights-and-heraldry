package com.knightsheraldry.util.itemdata;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.model.ModelLoader;

public enum KHTags {

    // Weapon Tags
    /**
     * If you want to have your Weapon act as a KH Weapon
     * It is highly recommended to put this in your Melee Weapons
     * Don't use it for Ranged Weapons
     */
    WEAPONS("weapons"),
    /**
     * If you want to have your Weapon 3D
     * <p>
     * You will need to make another model file with the name "your_item_3d.json",
     * <p>
     * Mixin {@link ModelLoader} and Inject in method ModelLoader#addModel:
     *  <pre>
     *   {@code
     *   @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER))
     *   public void mod_id$add3dModels(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
     *       String[] modelNames = {
     *           "your_item_3d",
     *           // Add other model names here
     *       };
     *
     *       for (String modelName : modelNames) {
     *           this.addModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, modelName, "inventory"));
     *       }
     *   }
     *   </pre>
     */
    WEAPONS_3D("weapons_3d"),
    /**
     * If you want to have your Weapon act as a Shield
     * If you want to change the position of the Weapon in First Person,
     * you will need to add in your 3D Model file the predicate "blocking"
     * It's compatible with "bludgeoning" predicate
     */
    WEAPONS_SHIELD("weapons_shield"),
    /**
     * If you want to have your Weapon deals x2 of its original damage when hit from Behind
     */
    WEAPONS_DAMAGE_BEHIND("weapons_damage_behind"),
    /**
     * If you want to have your Weapon ignore Armor
     */
    WEAPONS_IGNORES_ARMOR("weapons_ignores_armor"),
    /**
     * If you want your Weapon deal Bludgeoning Damage
     * If you want to change the position of the Weapon in First Person,
     * you will need to add in your 3D Model file the predicate "bludgeoning"
     * It's compatible with "blocking" predicate
     */
    WEAPONS_BLUDGEONING("weapons_bludgeoning"),
    /**
     * If you want your Weapon deal Piercing Damage
     */
    WEAPONS_PIERCING("weapons_piercing"),
    /**
     * If you want your Weapon disable the Shield/Parrying of another Weapon as Axes with the Shield
     */
    WEAPONS_DISABLE_SHIELD("weapons_disable_shield"),
    /**
     * If you want your Weapon bypass Shield/Parrying
     */
    WEAPONS_BYPASS_BLOCK("weapons_bypass_block"),
    /**
     * If you want your Weapon change bludgeoning to piercing and the other way back
     */
    WEAPONS_BLUDGEONING_TO_PIERCING("weapons_bludgeoning_to_piercing"),
    /**
     * If you want your Weapon can Harvest
     */
    WEAPONS_HARVEST("weapons_harvest"),
    /**
     * If you want your Geo Weapon (Geckolib Weapon) has a 2D Texture in HUD
     * <p>
     * You will need to make another model file with the name "your_item_icon.json"
     * <p>
     * Mixin {@link ModelLoader} and Inject in method ModelLoader#addModel:
     * <pre>
     * {@code
     * @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER))
     * public void mod_id$add3dModels(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
     *          String[] modelNames = {
     *              "your_item_icon",
     *              // Add other model names here
     *          };
     *
     *          for (String modelName : modelNames) {
     *              this.addModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, modelName, "inventory"));
     *          }
     *}
     *</pre>
     */
    GEO_2D_ITEMS("geo_2d_items"),

    // Armor Tags
    /**
     * Trinkets Items that can be Wearable, also if there's not the full Under Armor
     */
    ALWAYS_WEARABLE("always_wearable"),
    /**
     * Give the player reduced vision with an overlay that covers the top and bottom of the screen.
     */
    VISORED_HELMET("visored_helmet"),
    /**
     * Armor that can deflect SwallowTail Arrow
     */
    DEFLECTIVE_ARMOR("deflective_armor");

    private final TagKey<Item> tag;

    KHTags(String name) {
        this.tag = TagKey.of(RegistryKeys.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name));
    }

    public TagKey<Item> getTag() {
        return tag;
    }
}