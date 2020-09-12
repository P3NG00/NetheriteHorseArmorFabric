package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.Settings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static com.p3ng00.netheritehorsearmor.NetheriteHorseArmor.ENDERITE_HORSE_ARMOR;
import static com.p3ng00.netheritehorsearmor.NetheriteHorseArmor.NETHERITE_HORSE_ARMOR;

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

            if (Settings.OPTION_NETHERITE_BURN_RESIST_HORSE.get()) {

                for (ItemStack itemStack : getArmorItems()) {

                    item = itemStack.getItem();

                    if (item == NETHERITE_HORSE_ARMOR) {

                        // Netherite Horse Armor
                        setOnFireFor = 9;
                        damage = 2.25f;

                    } else if (item == ENDERITE_HORSE_ARMOR) {

                        // Enderite Horse Armor
                        setOnFireFor = 7;
                        damage = 1.8f;

                    }

                }

            }

            setOnFireFor(setOnFireFor);
            damage(DamageSource.LAVA, damage);

        }

    }

}
