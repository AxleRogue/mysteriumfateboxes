package me.axlerogue.mysteriumfateboxes.api.handlers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Interface for handlers that dictate the chance of events occurring.
 */
public interface IRollChanceHandler {
    /**
     * Calculates the fate outcome (Good or Bad) based on the player or environment.
     * @param level The level.
     * @param player The player rolling the fate.
     * @return True if good fate, false if bad fate.
     */
    boolean isGoodFate(Level level, Player player);
    
    /**
     * Rolls a specific percentage chance.
     * @param chance The percentage threshold to beat.
     * @return True if successful.
     */
    boolean rollChance(int chance);
}