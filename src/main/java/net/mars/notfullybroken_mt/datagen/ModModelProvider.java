package net.mars.notfullybroken_mt.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.mars.notfullybroken_mt.NotFullyBroken;
import net.mars.notfullybroken_mt.util.ModTags;
import net.minecraft.client.data.*;
import net.minecraft.client.item.ItemAsset;
import net.minecraft.client.render.item.model.ConditionItemModel;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.RangeDispatchItemModel;
import net.minecraft.client.render.item.property.bool.HasComponentProperty;
import net.minecraft.client.render.item.property.numeric.DamageProperty;
import net.minecraft.client.render.item.property.numeric.NumericProperties;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.data.tag.TagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import javax.swing.text.html.Option;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (String stringId : ModTags.Items.TOOLS_WITH_BROKEN_STATE) {
            Item item = Registries.ITEM.get(Identifier.ofVanilla(stringId));
            Identifier vanilaId = Identifier.ofVanilla(String.format("item/%s", stringId));
            Identifier modId = Identifier.of(NotFullyBroken.MOD_ID, String.format("item/%s_broken", stringId));
            ItemModel.Unbaked unbaked = ItemModels.basic(Models.GENERATED.upload(vanilaId, TextureMap.layer0(vanilaId), itemModelGenerator.modelCollector));
            ItemModel.Unbaked unbakedBroken = ItemModels.basic(Models.GENERATED.upload(modId, TextureMap.layer0(modId), itemModelGenerator.modelCollector));
            List<RangeDispatchItemModel.Entry> entries = new ArrayList<>();
            entries.add(new RangeDispatchItemModel.Entry(0, unbaked));
            entries.add(new RangeDispatchItemModel.Entry(1, unbakedBroken));
            itemModelGenerator.output.accept(item, new ItemAsset(new RangeDispatchItemModel.Unbaked(new DamageProperty(true), 1, entries, Optional.empty()), new ItemAsset.Properties(false)).model());
        }
    }
}
