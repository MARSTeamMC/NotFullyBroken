package net.mars.notfullybroken_mt.item;

import net.mars.notfullybroken_mt.NotFullyBroken;
import net.mars.notfullybroken_mt.item.custom.BrokenTool;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static final Item BROKEN_TOOL = registerItem("broken_tool", properties -> new BrokenTool(properties.durability(1)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NotFullyBroken.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NotFullyBroken.MOD_ID, name)))));
    }

    public static void registerModItems() {
        NotFullyBroken.LOGGER.info("[{}] Registering Mod Items", NotFullyBroken.MOD_ID);
    }
}
