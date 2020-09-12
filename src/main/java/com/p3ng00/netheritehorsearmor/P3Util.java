package com.p3ng00.netheritehorsearmor;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

public class P3Util {

    private final String modName;
    private final String modId;

    public P3Util(String modName, String modId) {
        this.modName = modName;
        this.modId = modId;
    }

    public String getModName() {
        return modName;
    }

    public String getModId() {
        return modId;
    }

    public void register(String name, ItemConvertible item) {

        Identifier id = new Identifier(modId, name);

        if (item instanceof Item)
            Registry.register(Registry.ITEM, id, (Item)item);
        else if (item instanceof Block)
            Registry.register(Registry.BLOCK, id, (Block)item);
        else
            throw new IllegalArgumentException("Can only use this method for ITEMs or BLOCKs");

    }

    public void log(Level level, String message) {
        LogManager.getLogger().log(level, String.format("[%s] %s", modName, message));
    }

    public TranslatableText createTranslatableText(String category, String path) {
        return new TranslatableText(String.format("%s.%s.%s", category, modId, path));
    }

    public static class Config {

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

                    if (FILE.createNewFile())
                        save();
                    else
                        throw new IOException();

                } else {

                    PROPERTIES.load(new FileReader(FILE));

                    for (Option<?> option : options)
                        option.load(PROPERTIES);

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

    public static class Option<E> {

        private final String PATH;
        private final Function<String, E> PARSER;
        private final E DEFAULT_VALUE;
        private E value;

        public Option(String propertyName, E defaultValue, Function<String, E> parser) {
            PATH = propertyName;
            this.PARSER = parser;
            this.DEFAULT_VALUE = defaultValue;
            value = defaultValue;
        }

        // To load setting's saved value
        public final void load(Properties properties) {
            value = PARSER.apply(properties.getProperty(PATH));
        }

        // To get setting's default value
        public final E getDefaultValue() {
            return DEFAULT_VALUE;
        }

        public final String getPath() {
            return PATH;
        }

        public void set(E value) {
            this.value = value;
        }

        public E get() {
            return value;
        }

        public String getSaveValue() {
            return value.toString();
        }

    }

}
