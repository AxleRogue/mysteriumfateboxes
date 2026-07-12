package me.axlerogue.mysteriumfateboxes.handlers.event.handlers;

import me.axlerogue.mysteriumfateboxes.handlers.title.handlers.WoolaramaTitleHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
// import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ColoredSheepHandler {
    private static final Random RANDOM = new Random();

    public static void spawnSheep(Level level, Player player) {
        BlockPos playerPos = player.blockPosition();
        
        int count = 16 + RANDOM.nextInt(17); // 16 to 32 sheep
        for (int i = 0; i < count; i++) {
            var sheep = EntityTypes.SHEEP.create(level, EntitySpawnReason.SPAWNER);
            if (sheep != null) {
                double offsetX = (RANDOM.nextDouble() - 0.5) * 20;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 20;
                double offsetY = 15 + RANDOM.nextInt(10);
                
                sheep.setPos(playerPos.getX() + offsetX, playerPos.getY() + offsetY, playerPos.getZ() + offsetZ);
                sheep.setColor(DyeColor.values()[RANDOM.nextInt(DyeColor.values().length)]);
                
                sheep.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 1, false, false));
                
                level.addFreshEntity(sheep);
            }
        }
        
        if (player instanceof ServerPlayer serverPlayer) {
            WoolaramaTitleHandler.animateWoolaramaTitle(serverPlayer, level);
        }
    }
}
