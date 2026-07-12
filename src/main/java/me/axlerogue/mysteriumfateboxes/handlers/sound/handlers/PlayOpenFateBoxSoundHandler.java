package me.axlerogue.mysteriumfateboxes.handlers.sound.handlers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import me.axlerogue.mysteriumfateboxes.Config;
import me.axlerogue.mysteriumfateboxes.handlers.util.handlers.ChoiceHandler;
import me.axlerogue.mysteriumfateboxes.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PlayOpenFateBoxSoundHandler {
    private static final int SOUND_DURATION_SECONDS = 15; // Timer check for when the open sound is finished

    public static void handle(Level level, BlockPos pos, Player player) {
        boolean isDevMode = Config.FATE_BOX_CONFIG.devMode.get();
        int duration = isDevMode ? 0 : SOUND_DURATION_SECONDS;

        // Play the open sound
        if (isDevMode) {
            level.playSound(null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
        } else {
            level.playSound(null, pos, ModSounds.FATE_BOX_OPEN.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }

        if (!level.isClientSide() && level.getServer() != null) {
            // Wait for the sound to finish, then play fireworks and do the rest of the actions
            CompletableFuture.runAsync(() -> {}, CompletableFuture.delayedExecutor(duration, TimeUnit.SECONDS))
                    .thenRunAsync(() -> {
                        net.minecraft.world.level.block.state.BlockState currentState = level.getBlockState(pos);
                        if (currentState.getBlock() instanceof me.axlerogue.mysteriumfateboxes.block.MysteriumFateBoxBlock) {
                            level.removeBlock(pos, false);
                            PlayFireWorksSoundHandler.handle(level, pos);
                            ChoiceHandler.executeFate(level, pos, player);
                        }
                    }, level.getServer());
        }
    }
}
