package fuzs.distinctpotions.fabric.client;

import fuzs.distinctpotions.DistinctPotions;
import fuzs.distinctpotions.client.DistinctPotionsClient;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class DistinctPotionsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(DistinctPotions.MOD_ID, DistinctPotionsClient::new);
    }
}
