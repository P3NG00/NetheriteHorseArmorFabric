package com.p3ng00.netheritehorsearmor;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import net.minecraft.text.TranslatableText;

public class NetheriteHorseArmorModMenu implements ModMenuApi {

    @Override
    public String getModId() {
        return NetheriteHorseArmor.MODID;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableText("config." + getModId() + ".title"));
            ConfigCategory category = builder.getOrCreateCategory(new TranslatableText("config." + getModId() + ".category"));
            BooleanListEntry entry = builder.entryBuilder().startBooleanToggle(new TranslatableText("config." + getModId() + ".netherite_provides_fire_resistance_like_water_breathing"), Config.OPTION_GIVE_TEMP_FIRE_RESIST.value).setSaveConsumer(Config.OPTION_GIVE_TEMP_FIRE_RESIST::set).build();
            category.addEntry(entry);
            return builder.build();
        };
    }
}
