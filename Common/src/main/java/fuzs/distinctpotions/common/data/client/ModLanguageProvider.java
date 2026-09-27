package fuzs.distinctpotions.common.data.client;

import fuzs.distinctpotions.common.client.handler.PotionNameHandler;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(PotionNameHandler.LESSER_POTION_TRANSLATION_KEY, "Lesser %s");
        this.add(PotionNameHandler.GREATER_POTION_TRANSLATION_KEY, "Greater %s");
        this.add(PotionNameHandler.EXTENDED_POTION_TRANSLATION_KEY, "Extended %s");
    }
}
