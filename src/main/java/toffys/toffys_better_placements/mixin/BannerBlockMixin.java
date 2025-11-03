package toffys.toffys_better_placements.mixin;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BannerBlock.class)
public class BannerBlockMixin {
    @Inject(
            method = {"getStateForPlacement"},
            at = {@At("TAIL")},
            cancellable = true)
    public void getStateForPlacement(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<BlockState> cir){
        Block block = (Block)(Object)this;
        if (blockPlaceContext.isSecondaryUseActive()) {
            cir.setReturnValue( (BlockState)block.defaultBlockState().setValue(BannerBlock.ROTATION, RotationSegment.convertToSegment(blockPlaceContext.getRotation())));
        }
    }
}
