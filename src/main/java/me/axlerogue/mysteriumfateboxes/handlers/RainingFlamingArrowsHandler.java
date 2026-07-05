package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.level.Level;

import java.util.Random;

public class RainingFlamingArrowsHandler {
    private static final Random RANDOM = new Random();

    public static void rainArrows(Level level, BlockPos pos) {
        int arrowCount = RANDOM.nextInt(15) + 15; // 15 to 30 arrows
        
        for (int i = 0; i < arrowCount; i++) {
            Arrow arrow = EntityTypes.ARROW.create(level, EntitySpawnReason.SPAWNER);
            if (arrow != null) {
                // Spawn randomly within a 10x10 area above the player
                double offsetX = (RANDOM.nextDouble() - 0.5) * 10;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 10;
                
                arrow.setPos(pos.getX() + 0.5 + offsetX, pos.getY() + 15 + RANDOM.nextInt(5), pos.getZ() + 0.5 + offsetZ);
                arrow.igniteForSeconds(100);
                arrow.setDeltaMovement(0, -1.5, 0); // Force them downward
                
                level.addFreshEntity(arrow);
            }
        }
    }
}
