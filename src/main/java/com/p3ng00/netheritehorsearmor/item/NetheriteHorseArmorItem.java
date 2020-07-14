package com.p3ng00.netheritehorsearmor.item;

import com.p3ng00.netheritehorsearmor.NetheriteHorseArmor;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class NetheriteHorseArmorItem extends HorseArmorItem {

    private static Identifier TEXTURE;

    public NetheriteHorseArmorItem() {
        super(15, null, new Settings().maxCount(1).group(ItemGroup.MISC).fireproof());
        TEXTURE = new Identifier(NetheriteHorseArmor.MODID, "textures/entity/horse/armor/horse_armor_netherite.png");
    }

    @Override
    public Identifier getEntityTexture() {
        return TEXTURE;
    }
}
