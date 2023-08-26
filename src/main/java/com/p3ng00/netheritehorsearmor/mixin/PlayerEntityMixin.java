package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.settings.Settings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static com.p3ng00.netheritehorsearmor.settings.Settings.NETHERITE_ARMOR_STAT_TABLE;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setOnFireFromLava() {

        if (!this.isFireImmune()) {

            int time = 0;

            if (Settings.OPTION_NETHERITE_BURN_RESIST_PLAYER.get()) {

                for (ItemStack itemStack : getArmorItems()) {
                    if (NETHERITE_ARMOR_STAT_TABLE.containsKey(itemStack.getItem())) {
                        time += NETHERITE_ARMOR_STAT_TABLE.get(itemStack.getItem());
                    }
                }
            }

            this.setOnFireFor(time == 0 ? 15 : 40 / time);
            if (this.damage(DamageSource.LAVA, time == 0 ? 4.0f : 10.0f / time)) {
                this.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4f, 2.0f + this.random.nextFloat() * 0.4f);
            }
        }

    }

}
