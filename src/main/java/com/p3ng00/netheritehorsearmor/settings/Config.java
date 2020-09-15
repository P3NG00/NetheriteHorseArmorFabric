package com.p3ng00.netheritehorsearmor.settings;


import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public final File FILE;
    public final Properties PROPERTIES = new Properties();
    public final Option<?>[] OPTIONS;
    public final String MOD_NAME;

    public Config(String modName, String fileName, Option<?>... options) {

        MOD_NAME = modName;
        OPTIONS = options;
        FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), fileName);

        try {

            if (!FILE.exists()) {

                if (FILE.createNewFile()) {

                    save();

                } else {

                    throw new IOException();

                }

            } else {

                PROPERTIES.load(new FileReader(FILE));
                for (Option<?> option : options) option.load(PROPERTIES);

            }

        } catch (IOException e) {

            System.out.printf("IO Error: Using default settings for %s\n", MOD_NAME);

        }

    }

    public void save() {

        try (FileWriter writer = new FileWriter(FILE, false)) {

            writer.write(String.format("# %s by P3NG00\n", MOD_NAME));
            String saveValue;

            for (Option<?> option : OPTIONS) {

                saveValue = option.getSaveValue();
                String path = option.getPath();
                writer.write(String.format("%s=%s\n", path, saveValue));
                PROPERTIES.setProperty(path, saveValue);

            }

        } catch (IOException e) {

            System.out.printf("IO Error: Couldn't save settings for %s\n", MOD_NAME);

        }

    }

}