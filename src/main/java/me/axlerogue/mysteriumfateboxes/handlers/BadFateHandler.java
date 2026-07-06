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

        if (fateLevel == FateLevelTypeEnum.HIGH && RANDOM.nextInt(100) < 5) {
            WitherBossHandler.spawnWithers(level, player);
            return;
        }

        if (roll <= 50) {
            ExplosionDetonationHandler.detonate(level, player);
        } else if (roll <= 65) {
            RainingFlamingArrowsHandler.rainArrows(level, player);
        } else if (roll <= 75) {
            LavaPoolHandler.createPool(level, player);
        } else {
            int hordeType = RANDOM.nextInt(3);
            switch (hordeType) {
                case 0:
                    ZombieHordeHandler.spawnHorde(level, player);
                    break;
                case 1:
                    HuskHordeHandler.spawnHorde(level, player);
                    break;
                case 2:
                    DrownedHordeHandler.spawnHorde(level, player);
                    break;
            }
        }
    }
}
