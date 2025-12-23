package com.knightsheraldry.mixin;

import com.knightsheraldry.client.entity.armor.HorseBardingFeatureRenderer;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HorseRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.animal.horse.Horse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseRenderer.class)
public abstract class HorseRendererMixin extends LivingEntityRenderer<Horse, HorseModel<Horse>> {
    public HorseRendererMixin(EntityRendererProvider.Context ctx, HorseModel<Horse> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new HorseBardingFeatureRenderer(this, context.getModelSet()));
    }
}