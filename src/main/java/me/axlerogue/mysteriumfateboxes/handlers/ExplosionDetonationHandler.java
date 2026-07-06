package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ExplosionDetonationHandler {
    public static void detonate(Level level, BlockPos pos, Player player) {
        BlockPos playerPos = player.blockPosition(); // Track player's current position
        level.explode(null, playerPos.getX() + 0.5, playerPos.getY() + 0.5, playerPos.getZ() + 0.5, 3.0F, Level.ExplosionInteraction.TNT);
    }
}
