package net.bi83.bonappetit.core.content.item;

import net.bi83.bonappetit.core.BAEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class EclipsedCookieItem extends Item {
    public EclipsedCookieItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (entity instanceof Player player) {
            InteractionHand hand = player.getUsedItemHand();

            if (hand == InteractionHand.MAIN_HAND) {
                if (!level.isClientSide) {
                    player.addEffect(new MobEffectInstance(BAEffects.RESONANCE, 200, 0, false, true));
                }
            } else {
                if (!level.isClientSide) {
                    player.addEffect(new MobEffectInstance(BAEffects.CAFFEINATED, 200, 0, false, true));
                }
            }
        }
        return result;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.EAT;}
}