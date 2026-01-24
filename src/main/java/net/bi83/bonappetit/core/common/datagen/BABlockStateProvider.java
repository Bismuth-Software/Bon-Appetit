package net.bi83.bonappetit.core.common.datagen;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class BABlockStateProvider extends BlockStateProvider {
    public BABlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {super(output, BonAppetit.ID, exFileHelper);}

    @Override
    protected void registerStatesAndModels() {
        //blockitem
    }

    private String name(Block block) {
        return key(block).getPath();
    }
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);getVariantBuilder(block).forAllStates(function);}
    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {ConfiguredModel[] models = new ConfiguredModel[1];models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(SweetBerryBushBlock.AGE), ResourceLocation.fromNamespaceAndPath(BonAppetit.ID, "block/" + textureName + state.getValue(SweetBerryBushBlock.AGE))).renderType("cutout"));return models;}
    private void saplingBlock(DeferredBlock<SaplingBlock> blockRegistryObject) {simpleBlock(blockRegistryObject.get(), models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));}
    private void blockWithItem(DeferredBlock<?> deferredBlock) {simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));}
    private void blockItem(DeferredBlock<?> deferredBlock) {simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("bonappetit:block/" + deferredBlock.getId().getPath()));}
    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("bonappetit:block/" + deferredBlock.getId().getPath() + appendix));}
}