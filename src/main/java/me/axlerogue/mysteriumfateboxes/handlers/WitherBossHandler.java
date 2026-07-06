package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.Level;

import java.util.Random;

public class WitherBossHandler {
    private static final Random RANDOM = new Random();

    public static void spawnWithers(Level level, BlockPos pos) {
        for (int i = 0; i < 4; i++) {
            WitherBoss wither = EntityTypes.WITHER.create(level, EntitySpawnReason.SPAWNER);
            if (wither != null) {
                double offsetX = (RANDOM.nextDouble() - 0.5) * 6;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 6;
                wither.setPos(pos.getX() + 0.5 + offsetX, pos.getY(), pos.getZ() + 0.5 + offsetZ);
                level.addFreshEntity(wither);
            }
        }
    }
}
