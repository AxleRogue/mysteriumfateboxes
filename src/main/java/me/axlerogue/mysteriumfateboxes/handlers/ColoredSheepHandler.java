package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
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
            animateWoolaramaTitle(serverPlayer, level);
        }
    }
    
    private static void animateWoolaramaTitle(ServerPlayer player, Level level) {
        String text = "WOOLARAMA!";
        int[] colors = {0xFF0000, 0xFF7F00, 0xFFFF00, 0x00FF00, 0x0000FF, 0x4B0082, 0x9400D3};
        
        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 60, 20));
        
        new Thread(() -> {
            for (int step = 0; step < 20; step++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                int finalStep = step;
                level.getServer().execute(() -> {
                    MutableComponent component = Component.empty();
                    for (int i = 0; i < text.length(); i++) {
                        int colorIndex = (i + finalStep) % colors.length;
                        component.append(Component.literal(String.valueOf(text.charAt(i)))
                            .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(colors[colorIndex])).withBold(true)));
                    }
                    player.connection.send(new ClientboundSetTitleTextPacket(component));
                });
            }
        }).start();
    }
}
