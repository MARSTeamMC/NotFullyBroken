package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.util.NotFullyBrokenHelper;
import net.minecraft.block.BlockState;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(AxeItem.class)
public class AxeItemMixin {
	@Inject(method = "shouldCancelStripAttempt", at = @At("HEAD"), cancellable = true)
	private static void cancelStripAttemptOnBroken(ItemUsageContext context, CallbackInfoReturnable<Boolean> cir) {
		if (NotFullyBrokenHelper.isInBrokenState(context.getStack())) cir.setReturnValue(true);
	}
}