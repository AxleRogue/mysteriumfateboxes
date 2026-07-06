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
    private static final Item[] VALUABLE_ITEMS = {
            Items.DIAMOND, Items.EMERALD, Items.GOLD_INGOT, Items.IRON_INGOT, Items.ENCHANTED_GOLDEN_APPLE,
            Items.NETHERITE_INGOT, Items.TOTEM_OF_UNDYING, Items.GOLDEN_APPLE, Items.EXPERIENCE_BOTTLE, Items.ENDER_PEARL,
            Items.DIAMOND_BLOCK, Items.EMERALD_BLOCK, Items.GOLD_BLOCK, Items.IRON_BLOCK, Items.NETHERITE_SCRAP
    };

    public static void giftRandomItem(Level level, BlockPos pos, Player player) {
        GiftTitleHandler.sendGiftTitle(player);
        Item randomItem = VALUABLE_ITEMS[RANDOM.nextInt(VALUABLE_ITEMS.length)];
        int amount = RANDOM.nextInt(3) + 1; // 1 to 3 items
        
        ItemStack reward = new ItemStack(randomItem, amount);
        ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, reward);
        level.addFreshEntity(itemEntity);
    }
}
