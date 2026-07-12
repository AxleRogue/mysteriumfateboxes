package me.axlerogue.mysteriumfateboxes.api.item;

import net.minecraft.world.item.Item;

/**
 * Abstract class for a custom Fate Box Key.
 * Addon creators can extend this to make custom keys for their custom Fate Boxes.
 */
public abstract class AbstractFateBoxKeyItem extends Item {
    public AbstractFateBoxKeyItem(Properties properties) {
        super(properties);
    }
}
