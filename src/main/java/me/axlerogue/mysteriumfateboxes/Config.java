package me.axlerogue.mysteriumfateboxes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import me.axlerogue.mysteriumfateboxes.api.configuration.FateBoxConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final FateBoxConfig FATE_BOX_CONFIG = new FateBoxConfig(BUILDER);

    static final ModConfigSpec SPEC = BUILDER.build();
}
