package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class GoodFateHandler {
    private static final Random RANDOM = new Random();

    public static void handleGoodFate(Level level, BlockPos pos, Player player, int roll, FateLevelTypeEnum FateLevel) {
        PlayFateSoundHandler.playSound(level, pos, true);
        ColorableFateMessageHandler.sendFateMessage(player, true, "You received Good Fate! (" + FateLevel.name() + " Fate)");
        
        if (roll <= 45) {
            int giftType = RANDOM.nextInt(5);
            switch (giftType) {
                case 0:
                    ItemGiftHandler.giftRandomItem(level, pos);
                    break;
                case 1:
                    WeaponGiftHandler.giftRandomWeapon(level, pos);
                    break;
                case 2:
                    ArmorSetGiftHandler.giftRandomArmorSet(level, pos);
                    break;
                case 3:
                    ToolGiftHandler.giftRandomTool(level, pos);
                    break;
                case 4:
                    FoodGiftHandler.giftRandomFood(level, pos);
                    break;
            }
        } else {
            // Default fallback if they miss the 45% roll to ensure they still get something
            ItemGiftHandler.giftRandomItem(level, pos);
        }
    }
}
