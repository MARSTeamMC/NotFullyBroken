package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.util.ModTags;
import net.mars.notfullybroken_mt.util.NotFullyBrokenHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow public abstract ItemStack copy();

	@Shadow public abstract boolean isIn(TagKey<Item> tag);

	@Inject(method = "shouldBreak", at = @At("HEAD"), cancellable = true)
	private void disableBreaking(CallbackInfoReturnable<Boolean> cir) {
		if (NotFullyBrokenHelper.isInBrokenState(this.copy())) {
			cir.setReturnValue(false);
		}
	}
}