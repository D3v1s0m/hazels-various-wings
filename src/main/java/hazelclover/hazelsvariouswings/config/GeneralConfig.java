package hazelclover.hazelsvariouswings.config;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import hazelclover.hazelsvariouswings.item.ModTags;
import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.annotations.Version;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedCondition;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

@SuppressWarnings({"CanBeFinal", "unused"})
@Version(version = 1)
public class GeneralConfig extends Config {

    public static GeneralConfig INSTANCE;

    public GeneralConfig() {
        super(Identifier.of(HazelsVariousWings.MOD_ID, "config"));
    }

    public static void init() {
        INSTANCE = ConfigApiJava.registerAndLoadConfig(GeneralConfig::new);
    }

    @Comment("If true, the vanilla elytra will be disabled and only be used for crafting.")
    public ValidatedBoolean disableVanillaElytra = new ValidatedBoolean(false);

    @Comment("""
            If false, fireworks can't be used to boost the player while flying with vanilla elytra.
            Note: this option is only used if the vanilla elytra is enabled.""")
    public ValidatedCondition<Boolean> canUseFireworks = new ValidatedBoolean(true).toCondition(() -> !disableVanillaElytra.get(), Text.literal("Vanilla Elytra must be enabled").formatted(Formatting.AQUA), () -> false);

    @Comment("""
            If true, the elytra will be replaced with the wings specified in the 'replaceElytraInEndCityShipWith' option.
            Note: already generated End City Ships will not be affected.""")
    public ValidatedBoolean replaceElytraInEndCityShip = new ValidatedBoolean(true);

    @Comment("""
            The wings that will replace the elytra in the End City Ship. Only used if 'replaceElytraInEndCityShip' is true.
            Note: already generated End City Ships will not be affected.""")
    public ValidatedCondition<Identifier> replaceElytraInEndCityShipWith = (ValidatedIdentifier.ofTag(Identifier.of(HazelsVariousWings.MOD_ID, "elytra_wings"), ModTags.WINGS)).toCondition(replaceElytraInEndCityShip, Text.literal("Replace Elytra In End City Ship must be true").formatted(Formatting.AQUA), () -> Identifier.of(HazelsVariousWings.MOD_ID, "elytra_wings"));

    @Override
    public int defaultPermLevel() {
        return 3;
    }
}
