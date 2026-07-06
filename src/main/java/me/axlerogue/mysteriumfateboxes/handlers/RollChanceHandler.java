package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class RollChanceHandler {
    private static final Random RANDOM = new Random();

    public static FateTypeEnum getFateType() {
        return RANDOM.nextBoolean() ? FateTypeEnum.GOOD : FateTypeEnum.BAD;
    }

    public static FateLevelTypeEnum getFateLevel() {
        int chance = RANDOM.nextInt(100);
        if (chance < 33) return FateLevelTypeEnum.LOW;
        if (chance < 66) return FateLevelTypeEnum.MID;
        return FateLevelTypeEnum.HIGH;
    }

    public static void executeRoll(Level level, BlockPos pos, Player player) {
        FateTypeEnum fateType = getFateType();
        FateLevelTypeEnum fateLevel = getFateLevel();

        int roll = RANDOM.nextInt(100) + 1; // 1 to 100

        // Incorporate Fate level into the roll for more depth
        if (fateLevel == FateLevelTypeEnum.LOW) {
            roll -= 10;
        } else if (fateLevel == FateLevelTypeEnum.HIGH) {
            roll += 10;
        }

        // Keep roll within bounds
        roll = Math.max(1, Math.min(100, roll));

        if (fateType == FateTypeEnum.GOOD) {
            GoodFateHandler.handleGoodFate(level, pos, player, roll, fateLevel);
        } else {
            BadFateHandler.handleBadFate(level, pos, player, roll, fateLevel);
        }
    }
}
