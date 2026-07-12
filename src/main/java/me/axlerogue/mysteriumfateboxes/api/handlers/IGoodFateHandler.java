package me.axlerogue.mysteriumfateboxes.api.handlers;

/**
 * Interface representing a Good Fate outcome.
 */
public interface IGoodFateHandler extends IFateHandler {
    /**
     * Gets the weight or chance threshold for this event to roll.
     * @return The integer weight.
     */
    int getWeight();
}