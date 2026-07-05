package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class BadLuckHandler {
    private static final Random RANDOM = new Random();

    public static void handleBadLuck(Level level, BlockPos pos, Player player) {
        PlayLuckSoundHandler.playSound(level, pos, false);
        ColorableLuckMessageHandler.sendLuckMessage(player, false, "You received Bad Fate!");

        int punishmentType = RANDOM.nextInt(6);
        switch (punishmentType) {
            case 0:
                ExplosionDetonationHandler.detonate(level, pos);
                break;
            case 1:
                RainingFlamingArrowsHandler.rainArrows(level, pos);
                break;
            case 2:
                ZombieHordeHandler.spawnHorde(level, pos);
                break;
            case 3:
                HuskHordeHandler.spawnHorde(level, pos);
                break;
            case 4:
                DrownedHordeHandler.spawnHorde(level, pos);
                break;
            case 5:
                LavaPoolHandler.createPool(level, pos, player);
                break;
        }
    }
}
