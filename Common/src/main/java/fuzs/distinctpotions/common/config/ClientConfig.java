package fuzs.distinctpotions.common.config;

import fuzs.puzzleslib.common.api.config.v3.Config;
import fuzs.puzzleslib.common.api.config.v3.ConfigCore;
import fuzs.puzzleslib.common.api.config.v3.serialization.ConfigDataSet;
import fuzs.puzzleslib.common.api.config.v3.serialization.KeyedValueProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.alchemy.Potion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientConfig implements ConfigCore {
    private static final String POTION_TYPES_CATEGORY = "potion_types";

    @Config(description = "Give base potions (awkward, mundane and thick) a unique effect color to set them apart from water bottles.")
    public boolean distinctBasePotions = true;
    @Config(description = "The color given to base potions (awkward, mundane and thick).")
    public DyeColor basePotionsColor = DyeColor.BROWN;
    @Config(name = "mob_effect_color_overrides", description = {
            "Custom color overrides for mob effects, allows for restoring effect colors from previous Minecraft versions.",
            "Format for every entry is \"<namespace>:<path>,<rgbcolor>\". Tags are supported, must be in the format of \"#<namespace>:<path>,<rgbcolor>\". Namespace may be omitted to use \"minecraft\" by default. May use asterisk as wildcard parameter via pattern matching, e.g. \"minecraft:*_shulker_box\" to match all shulker boxes no matter of color."
    })
    List<String> mobEffectColorOverridesRaw = KeyedValueProvider.asString(Registries.MOB_EFFECT);
    @Config(description = {
            "Makes the bottle form of greater and extended potion items unique.",
            "Requires an asset reload to apply (e.g., via F3 + T)."
    })
    public boolean uniquePotionBottles = true;
    @Config(description = "Highlights lesser, greater and extended potions directly in the potion name on the item tooltip.")
    public boolean uniquePotionNames = true;
    @Config(name = "greater_potions", category = POTION_TYPES_CATEGORY, description = {
            "Potions categorized as granting a stronger than usual effect amplifier.",
            "Used for providing alternate item names & textures.",
            "This option applies before extended & lesser potions will be handled.",
            ConfigDataSet.CONFIG_DESCRIPTION
    })
    List<String> greaterPotionsRaw = new ArrayList<>(Arrays.asList("*:strong_*", "*:*_strong"));
    @Config(name = "extended_potions", category = POTION_TYPES_CATEGORY, description = {
            "Potions categorized as having a longer than usual effect duration.",
            "Used for providing alternate item names & textures.",
            "This option applies only after greater potions have been handled, but before lesser potions will be.",
            ConfigDataSet.CONFIG_DESCRIPTION
    })
    List<String> extendedPotionsRaw = new ArrayList<>(Arrays.asList("*:long_*", "*:*_long"));
    @Config(name = "lesser_potions", category = POTION_TYPES_CATEGORY, description = {
            "Potions categorized as normal potions.",
            "Used for providing alternate item names.",
            "This option applies only after greater and extended potions have been handled.",
            ConfigDataSet.CONFIG_DESCRIPTION
    })
    List<String> lesserPotionsRaw = new ArrayList<>(Arrays.asList("*:*"));

    public ConfigDataSet<MobEffect> mobEffectColorOverrides;
    public ConfigDataSet<Potion> greaterPotions;
    public ConfigDataSet<Potion> extendedPotions;
    public ConfigDataSet<Potion> lesserPotions;

    @Override
    public void afterConfigReload() {
        this.mobEffectColorOverrides = ConfigDataSet.from(Registries.MOB_EFFECT,
                this.mobEffectColorOverridesRaw,
                int.class);
        this.greaterPotions = ConfigDataSet.from(Registries.POTION, this.greaterPotionsRaw);
        this.extendedPotions = ConfigDataSet.from(Registries.POTION, this.extendedPotionsRaw);
        this.lesserPotions = ConfigDataSet.from(Registries.POTION, this.lesserPotionsRaw);
    }
}
