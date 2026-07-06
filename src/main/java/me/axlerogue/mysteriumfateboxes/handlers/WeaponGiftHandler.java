package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WeaponGiftHandler {
    private static final Random RANDOM = new Random();
    private static final Item[] WEAPONS = {
            Items.DIAMOND_SWORD, Items.NETHERITE_SWORD, Items.BOW, Items.CROSSBOW, Items.TRIDENT,
            Items.GOLDEN_SWORD, Items.IRON_SWORD
    };

    public static void giftRandomWeapon(Level level, BlockPos pos) {
        Item randomWeapon = WEAPONS[RANDOM.nextInt(WEAPONS.length)];
        ItemStack reward = new ItemStack(randomWeapon, 1);
        ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, reward);
        level.addFreshEntity(itemEntity);
    }
}
