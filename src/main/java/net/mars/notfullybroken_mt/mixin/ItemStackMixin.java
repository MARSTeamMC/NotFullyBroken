package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.util.NotFullyBrokenHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow public abstract ItemStack copy();
	@Shadow public abstract int getDamage();
	@Shadow public abstract Item getItem();
	@Shadow public abstract int getMaxDamage();

	@Inject(method = "shouldBreak", at = @At("HEAD"), cancellable = true)
	private void disableBreaking(CallbackInfoReturnable<Boolean> cir) {
		if (NotFullyBrokenHelper.isInBrokenState(this.copy())) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "onDurabilityChange", at = @At("HEAD"))
	private void ItemBreakHandler(int damage, @Nullable ServerPlayerEntity player, Consumer<Item> breakCallback, CallbackInfo ci) {
		Item item = this.getItem();
		ItemStack damagedStack = this.copy();
		damage = MathHelper.clamp(damage, 0, this.getMaxDamage());
		damagedStack.setDamage(damage);
		if (NotFullyBrokenHelper.isInBrokenState(damagedStack) && this.getDamage()<damage) {
			breakCallback.accept(item);
		}
	}
}