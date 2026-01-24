package fuzs.distinctpotions.neoforge.client;

import fuzs.distinctpotions.DistinctPotions;
import fuzs.distinctpotions.client.DistinctPotionsClient;
import fuzs.distinctpotions.data.client.ModLanguageProvider;
import fuzs.distinctpotions.data.client.ModModelProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = DistinctPotions.MOD_ID, dist = Dist.CLIENT)
public class DistinctPotionsNeoForgeClient {

    public DistinctPotionsNeoForgeClient() {
        ClientModConstructor.construct(DistinctPotions.MOD_ID, DistinctPotionsClient::new);
        DataProviderHelper.registerDataProviders(DistinctPotions.MOD_ID,
                ModLanguageProvider::new,
                ModModelProvider::new);
    }
}
