package me.axlerogue.mysteriumfateboxes.block;

import com.mojang.serialization.MapCodec;
import me.axlerogue.mysteriumfateboxes.api.block.AbstractFateBoxBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class MysteriumFateBoxBlock extends AbstractFateBoxBlock {
    public static final MapCodec<MysteriumFateBoxBlock> CODEC = simpleCodec(MysteriumFateBoxBlock::new);
    public static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 8, 12);

    public MysteriumFateBoxBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getCustomShape() {
        return SHAPE;
    }

    @Override
    protected net.minecraft.world.item.Item getRequiredKey() {
        return me.axlerogue.mysteriumfateboxes.registry.ModItems.MYSTERIUM_FATE_BOX_KEY.get();
    }
}
