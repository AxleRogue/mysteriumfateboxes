package me.axlerogue.mysteriumfateboxes.handlers;

import me.axlerogue.mysteriumfateboxes.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public class PlayLuckSoundHandler {
    public static void playSound(Level level, BlockPos pos, boolean isGoodLuck) {
        if (isGoodLuck) {
            level.playSound(null, pos, ModSounds.GOOD_LUCK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        } else {
            level.playSound(null, pos, ModSounds.BAD_LUCK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}
