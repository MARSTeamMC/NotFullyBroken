package net.mars.notfullybroken_mt.mixin;

import net.mars.notfullybroken_mt.item.ModItems;
import net.mars.notfullybroken_mt.util.ModTags;
import net.mars.notfullybroken_mt.util.NotFullyBrokenHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
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
    public abstract Item getItem();

    @Shadow
    public abstract ItemStack copyComponentsToNewStack(ItemConvertible item, int count);

    @Inject(method = "damage(ILnet/minecraft/server/world/ServerWorld;Lnet/minecraft/server/network/ServerPlayerEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxDamage()I", shift = At.Shift.AFTER))
    private void ItemBreakHandler(int amount, ServerWorld world, ServerPlayerEntity player, Consumer<Item> breakCallback, CallbackInfo ci) {
        if (player != null && NotFullyBrokenHelper.isInBrokenState(this.copy())) {
            ItemStack brokenStack = this.copyComponentsToNewStack(ModItems.BROKEN_TOOL, 1);
            List<ItemStack> newContents = List.of(this.copy());
            ContainerComponent created = ContainerComponent.fromStacks(newContents);
            brokenStack.set(DataComponentTypes.CONTAINER, created);
            brokenStack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(ModTags.Items.TOOLS_WITH_BROKEN_STATE.indexOf(this.getItem()) + 1));
            if (this.getItem().equals(Items.FISHING_ROD)) {
                player.giveItemStack(brokenStack);
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