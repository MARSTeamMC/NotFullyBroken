package net.mars.notfullybroken_mt.util;

import net.mars.notfullybroken_mt.NotFullyBroken;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> HAS_BROKEN_STATE = createTag("has_broken_state");

        public static final List<@NotNull Item> TOOLS_WITH_BROKEN_STATE = List.of(
                net.minecraft.item.Items.DIAMOND_PICKAXE,
                net.minecraft.item.Items.DIAMOND_AXE,
                net.minecraft.item.Items.DIAMOND_SHOVEL,
                net.minecraft.item.Items.DIAMOND_HOE,
                net.minecraft.item.Items.DIAMOND_SWORD,
                net.minecraft.item.Items.DIAMOND_SPEAR,

                net.minecraft.item.Items.STONE_PICKAXE,
                net.minecraft.item.Items.STONE_AXE,
                net.minecraft.item.Items.STONE_SHOVEL,
                net.minecraft.item.Items.STONE_HOE,
                net.minecraft.item.Items.STONE_SWORD,
                net.minecraft.item.Items.STONE_SPEAR,

                net.minecraft.item.Items.GOLDEN_PICKAXE,
                net.minecraft.item.Items.GOLDEN_AXE,
                net.minecraft.item.Items.GOLDEN_SHOVEL,
                net.minecraft.item.Items.GOLDEN_HOE,
                net.minecraft.item.Items.GOLDEN_SWORD,
                net.minecraft.item.Items.GOLDEN_SPEAR,

                net.minecraft.item.Items.NETHERITE_PICKAXE,
                net.minecraft.item.Items.NETHERITE_AXE,
                net.minecraft.item.Items.NETHERITE_SHOVEL,
                net.minecraft.item.Items.NETHERITE_HOE,
                net.minecraft.item.Items.NETHERITE_SWORD,
                net.minecraft.item.Items.NETHERITE_SPEAR,

                net.minecraft.item.Items.WOODEN_PICKAXE,
                net.minecraft.item.Items.WOODEN_AXE,
                net.minecraft.item.Items.WOODEN_SHOVEL,
                net.minecraft.item.Items.WOODEN_HOE,
                net.minecraft.item.Items.WOODEN_SWORD,
                net.minecraft.item.Items.WOODEN_SPEAR,

                net.minecraft.item.Items.IRON_PICKAXE,
                net.minecraft.item.Items.IRON_AXE,
                net.minecraft.item.Items.IRON_SHOVEL,
                net.minecraft.item.Items.IRON_HOE,
                net.minecraft.item.Items.IRON_SWORD,
                net.minecraft.item.Items.IRON_SPEAR,

                net.minecraft.item.Items.COPPER_PICKAXE,
                net.minecraft.item.Items.COPPER_AXE,
                net.minecraft.item.Items.COPPER_SHOVEL,
                net.minecraft.item.Items.COPPER_HOE,
                net.minecraft.item.Items.COPPER_SWORD,
                net.minecraft.item.Items.COPPER_SPEAR,

                net.minecraft.item.Items.MACE,
                net.minecraft.item.Items.TRIDENT,
                net.minecraft.item.Items.BOW,
                net.minecraft.item.Items.CROSSBOW,
                net.minecraft.item.Items.FISHING_ROD,
                net.minecraft.item.Items.FLINT_AND_STEEL,
                net.minecraft.item.Items.BRUSH,
                net.minecraft.item.Items.SHEARS
        );

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(NotFullyBroken.MOD_ID, name));
        }
    }
}
