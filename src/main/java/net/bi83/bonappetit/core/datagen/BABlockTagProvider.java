package net.bi83.bonappetit.core.datagen;

import net.bi83.bonappetit.BonAppetit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BABlockTagProvider extends BlockTagsProvider {
    public BABlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {super(output, lookupProvider, BonAppetit.ID, existingFileHelper);}

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}