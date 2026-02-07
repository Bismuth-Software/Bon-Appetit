package net.bi83.bonappetit.core.common.datagen;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.BAItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Map;
import java.util.Set;

import static net.bi83.bonappetit.core.BAItems.*;

public class BAItemModelProvider extends ItemModelProvider {

    private static final Set<String> BLOCK_ITEMS = Set.of(
            "drying_rack",
            "copper_tank"
    );

    private static final Set<String> HANDHELD = Set.of(
            "cinnamon_sticks"
    );

    public BAItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BonAppetit.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        BAItems.ITEMS.getEntries().forEach(item -> {
            if (item.getId() == null) return;
            String path = item.getId().getPath();

            if (HANDHELD.contains(path)) {
                handheldItem(item.get());
            } else if (BLOCK_ITEMS.contains(path)) {
                withExistingParent(path, modLoc("block/" + path));
            } else {
                basicItem(item.get());
            }
        });
    }
}