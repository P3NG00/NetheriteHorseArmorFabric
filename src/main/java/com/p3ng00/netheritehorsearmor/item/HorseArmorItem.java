package com.p3ng00.netheritehorsearmor.item;

import com.p3ng00.netheritehorsearmor.NetheriteHorseArmor;
import net.minecraft.util.Identifier;

public class HorseArmorItem extends net.minecraft.item.HorseArmorItem {

    private final Identifier texture;

    public HorseArmorItem(int bonus, String name, Settings settings) {
        super(bonus, name, settings);
        texture = new Identifier(NetheriteHorseArmor.MODID, super.getEntityTexture().getPath());
    }

    @Override
    public Identifier getEntityTexture() {
        return texture;
    }
}
