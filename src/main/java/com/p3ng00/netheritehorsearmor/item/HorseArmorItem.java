package com.p3ng00.netheritehorsearmor.item;

import com.p3ng00.netheritehorsearmor.NetheriteHorseArmor;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

import static com.p3ng00.netheritehorsearmor.NetheriteHorseArmor.UTIL;

public class HorseArmorItem extends net.minecraft.item.HorseArmorItem {

    private final Identifier texture;

    public HorseArmorItem(int bonus, String materialName) {
        super(bonus, null, new Settings().maxCount(1).group(ItemGroup.MISC).fireproof());
        texture = new Identifier(UTIL.getModId(), String.format("textures/entity/horse/armor/horse_armor_%s.png", materialName));
    }

    @Override
    public Identifier getEntityTexture() {
        return texture;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {

        if (!NetheriteHorseArmor.isEnderiteModLoaded && asItem() == NetheriteHorseArmor.ENDERITE_HORSE_ARMOR) {

            tooltip.add(UTIL.createTranslatableText("tooltip", "enderite_mod_missing_0"));
            tooltip.add(UTIL.createTranslatableText("tooltip", "enderite_mod_missing_1"));

        }

    }

}
