package net.mars.notfullybroken_mt.util;

import net.mars.notfullybroken_mt.NotFullyBroken;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModTags {
    public static class ItemTags {
        public static final TagKey<Item> HAS_BROKEN_STATE = createTag("has_broken_state");

        public static final List<@NotNull Item> TOOLS_WITH_BROKEN_STATE = List.of(
                Items.DIAMOND_PICKAXE,
                Items.DIAMOND_AXE,
                Items.DIAMOND_SHOVEL,
                Items.DIAMOND_HOE,
                Items.DIAMOND_SWORD,
                Items.DIAMOND_SPEAR,

                Items.STONE_PICKAXE,
                Items.STONE_AXE,
                Items.STONE_SHOVEL,
                Items.STONE_HOE,
                Items.STONE_SWORD,
                Items.STONE_SPEAR,

                Items.GOLDEN_PICKAXE,
                Items.GOLDEN_AXE,
                Items.GOLDEN_SHOVEL,
                Items.GOLDEN_HOE,
                Items.GOLDEN_SWORD,
                Items.GOLDEN_SPEAR,

                Items.NETHERITE_PICKAXE,
                Items.NETHERITE_AXE,
                Items.NETHERITE_SHOVEL,
                Items.NETHERITE_HOE,
                Items.NETHERITE_SWORD,
                Items.NETHERITE_SPEAR,

                Items.WOODEN_PICKAXE,
                Items.WOODEN_AXE,
                Items.WOODEN_SHOVEL,
                Items.WOODEN_HOE,
                Items.WOODEN_SWORD,
                Items.WOODEN_SPEAR,

                Items.IRON_PICKAXE,
                Items.IRON_AXE,
                Items.IRON_SHOVEL,
                Items.IRON_HOE,
                Items.IRON_SWORD,
                Items.IRON_SPEAR,

                Items.COPPER_PICKAXE,
                Items.COPPER_AXE,
                Items.COPPER_SHOVEL,
                Items.COPPER_HOE,
                Items.COPPER_SWORD,
                Items.COPPER_SPEAR,

                Items.MACE,
                Items.TRIDENT,
                Items.BOW,
                Items.CROSSBOW,
                Items.FISHING_ROD,
                Items.FLINT_AND_STEEL,
                Items.BRUSH,
                Items.SHEARS
        );

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NotFullyBroken.MOD_ID, name));
        }
    }
}
