package me.axlerogue.mysteriumfateboxes.api.handlers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Base interface for all Fate Handlers in MysteriumFateboxes.
 */
public interface IFateHandler {
    /**
     * Executes the specific fate event.
     *
     * @param level  The level where the event is occurring.
     * @param player The player who triggered the fate box.
     */
    void execute(Level level, Player player);
    
    /**
     * Gets the unique identifier for this fate handler.
     * @return A unique string ID (e.g. "mysteriumfateboxes:zombie_horde")
     */
    String getId();
}