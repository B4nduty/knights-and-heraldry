package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow
    protected abstract void addModel(ModelIdentifier modelId);

    @Inject(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER)
    )
    public void add3dModels(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {

        String[] modelNames = {
                "dagger_3d",
                "rapier_3d",
                "sword_3d",
                "v_sword_3d",
                "arming_sword_3d",
                "axe_3d",
                "broad_axe_3d",
                "crooked_axe_3d",
                "straight_crooked_axe_3d",
                "mace_3d",
                "spiked_mace_3d",
                "flail_3d",
                "ball_flail_3d",
                "hammer_3d",
                "war_hammer_3d",
                "longsword_3d",
                "v_longsword_3d",
                "falchion_3d",
                "scimitar_3d",
                "katana_3d",
                "pitchfork_3d",
                "spear_3d",
                "pike_3d",
                "billhook_3d",
                "glaive_3d",
                "curved_glaive_3d",
                "warsword_3d",
                "warsword_claymore_3d",
                "warsword_flamberge_3d",
                "warsword_zweihander_3d",
        };

        for (String modelName : modelNames) {
            this.addModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, modelName, "inventory"));
        }
    }
}