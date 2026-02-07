package net.bi83.bonappetit.core.common.datagen;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.BABlocks;
import net.bi83.bonappetit.core.BAEffects;
import net.bi83.bonappetit.core.BAItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class BAEnglishLanguageProvider extends LanguageProvider {
    String BA = BonAppetit.ID;
    private static final Map<String, String> OVERRIDES = Map.of(
            "corn_on_a_cob", "Corn on a Cob"
    );

    public BAEnglishLanguageProvider(PackOutput output) {
        super(output, BonAppetit.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("tab." + BA, "Bon Appétit");

        //emi info
        add("emi.info.macarons",
                "Macarons are able to be dyed like leather armor!");

        //effect desc
        add("effect." + BA + ".hearttrickle.description",
                "Temporary description!");
        add("effect." + BA + ".reflection.description",
                "Reflects any projectiles that make contact with the user; higher levels increase the chance.");
        add("effect." + BA + ".concentration.description",
                "Removes gravity from any projectile shot and amplifies the damage; higher levels increase the projectile velocity and damage.");
        add("effect." + BA + ".agility.description",
                "Temporary description!");
        add("effect." + BA + ".resonance.description",
                "Spawns a potion cloud with all your positive effects; higher levels increase the cloud duration and size.");
        add("effect." + BA + ".dissonance.description",
                "Spawns a potion cloud with all your negative effects; higher levels increase the cloud duration and size.");
        add("effect." + BA + ".rooted.description",
                "Increases knockback resistance and armor for the user.");
        add("effect." + BA + ".caffeinated.description",
                "Increases attack speed and mining speed, and reduces the movement speed reduction from blocks like Soul Sand.");

        //advancements
        add("advancements.husbandry.wake_and_bake.title", "Wake And Bake");
        add("advancements.husbandry.wake_and_bake.description",
                "Bake a cookie");
        add("advancements.husbandry.lucky_day.title", "Lucky Day");
        add("advancements.husbandry.lucky_day.description",
                "Consume a golden cookie");
        add("advancements.husbandry.black_cats_paw.title", "Black Cat's Paw");
        add("advancements.husbandry.black_cats_paw.description",
                "Obtain 777 golden cookies and question your sanity");

        //tags
        add("tag.item." + BA + ".citrus_fruits", "Citrus Fruits");

        //configs
        add("bonappetit.configuration.title", "Bon Appétit Config");
        add("bonappetit.configuration.section.bonappetit.common.toml", "Bon Appétit Config");
        add("bonappetit.configuration.section.bonappetit.common.toml.title", "Bon Appétit Config");

        add("bonappetit.configuration.registerCoffee", "Coffee");

        add("bonappetit.configuration.items", "Item List");
        add("bonappetit.configuration.magicNumberIntroduction", "Magic Number Text");
        add("bonappetit.configuration.magicNumber", "Magic Number");

        BAItems.ITEMS.getEntries().forEach(item -> {
            if (item.getId() == null) return;

            String path = item.getId().getPath();
            String name = OVERRIDES.getOrDefault(path, toTitleCase(path));
            add("item." + BA + "." + path, name);
        });

        BABlocks.BLOCKS.getEntries().forEach(block -> {
            if (block.getId() == null) return;

            String path = block.getId().getPath();
            add("block." + BA + "." + path, toTitleCase(path));
        });

        BAEffects.EFFECTS.getEntries().forEach(effect -> {
            if (effect.getId() == null) return;

            String path = effect.getId().getPath();
            add("effect." + BA + "." + path, toTitleCase(path));
        });
    }

    private static String toTitleCase(String id) {
        return Arrays.stream(id.split("_"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }
}
