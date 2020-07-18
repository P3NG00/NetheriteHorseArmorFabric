package com.p3ng00.netheritehorsearmor.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public final class Config {

    public static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "netherite_horse_armor.txt");
    public static final Properties PROPERTIES = new Properties();

    public static final Option<Boolean> OPTION_NETHERITE_FIRE_RESIST_HORSE = new Options.BooleanOption(true, "netheriteFireResistHorse");
    public static final Option<Boolean> OPTION_NETHERITE_FIRE_RESIST_PLAYER = new Options.BooleanOption(true, "netheriteFireResistPlayer");
    public static final Option<Integer> OPTION_BASTION_TREASURE_AMOUNT = new Options.IntegerOption(3, "bastionTreasureAmount");
    public static final Option<Float> OPTION_BASTION_TREASURE_CHANCE = new Options.FloatOption(0.25f, "bastionTreasureChance");
    public static final Option<Integer> OPTION_RUINED_PORTAL_AMOUNT = new Options.IntegerOption(2, "ruinedPortalAmount");
    public static final Option<Float> OPTION_RUINED_PORTAL_CHANCE = new Options.FloatOption(0.1f, "ruinedPortalChance");

    private static final Option<?>[] OPTIONS = new Option[] {
            OPTION_NETHERITE_FIRE_RESIST_HORSE,
            OPTION_NETHERITE_FIRE_RESIST_PLAYER,
            OPTION_BASTION_TREASURE_AMOUNT,
            OPTION_BASTION_TREASURE_CHANCE,
            OPTION_RUINED_PORTAL_AMOUNT,
            OPTION_RUINED_PORTAL_CHANCE
    };

    static {
        try {
            if (!CONFIG_FILE.exists()) {
                if (CONFIG_FILE.createNewFile()) {
                    for (Option<?> option : OPTIONS) {
                        PROPERTIES.setProperty(option.path, option.value.toString());
                    }
                    save();
                } else {
                    throw new IOException();
                }
            } else {
                PROPERTIES.load(new FileReader(CONFIG_FILE));
                Arrays.stream(OPTIONS).forEach(Option::load);
            }
        } catch (IOException e) {
            System.out.println("IO Error: Using default settings for Netherite Horse Armor");
        }
    }

    public static void save() {
        try {
            FileWriter writer = new FileWriter(CONFIG_FILE, false);
            writer.write("# Netherite Horse Armor Mod by P3NG00\n");
            for (Option<?> option : OPTIONS) {
                writer.write(String.format("%s=%s\n", option.path, option.value));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IO Error: Couldn't save settings for Netherite Horse Armor");
        }
    }
}
