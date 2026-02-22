package net.bi83.bonappetit.core.common.datagen;

import net.bi83.bonappetit.core.BABlocks;
import net.bi83.bonappetit.core.BAItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class BABlockLootTableProvider extends BlockLootSubProvider {
    private final Set<Block> knownBlocks = new java.util.HashSet<>();
    protected BABlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(BABlocks.COPPER_TANK.get());
        dropCake(Blocks.CAKE, BAItems.CAKE_SLICE.get());
        dropCake(BABlocks.LEMON_CAKE.get(), BAItems.LEMON_CAKE_SLICE.get());
        dropCake(BABlocks.LIME_CAKE.get(), BAItems.LIME_CAKE_SLICE.get());
    }



    protected void dropCake(Block cakeBlock, ItemLike sliceItem) {
        this.add(cakeBlock, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(cakeBlock)
                                .when(this.hasSilkTouch())
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cakeBlock)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CakeBlock.BITES, 0)))

                                .otherwise(
                                        LootItem.lootTableItem(sliceItem)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(7)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cakeBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CakeBlock.BITES, 0))))
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(6)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cakeBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CakeBlock.BITES, 1))))
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(5)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cakeBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CakeBlock.BITES, 2))))
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cakeBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CakeBlock.BITES, 3))))
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cakeBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CakeBlock.BITES, 4))))
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cakeBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CakeBlock.BITES, 5))))
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cakeBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CakeBlock.BITES, 6))))
                                )
                        )
        ));
    }

    @Override
    protected void dropSelf(Block block) {
        this.knownBlocks.add(block);
        super.dropSelf(block);
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        this.knownBlocks.add(block);
        super.add(block, builder);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.knownBlocks;
    }
}