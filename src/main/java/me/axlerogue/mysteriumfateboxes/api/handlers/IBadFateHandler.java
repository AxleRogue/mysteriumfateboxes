package me.axlerogue.mysteriumfateboxes.api.handlers;

/**
 * Interface representing a Bad Fate (punishment) outcome.
 */
public interface IBadFateHandler extends IFateHandler {
    /**
     * Gets the weight or chance threshold for this event to roll.
     * @return The integer weight.
     */
    int getWeight();
}