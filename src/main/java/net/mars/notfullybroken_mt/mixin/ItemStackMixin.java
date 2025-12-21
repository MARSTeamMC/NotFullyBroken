package net.mars.notfullybroken_mt.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.mars.notfullybroken_mt.NotFullyBroken;
import net.mars.notfullybroken_mt.item.ModItems;
import net.mars.notfullybroken_mt.util.NotFullyBrokenHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.EnchantableComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract ItemStack copy();

    @Shadow
    public abstract int getDamage();

    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract ItemStack copyComponentsToNewStack(ItemConvertible item, int count);

    @Inject(method = "onDurabilityChange", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shouldBreak()Z", shift = At.Shift.AFTER))
    private void ItemBreakHandler(int damage, @Nullable ServerPlayerEntity player, Consumer<Item> breakCallback, CallbackInfo ci) {
        if (player != null && NotFullyBrokenHelper.isInBrokenState(this.copy())) {
            ItemStack brokenStack = this.copyComponentsToNewStack(ModItems.BROKEN_TOOL, 1);
            List<ItemStack> newContents = List.of(this.copy());
            ContainerComponent created = ContainerComponent.fromStacks(newContents);
            brokenStack.set(DataComponentTypes.CONTAINER, created);

            brokenStack.set(DataComponentTypes.ITEM_MODEL, Identifier.of(NotFullyBroken.MOD_ID, Registries.ITEM.getId(this.getItem()).getPath()+"_broken"));

            RegistryEntryLookup<Enchantment> enchantmentLookup = player.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
            RegistryEntry.Reference<Enchantment> mending = enchantmentLookup.getOrThrow(Enchantments.MENDING);
            Set<Object2IntMap.Entry<RegistryEntry<Enchantment>>> stackEnchantments = brokenStack.getEnchantments().getEnchantmentEntries();
            ItemStack stackWithoutMending = brokenStack.copy();
            stackWithoutMending.set(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> enchantment : stackEnchantments) {
                if (!enchantment.getKey().equals(mending)) {
                    stackWithoutMending.addEnchantment(enchantment.getKey(), enchantment.getIntValue());
                }
            }
            brokenStack.set(DataComponentTypes.STORED_ENCHANTMENTS, stackWithoutMending.getEnchantments());
            brokenStack.set(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
            brokenStack.addEnchantment(mending, 1);

            if (this.getItem().equals(Items.FISHING_ROD)) {
                player.giveOrDropStack(brokenStack);
            } else {
                if (ItemStack.areEqual(player.getMainHandStack(), this.copy())) {
                    player.setStackInHand(Hand.MAIN_HAND, brokenStack);
                } else if (ItemStack.areEqual(player.getOffHandStack(), this.copy())) {
                    player.setStackInHand(Hand.OFF_HAND, brokenStack);
                }
            }
        }
    }
}