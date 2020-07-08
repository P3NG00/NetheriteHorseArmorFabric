package com.p3ng00.netheritehorsearmor;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetheriteHorseArmor implements ModInitializer {

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("netheritehorsearmor", "netherite_horse_armor"), new HorseArmorItem(15, "netherite", new Item.Settings().maxCount(1).group(ItemGroup.MISC).fireproof()) {
            @Override
            public Identifier getEntityTexture() {
                return new Identifier("netheritehorsearmor", super.getEntityTexture().getPath());
            }
        });
    }
}
