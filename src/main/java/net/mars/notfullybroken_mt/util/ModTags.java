package net.mars.notfullybroken_mt.util;

import net.mars.notfullybroken_mt.NotFullyBroken;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import java.util.List;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> HAS_BROKEN_STATE = createTag("has_broken_state");

        public static final List<String> TOOLS_WITH_BROKEN_STATE = List.of(
                "diamond_pickaxe",
                "stone_pickaxe",
                "golden_pickaxe",
                "netherite_pickaxe",
                "wooden_pickaxe",
                "iron_pickaxe",

                "diamond_axe",
                "stone_axe",
                "golden_axe",
                "netherite_axe",
                "wooden_axe",
                "iron_axe",

                "diamond_shovel",
                "stone_shovel",
                "golden_shovel",
                "netherite_shovel",
                "wooden_shovel",
                "iron_shovel",

                "diamond_hoe",
                "stone_hoe",
                "golden_hoe",
                "netherite_hoe",
                "wooden_hoe",
                "iron_hoe",

                "diamond_sword",
                "stone_sword",
                "golden_sword",
                "netherite_sword",
                "wooden_sword",
                "iron_sword",

                "mace",
                "trident",
                "bow",
                "crossbow",
                "fishing_rod",
                "flint_and_steel",
                "brush"
        );

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(NotFullyBroken.MOD_ID, name));
        }
    }
}
