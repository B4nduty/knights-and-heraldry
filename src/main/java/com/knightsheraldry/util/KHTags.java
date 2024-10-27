package com.knightsheraldry.util;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.model.ModelLoader;

public class KHTags {
    public static class Weapon {

        /**
         * If you want to have your Weapon act as a KH Weapon
         * It is highly recommended to put this in your Melee Weapons
         * Don't use it for Ranged Weapons
         */
        public static final TagKey<Item> KH_WEAPONS = createTag("kh_weapons");

        /**
         * If you want to have your Weapon 3D
         * <p>
         * You will need to make another model file with the name "your_item_3D.json",
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
        public static final TagKey<Item> KH_WEAPONS_3D = createTag("kh_weapons_3d");

        /**
         * If you want to have your Weapon act as a Shield
         * If you want to change the position of the Weapon in First Person,
         * you will need to add in your 3D Model file the predicate "blocking"
         * It's compatible with "bludgeoning" predicate
         */
        public static final TagKey<Item> KH_WEAPONS_SHIELD = createTag("kh_weapons_shield");

        /**
         * If you want to have your Weapon deals x2 of its original damage when hit from Behind
         */
        public static final TagKey<Item> KH_WEAPONS_DAMAGE_BEHIND = createTag("kh_weapons_damage_behind");

        /**
         * If you want to have your Weapon ignore Armor
         */
        public static final TagKey<Item> KH_WEAPONS_IGNORES_ARMOR = createTag("kh_weapons_ignores_armor");

        /**
         * If you want your Weapon deal Bludgeoning Damage
         * If you want to change the position of the Weapon in First Person,
         * you will need to add in your 3D Model file the predicate "bludgeoning"
         * It's compatible with "blocking" predicate
         */
        public static final TagKey<Item> KH_WEAPONS_BLUDGEONING = createTag("kh_weapons_bludgeoning");

        /**
         * If you want your Weapon deal Piercing Damage
         */
        public static final TagKey<Item> KH_WEAPONS_PIERCING = createTag("kh_weapons_piercing");

        /**
         * If you want your Weapon disable the Shield/Parrying of another Weapon as Axes with the Shield
         */
        public static final TagKey<Item> KH_WEAPONS_DISABLE_SHIELD = createTag("kh_weapons_disable_shield");

        /**
         * If you want your Weapon bypass Shield/Parrying
         */
        public static final TagKey<Item> KH_WEAPONS_BYPASS_BLOCK = createTag("kh_weapons_bypass_block");

        /**
         * If you want your Weapon change bludgeoning to piercing and the other way back
         */
        public static final TagKey<Item> KH_WEAPONS_BLUDGEONING_TO_PIERCING = createTag("kh_weapons_bludgeoning_to_piercing");

        /**
         * If you want your Weapon can Harvest
         */
        public static final TagKey<Item> KH_WEAPONS_HARVEST = createTag("kh_weapons_harvest");

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
        public static final TagKey<Item> KH_GEO_2D_ITEMS = createTag("kh_geo_2d_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name));
        }
    }

    public static class Armors {

        /**
         * Trinkets Items that can be Wearable, also if there's not the full Under Armor
         */
        public static final TagKey<Item> KH_ALWAYS_WEARABLE = createTag("kh_always_wearable");

        /**
         * Give the player reduced vision with an overlay that covers the top and bottom of the screen.
         */
        public static final TagKey<Item> VISORED_HELMET = createTag("visored_helmet");

        /**
         * Armor that can deflect SwallowTail Arrow
         */
        public static final TagKey<Item> KH_DEFLECTIVE_ARMOR = createTag("kh_deflective_armor");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(KnightsHeraldry.MOD_ID, name));
        }
    }
}
