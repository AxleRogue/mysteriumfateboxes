package me.axlerogue.mysteriumfateboxes.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class MysteriumFateBoxBlock extends HorizontalDirectionalBlock {
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
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter worldIn, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected net.minecraft.world.InteractionResult useWithoutItem(BlockState state, net.minecraft.world.level.Level level, BlockPos pos, net.minecraft.world.entity.player.Player player, net.minecraft.world.phys.BlockHitResult hitResult) {
        return net.minecraft.world.InteractionResult.PASS;
    }

    @Override
    protected net.minecraft.world.InteractionResult useItemOn(net.minecraft.world.item.ItemStack itemStack, BlockState state, net.minecraft.world.level.Level level, BlockPos pos, net.minecraft.world.entity.player.Player player, net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hitResult) {
        if (itemStack.is(me.axlerogue.mysteriumfateboxes.registry.ModItems.MYSTERIUM_FATE_BOX_KEY.get())) {
            if (!level.isClientSide()) {
                if (me.axlerogue.mysteriumfateboxes.handlers.CooldownHandler.isOnCooldown(player)) {
                    long remaining = me.axlerogue.mysteriumfateboxes.handlers.CooldownHandler.getRemainingCooldown(player);
                    me.axlerogue.mysteriumfateboxes.handlers.ColorableCoolDownMessageHandler.sendOnCooldownMessage(player, remaining);
                } else {
                    level.removeBlock(pos, false);
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    me.axlerogue.mysteriumfateboxes.handlers.CooldownHandler.setCooldown(player);
                    me.axlerogue.mysteriumfateboxes.handlers.ColorableCoolDownMessageHandler.sendCooldownStartedMessage(player, me.axlerogue.mysteriumfateboxes.handlers.CooldownHandler.COOLDOWN_SECONDS);
                    me.axlerogue.mysteriumfateboxes.handlers.PlayOpenFateBoxSoundHandler.handle(level, pos, player);
                }
            }
            return level.isClientSide() ? net.minecraft.world.InteractionResult.SUCCESS : net.minecraft.world.InteractionResult.CONSUME;
        }
        return net.minecraft.world.InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
