package net.bi83.bonappetit.core.item;

import net.bi83.bonappetit.core.BAItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class CornCobItem extends Item {
    public CornCobItem(Properties properties) {super(properties);}

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        super.finishUsingItem(stack, level, entityLiving);
        if (entityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.STICK);
        } else {
            if (entityLiving instanceof Player) {
                Player player = (Player)entityLiving;
                if (!player.hasInfiniteMaterials()) {
                    ItemStack itemstack = new ItemStack(Items.STICK);
                    if (!player.getInventory().add(itemstack)) {
                        player.drop(itemstack, false);
                    }
                }
            }
            return stack;
        }
    }
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }
}
