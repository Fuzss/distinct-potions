package fuzs.distinctpotions.common.client.renderer.item.properties.conditional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import fuzs.distinctpotions.common.DistinctPotions;
import fuzs.distinctpotions.common.client.handler.PotionNameHandler;
import fuzs.distinctpotions.common.config.ClientConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import org.jspecify.annotations.Nullable;

import java.util.Locale;

public record PotionType() implements SelectItemModelProperty<PotionType.Type> {
    public static final Codec<Type> VALUE_CODEC = Type.CODEC;
    public static final SelectItemModelProperty.Type<PotionType, PotionType.Type> TYPE = SelectItemModelProperty.Type.create(
            MapCodec.unit(new PotionType()),
            PotionType.Type.CODEC);

    @Override
    public Type get(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int seed, ItemDisplayContext itemDisplayContext) {
        if (!DistinctPotions.CONFIG.get(ClientConfig.class).dedicatedPotionBottles) {
            return Type.STANDARD;
        }

        Potion potion = PotionNameHandler.getNullablePotion(itemStack);
        if (potion != null) {
            if (DistinctPotions.CONFIG.get(ClientConfig.class).strongPotions.contains(potion)) {
                return Type.STRONG;
            } else if (DistinctPotions.CONFIG.get(ClientConfig.class).longPotions.contains(potion)) {
                return Type.LONG;
            }
        }

        return Type.STANDARD;
    }

    @Override
    public Codec<Type> valueCodec() {
        return VALUE_CODEC;
    }

    @Override
    public SelectItemModelProperty.Type<? extends SelectItemModelProperty<Type>, Type> type() {
        return TYPE;
    }

    public enum Type implements StringRepresentable {
        STANDARD,
        LONG,
        STRONG;

        public static final Codec<Type> CODEC = StringRepresentable.fromEnum(Type::values);

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
