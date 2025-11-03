package toffys.toffys_better_placements.mixin;

import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrafterBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(CrafterBlock.class)
public class CrafterBlockMixin {
    @Shadow
    private static final EnumProperty<FrontAndTop> ORIENTATION = BlockStateProperties.ORIENTATION;;
    @Inject(
        method = {"getStateForPlacement"},
        at = {@At("TAIL")},
        cancellable = true)
    public void getStateForPlacement(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<BlockState> cir){
        Block block = (Block)(Object)this;
        if (blockPlaceContext.isSecondaryUseActive()) {

            Direction direction = blockPlaceContext.getNearestLookingDirection().getOpposite();
            Direction var10000;
            switch (direction) {
                case DOWN:
                    var10000 = blockPlaceContext.getHorizontalDirection().getOpposite();
                    break;
                case UP:
                    var10000 = blockPlaceContext.getHorizontalDirection();
                    break;
                case NORTH:
                case SOUTH:
                case WEST:
                case EAST:
                    var10000 = Direction.UP;
                    break;
                default:
                    throw new MatchException((String)null, (Throwable)null);
            }

            Direction direction2 = var10000;
            cir.setReturnValue(((BlockState)block.defaultBlockState().setValue(ORIENTATION, FrontAndTop.fromFrontAndTop(direction.getOpposite(), direction2))).setValue(CrafterBlock.TRIGGERED, blockPlaceContext.getLevel().hasNeighborSignal(blockPlaceContext.getClickedPos())));
        }
    }
}
