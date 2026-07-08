package net.mars.notfullybroken_mt.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class NotFullyBrokenHelper {
    public static boolean isInBrokenState(ItemStack stack) {
        return stack.is(ModTags.ItemTags.HAS_BROKEN_STATE) && stack.getDamageValue()>=stack.getMaxDamage() && hasMending(stack);
    }

    public static boolean hasMending(ItemStack stack) {
        ResourceKey<Enchantment> mending = Enchantments.MENDING;
        for (Object2IntMap.Entry<Holder<Enchantment>> enchantment : stack.getEnchantments().entrySet()) {
            if (enchantment.getKey().is(mending)) {
                return stack.getEnchantments().getLevel(enchantment.getKey())>0;
            }
        }
        return false;
    }
}
