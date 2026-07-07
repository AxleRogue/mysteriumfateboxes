package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BadFateHandler {
    private static final Random RANDOM = new Random();

    public static void handleBadFate(Level level, BlockPos pos, Player player, int roll, FateLevelTypeEnum fateLevel) {
        PlayFateSoundHandler.playSound(level, pos, false);
        ColorableFateMessageHandler.sendFateMessage(player, false, "You received Bad Fate! (" + fateLevel.name() + " Fate)");

        if (fateLevel == FateLevelTypeEnum.HIGH && RANDOM.nextInt(100) < 15) {
            WitherBossHandler.spawnWithers(level, player);
            return;
        }

        boolean isTrap = RANDOM.nextBoolean();

        if (isTrap) {
            switch (fateLevel) {
                case LOW:
                    ExplosionDetonationHandler.detonate(level, player);
                    break;
                case MID:
                    RainingFlamingArrowsHandler.rainArrows(level, player);
                    break;
                case HIGH:
                    LavaPoolHandler.createPool(level, player);
                    break;
            }
        } else {
            switch (fateLevel) {
                case LOW:
                    DrownedHordeHandler.spawnHorde(level, player);
                    break;
                case MID:
                    HuskHordeHandler.spawnHorde(level, player);
                    break;
                case HIGH:
                    ZombieHordeHandler.spawnHorde(level, player);
                    break;
            }
        }
    }
}
