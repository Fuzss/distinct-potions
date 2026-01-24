package fuzs.distinctpotions.data.client;

import fuzs.distinctpotions.client.handler.PotionNameHandler;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(PotionNameHandler.STANDARD_POTION_TRANSLATION_KEY, "Lesser %s");
        translationBuilder.add(PotionNameHandler.STRONG_POTION_TRANSLATION_KEY, "Greater %s");
        translationBuilder.add(PotionNameHandler.LONG_POTION_TRANSLATION_KEY, "Extended %s");
    }
}
