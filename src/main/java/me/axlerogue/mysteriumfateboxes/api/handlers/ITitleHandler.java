package me.axlerogue.mysteriumfateboxes.api.handlers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

/**
 * Interface for custom animated titles to display on screen.
 */
public interface ITitleHandler {
    /**
     * Animates a title for a player.
     * @param player The ServerPlayer.
     * @param level The Level.
     */
    void animateTitle(ServerPlayer player, Level level);
}