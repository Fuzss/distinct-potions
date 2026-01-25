package fuzs.distinctpotions.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import fuzs.puzzleslib.api.config.v3.serialization.ConfigDataSet;
import fuzs.puzzleslib.api.config.v3.serialization.KeyedValueProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.alchemy.Potion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientConfig implements ConfigCore {
    @Config(description = "Give base potions (awkward, mundane and thick) a unique effect color to set them apart from water bottles.")
    public boolean distinctBasePotions = true;
    @Config(description = "The color given to base potions (awkward, mundane and thick).")
    public DyeColor basePotionsColor = DyeColor.BROWN;
    @Config(description = {
            "Custom color overrides for mob effects, allows for restoring effect colors from previous Minecraft versions.",
            "Format for every entry is \"<namespace>:<path>,<rgbcolor>\". Tags are supported, must be in the format of \"#<namespace>:<path>,<rgbcolor>\". Namespace may be omitted to use \"minecraft\" by default. May use asterisk as wildcard parameter via pattern matching, e.g. \"minecraft:*_shulker_box\" to match all shulker boxes no matter of color."
    })
    List<String> mobEffectColorOverridesRaw = KeyedValueProvider.asString(Registries.MOB_EFFECT);
    @Config(description = {
            "Makes the bottle form and cork color of strong and long potions unique.",
            "Requires an asset reload to apply."
    })
    public boolean dedicatedPotionBottles = true;
    @Config(description = "Highlights strong and long potions directly in the potion name on the item tooltip.")
    public boolean extendedPotionNames = true;
    @Config(name = "strong_potions", description = {
            "Potion types to be categorized as strong potions for providing alternate item names & textures.",
            "This option applies before long & standard potions will be handled.",
            ConfigDataSet.CONFIG_DESCRIPTION
    })
    List<String> strongPotionsRaw = new ArrayList<>(Arrays.asList("*:strong_*", "*:*_strong"));
    @Config(name = "long_potions", description = {
            "Potion types to be categorized as long potions for providing alternate item names & textures.",
            "This option applies only after strong potions have been handled, but before standard potions will be.",
            ConfigDataSet.CONFIG_DESCRIPTION
    })
    List<String> longPotionsRaw = new ArrayList<>(Arrays.asList("*:long_*", "*:*_long"));
    @Config(name = "standard_potions", description = {
            "Potion types to be categorized as standard potions for providing alternate item names.",
            "This option applies only after strong & long potions have been handled.",
            ConfigDataSet.CONFIG_DESCRIPTION
    })
    List<String> standardPotionsRaw = new ArrayList<>(Arrays.asList("*:*"));

    public ConfigDataSet<MobEffect> mobEffectColorOverrides;
    public ConfigDataSet<Potion> strongPotions;
    public ConfigDataSet<Potion> longPotions;
    public ConfigDataSet<Potion> standardPotions;

    @Override
    public void afterConfigReload() {
        this.mobEffectColorOverrides = ConfigDataSet.from(Registries.MOB_EFFECT,
                this.mobEffectColorOverridesRaw,
                int.class);
        this.strongPotions = ConfigDataSet.from(Registries.POTION, this.strongPotionsRaw);
        this.longPotions = ConfigDataSet.from(Registries.POTION, this.longPotionsRaw);
        this.standardPotions = ConfigDataSet.from(Registries.POTION, this.standardPotionsRaw);
    }
}
