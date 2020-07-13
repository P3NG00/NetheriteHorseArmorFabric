package com.p3ng00.netheritehorsearmor;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.TooltipListEntry;
import net.minecraft.text.TranslatableText;

import static com.p3ng00.netheritehorsearmor.Config.*;

public class NetheriteHorseArmorModMenu implements ModMenuApi {

    @Override
    public String getModId() {
        return NetheriteHorseArmor.MODID;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(createTranslatableText("title")).setSavingRunnable(Config::save);
            ConfigCategory category = builder.getOrCreateCategory(createTranslatableText("category"));
            for (TooltipListEntry<?> entry : new TooltipListEntry<?>[] {
                    builder.entryBuilder().startBooleanToggle(createTranslatableText("netherite_provides_fire_resistance_like_water_breathing"), OPTION_GIVE_TEMP_FIRE_RESIST.value).setSaveConsumer(OPTION_GIVE_TEMP_FIRE_RESIST::set).setDefaultValue(OPTION_GIVE_TEMP_FIRE_RESIST.defaultValue).build(),
                    builder.entryBuilder().startIntSlider(createTranslatableText("bastion_treasure_amount"), OPTION_BASTION_TREASURE_AMOUNT.value, 0, 5).setSaveConsumer(OPTION_BASTION_TREASURE_AMOUNT::set).setDefaultValue(OPTION_BASTION_TREASURE_AMOUNT.defaultValue).build(),
                    builder.entryBuilder().startFloatField(createTranslatableText("bastion_treasure_chance"), OPTION_BASTION_TREASURE_CHANCE.value).setSaveConsumer(OPTION_BASTION_TREASURE_CHANCE::set).setDefaultValue(OPTION_BASTION_TREASURE_CHANCE.defaultValue).build(),
                    builder.entryBuilder().startIntSlider(createTranslatableText("ruined_portal_amount"), OPTION_RUINED_PORTAL_AMOUNT.value, 0, 5).setSaveConsumer(OPTION_RUINED_PORTAL_AMOUNT::set).setDefaultValue(OPTION_RUINED_PORTAL_AMOUNT.defaultValue).build(),
                    builder.entryBuilder().startFloatField(createTranslatableText("ruined_portal_chance"), OPTION_RUINED_PORTAL_CHANCE.value).setSaveConsumer(OPTION_RUINED_PORTAL_CHANCE::set).setDefaultValue(OPTION_RUINED_PORTAL_CHANCE.defaultValue).build()
            }) {
                category.addEntry(entry);
            }
            return builder.build();
        };
    }

    private TranslatableText createTranslatableText(String path) {
        return new TranslatableText(String.format("config.%s.%s", getModId(), path));
    }
}
