package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.util.ModTags;
import net.mars.notfullybroken_mt.util.NotFullyBrokenHelper;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
	@Inject(at = @At("HEAD"), method = "getMiningSpeed", cancellable = true)
	private void setMiningSpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
		if (NotFullyBrokenHelper.isInBrokenState(stack)) {
			cir.setReturnValue(1.0F);
		}
	}

	@Inject(at = @At("HEAD"), method = "isCorrectForDrops", cancellable = true)
	private void setAsIncorrectForDrops(ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> cir) {
		if (NotFullyBrokenHelper.isInBrokenState(stack)) {
			cir.setReturnValue(false);
		}
	}
}