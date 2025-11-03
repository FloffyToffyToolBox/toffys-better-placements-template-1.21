package toffys.toffys_better_placements.mixin;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(TrapDoorBlock.class)
public class TrapDoorBlockMixin {
    @Inject(
            method = {"getStateForPlacement"},
        at = {@At("RETURN")},
            cancellable = true)
    public void getStateForPlacement(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<BlockState> cir){
        Block block = (Block)(Object)this;
        BlockState blockState = cir.getReturnValue();
        if (blockPlaceContext.isSecondaryUseActive()) {
            blockState=blockState.setValue(HorizontalDirectionalBlock.FACING,blockPlaceContext.getHorizontalDirection());
            cir.setReturnValue(blockState);
        }
    }
}
