package me.axlerogue.mysteriumfateboxes.api.handlers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Interface for determining which handler gets chosen given a specific situation.
 */
public interface IChoiceHandler {
    /**
     * Executes the choice logic to pick an event.
     * @param level The level.
     * @param player The player.
     * @param isGoodFate Whether a Good or Bad fate was rolled.
     */
    void makeChoice(Level level, Player player, boolean isGoodFate);
}