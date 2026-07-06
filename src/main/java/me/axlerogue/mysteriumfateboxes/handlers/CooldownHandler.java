package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownHandler {
    private static final Map<UUID, Long> cooldowns = new HashMap<>();
    public static final int COOLDOWN_SECONDS = 26; // Change this to whatever you want

    public static boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player.getUUID()) && cooldowns.get(player.getUUID()) > System.currentTimeMillis();
    }

    public static long getRemainingCooldown(Player player) {
        if (!isOnCooldown(player)) {
            return 0;
        }
        return (long) Math.ceil((cooldowns.get(player.getUUID()) - System.currentTimeMillis()) / 1000.0);
    }

    public static void setCooldown(Player player) {
        cooldowns.put(player.getUUID(), System.currentTimeMillis() + (COOLDOWN_SECONDS * 1000));
    }
}
