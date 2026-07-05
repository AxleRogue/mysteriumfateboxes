package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ExplosionDetonationHandler {
    public static void detonate(Level level, BlockPos pos) {
        level.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 3.0F, Level.ExplosionInteraction.TNT);
    }
}
