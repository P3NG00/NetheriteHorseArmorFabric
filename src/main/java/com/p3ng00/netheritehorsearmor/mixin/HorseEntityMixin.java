package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.settings.Settings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static com.p3ng00.netheritehorsearmor.NetheriteHorseArmor.ENDERITE_HORSE_ARMOR;
import static com.p3ng00.netheritehorsearmor.NetheriteHorseArmor.NETHERITE_HORSE_ARMOR;

@Mixin(HorseEntity.class)
public abstract class HorseEntityMixin extends AnimalEntity {

    protected HorseEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setOnFireFromLava() {

        if (!this.isFireImmune()) {

            int setOnFireFor = 15;
            float damage = 4.0f;

            if (Settings.OPTION_NETHERITE_BURN_RESIST_HORSE.get()) {

                for (ItemStack itemStack : getArmorItems()) {

                    Item item = itemStack.getItem();

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
            if (this.damage(this.getDamageSources().lava(), damage)) {
                this.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4f, 2.0f + this.random.nextFloat() * 0.4f);
            }

        }

    }

}
