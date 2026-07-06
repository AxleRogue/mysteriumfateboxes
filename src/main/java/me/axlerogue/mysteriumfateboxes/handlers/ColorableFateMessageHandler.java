package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ColorableFateMessageHandler {
    public static void sendFateMessage(Player player, boolean isGoodFate, String message) {
        ChatFormatting color = isGoodFate ? ChatFormatting.GREEN : ChatFormatting.RED;
        player.sendSystemMessage(Component.literal(message).withStyle(color, ChatFormatting.BOLD));
    }
}
