package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class BadFateHandler {
    private static final Random RANDOM = new Random();

    public static void handleBadFate(Level level, BlockPos pos, Player player, int roll, FateLevelTypeEnum FateLevel) {
        PlayFateSoundHandler.playSound(level, pos, false);
        ColorableFateMessageHandler.sendFateMessage(player, false, "You received Bad Fate! (" + FateLevel.name() + " Fate)");

        if (roll <= 50) {
            ExplosionDetonationHandler.detonate(level, pos, player);
        } else if (roll <= 65) {
            RainingFlamingArrowsHandler.rainArrows(level, pos, player);
        } else if (roll <= 75) {
            LavaPoolHandler.createPool(level, pos, player);
        } else {
            int hordeType = RANDOM.nextInt(3);
            switch (hordeType) {
                case 0:
                    ZombieHordeHandler.spawnHorde(level, pos);
                    break;
                case 1:
                    HuskHordeHandler.spawnHorde(level, pos);
                    break;
                case 2:
                    DrownedHordeHandler.spawnHorde(level, pos);
                    break;
            }
        }
    }
}
