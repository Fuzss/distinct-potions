package fuzs.distinctpotions.client;

import fuzs.distinctpotions.DistinctPotions;
import fuzs.distinctpotions.client.renderer.item.properties.conditional.PotionType;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.ItemModelsContext;

public class DistinctPotionsClient implements ClientModConstructor {

    @Override
    public void onRegisterItemModels(ItemModelsContext context) {
        context.registerSelectItemModelProperty(DistinctPotions.id("potion_type"), PotionType.TYPE);
    }
}
