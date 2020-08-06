package com.p3ng00.netheritehorsearmor;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

public abstract class Util {

    public static String modId = null;

    public static void register(String name, ItemConvertible item) {

        if (modId == null) throw new IllegalStateException("Set 'P3Util.modId' to your Mod ID before registering items/blocks!");

        Identifier id = new Identifier(modId, name);

        if (item instanceof Item) {

            Registry.register(Registry.ITEM, id, (Item)item);

        } else if (item instanceof Block) {

            Registry.register(Registry.BLOCK, id, (Block)item);

        } else {

            throw new IllegalArgumentException("Can only use this method for ITEMs or BLOCKs");

        }

    }

    public static TranslatableText createTranslatableText(String category, String path) {
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

            try {

                FileWriter writer = new FileWriter(FILE, false);
                writer.write(String.format("# %s by P3NG00\n", MOD_NAME));
                String saveValue;

                for (Option<?> option : OPTIONS) {

                    saveValue = option.getSaveValue();
                    writer.write(String.format("%s=%s\n", option.getPath(), saveValue));
                    PROPERTIES.setProperty(option.getPath(), saveValue);

                }

                writer.close();

            } catch (IOException e) {

                System.out.printf("IO Error: Couldn't save settings for %s\n", MOD_NAME);

            }

        }

    }

    public static abstract class Option<E> {

        private final Function<?, E> defaultValueFunc;
        private E value;
        private final String path;

        public Option(String propertyName, Function<?, E> defaultValueFunc) {
            this.defaultValueFunc = defaultValueFunc;
            value = defaultValueFunc.apply(null);
            path = propertyName;
        }

        public Option(String propertyName, E defaultValue) {
            this(propertyName, f -> defaultValue);
        }

        public final void load(Properties properties) {
            value = parse(properties.getProperty(path));
        }

        public final E getDefaultValue() {
            return defaultValueFunc.apply(null);
        }

        public final String getPath() {
            return path;
        }

        public abstract E parse(String s);

        public void set(E value) {
            this.value = value;
        }

        public E get() {
            return value;
        }

        public String getSaveValue() {
            return value.toString();
        }

        public static final class BooleanOption extends Option<Boolean> {

            public BooleanOption(String propertyName, Function<?, Boolean> defaultValueFunc) {
                super(propertyName, defaultValueFunc);
            }

            public BooleanOption(String propertyName, Boolean defaultValue) {
                super(propertyName, defaultValue);
            }

            @Override
            public Boolean parse(String s) {
                return Boolean.parseBoolean(s);
            }

        }

        public static final class DoubleOption extends Option<Double> {

            public DoubleOption(String propertyName, Function<?, Double> defaultValueFunc) {
                super(propertyName, defaultValueFunc);
            }

            public DoubleOption(String propertyName, Double defaultValue) {
                super(propertyName, defaultValue);
            }

            @Override
            public Double parse(String s) {
                return Double.parseDouble(s);
            }

        }

        public static class FloatOption extends Option<Float> {

            public FloatOption(String propertyName, Function<?, Float> defaultValueFunc) {
                super(propertyName, defaultValueFunc);
            }

            public FloatOption(String propertyName, Float defaultValue) {
                super(propertyName, defaultValue);
            }

            @Override
            public Float parse(String s) {
                return Float.parseFloat(s);
            }

        }

        public static class IntegerOption extends Option<Integer> {

            public IntegerOption(String propertyName, Function<?, Integer> defaultValueFunc) {
                super(propertyName, defaultValueFunc);
            }

            public IntegerOption(String propertyName, Integer defaultValue) {
                super(propertyName, defaultValue);
            }

            @Override
            public Integer parse(String s) {
                return Integer.parseInt(s);
            }

        }

        public static class StringListOption extends Option<List<String>> {

            public StringListOption(String propertyName, Function<?, List<String>> defaultValueFunc) {
                super(propertyName, defaultValueFunc);
            }

            public StringListOption(String propertyName, List<String> defaultValue) {
                super(propertyName, defaultValue);
            }

            @Override
            public List<String> parse(String s) {
                return s.isEmpty() ? getDefaultValue() : Arrays.asList(s.split(","));
            }

            @Override
            public String getSaveValue() {

                boolean first = true;
                StringBuilder sb = new StringBuilder();

                for (String s : get()) {

                    if (first) first = false;
                    else sb.append(",");

                    sb.append(s);

                }

                return sb.toString();

            }

        }

        public static class StringOption extends Option<String> {

            public StringOption(String propertyName, Function<?, String> defaultFunctionValue) {
                super(propertyName, defaultFunctionValue);
            }

            public StringOption(String propertyName, String defaultValue) {
                super(propertyName, defaultValue);
            }

            @Override
            public String parse(String s) {
                return s;
            }

        }

    }

}
