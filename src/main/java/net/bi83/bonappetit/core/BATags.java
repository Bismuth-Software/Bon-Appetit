package net.bi83.bonappetit.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class BATags {
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> RICOCHET_IMMUNE = createTag("ricochet_immune");

        private static TagKey<EntityType<?>> createTag(String name) {return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace(name)); }
    }
}