package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.NetheriteHorseArmorMain;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.item.Item;
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

            Item item;
            int setOnFireFor = 15;
            float damage = 4.0f;

            for (ItemStack itemStack : getArmorItems()) {

                item = itemStack.getItem();

                if (item == NetheriteHorseArmorMain.NETHERITE_HORSE_ARMOR) {

                    // Netherite Horse Armor
                    setOnFireFor = 9;
                    damage = 2.25f;

                } else if (item == NetheriteHorseArmorMain.ENDERITE_HORSE_ARMOR) {

                    // Enderite Horse Armor
                    setOnFireFor = 5;
                    damage = 1.5f;

                }

            }

            setOnFireFor(setOnFireFor);
            damage(DamageSource.LAVA, damage);

        }

    }

}
