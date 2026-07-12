package me.axlerogue.mysteriumfateboxes.handlers.event.handlers;

import java.util.Random;

import me.axlerogue.mysteriumfateboxes.handlers.title.handlers.GiftTitleHandler;
import me.axlerogue.mysteriumfateboxes.handlers.util.handlers.FateLevelTypeEnum;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ArmorSetGiftHandler {
    private static final Random RANDOM = new Random();
    private static final Item[][] LOW_ARMOR = { {Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS} };
    private static final Item[][] MID_ARMOR = { {Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS}, {Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS} };
    private static final Item[][] HIGH_ARMOR = { {Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS} };

    public static void giftRandomArmorSet(Level level, BlockPos pos, Player player, FateLevelTypeEnum fateLevel) {
        GiftTitleHandler.sendGiftTitle(player);
        Item[][] armorSets;
        switch (fateLevel) {
            case LOW: armorSets = LOW_ARMOR; break;
            case MID: armorSets = MID_ARMOR; break;
            case HIGH: armorSets = HIGH_ARMOR; break;
            default: armorSets = LOW_ARMOR; break;
        }
        Item[] randomSet = armorSets[RANDOM.nextInt(armorSets.length)];
        BlockPos playerPos = player.blockPosition();
        for (Item armorPiece : randomSet) {
            ItemStack reward = new ItemStack(armorPiece, 1);
            ItemEntity itemEntity = new ItemEntity(level, playerPos.getX() + 0.5, playerPos.getY() + 0.5, playerPos.getZ() + 0.5, reward);
            level.addFreshEntity(itemEntity);
        }
    }
}
