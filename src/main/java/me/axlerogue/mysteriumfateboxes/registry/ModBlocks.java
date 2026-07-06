package me.axlerogue.mysteriumfateboxes.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import me.axlerogue.mysteriumfateboxes.MysteriumFateboxes;
import me.axlerogue.mysteriumfateboxes.block.MysteriumFateBoxBlock;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MysteriumFateboxes.MODID);


    public static final DeferredBlock<MysteriumFateBoxBlock> MYSTERIUM_FATE_BOX = registerBlock("mysterium_fate_box", props -> new MysteriumFateBoxBlock(props.strength(-1.0F, 3600000.0F)));


    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, block);
        return block;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
