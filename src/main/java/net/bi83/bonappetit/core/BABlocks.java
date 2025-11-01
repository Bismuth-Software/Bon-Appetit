package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.block.CornCropBlock;
import net.bi83.bonappetit.core.block.CornCropBlockTop;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BABlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BonAppetit.ID);

    public static final DeferredBlock<Block> CORN_BASE = registerBlock("corn_base", () -> new CornCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).randomTicks().offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.CROP)));
    public static final DeferredBlock<Block> CORN_TOP = registerBlock("corn_top", () -> new CornCropBlockTop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {DeferredBlock<T> toReturn = BLOCKS.register(name, block); registerBlockItem(name, toReturn); return toReturn;}
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {BAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties())); }
    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus); }
}
