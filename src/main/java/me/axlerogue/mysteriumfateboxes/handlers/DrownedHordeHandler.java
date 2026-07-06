package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DrownedHordeHandler {
    private static final Random RANDOM = new Random();

    public static void spawnHorde(Level level, Player player) {
        BlockPos pos = player.blockPosition();
        int count = RANDOM.nextInt(4) + 4; // 4 to 7 drowned
        
        for (int i = 0; i < count; i++) {
            Drowned drowned = EntityTypes.DROWNED.create(level, EntitySpawnReason.SPAWNER);
            if (drowned != null) {
                double offsetX = (RANDOM.nextDouble() - 0.5) * 6;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 6;
                drowned.setPos(pos.getX() + 0.5 + offsetX, pos.getY(), pos.getZ() + 0.5 + offsetZ);
                
                if (RANDOM.nextInt(3) == 0) { // 33% chance to be the elite variant
                    drowned.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.NETHERITE_HELMET));
                    drowned.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.NETHERITE_CHESTPLATE));
                    drowned.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.NETHERITE_LEGGINGS));
                    drowned.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.NETHERITE_BOOTS));
                    drowned.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.TRIDENT));
                }
                
                level.addFreshEntity(drowned);
            }
        }
    }
}
