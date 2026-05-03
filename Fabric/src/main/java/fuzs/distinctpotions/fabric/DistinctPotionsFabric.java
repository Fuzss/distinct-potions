package fuzs.distinctpotions.fabric;

import fuzs.distinctpotions.common.DistinctPotions;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class DistinctPotionsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(DistinctPotions.MOD_ID, DistinctPotions::new);
    }
}
