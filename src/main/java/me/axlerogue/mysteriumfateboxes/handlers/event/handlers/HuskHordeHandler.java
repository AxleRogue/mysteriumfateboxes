package me.axlerogue.mysteriumfateboxes.handlers.event.handlers;

import java.util.Random;

import me.axlerogue.mysteriumfateboxes.handlers.title.handlers.HordeTitleHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HuskHordeHandler {
    private static final Random RANDOM = new Random();

    public static void spawnHorde(Level level, Player player) {
        BlockPos pos = player.blockPosition();
        int count = RANDOM.nextInt(4) + 4; // 4 to 7 husks
        
        for (int i = 0; i < count; i++) {
            Husk husk = EntityTypes.HUSK.create(level, EntitySpawnReason.SPAWNER);
            if (husk != null) {
                double offsetX = (RANDOM.nextDouble() - 0.5) * 6;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 6;
                husk.setPos(pos.getX() + 0.5 + offsetX, pos.getY(), pos.getZ() + 0.5 + offsetZ);
                
                if (RANDOM.nextInt(3) == 0) { // 33% chance to be the elite variant
                    husk.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.NETHERITE_HELMET));
                    husk.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.NETHERITE_CHESTPLATE));
                    husk.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.NETHERITE_LEGGINGS));
                    husk.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.NETHERITE_BOOTS));
                    husk.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_AXE));
                }
                
                level.addFreshEntity(husk);
            }
        }
        
        HordeTitleHandler.sendHordeTitle(player);
    }
}
