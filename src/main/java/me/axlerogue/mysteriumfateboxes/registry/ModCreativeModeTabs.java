package me.axlerogue.mysteriumfateboxes.registry;

import me.axlerogue.mysteriumfateboxes.MysteriumFateboxes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MysteriumFateboxes.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MysteriumFateboxesTab = CREATIVE_MODE_TABS.register("mysteriumfateboxes_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.MYSTERIUM_FATE_BOX.get()))
            .title(Component.translatable("itemgroup.mysteriumfateboxes"))
            .displayItems((displayParameters, output) -> {
                output.accept(ModBlocks.MYSTERIUM_FATE_BOX.get());
                output.accept(ModItems.MYSTERIUM_FATE_BOX_KEY.get());
            })
            .build());

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
