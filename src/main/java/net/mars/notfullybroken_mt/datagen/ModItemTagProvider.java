package net.mars.notfullybroken_mt.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.mars.notfullybroken_mt.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (@NotNull Item item : ModTags.ItemTags.TOOLS_WITH_BROKEN_STATE) {
            valueLookupBuilder(ModTags.ItemTags.HAS_BROKEN_STATE).add(item);
        }
    }
}