package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.NetheriteHorseArmorMain;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HorseBaseEntity.class)
public abstract class HorseEntityMixin extends AnimalEntity {

    protected HorseEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void setOnFireFromLava() {
        if (!this.isFireImmune()) {
            boolean wearingNetherite = false;
            for (ItemStack itemStack : getArmorItems()) {
                if (itemStack.getItem() == NetheriteHorseArmorMain.NETHERITE_HORSE_ARMOR) {
                    wearingNetherite = true;
                    break;
                }
            }
            setOnFireFor(wearingNetherite ? 7 : 15);
            damage(DamageSource.LAVA, wearingNetherite ? 1.75f : 4.0f);
        }
    }
}
