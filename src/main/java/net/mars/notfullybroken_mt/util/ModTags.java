package net.mars.notfullybroken_mt.util;

import net.mars.notfullybroken_mt.NotFullyBroken;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
    public static final TagKey<Item> HAS_BROKEN_STATE = createTag("has_broken_state");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(NotFullyBroken.MOD_ID, name));
        }
    }
}
