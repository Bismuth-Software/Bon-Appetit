package net.bi83.bonappetit.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class BATags {
    public static class Items {
        public static final TagKey<Item> CITRUS_FRUITS = createTag("citrus_fruits");

        private static TagKey<Item> createTag(String name) {return TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(name));}}

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> RICOCHET_IMMUNE = createTag("ricochet_immune");

        private static TagKey<EntityType<?>> createTag(String name) {return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace(name));}}
    }