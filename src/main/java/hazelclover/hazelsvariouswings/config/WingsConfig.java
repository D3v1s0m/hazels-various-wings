package hazelclover.hazelsvariouswings.config;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import me.fzzyhmstrs.fzzy_config.annotations.*;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedCondition;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

// FIXME: This headers doesn't seem to work
@SuppressWarnings("CanBeFinal")
@TomlHeaderComment(text = """
        This is the config file for the wings in Hazel's Various Wings.
        Here you can change the durability, scale, and power of each wing.
        You can also enable or disable the wings' functionality in water and gliding.
        If you change the durability of a wing, you will need to restart the game for the changes to take effect.
        Properties:
        - durability: The durability of the wing.
        - scale: The scale of the wing.
        - flyDuration: The duration of the wing's flight.
        - flyPower: The power of the wing's flight.
        - hoverDuration: The duration of the wing's hover.
        - hoverPower: The power of the wing's hover.
        - inWaterFunctional: If the wing is functional in water.
        - doesGlide: If the wing can glide.""")
@Version(version = 1)
public class WingsConfig extends Config {

    public static WingsConfig INSTANCE;

    public WingConfig flimsyWings = new WingConfig(54, 0.7f, 0.3f, 0.2f, 0f, 0f, false, false, false);
    public WingConfig fledglingWings = new WingConfig(47, 0.8f, 0f, 0f, 0.3f, 0.2f, false, false, false);
    public WingConfig phantomWings = new WingConfig(130, 1f, 0.3f, 0.29f, 0f, 0f, false, false, false);
    public WingConfig leafWings = new WingConfig(122, 0.9f, 0.3f, 0.28f, 0.6f, 0.2f, false, false, false);
    public WingConfig coralWings = new WingConfig(370, 1f, 0.6f, 0.22f, 2f, 0.22f, true, false, false);
    public WingConfig beeWings = new WingConfig(235, 0.8f, 0.36f, 0.22f, 1.8f, 0.18f, false, false, false);
    public WingConfig mothWings = new WingConfig(324, 1f, 0.66f, 0.24f, 0.8f, 0.16f, false, false, false);
    public WingConfig demonWings = new WingConfig(145, 1.1f, 0.35f, 0.22f, 1.4f, 0.2f, false, false, false);
    public WingConfig blazeWings = new WingConfig(478, 1.3f, 0.5f, 0.32f, 2.2f, 0.17f, false, false, false);
    public WingConfig gemWings = new WingConfig(611, 1.2f, 0.4f, 0.28f, 0.7f, 0.2f, false, false, false);
    public WingConfig voidWings = new WingConfig(1100, 1.3f, 0.72f, 0.34f, 1.8f, 0.14f, false, false, false);
    public WingConfig elytraWings = new WingConfig(1620, 1.1f, 0f, 0f, 0f, 0f, false, true, true);
    public WingConfig pristineWings = new WingConfig(2120, 1.5f, 1.4f, 0.41f, 4f, 0.16f, true, true, false);
    public WingConfig nebulousWings = new WingConfig(2034, 1.65f, 1.84f, 0.502f, 9999f, 0.1f, true, false, false);
    public WingConfig aslerixWings = new WingConfig(2170, 1.6f, 2.1f, 0.3f, 9999f, 0.11f, true, true, false);
    public WingConfig hazelWings = new WingConfig(2140, 1.6f, 1.02f, 0.47f, 2f, 0.17f, true, true, false);
    public WingConfig mechanicalWings = new WingConfig(220, 1.1f, 0.5f, 0.26f, 1.2f, 0.17f, false, false, false);
    public WingConfig brassWings = new WingConfig(2300, 1.55f, 9f, 0.3f, 9999f, 0.2f, true, false, false);

    public WingsConfig() {
        super(Identifier.of(HazelsVariousWings.MOD_ID, "wings_config"));
    }

    public static void init() {
        INSTANCE = ConfigApiJava.registerAndLoadConfig(WingsConfig::new);
    }

    public static class WingConfig extends ConfigSection {
        @RequiresAction(action = Action.RESTART)
        @ValidatedInt.Restrict(min = 1)
        public int durability;
        @ValidatedFloat.Restrict(min = 0)
        public float scale;
        @ValidatedFloat.Restrict(min = 0)
        public float flyDuration;
        @ValidatedFloat.Restrict(min = 0)
        public float flyPower;
        @ValidatedFloat.Restrict(min = 0)
        public float hoverDuration;
        @ValidatedFloat.Restrict(min = 0)
        public float hoverPower;
        // TODO: Actually make this work?
        @Comment("If true, the wing can be used in water.")
        public boolean inWaterFunctional;
        @Comment("If true, the wing can be used to glide.")
        public ValidatedBoolean doesGlide;
        @Comment("""
            If true, fireworks can be used while gliding with this wing to increase speed, just like with the vanilla elytra.
            Note: This only works if the wing can glide.""")
        public ValidatedCondition<Boolean> canUseFireworks;

        public WingConfig(int durability, float scale, float flyDuration, float flyPower, float hoverDuration, float hoverPower, boolean inWaterFunctional, boolean doesGlide, boolean canUseFireworks) {
            this.durability = durability;
            this.scale = scale;
            this.flyDuration = flyDuration;
            this.flyPower = flyPower;
            this.hoverDuration = hoverDuration;
            this.hoverPower = hoverPower;
            this.inWaterFunctional = inWaterFunctional;
            this.doesGlide = new ValidatedBoolean(doesGlide);
            this.canUseFireworks = new ValidatedBoolean(canUseFireworks).toCondition(this.doesGlide, Text.literal("Does Glide must be true").formatted(Formatting.AQUA), () -> canUseFireworks);
        }
    }

    @Override
    public int defaultPermLevel() {
        return 3;
    }
}
