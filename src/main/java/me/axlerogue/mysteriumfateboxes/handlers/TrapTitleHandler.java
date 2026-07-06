package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class TrapTitleHandler {
    public static void sendTrapTitle(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.connection.send(new ClientboundSetTitlesAnimationPacket(10, 60, 20));
            Component title = Component.literal("You've unleashed a Trap, Oh no!")
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000)).withBold(true)); // Red
            serverPlayer.connection.send(new ClientboundSetTitleTextPacket(title));
        }
    }
}
