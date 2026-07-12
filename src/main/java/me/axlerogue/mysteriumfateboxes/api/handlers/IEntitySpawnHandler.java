package me.axlerogue.mysteriumfateboxes.api.handlers;

/**
 * Interface for spawning entities (Good or Bad).
 */
public interface IEntitySpawnHandler extends IFateHandler {
    /**
     * Whether these entities are hostile (Bad Fate) or passive/helpful (Good Fate).
     * @return true if hostile
     */
    boolean isHostile();
}