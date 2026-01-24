package fuzs.distinctpotions.mixin.client;

import fuzs.distinctpotions.DistinctPotions;
import fuzs.distinctpotions.config.ClientConfig;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionContents.class)
abstract class PotionContentsMixin {

    @Inject(method = "getColorOr", at = @At("HEAD"), cancellable = true)
    public void getColor(int defaultValue, CallbackInfoReturnable<Integer> callback) {
        if (!DistinctPotions.CONFIG.get(ClientConfig.class).distinctBasePotions) {
            return;
        }

        if (defaultValue == PotionContents.BASE_POTION_COLOR) {
            // Only checking for an empty effects list is not enough as it will also recolor water bottles.
            if (this.is(Potions.AWKWARD) || this.is(Potions.THICK) || this.is(Potions.MUNDANE)) {
                DyeColor dyeColor = DistinctPotions.CONFIG.get(ClientConfig.class).basePotionsColor;
                callback.setReturnValue(dyeColor.getTextureDiffuseColor());
            }
        }
    }

    @Shadow
    public abstract boolean is(Holder<Potion> potion);
}
