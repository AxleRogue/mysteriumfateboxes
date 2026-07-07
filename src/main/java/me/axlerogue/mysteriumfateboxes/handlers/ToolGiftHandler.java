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
    private static final Item[] LOW_TOOLS = {
            Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SHOVEL, Items.IRON_HOE
    };
    private static final Item[] MID_TOOLS = {
            Items.GOLDEN_PICKAXE, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE
    };
    private static final Item[] HIGH_TOOLS = {
            Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE
    };

    public static void giftRandomTool(Level level, BlockPos pos, Player player, FateLevelTypeEnum fateLevel) {
        GiftTitleHandler.sendGiftTitle(player);
        Item[] toolSet;
        switch (fateLevel) {
            case LOW: toolSet = LOW_TOOLS; break;
            case MID: toolSet = MID_TOOLS; break;
            case HIGH: toolSet = HIGH_TOOLS; break;
            default: toolSet = LOW_TOOLS; break;
        }
        Item randomTool = toolSet[RANDOM.nextInt(toolSet.length)];
        BlockPos playerPos = player.blockPosition();
        ItemStack reward = new ItemStack(randomTool, 1);
        ItemEntity itemEntity = new ItemEntity(level, playerPos.getX() + 0.5, playerPos.getY() + 0.5, playerPos.getZ() + 0.5, reward);
        level.addFreshEntity(itemEntity);
    }
}
