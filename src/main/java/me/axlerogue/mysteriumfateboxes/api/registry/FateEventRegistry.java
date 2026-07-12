package me.axlerogue.mysteriumfateboxes.api.registry;

import me.axlerogue.mysteriumfateboxes.api.handlers.IBadFateHandler;
import me.axlerogue.mysteriumfateboxes.api.handlers.IGoodFateHandler;
import me.axlerogue.mysteriumfateboxes.api.handlers.IFateHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry for custom fate events. Addons can use this to register their custom handlers.
 */
public class FateEventRegistry {
    private static final Map<String, IGoodFateHandler> GOOD_FATES = new HashMap<>();
    private static final Map<String, IBadFateHandler> BAD_FATES = new HashMap<>();

    /**
     * Register a new Good Fate event.
     * @param handler The good fate handler to register.
     */
    public static void registerGoodFate(IGoodFateHandler handler) {
        if (handler != null && handler.getId() != null) {
            GOOD_FATES.put(handler.getId(), handler);
        }
    }

    /**
     * Register a new Bad Fate event.
     * @param handler The bad fate handler to register.
     */
    public static void registerBadFate(IBadFateHandler handler) {
        if (handler != null && handler.getId() != null) {
            BAD_FATES.put(handler.getId(), handler);
        }
    }

    /**
     * Gets all registered Good Fates.
     * @return A list of IGoodFateHandler.
     */
    public static List<IGoodFateHandler> getGoodFates() {
        return new ArrayList<>(GOOD_FATES.values());
    }

    /**
     * Gets all registered Bad Fates.
     * @return A list of IBadFateHandler.
     */
    public static List<IBadFateHandler> getBadFates() {
        return new ArrayList<>(BAD_FATES.values());
    }
}
