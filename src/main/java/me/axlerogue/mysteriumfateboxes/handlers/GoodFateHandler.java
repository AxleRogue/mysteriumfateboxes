package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GoodFateHandler {
    private static final Random RANDOM = new Random();

    public static void handleGoodFate(Level level, BlockPos pos, Player player, FateLevelTypeEnum fateLevel) {
        PlayFateSoundHandler.playSound(level, pos, true);
        ColorableFateMessageHandler.sendFateMessage(player, true, "You received Good Fate! (" + fateLevel.name() + " Fate)");
        
        if (fateLevel == FateLevelTypeEnum.HIGH && RANDOM.nextInt(100) < 20) {
            ColoredSheepHandler.spawnSheep(level, player);
            return;
        }

        if (fateLevel == FateLevelTypeEnum.MID && RANDOM.nextInt(100) < 20) {
            TamedWolfHandler.spawnWolves(level, player);
            return;
        }

        int giftType = RANDOM.nextInt(5);
        switch (giftType) {
            case 0:
                ItemGiftHandler.giftRandomItem(level, pos, player, fateLevel);
                break;
            case 1:
                WeaponGiftHandler.giftRandomWeapon(level, pos, player, fateLevel);
                break;
            case 2:
                ArmorSetGiftHandler.giftRandomArmorSet(level, pos, player, fateLevel);
                break;
            case 3:
                ToolGiftHandler.giftRandomTool(level, pos, player, fateLevel);
                break;
            case 4:
                FoodGiftHandler.giftRandomFood(level, pos, player, fateLevel);
                break;
        }
    }
}
