package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ColorableLuckMessageHandler {
    public static void sendLuckMessage(Player player, boolean isGoodLuck, String message) {
        ChatFormatting color = isGoodLuck ? ChatFormatting.GREEN : ChatFormatting.RED;
        player.sendSystemMessage(Component.literal(message).withStyle(color, ChatFormatting.BOLD));
    }
}
