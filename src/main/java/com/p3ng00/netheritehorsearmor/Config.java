package com.p3ng00.netheritehorsearmor;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public final class Config {

    private static final File CONFIG = new File(FabricLoader.getInstance().getConfigDirectory(), NetheriteHorseArmor.NETHERITE_HORSE_ARMOR_ID.getPath() + ".txt");
    private static final Properties PROPERTIES = new Properties();

    public static final Option<Boolean> OPTION_NETHERITE_FIRE_RESIST_HORSE = new Option<>(true, "netheriteFireResistHorse");
    public static final Option<Boolean> OPTION_NETHERITE_FIRE_RESIST_PLAYER = new Option<>(true, "netheriteFireResistPlayer");
    public static final Option<Integer> OPTION_BASTION_TREASURE_AMOUNT = new Option<>(3, "bastionTreasureAmount");
    public static final Option<Float> OPTION_BASTION_TREASURE_CHANCE = new Option<>(0.25f, "bastionTreasureChance");
    public static final Option<Integer> OPTION_RUINED_PORTAL_AMOUNT = new Option<>(2, "ruinedPortalAmount");
    public static final Option<Float> OPTION_RUINED_PORTAL_CHANCE = new Option<>(0.1f, "ruinedPortalChance");

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
            if (!CONFIG.exists()) {
                if (CONFIG.createNewFile()) {
                    for (Option<?> option : OPTIONS) {
                        PROPERTIES.setProperty(option.path, option.value.toString());
                    }
                    save();
                } else {
                    throw new IOException();
                }
            } else {
                // Load all options
                PROPERTIES.load(new FileReader(CONFIG));
                OPTION_NETHERITE_FIRE_RESIST_HORSE.value = Boolean.parseBoolean(PROPERTIES.getProperty(OPTION_NETHERITE_FIRE_RESIST_HORSE.path));
                OPTION_NETHERITE_FIRE_RESIST_PLAYER.value = Boolean.parseBoolean(PROPERTIES.getProperty(OPTION_NETHERITE_FIRE_RESIST_PLAYER.path));
                OPTION_BASTION_TREASURE_AMOUNT.value = Integer.parseInt(PROPERTIES.getProperty(OPTION_BASTION_TREASURE_AMOUNT.path));
                OPTION_BASTION_TREASURE_CHANCE.value = Float.parseFloat(PROPERTIES.getProperty(OPTION_BASTION_TREASURE_CHANCE.path));
                OPTION_RUINED_PORTAL_AMOUNT.value = Integer.parseInt(PROPERTIES.getProperty(OPTION_RUINED_PORTAL_AMOUNT.path));
                OPTION_RUINED_PORTAL_CHANCE.value = Float.parseFloat(PROPERTIES.getProperty(OPTION_RUINED_PORTAL_CHANCE.path));
            }
        } catch (IOException e) {
            System.out.println("IO Error: Using default settings for Netherite Horse Armor");
        }
    }

    public static class Option<E> {

        public final E defaultValue;
        public E value;
        public final String path;

        private Option(E defaultValue, String propertyName) {
            this.defaultValue = defaultValue;
            value = defaultValue;
            this.path = propertyName;
        }

        public void set(E value) {
            this.value = value;
            PROPERTIES.setProperty(path, value.toString());
        }
    }

    static void save() {
        try {
            FileWriter writer = new FileWriter(CONFIG, false);
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
