package net.mars.notfullybroken_mt.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;

public class NotFullyBrokenHelper {
    public static boolean isInBrokenState(ItemStack stack) {
        return stack.isIn(ModTags.Items.HAS_BROKEN_STATE) && stack.getDamage() >= stack.getMaxDamage() && hasMending(stack);
    }

    public static boolean hasMending(ItemStack stack) {
        RegistryKey<Enchantment> enchantment = Enchantments.MENDING;
        for (RegistryEntry<Enchantment> enchantments : stack.getEnchantments().getEnchantments()) {
            if (enchantments.toString().contains(enchantment.getValue().toString())) {
                return stack.getEnchantments().getLevel(enchantments) > 0;
            }
        }
        return false;
    }
}
