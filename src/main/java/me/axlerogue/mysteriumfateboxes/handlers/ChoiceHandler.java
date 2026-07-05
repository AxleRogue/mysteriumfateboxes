package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ChoiceHandler {
    public static void executeFate(Level level, BlockPos pos, Player player) {
        boolean isGoodLuck = LuckDecisionHandler.determineLuck();

        if (isGoodLuck) {
            GoodLuckHandler.handleGoodLuck(level, pos, player);
        } else {
            BadLuckHandler.handleBadLuck(level, pos, player);
        }
    }
}
