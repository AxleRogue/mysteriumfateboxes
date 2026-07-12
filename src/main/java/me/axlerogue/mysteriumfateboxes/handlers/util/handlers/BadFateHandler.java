package me.axlerogue.mysteriumfateboxes.handlers.util.handlers;

import java.util.List;
import java.util.Random;

import me.axlerogue.mysteriumfateboxes.api.handlers.IBadFateHandler;
import me.axlerogue.mysteriumfateboxes.api.registry.FateEventRegistry;
import me.axlerogue.mysteriumfateboxes.Config;
import me.axlerogue.mysteriumfateboxes.handlers.event.handlers.*;
import me.axlerogue.mysteriumfateboxes.handlers.sound.handlers.PlayFateSoundHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BadFateHandler {
    private static final Random RANDOM = new Random();

    public static void handleBadFate(Level level, BlockPos pos, Player player, FateLevelTypeEnum fateLevel) {
        PlayFateSoundHandler.playSound(level, pos, false);
        ColorableFateMessageHandler.sendFateMessage(player, false, "You received Bad Fate! (" + fateLevel.name() + " Fate)");

        List<IBadFateHandler> customFates = FateEventRegistry.getBadFates();
        if (!customFates.isEmpty()) {
            int totalWeight = customFates.stream().mapToInt(IBadFateHandler::getWeight).sum();
            int vanillaWeight = 100; // Base weight for vanilla mod outcomes
            int roll = RANDOM.nextInt(totalWeight + vanillaWeight);
            
            if (roll < totalWeight) {
                int currentWeight = 0;
                for (IBadFateHandler handler : customFates) {
                    currentWeight += handler.getWeight();
                    if (roll < currentWeight) {
                        handler.execute(level, player);
                        return; // Custom fate executed, stop vanilla execution
                    }
                }
            }
        }

        if (Config.FATE_BOX_CONFIG.allowWitherBoss.get() && fateLevel == FateLevelTypeEnum.HIGH && RANDOM.nextInt(100) < 15) {
            WitherBossHandler.spawnWithers(level, player);
            return;
        }

        if (fateLevel == FateLevelTypeEnum.HIGH && RANDOM.nextInt(100) < 20) {
            AngryWolfHandler.spawnWolves(level, player);
            return;
        }

        boolean isTrap = RANDOM.nextBoolean();

        if (isTrap) {
            switch (fateLevel) {
                case LOW:
                    if (Config.FATE_BOX_CONFIG.allowExplosions.get()) {
                        ExplosionDetonationHandler.detonate(level, player);
                    } else {
                        DrownedHordeHandler.spawnHorde(level, player); // fallback
                    }
                    break;
                case MID:
                    RainingFlamingArrowsHandler.rainArrows(level, player);
                    break;
                case HIGH:
                    if (Config.FATE_BOX_CONFIG.allowLavaPools.get()) {
                        LavaPoolHandler.createPool(level, player);
                    } else {
                        ZombieHordeHandler.spawnHorde(level, player); // fallback
                    }
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
