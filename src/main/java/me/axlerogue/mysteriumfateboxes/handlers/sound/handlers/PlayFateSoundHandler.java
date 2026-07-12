package me.axlerogue.mysteriumfateboxes.handlers.sound.handlers;

import me.axlerogue.mysteriumfateboxes.Config;
import me.axlerogue.mysteriumfateboxes.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public class PlayFateSoundHandler {
    public static void playSound(Level level, BlockPos pos, boolean isGoodFate) {
        boolean isDevMode = Config.FATE_BOX_CONFIG.devMode.get();
        if (isGoodFate) {
            if (isDevMode) {
                level.playSound(null, pos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
            } else {
                level.playSound(null, pos, ModSounds.GOOD_FATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        } else {
            if (isDevMode) {
                level.playSound(null, pos, SoundEvents.ENDER_DRAGON_GROWL, SoundSource.BLOCKS, 1.0F, 1.0F);
            } else {
                level.playSound(null, pos, ModSounds.BAD_FATE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }
}
