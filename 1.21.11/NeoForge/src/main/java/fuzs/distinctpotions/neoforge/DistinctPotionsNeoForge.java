package fuzs.distinctpotions.neoforge;

import fuzs.distinctpotions.DistinctPotions;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.neoforged.fml.common.Mod;

@Mod(DistinctPotions.MOD_ID)
public class DistinctPotionsNeoForge {

    public DistinctPotionsNeoForge() {
        ModConstructor.construct(DistinctPotions.MOD_ID, DistinctPotions::new);
    }
}
