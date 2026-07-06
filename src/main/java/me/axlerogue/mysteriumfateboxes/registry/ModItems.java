package me.axlerogue.mysteriumfateboxes.registry;

import me.axlerogue.mysteriumfateboxes.item.MysteriumFateBoxKeyItem;
import me.axlerogue.mysteriumfateboxes.MysteriumFateboxes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MysteriumFateboxes.MODID);

    public static final DeferredItem<MysteriumFateBoxKeyItem> MYSTERIUM_FATE_BOX_KEY = ITEMS.registerItem("mysterium_fate_box_key", MysteriumFateBoxKeyItem::new);

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
