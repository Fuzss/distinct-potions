package fuzs.distinctpotions.common.client.handler;

import fuzs.distinctpotions.common.DistinctPotions;
import fuzs.distinctpotions.common.config.ClientConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jspecify.annotations.Nullable;

public class PotionNameHandler {
    public static final String STANDARD_POTION_TRANSLATION_KEY = DistinctPotions.id("potion")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "standard");
    public static final String STRONG_POTION_TRANSLATION_KEY = DistinctPotions.id("potion")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "strong");
    public static final String LONG_POTION_TRANSLATION_KEY = DistinctPotions.id("potion")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "long");

    public static Component getExtendedPotionName(ItemStack itemStack, Component component) {
        if (!DistinctPotions.CONFIG.get(ClientConfig.class).extendedPotionNames) {
            return component;
        }

        Potion potion = getNullablePotion(itemStack);
        if (potion == null) {
            return component;
        } else if (DistinctPotions.CONFIG.get(ClientConfig.class).strongPotions.contains(potion)) {
            return Component.translatable(STRONG_POTION_TRANSLATION_KEY, component);
        } else if (DistinctPotions.CONFIG.get(ClientConfig.class).longPotions.contains(potion)) {
            return Component.translatable(LONG_POTION_TRANSLATION_KEY, component);
        } else if (!potion.getEffects().isEmpty()
                && DistinctPotions.CONFIG.get(ClientConfig.class).standardPotions.contains(potion)) {
            return Component.translatable(STANDARD_POTION_TRANSLATION_KEY, component);
        } else {
            return component;
        }
    }

    @Nullable
    public static Potion getNullablePotion(ItemStack itemStack) {
        PotionContents potionContents = itemStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        return potionContents.potion().map(Holder::value).orElse(null);
    }
}
