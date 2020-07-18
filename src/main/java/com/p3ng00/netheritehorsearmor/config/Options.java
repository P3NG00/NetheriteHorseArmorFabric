package com.p3ng00.netheritehorsearmor.config;

public abstract class Options {

    public static final class StringOption extends Option<String> {

        public StringOption(String defaultValue, String propertyName) {
            super(defaultValue, propertyName);
        }

        @Override
        public String parse(String s) {
            return s;
        }
    }

    public static final class IntegerOption extends Option<Integer> {

        public IntegerOption(int defaultValue, String propertyName) {
            super(defaultValue, propertyName);
        }

        @Override
        public Integer parse(String s) {
            return Integer.parseInt(s);
        }
    }

    public static final class FloatOption extends Option<Float> {

        public FloatOption(float defaultValue, String propertyName) {
            super(defaultValue, propertyName);
        }

        @Override
        public Float parse(String s) {
            return Float.parseFloat(s);
        }
    }

    public static final class DoubleOption extends Option<Double> {

        public DoubleOption(double defaultValue, String propertyName) {
            super(defaultValue, propertyName);
        }

        @Override
        public Double parse(String s) {
            return Double.parseDouble(s);
        }
    }

    public static final class BooleanOption extends Option<Boolean> {

        public BooleanOption(boolean defaultValue, String propertyName) {
            super(defaultValue, propertyName);
        }

        @Override
        public Boolean parse(String s) {
            return Boolean.parseBoolean(s);
        }
    }
}
