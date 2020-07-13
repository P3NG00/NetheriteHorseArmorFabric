package com.p3ng00.netheritehorsearmor;

import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

class Config {

    private static final File CONFIG = new File(MinecraftClient.getInstance().runDirectory + "/config/" + NetheriteHorseArmor.NETHERITE_HORSE_ARMOR_ID.getPath() + ".txt");
    private static final Properties PROPERTIES = new Properties();

    static final Option<Boolean> OPTION_GIVE_TEMP_FIRE_RESIST = new Option<>(true, "giveTempFireResist");
    static final Option<Integer> OPTION_BASTION_TREASURE_AMOUNT = new Option<>(3, "bastionTreasureAmount");
    static final Option<Float> OPTION_BASTION_TREASURE_CHANCE = new Option<>(0.25f, "bastionTreasureChance");
    static final Option<Integer> OPTION_RUINED_PORTAL_AMOUNT = new Option<>(2, "ruinedPortalAmount");
    static final Option<Float> OPTION_RUINED_PORTAL_CHANCE = new Option<>(0.1f, "ruinedPortalChance");

    private static final Option<?>[] OPTIONS = new Option[] {
            OPTION_GIVE_TEMP_FIRE_RESIST,
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
                OPTION_GIVE_TEMP_FIRE_RESIST.value = Boolean.parseBoolean(PROPERTIES.getProperty(OPTION_GIVE_TEMP_FIRE_RESIST.path));
                OPTION_BASTION_TREASURE_AMOUNT.value = Integer.parseInt(PROPERTIES.getProperty(OPTION_BASTION_TREASURE_AMOUNT.path));
                OPTION_BASTION_TREASURE_CHANCE.value = Float.parseFloat(PROPERTIES.getProperty(OPTION_BASTION_TREASURE_CHANCE.path));
                OPTION_RUINED_PORTAL_AMOUNT.value = Integer.parseInt(PROPERTIES.getProperty(OPTION_RUINED_PORTAL_AMOUNT.path));
                OPTION_RUINED_PORTAL_CHANCE.value = Float.parseFloat(PROPERTIES.getProperty(OPTION_RUINED_PORTAL_CHANCE.path));
            }
        } catch (IOException e) {
            System.out.println("IO Error: Using default settings for Netherite Horse Armor");
        }
    }

    static class Option<E> {

        public final E defaultValue;
        public E value;
        public final String path;

        Option(E defaultValue, String propertyName) {
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
            PROPERTIES.store(writer, null);
            writer.close();
        } catch (IOException e) {
            System.out.println("IO Error: Couldn't save settings for Netherite Horse Armor");
        }
    }
}
