package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.NotFullyBroken;
import net.mars.notfullybroken_mt.item.ModItems;
import net.mars.notfullybroken_mt.util.NotFullyBrokenHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
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

    @Shadow public abstract boolean shouldBreak();

    @Inject(method = "onDurabilityChange", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shouldBreak()Z", shift = At.Shift.AFTER))
    private void ItemBreakHandler(int damage, @Nullable ServerPlayerEntity player, Consumer<Item> breakCallback, CallbackInfo ci) {
        if (player != null && shouldBreak() && NotFullyBrokenHelper.isInBrokenState(this.copy())) {
            ItemStack brokenStack = this.copyComponentsToNewStack(ModItems.BROKEN_TOOL, 1);
            List<ItemStack> newContents = List.of(this.copy());
            ContainerComponent created = ContainerComponent.fromStacks(newContents);
            brokenStack.set(DataComponentTypes.CONTAINER, created);
            brokenStack.set(DataComponentTypes.ITEM_MODEL, Identifier.of(NotFullyBroken.MOD_ID, Registries.ITEM.getId(this.getItem()).getPath()+"_broken"));
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