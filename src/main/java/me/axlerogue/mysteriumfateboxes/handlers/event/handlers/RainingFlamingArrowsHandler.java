package me.axlerogue.mysteriumfateboxes.handlers.event.handlers;

import java.util.Random;

import me.axlerogue.mysteriumfateboxes.handlers.title.handlers.TrapTitleHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.level.Level;

public class RainingFlamingArrowsHandler {
    private static final Random RANDOM = new Random();

    public static void rainArrows(Level level, Player player) {
        BlockPos playerPos = player.blockPosition(); // Track player's current position
        int arrowCount = RANDOM.nextInt(25) + 25; // Increased to 25-50 arrows for the "army" feel
        
        for (int i = 0; i < arrowCount; i++) {
            Arrow arrow = EntityTypes.ARROW.create(level, EntitySpawnReason.SPAWNER);
            if (arrow != null) {
                // Spread out significantly more (20x20 area)
                double offsetX = (RANDOM.nextDouble() - 0.5) * 20;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 20;
                
                // Spawn higher so they have time to spread
                arrow.setPos(playerPos.getX() + 0.5 + offsetX, playerPos.getY() + 25 + RANDOM.nextInt(10), playerPos.getZ() + 0.5 + offsetZ);
                arrow.igniteForSeconds(100);
                
                // Give them a spread velocity angling inwards towards the player slightly
                double velocityX = (playerPos.getX() + 0.5 - arrow.getX()) * 0.05 + (RANDOM.nextDouble() - 0.5) * 0.5;
                double velocityZ = (playerPos.getZ() + 0.5 - arrow.getZ()) * 0.05 + (RANDOM.nextDouble() - 0.5) * 0.5;
                double velocityY = -1.5 - RANDOM.nextDouble();
                
                arrow.setDeltaMovement(velocityX, velocityY, velocityZ);
                
                level.addFreshEntity(arrow);
            }
        }
        
        TrapTitleHandler.sendTrapTitle(player);
    }
}
