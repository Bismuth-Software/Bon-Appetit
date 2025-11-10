package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.content.blockentity.CopperTankEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BABlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BonAppetit.ID);

    public static final Supplier<BlockEntityType<CopperTankEntity>> COPPER_TANK =
            BLOCK_ENTITIES.register("copper_tank", () -> BlockEntityType.Builder.of(
                    CopperTankEntity::new, BABlocks.COPPER_TANK.get()).build(null));

    public static void register(IEventBus eventBus) {BLOCK_ENTITIES.register(eventBus);}
}