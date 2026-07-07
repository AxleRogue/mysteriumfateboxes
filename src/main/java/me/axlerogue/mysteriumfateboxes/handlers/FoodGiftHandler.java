package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class FoodGiftHandler {
    private static final Random RANDOM = new Random();
    private static final Item[] LOW_FOOD = { Items.BREAD, Items.BAKED_POTATO };
    private static final Item[] MID_FOOD = { Items.COOKED_BEEF, Items.COOKED_PORKCHOP, Items.COOKED_CHICKEN, Items.COOKED_MUTTON, Items.COOKED_SALMON, Items.PUMPKIN_PIE };
    private static final Item[] HIGH_FOOD = { Items.GOLDEN_CARROT, Items.GOLDEN_APPLE };

    public static void giftRandomFood(Level level, BlockPos pos, Player player, FateLevelTypeEnum fateLevel) {
        GiftTitleHandler.sendGiftTitle(player);
        Item[] foodSet;
        switch (fateLevel) {
            case LOW: foodSet = LOW_FOOD; break;
            case MID: foodSet = MID_FOOD; break;
            case HIGH: foodSet = HIGH_FOOD; break;
            default: foodSet = LOW_FOOD; break;
        }
        Item randomFood = foodSet[RANDOM.nextInt(foodSet.length)];
        
        // Multiples of 2, between 2 and 16 items
        int amount = (RANDOM.nextInt(8) + 1) * 2; 
        
        BlockPos playerPos = player.blockPosition();
        ItemStack reward = new ItemStack(randomFood, amount);
        ItemEntity itemEntity = new ItemEntity(level, playerPos.getX() + 0.5, playerPos.getY() + 0.5, playerPos.getZ() + 0.5, reward);
        level.addFreshEntity(itemEntity);
    }
}
