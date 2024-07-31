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
                "stiletto_3d",
                "rapier_3d",
                "rapier_3d_blocking",
                "sword_3d",
                "sword_3d_blocking",
                "sword_3d_bludgeoning",
                "sword_3d_bludgeoning_blocking",
                "v_sword_3d",
                "v_sword_3d_blocking",
                "v_sword_3d_bludgeoning",
                "v_sword_3d_bludgeoning_blocking",
                "arming_sword_3d",
                "arming_sword_3d_blocking",
                "arming_sword_3d_bludgeoning",
                "arming_sword_3d_bludgeoning_blocking",
                "axe_3d",
                "axe_3d_blocking",
                "broad_axe_3d",
                "broad_axe_3d_blocking",
                "crooked_axe_3d",
                "crooked_axe_3d_blocking",
                "straight_crooked_axe_3d",
                "straight_crooked_axe_3d_blocking",
                "warsword_3d",
                "warsword_3d_blocking",
                "warsword_3d_bludgeoning",
                "warsword_3d_bludgeoning_blocking",
                "warsword_claymore_3d",
                "warsword_claymore_3d_blocking",
                "warsword_claymore_3d_bludgeoning",
                "warsword_claymore_3d_bludgeoning_blocking",
                "warsword_flamberge_3d",
                "warsword_flamberge_3d_blocking",
                "warsword_flamberge_3d_bludgeoning",
                "warsword_flamberge_3d_bludgeoning_blocking",
                "warsword_zweihander_3d",
                "warsword_zweihander_3d_blocking",
                "warsword_zweihander_3d_bludgeoning",
                "warsword_zweihander_3d_bludgeoning_blocking"
        };

        for (String modelName : modelNames) {
            this.addModel(new ModelIdentifier(KnightsHeraldry.MOD_ID, modelName, "inventory"));
        }
    }
}