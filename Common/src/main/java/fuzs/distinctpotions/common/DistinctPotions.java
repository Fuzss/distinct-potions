package fuzs.distinctpotions.common;

import fuzs.distinctpotions.common.config.ClientConfig;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DistinctPotions implements ModConstructor {
    public static final String MOD_ID = "distinctpotions";
    public static final String MOD_NAME = "Distinct Potions";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).client(ClientConfig.class);

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
