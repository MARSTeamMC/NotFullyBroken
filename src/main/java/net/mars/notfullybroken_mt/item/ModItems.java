package net.mars.notfullybroken_mt.item;

import net.mars.notfullybroken_mt.NotFullyBroken;
import net.mars.notfullybroken_mt.item.custom.BrokenTool;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item BROKEN_TOOL = registerItem("broken_tool", settings -> new BrokenTool(settings.maxDamage(1)));

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(NotFullyBroken.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NotFullyBroken.MOD_ID, name)))));
    }

    public static void registerModItems() {
        NotFullyBroken.LOGGER.info("[{}] Registering Mod Items", NotFullyBroken.MOD_ID);
    }
}
