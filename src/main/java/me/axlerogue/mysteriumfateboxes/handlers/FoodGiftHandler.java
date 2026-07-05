package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class FoodGiftHandler {
    private static final Random RANDOM = new Random();
    private static final Item[] FOOD_ITEMS = {
            Items.COOKED_BEEF, Items.COOKED_PORKCHOP, Items.GOLDEN_CARROT, 
            Items.BREAD, Items.BAKED_POTATO, Items.PUMPKIN_PIE, 
            Items.COOKED_CHICKEN, Items.COOKED_MUTTON, Items.COOKED_SALMON
    };

    public static void giftRandomFood(Level level, BlockPos pos) {
        Item randomFood = FOOD_ITEMS[RANDOM.nextInt(FOOD_ITEMS.length)];
        
        // Multiples of 2, between 2 and 16 items
        int amount = (RANDOM.nextInt(8) + 1) * 2; 
        
        ItemStack reward = new ItemStack(randomFood, amount);
        ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, reward);
        level.addFreshEntity(itemEntity);
    }
}
