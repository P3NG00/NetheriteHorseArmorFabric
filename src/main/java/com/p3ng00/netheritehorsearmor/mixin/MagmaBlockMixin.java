package com.p3ng00.netheritehorsearmor.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.MagmaBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static com.p3ng00.netheritehorsearmor.Settings.NETHERITE_ARMOR_STAT_TABLE;

@Mixin(MagmaBlock.class)
public abstract class MagmaBlockMixin extends Block {

    public MagmaBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {

        if (!entity.isFireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {

            float damage = 1.0f;

            if (com.p3ng00.netheritehorsearmor.Settings.OPTION_NETHERITE_BURN_RESIST_PLAYER.get())
                for (ItemStack itemStack : entity.getArmorItems())
                    if (NETHERITE_ARMOR_STAT_TABLE.containsKey(itemStack.getItem()))
                        damage -= NETHERITE_ARMOR_STAT_TABLE.get(itemStack.getItem()) * 0.01;

            entity.damage(DamageSource.HOT_FLOOR, damage);

        }

        super.onSteppedOn(world, pos, entity);

    }

}
