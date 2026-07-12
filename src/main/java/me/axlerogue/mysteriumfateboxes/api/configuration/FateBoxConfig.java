package me.axlerogue.mysteriumfateboxes.api.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Base configuration class for Mysterium Fateboxes.
 * Addons can extend this or use it to check main mod configurations.
 */
public class FateBoxConfig {
    public final ModConfigSpec.BooleanValue allowLavaPools;
    public final ModConfigSpec.BooleanValue allowExplosions;
    public final ModConfigSpec.BooleanValue allowWitherBoss;
    public final ModConfigSpec.BooleanValue devMode;

    public FateBoxConfig(ModConfigSpec.Builder builder) {
        builder.translation("config.mysteriumfateboxes.category.fatebox_events").push("fatebox_events");
        allowLavaPools = builder.comment("Whether to allow Lava Pools as a bad fate.")
                .translation("config.mysteriumfateboxes.allowLavaPools")
                .define("allowLavaPools", true);
        allowExplosions = builder.comment("Whether to allow Explosions as a bad fate.")
                .translation("config.mysteriumfateboxes.allowExplosions")
                .define("allowExplosions", true);
        allowWitherBoss = builder.comment("Whether to allow Wither Boss spawning as a bad fate.")
                .translation("config.mysteriumfateboxes.allowWitherBoss")
                .define("allowWitherBoss", true);
        devMode = builder.comment("Developer Mode: Disables cooldowns, changes sounds, and opens boxes instantly for testing.")
                .translation("config.mysteriumfateboxes.devMode")
                .define("devMode", false);
        builder.pop();
    }
}