package me.axlerogue.mysteriumfateboxes.handlers.util.handlers;

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

        if (fateType == FateTypeEnum.GOOD) {
            GoodFateHandler.handleGoodFate(level, pos, player, fateLevel);
        } else {
            BadFateHandler.handleBadFate(level, pos, player, fateLevel);
        }
    }
}
