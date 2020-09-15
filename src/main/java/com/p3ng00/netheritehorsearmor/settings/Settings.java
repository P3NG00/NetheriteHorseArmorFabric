package com.p3ng00.netheritehorsearmor.settings;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;

public class Settings {

    // Config
    public static final Option<Boolean> OPTION_NETHERITE_BURN_RESIST_HORSE = new Option<>("netheriteBurnResistHorse", true, Boolean::parseBoolean);
    public static final Option<Boolean> OPTION_NETHERITE_BURN_RESIST_PLAYER = new Option<>("netheriteBurnResistPlayer", true, Boolean::parseBoolean);
    public static final Option<Integer> OPTION_BASTION_TREASURE_AMOUNT = new Option<>("bastionTreasureAmount", 3, Integer::parseInt);
    public static final Option<Float> OPTION_BASTION_TREASURE_CHANCE = new Option<>("bastionTreasureChance", 0.25f, Float::parseFloat);
    public static final Option<Integer> OPTION_RUINED_PORTAL_AMOUNT = new Option<>("ruinedPortalAmount", 2, Integer::parseInt);
    public static final Option<Float> OPTION_RUINED_PORTAL_CHANCE = new Option<>("ruinedPortalChance", 0.1f, Float::parseFloat);

    public static final Option<?>[] OPTIONS = {OPTION_NETHERITE_BURN_RESIST_HORSE, OPTION_NETHERITE_BURN_RESIST_PLAYER, OPTION_BASTION_TREASURE_AMOUNT, OPTION_BASTION_TREASURE_CHANCE, OPTION_RUINED_PORTAL_AMOUNT, OPTION_RUINED_PORTAL_CHANCE};

    public static final Config CONFIG = new Config("Netherite Horse Armor", "netherite_horse_armor.txt", OPTIONS);

    public static final Map<Item, Integer> NETHERITE_ARMOR_STAT_TABLE = new HashMap<>();

    static {

        NETHERITE_ARMOR_STAT_TABLE.put(Items.NETHERITE_HELMET, 3);
        NETHERITE_ARMOR_STAT_TABLE.put(Items.NETHERITE_CHESTPLATE, 5);
        NETHERITE_ARMOR_STAT_TABLE.put(Items.NETHERITE_LEGGINGS, 4);
        NETHERITE_ARMOR_STAT_TABLE.put(Items.NETHERITE_BOOTS, 3);

    }
}
