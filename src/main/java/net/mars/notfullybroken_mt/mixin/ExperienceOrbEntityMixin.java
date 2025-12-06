package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {
    @Inject(method = "repairPlayerGears", at = @At("HEAD"))
    private void test(ServerPlayerEntity player, int amount, CallbackInfoReturnable<Integer> cir) {
        Optional<EnchantmentEffectContext> optional = EnchantmentHelper.chooseEquipmentWith(
                EnchantmentEffectComponentTypes.REPAIR_WITH_XP, player, ItemStack::isDamaged
        );
        if (optional.isPresent()) {
            ItemStack stack = optional.get().stack();
            if (stack.getItem().equals(ModItems.BROKEN_TOOL) && stack.getComponents().contains(DataComponentTypes.CONTAINER)) {
                ItemStack originalStack = stack.getComponents().get(DataComponentTypes.CONTAINER).copyFirstStack();
                if (ItemStack.areEqual(player.getMainHandStack(), stack)) {
                    player.setStackInHand(Hand.MAIN_HAND, originalStack);
                } else if (ItemStack.areEqual(player.getOffHandStack(), stack)) {
                    player.setStackInHand(Hand.OFF_HAND, originalStack);
                } else {
                    player.giveItemStack(originalStack);
                }
            }
        }
    }
}
