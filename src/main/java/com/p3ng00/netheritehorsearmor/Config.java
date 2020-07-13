package com.p3ng00.netheritehorsearmor;

import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

class Config {

    private static final File CONFIG = new File(MinecraftClient.getInstance().runDirectory + "/config/" + NetheriteHorseArmor.NETHERITE_HORSE_ARMOR_ID.getPath() + ".txt");
    private static final Properties PROPERTIES = new Properties();

    static final Option<Boolean> OPTION_GIVE_TEMP_FIRE_RESIST = new Option<>(true, "giveTempFireResist");

    private static final Option<?>[] OPTIONS = new Option[] {
            OPTION_GIVE_TEMP_FIRE_RESIST
    };

    static {
        try {
            if (!CONFIG.exists()) {
                if (CONFIG.createNewFile()) {
                    for (Option<?> option : OPTIONS) {
                        PROPERTIES.setProperty(option.path, option.value.toString());
                    }
                    FileWriter writer = new FileWriter(CONFIG);
                    PROPERTIES.store(writer, null); // todo keep filewriter as seperate variable and close it after
                    writer.close();
                } else {
                    throw new IOException();
                }
            } else {
                // Load all options
                OPTION_GIVE_TEMP_FIRE_RESIST.value = Boolean.parseBoolean(PROPERTIES.getProperty(OPTION_GIVE_TEMP_FIRE_RESIST.path));
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
            try {
                FileWriter writer = new FileWriter(CONFIG);
                PROPERTIES.store(writer, null);
                writer.close();
            } catch (IOException e) {
                System.out.println("IO Error: Couldn't save settings for Netherite Horse Armor");
            }
        }
    }
}
