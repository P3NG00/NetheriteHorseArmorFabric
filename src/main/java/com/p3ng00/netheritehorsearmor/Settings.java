package com.p3ng00.netheritehorsearmor;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;

public class Settings {

    // Config
    public static final Util.Option<Boolean> OPTION_NETHERITE_BURN_RESIST_HORSE = new Util.Option.BooleanOption("netheriteBurnResistHorse", true);
    public static final Util.Option<Boolean> OPTION_NETHERITE_BURN_RESIST_PLAYER = new Util.Option.BooleanOption("netheriteBurnResistPlayer", true);
    public static final Util.Option<Integer> OPTION_BASTION_TREASURE_AMOUNT = new Util.Option.IntegerOption("bastionTreasureAmount", 3);
    public static final Util.Option<Float> OPTION_BASTION_TREASURE_CHANCE = new Util.Option.FloatOption("bastionTreasureChance", 0.25f);
    public static final Util.Option<Integer> OPTION_RUINED_PORTAL_AMOUNT = new Util.Option.IntegerOption("ruinedPortalAmount", 2);
    public static final Util.Option<Float> OPTION_RUINED_PORTAL_CHANCE = new Util.Option.FloatOption("ruinedPortalChance", 0.1f);

    public static final Util.Option<?>[] OPTIONS = {OPTION_NETHERITE_BURN_RESIST_HORSE, OPTION_NETHERITE_BURN_RESIST_PLAYER, OPTION_BASTION_TREASURE_AMOUNT, OPTION_BASTION_TREASURE_CHANCE, OPTION_RUINED_PORTAL_AMOUNT, OPTION_RUINED_PORTAL_CHANCE};

    public static final Util.Config CONFIG = new Util.Config("Netherite Horse Armor", "netherite_horse_armor.txt", OPTIONS);

    public static final Map<Item, Integer> NETHERITE_ARMOR_STAT_TABLE = new HashMap<>();

    static {

        NETHERITE_ARMOR_STAT_TABLE.put(Items.NETHERITE_HELMET, 3);
        NETHERITE_ARMOR_STAT_TABLE.put(Items.NETHERITE_CHESTPLATE, 5);
        NETHERITE_ARMOR_STAT_TABLE.put(Items.NETHERITE_LEGGINGS, 4);
        NETHERITE_ARMOR_STAT_TABLE.put(Items.NETHERITE_BOOTS, 3);

    }
}
