package me.axlerogue.mysteriumfateboxes.handlers.util.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ChoiceHandler {
    public static void executeFate(Level level, BlockPos pos, Player player) {
        RollChanceHandler.executeRoll(level, pos, player);
    }
}
