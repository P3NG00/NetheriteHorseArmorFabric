package com.p3ng00.netheritehorsearmor.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MagmaBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static com.p3ng00.netheritehorsearmor.settings.Settings.NETHERITE_ARMOR_STAT_TABLE;

@Mixin(MagmaBlock.class)
public abstract class MagmaBlockMixin extends Block {

    public MagmaBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {

        if (!entity.bypassesSteppingEffects() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {

            float damage = 1.0f;

            if (com.p3ng00.netheritehorsearmor.settings.Settings.OPTION_NETHERITE_BURN_RESIST_PLAYER.get()) {

                for (ItemStack itemStack : entity.getArmorItems()) {
                    if (NETHERITE_ARMOR_STAT_TABLE.containsKey(itemStack.getItem())) {
                        damage -= NETHERITE_ARMOR_STAT_TABLE.get(itemStack.getItem()) * 0.01f;
                    }
                }
            }

            entity.damage(world.getDamageSources().hotFloor(), damage);
        }

        super.onSteppedOn(world, pos, state, entity);
    }

}
