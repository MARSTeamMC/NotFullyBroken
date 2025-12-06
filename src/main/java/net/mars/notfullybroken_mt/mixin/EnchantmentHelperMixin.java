package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(EnchantmentHelper.class)
    public class EnchantmentHelperMixin {
    @Inject(method = "getRepairWithExperience", at = @At("HEAD"), cancellable = true)
    private static void cancelItemRepair(ServerWorld world, ItemStack stack, int baseRepairWithExperience, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem().equals(ModItems.BROKEN_TOOL)) {
            cir.setReturnValue(0);
        }
    }
}
