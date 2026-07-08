package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ExperienceOrb.class)
public class ExperienceOrbMixin {
    @Inject(method = "repairPlayerItems", at = @At("HEAD"))
    private void replaceBrokenItem(ServerPlayer player, int amount, CallbackInfoReturnable<Integer> cir) {
        Optional<EnchantedItemInUse> optional = EnchantmentHelper.getRandomItemWith(
                EnchantmentEffectComponents.REPAIR_WITH_XP, player, ItemStack::isDamaged
        );
        if (optional.isPresent()) {
            ItemStack stack = optional.get().itemStack();
            if (stack.getItem().equals(ModItems.BROKEN_TOOL) && stack.getComponents().has(DataComponents.CONTAINER)) {
                ItemStack originalStack = stack.getComponents().get(DataComponents.CONTAINER).copyOne();
                if (ItemStack.isSameItem(player.getMainHandItem(), stack)) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, originalStack);
                } else if (ItemStack.isSameItem(player.getOffhandItem(), stack)) {
                    player.setItemInHand(InteractionHand.OFF_HAND, originalStack);
                }
            }
        }
    }
}
