package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.Config;
import com.p3ng00.netheritehorsearmor.item.NetheriteHorseArmorItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseBaseEntity.class)
public abstract class HorseEntityMixin extends AnimalEntity {

    protected HorseEntityMixin(EntityType<? extends HorseBaseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void onTick(CallbackInfo info) {
        if (Config.OPTION_NETHERITE_FIRE_RESIST_HORSE.value && !isOnFire()) {
            for (ItemStack itemStack : getArmorItems()) {
                if (itemStack.getItem() instanceof NetheriteHorseArmorItem) {
                    addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false, true));
                    break;
                }
            }
        }
    }
}
