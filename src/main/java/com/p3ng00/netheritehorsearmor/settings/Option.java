package com.p3ng00.netheritehorsearmor.settings;


import java.util.Properties;
import java.util.function.Function;

public class Option<E> {

    private final String path;
    private final Function<String, E> parser;
    private final E defaultValue;
    private E value;

    public Option(String propertyName, E defaultValue, Function<String, E> parser) {
        path = propertyName;
        this.parser = parser;
        this.defaultValue = defaultValue;
        value = defaultValue;
    }

    // To load setting's saved value
    public final void load(Properties properties) {
        value = parser.apply(properties.getProperty(path));
    }

    // To get setting's default value
    public final E getDefaultValue() {
        return defaultValue;
    }

    public final String getPath() {
        return path;
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