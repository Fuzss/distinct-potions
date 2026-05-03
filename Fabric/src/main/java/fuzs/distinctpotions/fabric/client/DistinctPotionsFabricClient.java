package fuzs.distinctpotions.fabric.client;

import fuzs.distinctpotions.common.DistinctPotions;
import fuzs.distinctpotions.common.client.DistinctPotionsClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class DistinctPotionsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(DistinctPotions.MOD_ID, DistinctPotionsClient::new);
    }
}
