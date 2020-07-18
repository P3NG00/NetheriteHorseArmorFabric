package com.p3ng00.netheritehorsearmor.util;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Dictionary;
import java.util.Hashtable;

public class Util {

    public static final Dictionary<Item, Integer> NETHERITE_ARMOR_EFFECT_LENGTH_TABLE = new Hashtable<>();

    static {
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_HELMET, 3);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_CHESTPLATE, 5);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_LEGGINGS, 4);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_BOOTS, 3);
    }
}
