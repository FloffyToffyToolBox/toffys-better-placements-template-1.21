package toffys.toffys_better_placements.mixin;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.compress.harmony.pack200.NewAttributeBands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PistonBaseBlock.class)
public class PistonBaseBlockMixin {
    @Inject(
            method = {"getStateForPlacement"},
        at = {@At("TAIL")},
            cancellable = true)
    public void getStateForPlacement(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<BlockState> cir){
        Block block = (Block)(Object)this;
        if (blockPlaceContext.isSecondaryUseActive()) {
        cir.setReturnValue((BlockState)((BlockState)block.defaultBlockState().setValue(DirectionalBlock.FACING, blockPlaceContext.getNearestLookingDirection())).setValue(PistonBaseBlock.EXTENDED, false));
        }
    }
}
