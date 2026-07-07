package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ItemGiftHandler {
    private static final Random RANDOM = new Random();
    private static final Item[] LOW_ITEMS = {
            Items.IRON_INGOT, Items.IRON_BLOCK, Items.EXPERIENCE_BOTTLE, Items.ENDER_PEARL
    };
    private static final Item[] MID_ITEMS = {
            Items.GOLD_INGOT, Items.GOLD_BLOCK, Items.EMERALD, Items.EMERALD_BLOCK, Items.DIAMOND, Items.GOLDEN_APPLE
    };
    private static final Item[] HIGH_ITEMS = {
            Items.DIAMOND_BLOCK, Items.NETHERITE_INGOT, Items.NETHERITE_SCRAP, Items.ENCHANTED_GOLDEN_APPLE, Items.TOTEM_OF_UNDYING
    };

    public static void giftRandomItem(Level level, BlockPos pos, Player player, FateLevelTypeEnum fateLevel) {
        GiftTitleHandler.sendGiftTitle(player);
        Item[] itemSet;
        switch (fateLevel) {
            case LOW: itemSet = LOW_ITEMS; break;
            case MID: itemSet = MID_ITEMS; break;
            case HIGH: itemSet = HIGH_ITEMS; break;
            default: itemSet = LOW_ITEMS; break;
        }
        Item randomItem = itemSet[RANDOM.nextInt(itemSet.length)];
        int amount = RANDOM.nextInt(3) + 1; // 1 to 3 items
        
        BlockPos playerPos = player.blockPosition();
        ItemStack reward = new ItemStack(randomItem, amount);
        ItemEntity itemEntity = new ItemEntity(level, playerPos.getX() + 0.5, playerPos.getY() + 0.5, playerPos.getZ() + 0.5, reward);
        level.addFreshEntity(itemEntity);
    }
}
