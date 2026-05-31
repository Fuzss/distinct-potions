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
    public static final String LESSER_POTION_TRANSLATION_KEY = DistinctPotions.id("potion")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "lesser");
    public static final String GREATER_POTION_TRANSLATION_KEY = DistinctPotions.id("potion")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "greater");
    public static final String EXTENDED_POTION_TRANSLATION_KEY = DistinctPotions.id("potion")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "extended");

    public static Component getExtendedPotionName(ItemStack itemStack, Component component) {
        if (!DistinctPotions.CONFIG.get(ClientConfig.class).uniquePotionNames) {
            return component;
        }

        Potion potion = getNullablePotion(itemStack);
        if (potion == null) {
            return component;
        } else if (DistinctPotions.CONFIG.get(ClientConfig.class).greaterPotions.contains(potion)) {
            return Component.translatable(GREATER_POTION_TRANSLATION_KEY, component);
        } else if (DistinctPotions.CONFIG.get(ClientConfig.class).extendedPotions.contains(potion)) {
            return Component.translatable(EXTENDED_POTION_TRANSLATION_KEY, component);
        } else if (!potion.getEffects().isEmpty()
                && DistinctPotions.CONFIG.get(ClientConfig.class).lesserPotions.contains(potion)) {
            return Component.translatable(LESSER_POTION_TRANSLATION_KEY, component);
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
