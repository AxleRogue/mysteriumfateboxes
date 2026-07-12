package me.axlerogue.mysteriumfateboxes.handlers.event.handlers;

import me.axlerogue.mysteriumfateboxes.handlers.title.handlers.TrapTitleHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;

public class LavaPoolHandler {
    public static void createPool(Level level, Player player) {
        BlockPos playerPos = player.blockPosition(); // Use the player's exact position instead of the block's
        int radius = 3; // 3 block radius makes a nice 7x7 circle

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                // Check if the point is within the circle (x^2 + z^2 <= r^2)
                if (x * x + z * z <= radius * radius) {
                    BlockPos targetPos = playerPos.offset(x, -1, z); // One block down so they fall into it
                    
                    // Don't replace the exact block the player is standing on right away to give them a split second
                    if (x == 0 && z == 0) {
                        level.setBlock(targetPos, Blocks.OBSIDIAN.defaultBlockState(), 3);
                    } else {
                        level.setBlock(targetPos, Blocks.LAVA.defaultBlockState(), 3);
                        // Clear the blocks above the lava so it's an open pool
                        level.setBlock(targetPos.above(), Blocks.AIR.defaultBlockState(), 3);
                        level.setBlock(targetPos.above(2), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
        
        // Teleport the player exactly to the center of the trap (on top of the obsidian block)
        player.teleportTo(playerPos.getX() + 0.5, playerPos.getY(), playerPos.getZ() + 0.5);
        
        TrapTitleHandler.sendTrapTitle(player);
    }
}
