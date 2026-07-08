package net.mars.notfullybroken_mt.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.mars.notfullybroken_mt.NotFullyBroken;
import net.mars.notfullybroken_mt.item.ModItems;
import net.mars.notfullybroken_mt.util.NotFullyBrokenHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract ItemStack copy();

    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract ItemStack transmuteCopy(ItemLike newItem, int newCount);

    @Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isBroken()Z", shift = At.Shift.AFTER))
    private void ItemBreakHandler(int newDamage, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci) {
        if (player != null && NotFullyBrokenHelper.isInBrokenState(this.copy())) {
            ItemStack brokenStack = this.transmuteCopy(ModItems.BROKEN_TOOL, 1);
            List<ItemStack> newContents = List.of(this.copy());
            ItemContainerContents created = ItemContainerContents.fromItems(newContents);
            brokenStack.set(DataComponents.CONTAINER, created);

            brokenStack.set(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath(NotFullyBroken.MOD_ID, BuiltInRegistries.ITEM.getKey(this.getItem()).getPath()+"_broken"));

            Holder<Enchantment> mending = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.MENDING);;
            Set<Object2IntMap.Entry<Holder<Enchantment>>> stackEnchantments = brokenStack.getEnchantments().entrySet();
            ItemStack stackWithoutMending = brokenStack.copy();
            stackWithoutMending.set(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
            for (Object2IntMap.Entry<Holder<Enchantment>> enchantment : stackEnchantments) {
                if (!enchantment.getKey().equals(mending)) {
                    stackWithoutMending.enchant(enchantment.getKey(), enchantment.getIntValue());
                }
            }

            brokenStack.set(DataComponents.STORED_ENCHANTMENTS, stackWithoutMending.getEnchantments());
            brokenStack.set(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
            brokenStack.enchant(mending, 1);
            EnchantmentHelper.updateEnchantments(brokenStack, enchantments -> enchantments.upgrade(mending, 1));

            if (this.getItem().equals(Items.FISHING_ROD)) {
                player.addItem(brokenStack);
            } else {
                if (ItemStack.isSameItem(player.getMainHandItem(), this.copy())) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, brokenStack);
                } else if (ItemStack.isSameItem(player.getOffhandItem(), this.copy())) {
                    player.setItemInHand(InteractionHand.OFF_HAND, brokenStack);
                }
            }
        }
    }
}