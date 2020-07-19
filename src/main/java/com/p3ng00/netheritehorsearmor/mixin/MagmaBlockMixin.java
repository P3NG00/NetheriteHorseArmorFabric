package com.p3ng00.netheritehorsearmor.mixin;

import com.p3ng00.netheritehorsearmor.Util;
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

@Mixin(MagmaBlock.class)
public class MagmaBlockMixin extends Block {

    public MagmaBlockMixin(Settings settings) {
        super(settings);
    }

    public void onSteppedOn(World world, BlockPos pos, Entity entity) { // todo test
        if (!entity.isFireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            float damage = 1.0f;
            for (ItemStack itemStack : entity.getArmorItems()) {
                try {
                    damage -= Util.NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.get(itemStack.getItem()) * 0.06;
                } catch (NullPointerException ignored) {}
            }
            entity.damage(DamageSource.HOT_FLOOR, damage);
        }
        super.onSteppedOn(world, pos, entity);
    }
}
