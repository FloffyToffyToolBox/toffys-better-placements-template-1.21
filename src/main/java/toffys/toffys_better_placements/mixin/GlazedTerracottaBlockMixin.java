package toffys.toffys_better_placements.mixin;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlazedTerracottaBlock.class)
public class GlazedTerracottaBlockMixin {
    @Inject(
            method = {"getStateForPlacement"},
            at = {@At("TAIL")},
            cancellable = true)
    public void getStateForPlacement(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<BlockState> cir){
        Block block = (Block)(Object)this;
        if (blockPlaceContext.isSecondaryUseActive()) {
            cir.setReturnValue(block.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, blockPlaceContext.getHorizontalDirection()));
        }
    }
}
