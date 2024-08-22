package si.owo.mixin.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(BlockColors.class)
public class BlockColorsMixin {
    @Inject(at = @At("HEAD"), method = "getColor", cancellable = true)
    private void rainbowLeaves(BlockState state, BlockRenderView world, BlockPos pos, int tintIndex, CallbackInfoReturnable<Integer> cir) {
        if (pos == null) return;

        final int sc = 1024;
        final float hue = this.rainbowLeavesDist(pos.getX(), 32 * pos.getY(), pos.getX() + pos.getZ()) % sc / sc;
        cir.setReturnValue(Color.HSBtoRGB(hue, 0.7F, 1F));
    }

    @Unique
    private float rainbowLeavesDist(int x, int y, int z) {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }
}
