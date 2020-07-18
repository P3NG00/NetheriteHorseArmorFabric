package com.p3ng00.netheritehorsearmor.config;

public abstract class Option<E> {

    public final E defaultValue;
    public E value;
    public final String path;

    public Option(E defaultValue, String propertyName) {
        this.defaultValue = defaultValue;
        value = defaultValue;
        path = propertyName;
    }

    public final void set(E value) {
        this.value = value;
        Config.PROPERTIES.setProperty(path, value.toString());
    }

    public final void load() {
        value = parse(Config.PROPERTIES.getProperty(path));
    }

    public abstract E parse(String s);
}
