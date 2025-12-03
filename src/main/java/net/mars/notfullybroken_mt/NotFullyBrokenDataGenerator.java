package net.mars.notfullybroken_mt;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.mars.notfullybroken_mt.datagen.ModItemTagProvider;
import net.mars.notfullybroken_mt.datagen.ModModelProvider;

public class NotFullyBrokenDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModModelProvider::new);
    }
}
