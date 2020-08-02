package com.p3ng00.netheritehorsearmor.item;

import com.p3ng00.netheritehorsearmor.NetheriteHorseArmorMain;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class HorseArmorItem extends net.minecraft.item.HorseArmorItem {

    private final Identifier texture;

    public HorseArmorItem(int bonus, String materialName, Settings settings) {
        super(bonus, null, settings.maxCount(1).group(ItemGroup.MISC));
        texture = new Identifier(NetheriteHorseArmorMain.MODID, String.format("textures/entity/horse/armor/horse_armor_%s.png", materialName));
    }

    @Override
    public Identifier getEntityTexture() {
        return texture;
    }
}
