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

public class WeaponGiftHandler {
    private static final Random RANDOM = new Random();
    private static final Item[] LOW_WEAPONS = { Items.IRON_SWORD, Items.BOW };
    private static final Item[] MID_WEAPONS = { Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.CROSSBOW };
    private static final Item[] HIGH_WEAPONS = { Items.NETHERITE_SWORD, Items.TRIDENT };

    public static void giftRandomWeapon(Level level, BlockPos pos, Player player, FateLevelTypeEnum fateLevel) {
        GiftTitleHandler.sendGiftTitle(player);
        Item[] weaponSet;
        switch (fateLevel) {
            case LOW: weaponSet = LOW_WEAPONS; break;
            case MID: weaponSet = MID_WEAPONS; break;
            case HIGH: weaponSet = HIGH_WEAPONS; break;
            default: weaponSet = LOW_WEAPONS; break;
        }
        Item randomWeapon = weaponSet[RANDOM.nextInt(weaponSet.length)];
        BlockPos playerPos = player.blockPosition();
        ItemStack reward = new ItemStack(randomWeapon, 1);
        ItemEntity itemEntity = new ItemEntity(level, playerPos.getX() + 0.5, playerPos.getY() + 0.5, playerPos.getZ() + 0.5, reward);
        level.addFreshEntity(itemEntity);
    }
}
