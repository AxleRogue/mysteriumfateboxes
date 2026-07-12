package me.axlerogue.mysteriumfateboxes.handlers.title.handlers;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class WoolaramaTitleHandler {
    public static void animateWoolaramaTitle(ServerPlayer player, Level level) {
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
