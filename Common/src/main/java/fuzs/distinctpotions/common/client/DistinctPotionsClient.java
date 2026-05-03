package fuzs.distinctpotions.common.client;

import fuzs.distinctpotions.common.DistinctPotions;
import fuzs.distinctpotions.common.client.renderer.item.properties.conditional.PotionType;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.ItemModelsContext;

public class DistinctPotionsClient implements ClientModConstructor {

    @Override
    public void onRegisterItemModels(ItemModelsContext context) {
        context.registerSelectItemModelProperty(DistinctPotions.id("potion_type"), PotionType.TYPE);
    }
}
