package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.content.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BABlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BonAppetit.ID);

    public static final DeferredBlock<CookingPotBlock> COOKING_POT = registerBlockNoItem("cooking_pot",
            () -> new CookingPotBlock(BlockBehaviour.Properties.of().dynamicShape().sound(SoundType.LANTERN).noOcclusion()));
    public static final DeferredBlock<DryingRackBlock> DRYING_RACK = registerBlock("drying_rack",
            () -> new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).dynamicShape().sound(SoundType.WOOD).noOcclusion()));
    public static final DeferredBlock<CopperTankBlock> COPPER_TANK = registerBlock("copper_tank",
            () -> new CopperTankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_COPPER_BLOCK).sound(SoundType.COPPER).noOcclusion()));

    public static final DeferredBlock<CakeBlock> LEMON_CAKE = BLOCKS.register("lemon_cake", () -> new LemonCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));

    public static final DeferredBlock<Block> GRAPEFRUIT_VINE = registerBlockNoItem("grapefruit_vine", () -> new GrapefruitVineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).randomTicks().offsetType(BlockBehaviour.OffsetType.XZ).instabreak().noCollission().sound(SoundType.WEEPING_VINES)));
    public static final DeferredBlock<Block> CORN_BASE = registerBlockNoItem("corn_base", () -> new CornCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).randomTicks().offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.CROP)));
    public static final DeferredBlock<Block> CORN_TOP = registerBlockNoItem("corn_top", () -> new CornCropBlockTop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {DeferredBlock<T> toReturn = BLOCKS.register(name, block); registerBlockItem(name, toReturn); return toReturn;}
    private static <T extends Block> DeferredBlock<T> registerBlockNoItem(String name, Supplier<T> block) {return BLOCKS.register(name, block);}
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {BAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties())); }
    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
}
