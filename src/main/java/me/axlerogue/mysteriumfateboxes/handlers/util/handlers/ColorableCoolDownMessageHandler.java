package me.axlerogue.mysteriumfateboxes.handlers.util.handlers;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ColorableCoolDownMessageHandler {

    public static void sendOnCooldownMessage(Player player, long secondsLeft) {
        player.sendSystemMessage(Component.literal("You must wait " + secondsLeft + " seconds before opening another Fate Box!")
                .withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
    }

    public static void sendCooldownStartedMessage(Player player, int cooldownSeconds) {
        player.sendSystemMessage(Component.literal("Fate Box opened! You are now on cooldown for " + cooldownSeconds + " seconds.")
                .withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD)); // AQUA is the closest to Diamond Blue
    }
}
