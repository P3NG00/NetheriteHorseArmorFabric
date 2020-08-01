package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void setOnFireFromLava() {
        if (!this.isFireImmune()) {
            int time = 0;
            for (ItemStack itemStack : getArmorItems()) {
                if (Util.NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.containsKey(itemStack.getItem())) {
                    time += Util.NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.get(itemStack.getItem());
                }
            }
            setOnFireFor(time == 0 ? 15 : 40 / time);
            damage(DamageSource.LAVA, time == 0 ? 4.0f : 10.0f / time);
        }
    }
}
