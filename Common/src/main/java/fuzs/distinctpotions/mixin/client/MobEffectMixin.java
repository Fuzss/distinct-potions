package fuzs.distinctpotions.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import fuzs.distinctpotions.DistinctPotions;
import fuzs.distinctpotions.config.ClientConfig;
import fuzs.puzzleslib.api.config.v3.serialization.ConfigDataSet;
import net.minecraft.world.effect.MobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MobEffect.class)
abstract class MobEffectMixin {

    @ModifyReturnValue(method = "getColor", at = @At("RETURN"))
    public int getColor(int color) {
        ConfigDataSet<MobEffect> mobEffectColorOverrides = DistinctPotions.CONFIG.get(ClientConfig.class).mobEffectColorOverrides;
        if (!mobEffectColorOverrides.contains(MobEffect.class.cast(this))) {
            return color;
        }

        return (int) mobEffectColorOverrides.get(MobEffect.class.cast(this))[0];
    }
}
