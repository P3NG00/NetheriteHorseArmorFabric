package com.p3ng00.netheritehorsearmor;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class NetheriteHorseArmorModMenu implements ModMenuApi {

    @Override
    public String getModId() {
        return NetheriteHorseArmor.MODID;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

    }
}
