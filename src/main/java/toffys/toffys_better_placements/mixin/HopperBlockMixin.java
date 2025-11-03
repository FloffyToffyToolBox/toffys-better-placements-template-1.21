package toffys.toffys_better_placements.mixin;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(HopperBlock.class)
public class HopperBlockMixin {
    @Inject(
            method = {"getStateForPlacement"},
        at = {@At("TAIL")},
            cancellable = true)
    public void getStateForPlacement(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<BlockState> cir){
        Block block = (Block)(Object)this;
        if (blockPlaceContext.isSecondaryUseActive()) {
            Direction direction = blockPlaceContext.getClickedFace();
            cir.setReturnValue((BlockState)block.defaultBlockState().setValue(HopperBlock.FACING, direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction).setValue(HopperBlock.ENABLED, true));
        }
    }
}
