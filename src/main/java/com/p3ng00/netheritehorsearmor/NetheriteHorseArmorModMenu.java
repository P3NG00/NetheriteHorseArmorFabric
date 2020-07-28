package com.p3ng00.netheritehorsearmor;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.text.TranslatableText;

import static com.p3ng00.netheritehorsearmor.Util.*;

public class NetheriteHorseArmorModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(createTranslatableText("title")).setSavingRunnable(NetheriteHorseArmorMain.CONFIG::save).setTransparentBackground(true);
            ConfigCategory category = builder.getOrCreateCategory(createTranslatableText("category"));
            TranslatableText requiresRestart = createTranslatableText("requires_restart");
            category.addEntry(builder.entryBuilder().startBooleanToggle(createTranslatableText("netherite_burn_resist_horse"), OPTION_NETHERITE_BURN_RESIST_HORSE.value).setSaveConsumer(b -> OPTION_NETHERITE_BURN_RESIST_HORSE.value = b).setDefaultValue(OPTION_NETHERITE_BURN_RESIST_HORSE.defaultValue).build())
                    .addEntry(builder.entryBuilder().startBooleanToggle(createTranslatableText("netherite_burn_resist_player"), OPTION_NETHERITE_BURN_RESIST_PLAYER.value).setSaveConsumer(b -> OPTION_NETHERITE_BURN_RESIST_PLAYER.value = b).setDefaultValue(OPTION_NETHERITE_BURN_RESIST_PLAYER.defaultValue).build())
                    .addEntry(builder.entryBuilder().startIntSlider(createTranslatableText("bastion_treasure_amount"), OPTION_BASTION_TREASURE_AMOUNT.value, 0, 5).setSaveConsumer(b -> OPTION_BASTION_TREASURE_AMOUNT.value = b).setDefaultValue(OPTION_BASTION_TREASURE_AMOUNT.defaultValue).requireRestart().setTooltip(requiresRestart).build())
                    .addEntry(builder.entryBuilder().startFloatField(createTranslatableText("bastion_treasure_chance"), OPTION_BASTION_TREASURE_CHANCE.value).setSaveConsumer(b -> OPTION_BASTION_TREASURE_CHANCE.value = b).setDefaultValue(OPTION_BASTION_TREASURE_CHANCE.defaultValue).requireRestart().setTooltip(requiresRestart).build())
                    .addEntry(builder.entryBuilder().startIntSlider(createTranslatableText("ruined_portal_amount"), OPTION_RUINED_PORTAL_AMOUNT.value, 0, 5).setSaveConsumer(b -> OPTION_RUINED_PORTAL_AMOUNT.value = b).setDefaultValue(OPTION_RUINED_PORTAL_AMOUNT.defaultValue).requireRestart().setTooltip(requiresRestart).build())
                    .addEntry(builder.entryBuilder().startFloatField(createTranslatableText("ruined_portal_chance"), OPTION_RUINED_PORTAL_CHANCE.value).setSaveConsumer(b -> OPTION_RUINED_PORTAL_CHANCE.value = b).setDefaultValue(OPTION_RUINED_PORTAL_CHANCE.defaultValue).requireRestart().setTooltip(requiresRestart).build());
            return builder.build();
        };
    }

    private TranslatableText createTranslatableText(String path) {
        return new TranslatableText(String.format("config.%s.%s", getModId(), path));
    }
}
