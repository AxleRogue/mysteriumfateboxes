package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ToolGiftHandler {
    private static final Random RANDOM = new Random();
    private static final Item[] TOOLS = {
            Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE,
            Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL, Items.DIAMOND_HOE, Items.NETHERITE_HOE,
            Items.IRON_PICKAXE, Items.IRON_AXE, Items.GOLDEN_PICKAXE
    };

    public static void giftRandomTool(Level level, BlockPos pos, Player player) {
        GiftTitleHandler.sendGiftTitle(player);
        Item randomTool = TOOLS[RANDOM.nextInt(TOOLS.length)];
        ItemStack reward = new ItemStack(randomTool, 1);
        ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, reward);
        level.addFreshEntity(itemEntity);
    }
}
