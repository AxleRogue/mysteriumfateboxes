package me.axlerogue.mysteriumfateboxes.handlers.sound.handlers;

import me.axlerogue.mysteriumfateboxes.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public class PlayFateSoundHandler {
    public static void playSound(Level level, BlockPos pos, boolean isGoodFate) {
        if (isGoodFate) {
            level.playSound(null, pos, ModSounds.GOOD_FATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        } else {
            level.playSound(null, pos, ModSounds.BAD_FATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}
