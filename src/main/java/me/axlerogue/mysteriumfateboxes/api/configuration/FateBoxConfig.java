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

    public FateBoxConfig(ModConfigSpec.Builder builder) {
        builder.push("fatebox_events");
        allowLavaPools = builder.comment("Whether to allow Lava Pools as a bad fate.")
                .define("allowLavaPools", true);
        allowExplosions = builder.comment("Whether to allow Explosions as a bad fate.")
                .define("allowExplosions", true);
        allowWitherBoss = builder.comment("Whether to allow Wither Boss spawning as a bad fate.")
                .define("allowWitherBoss", true);
        builder.pop();
    }
}