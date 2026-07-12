package me.axlerogue.mysteriumfateboxes.handlers.title.handlers;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class PuppersTitleHandler {
    public static void animateHappyPuppersTitle(ServerPlayer player, Level level) {
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

    public static void animateAngryPuppersTitle(ServerPlayer player, Level level) {
        String text = "ANGRY PUPPERS!";
        // Red colors or alternating colors
        int[] colors = {0xFF0000, 0xDC143C, 0x8B0000, 0xB22222};
        
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
