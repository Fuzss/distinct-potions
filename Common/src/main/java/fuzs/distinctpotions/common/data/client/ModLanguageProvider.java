package fuzs.distinctpotions.common.data.client;

import fuzs.distinctpotions.common.client.handler.PotionNameHandler;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(PotionNameHandler.LESSER_POTION_TRANSLATION_KEY, "Lesser %s");
        translationBuilder.add(PotionNameHandler.GREATER_POTION_TRANSLATION_KEY, "Greater %s");
        translationBuilder.add(PotionNameHandler.EXTENDED_POTION_TRANSLATION_KEY, "Extended %s");
    }
}
