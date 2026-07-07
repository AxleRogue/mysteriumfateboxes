package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
// import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;

import java.util.Random;

public class TamedWolfHandler {
    private static final Random RANDOM = new Random();

    public static void spawnWolves(Level level, Player player) {
        BlockPos playerPos = player.blockPosition();
        
        int count = 16 + RANDOM.nextInt(17); // 16 to 32 wolves
        for (int i = 0; i < count; i++) {
            var wolf = EntityTypes.WOLF.create(level, EntitySpawnReason.SPAWNER);
            if (wolf != null) {
                double offsetX = (RANDOM.nextDouble() - 0.5) * 20;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 20;
                double offsetY = 15 + RANDOM.nextInt(10);
                
                wolf.setPos(playerPos.getX() + offsetX, playerPos.getY() + offsetY, playerPos.getZ() + offsetZ);
                
                wolf.tame(player);
                
                try {
                    java.lang.reflect.Method setCollarColor = wolf.getClass().getDeclaredMethod("setCollarColor", DyeColor.class);
                    setCollarColor.setAccessible(true);
                    setCollarColor.invoke(wolf, DyeColor.values()[RANDOM.nextInt(DyeColor.values().length)]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.registryAccess().lookup(Registries.WOLF_VARIANT).ifPresent(registry -> {
                        registry.getRandom(serverLevel.getRandom()).ifPresent(variant -> {
                            try {
                                java.lang.reflect.Method setVariant = wolf.getClass().getDeclaredMethod("setVariant", net.minecraft.core.Holder.class);
                                setVariant.setAccessible(true);
                                setVariant.invoke(wolf, variant);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    });
                }
                
                wolf.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 1, false, false));
                
                level.addFreshEntity(wolf);
            }
        }
        
        if (player instanceof ServerPlayer serverPlayer) {
            animateHappyPuppersTitle(serverPlayer, level);
        }
    }
    
    private static void animateHappyPuppersTitle(ServerPlayer player, Level level) {
        String text = "HAPPY PUPPERS!";
        // Green colors or alternating colors
        int[] colors = {0x00FF00, 0x32CD32, 0x90EE90, 0x008000};
        
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
                    net.minecraft.network.chat.MutableComponent component = Component.empty();
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
