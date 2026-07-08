package net.mars.notfullybroken_mt.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import org.jspecify.annotations.NonNull;


public class BrokenTool extends Item {
    public BrokenTool(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull Component getName(final ItemStack itemStack) {
        return super.getName(itemStack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyOne());
    }
}
