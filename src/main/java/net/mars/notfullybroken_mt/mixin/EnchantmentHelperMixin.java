package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.item.ModItems;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
    public class EnchantmentHelperMixin {
    @Inject(method = "modifyDurabilityToRepairFromXp", at = @At("HEAD"), cancellable = true)
    private static void cancelItemRepair(ServerLevel world, ItemStack stack, int baseRepairWithExperience, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem().equals(ModItems.BROKEN_TOOL)) {
            cir.setReturnValue(0);
        }
    }
}
