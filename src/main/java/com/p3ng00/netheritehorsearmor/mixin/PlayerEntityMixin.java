package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void onTick(CallbackInfo info) {
        if (Config.OPTION_NETHERITE_FIRE_RESIST_HORSE.value && !isOnFire()) {
            int seconds = 0;
            for (ItemStack itemStack : getArmorItems()) {
                if (itemStack.getItem() == Items.NETHERITE_HELMET) {
                    seconds += 3;
                } else if (itemStack.getItem() == Items.NETHERITE_CHESTPLATE) {
                    seconds += 5;
                } else if (itemStack.getItem() == Items.NETHERITE_LEGGINGS) {
                    seconds += 4;
                } else if (itemStack.getItem() == Items.NETHERITE_BOOTS) {
                    seconds += 3;
                }
            }
            if (seconds != 0) {
                addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, seconds * 20, 0, false, false, true));
            }
        }
    }
}
