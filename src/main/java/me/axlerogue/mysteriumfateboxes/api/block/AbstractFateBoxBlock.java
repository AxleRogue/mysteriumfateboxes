package me.axlerogue.mysteriumfateboxes.api.block;

import com.mojang.serialization.MapCodec;
import me.axlerogue.mysteriumfateboxes.handlers.sound.handlers.PlayOpenFateBoxSoundHandler;
import me.axlerogue.mysteriumfateboxes.handlers.util.handlers.ColorableCoolDownMessageHandler;
import me.axlerogue.mysteriumfateboxes.handlers.util.handlers.CooldownHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Abstract class for creating a custom Fate Box.
 * Addon creators can extend this class to create their own variant of the Mysterium Fate Box.
 */
public abstract class AbstractFateBoxBlock extends HorizontalDirectionalBlock {
    public static final net.minecraft.world.level.block.state.properties.BooleanProperty OPENING = net.minecraft.world.level.block.state.properties.BooleanProperty.create("opening");

    public AbstractFateBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, net.minecraft.core.Direction.NORTH).setValue(OPENING, false));
    }

    /**
     * Define the VoxelShape for this block. Can be overridden for custom block sizes.
     */
    protected abstract VoxelShape getCustomShape();

    /**
     * Define the Item that is required to unlock this specific box.
     */
    protected abstract net.minecraft.world.item.Item getRequiredKey();

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter worldIn, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return getCustomShape();
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(OPENING, false);
    }

    @Override
    protected net.minecraft.world.InteractionResult useWithoutItem(BlockState state, net.minecraft.world.level.Level level, BlockPos pos, net.minecraft.world.entity.player.Player player, net.minecraft.world.phys.BlockHitResult hitResult) {
        return net.minecraft.world.InteractionResult.PASS;
    }

    @Override
    protected net.minecraft.world.InteractionResult useItemOn(net.minecraft.world.item.ItemStack itemStack, BlockState state, net.minecraft.world.level.Level level, BlockPos pos, net.minecraft.world.entity.player.Player player, net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hitResult) {
        if (state.getValue(OPENING)) {
            return net.minecraft.world.InteractionResult.PASS;
        }

        if (itemStack.is(getRequiredKey())) {
            if (!level.isClientSide()) {
                if (CooldownHandler.isOnCooldown(player)) {
                    long remaining = CooldownHandler.getRemainingCooldown(player);
                    ColorableCoolDownMessageHandler.sendOnCooldownMessage(player, remaining);
                } else {
                    level.setBlock(pos, state.setValue(OPENING, true), 3);
                    level.scheduleTick(pos, this, 2);
                    
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    CooldownHandler.setCooldown(player);
                    ColorableCoolDownMessageHandler.sendCooldownStartedMessage(player, CooldownHandler.COOLDOWN_SECONDS);
                    PlayOpenFateBoxSoundHandler.handle(level, pos, player);
                    
                    // Hook for addons to trigger custom logic when the box starts opening
                    onBoxOpened(level, pos, player);
                }
            }
            return level.isClientSide() ? net.minecraft.world.InteractionResult.SUCCESS : net.minecraft.world.InteractionResult.CONSUME;
        }
        return net.minecraft.world.InteractionResult.PASS;
    }

    /**
     * Override this to run specific logic exactly when the box begins its opening sequence.
     */
    protected void onBoxOpened(net.minecraft.world.level.Level level, BlockPos pos, net.minecraft.world.entity.player.Player player) {
    }

    @Override
    protected void tick(BlockState state, net.minecraft.server.level.ServerLevel level, BlockPos pos, net.minecraft.util.RandomSource random) {
        if (state.getValue(OPENING)) {
            net.minecraft.core.Direction currentFacing = state.getValue(FACING);
            net.minecraft.core.Direction nextFacing = currentFacing.getClockWise();
            level.setBlock(pos, state.setValue(FACING, nextFacing), 3);
            level.scheduleTick(pos, this, 2);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPENING);
    }
}
