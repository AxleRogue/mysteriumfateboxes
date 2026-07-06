package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GoodFateHandler {
    private static final Random RANDOM = new Random();

    public static void handleGoodFate(Level level, BlockPos pos, Player player, int roll, FateLevelTypeEnum fateLevel) {
        PlayFateSoundHandler.playSound(level, pos, true);
        ColorableFateMessageHandler.sendFateMessage(player, true, "You received Good Fate! (" + fateLevel.name() + " Fate)");
        
        if (roll <= 25) {
            int giftType = RANDOM.nextInt(5);
            switch (giftType) {
                case 0:
                    ItemGiftHandler.giftRandomItem(level, pos, player);
                    break;
                case 1:
                    WeaponGiftHandler.giftRandomWeapon(level, pos, player);
                    break;
                case 2:
                    ArmorSetGiftHandler.giftRandomArmorSet(level, pos, player);
                    break;
                case 3:
                    ToolGiftHandler.giftRandomTool(level, pos, player);
                    break;
                case 4:
                    FoodGiftHandler.giftRandomFood(level, pos, player);
                    break;
            }
        } else if (roll <= 70) {
            ColoredSheepHandler.spawnSheep(level, player);
        } else {
            // Default fallback if they miss the 70% roll to ensure they still get something
            ItemGiftHandler.giftRandomItem(level, pos, player);
        }
    }
}
