package me.axlerogue.mysteriumfateboxes.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ZombieHordeHandler {
    private static final Random RANDOM = new Random();

    public static void spawnHorde(Level level, BlockPos pos) {
        int count = RANDOM.nextInt(4) + 4; // 4 to 7 zombies
        
        for (int i = 0; i < count; i++) {
            Zombie zombie = EntityTypes.ZOMBIE.create(level, EntitySpawnReason.SPAWNER);
            if (zombie != null) {
                double offsetX = (RANDOM.nextDouble() - 0.5) * 6;
                double offsetZ = (RANDOM.nextDouble() - 0.5) * 6;
                zombie.setPos(pos.getX() + 0.5 + offsetX, pos.getY(), pos.getZ() + 0.5 + offsetZ);
                
                if (RANDOM.nextInt(3) == 0) { // 33% chance to be the elite variant
                    zombie.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.NETHERITE_HELMET));
                    zombie.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.NETHERITE_CHESTPLATE));
                    zombie.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.NETHERITE_LEGGINGS));
                    zombie.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.NETHERITE_BOOTS));
                    zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_SWORD));
                }
                
                level.addFreshEntity(zombie);
            }
        }
    }
}
