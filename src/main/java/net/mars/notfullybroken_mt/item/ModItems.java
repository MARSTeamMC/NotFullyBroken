package net.mars.notfullybroken_mt.item;

import net.mars.notfullybroken_mt.NotFullyBroken;
import net.mars.notfullybroken_mt.item.custom.BrokenTool;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BROKEN_TOOL = registerItem("broken_tool", new BrokenTool(new Item.Settings().maxDamage(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NotFullyBroken.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NotFullyBroken.LOGGER.info("[{}] Registering Mod Items", NotFullyBroken.MOD_ID);
    }
}
