package com.p3ng00.netheritehorsearmor;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.text.TranslatableText;

import static com.p3ng00.netheritehorsearmor.Settings.*;

public class NetheriteHorseArmorModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        return parent -> {

            String c = "config";

            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Util.createTranslatableText(c, "title")).setSavingRunnable(CONFIG::save).setTransparentBackground(true);
            ConfigCategory category = builder.getOrCreateCategory(Util.createTranslatableText(c, "category.general"));

            TranslatableText requiresRestart = Util.createTranslatableText(c, "requires_restart");

            category.addEntry(builder.entryBuilder().startBooleanToggle(Util.createTranslatableText(c, "netherite_burn_resist_horse"), OPTION_NETHERITE_BURN_RESIST_HORSE.get()).setSaveConsumer(OPTION_NETHERITE_BURN_RESIST_HORSE::set).setDefaultValue(OPTION_NETHERITE_BURN_RESIST_HORSE::getDefaultValue).build());
            category.addEntry(builder.entryBuilder().startBooleanToggle(Util.createTranslatableText(c, "netherite_burn_resist_player"), OPTION_NETHERITE_BURN_RESIST_PLAYER.get()).setSaveConsumer(OPTION_NETHERITE_BURN_RESIST_PLAYER::set).setDefaultValue(OPTION_NETHERITE_BURN_RESIST_PLAYER::getDefaultValue).build());
            category.addEntry(builder.entryBuilder().startIntSlider(Util.createTranslatableText(c, "bastion_treasure_amount"), OPTION_BASTION_TREASURE_AMOUNT.get(), 0, 5).setSaveConsumer(OPTION_BASTION_TREASURE_AMOUNT::set).setDefaultValue(OPTION_BASTION_TREASURE_AMOUNT::getDefaultValue).requireRestart().setTooltip(requiresRestart).build());
            category.addEntry(builder.entryBuilder().startFloatField(Util.createTranslatableText(c, "bastion_treasure_chance"), OPTION_BASTION_TREASURE_CHANCE.get()).setSaveConsumer(OPTION_BASTION_TREASURE_CHANCE::set).setDefaultValue(OPTION_BASTION_TREASURE_CHANCE::getDefaultValue).requireRestart().setTooltip(requiresRestart).build());
            category.addEntry(builder.entryBuilder().startIntSlider(Util.createTranslatableText(c, "ruined_portal_amount"), OPTION_RUINED_PORTAL_AMOUNT.get(), 0, 5).setSaveConsumer(OPTION_RUINED_PORTAL_AMOUNT::set).setDefaultValue(OPTION_RUINED_PORTAL_AMOUNT::getDefaultValue).requireRestart().setTooltip(requiresRestart).build());
            category.addEntry(builder.entryBuilder().startFloatField(Util.createTranslatableText(c, "ruined_portal_chance"), OPTION_RUINED_PORTAL_CHANCE.get()).setSaveConsumer(OPTION_RUINED_PORTAL_CHANCE::set).setDefaultValue(OPTION_RUINED_PORTAL_CHANCE::getDefaultValue).requireRestart().setTooltip(requiresRestart).build());

            return builder.build();

        };

    }

}
