package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class GoodLuckHandler {
    private static final Random RANDOM = new Random();

    public static void handleGoodLuck(Level level, BlockPos pos, Player player) {
        PlayLuckSoundHandler.playSound(level, pos, true);
        ColorableLuckMessageHandler.sendLuckMessage(player, true, "You received Good Fate!");
        
        int giftType = RANDOM.nextInt(5);
        switch (giftType) {
            case 0:
                ItemGiftHandler.giftRandomItem(level, pos);
                break;
            case 1:
                WeaponGiftHandler.giftRandomWeapon(level, pos);
                break;
            case 2:
                ArmorSetGiftHandler.giftRandomArmorSet(level, pos);
                break;
            case 3:
                ToolGiftHandler.giftRandomTool(level, pos);
                break;
            case 4:
                FoodGiftHandler.giftRandomFood(level, pos);
                break;
        }
    }
}
