package net.bi83.bonappetit.core;

import net.bi83.bonappetit.BonAppetit;
import net.bi83.bonappetit.core.entity.goal.BeeMoveToFruitBushGoal;
import net.bi83.bonappetit.core.entity.goal.BeePollinateFruitGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@Mod(value = BonAppetit.ID) @EventBusSubscriber(modid = BonAppetit.ID)
public class BAEvents {
    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, BAItems.GOLDEN_STRAWBERRIES.get(), Potions.REGENERATION);
    }
    @SubscribeEvent
    public static void onBeeJoin(EntityJoinLevelEvent join) {
        if (join.getEntity() instanceof Bee bee) {
            bee.getGoalSelector().addGoal(3, new BeePollinateFruitGoal(bee));
            bee.getGoalSelector().addGoal(4, new BeeMoveToFruitBushGoal(bee));
        }
    }



    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((p_329705_, p_329706_) -> {
            event.register((stack, index) -> index == 0 ? DyedItemColor.getOrDefault(stack, -1) : -1, BAItems.MACARON);
            return 0xFFFFFFFF;
        }, BAItems.MACARON.value());
    }
}