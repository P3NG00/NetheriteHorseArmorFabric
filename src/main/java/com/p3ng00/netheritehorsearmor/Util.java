package com.p3ng00.netheritehorsearmor;

import com.p3ng00.p3utils.config.Option;
import com.p3ng00.p3utils.config.Options;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Dictionary;
import java.util.Hashtable;

public abstract class Util {

    // Config
    public static final Option<Boolean> OPTION_NETHERITE_FIRE_RESIST_HORSE = new Options.BooleanOption(true, "netheriteFireResistHorse");
    public static final Option<Boolean> OPTION_NETHERITE_FIRE_RESIST_PLAYER = new Options.BooleanOption(true, "netheriteFireResistPlayer");
    public static final Option<Integer> OPTION_BASTION_TREASURE_AMOUNT = new Options.IntegerOption(3, "bastionTreasureAmount");
    public static final Option<Float> OPTION_BASTION_TREASURE_CHANCE = new Options.FloatOption(0.25f, "bastionTreasureChance");
    public static final Option<Integer> OPTION_RUINED_PORTAL_AMOUNT = new Options.IntegerOption(2, "ruinedPortalAmount");
    public static final Option<Float> OPTION_RUINED_PORTAL_CHANCE = new Options.FloatOption(0.1f, "ruinedPortalChance");

    public static final Option<?>[] OPTIONS = {
            OPTION_NETHERITE_FIRE_RESIST_HORSE,
            OPTION_NETHERITE_FIRE_RESIST_PLAYER,
            OPTION_BASTION_TREASURE_AMOUNT,
            OPTION_BASTION_TREASURE_CHANCE,
            OPTION_RUINED_PORTAL_AMOUNT,
            OPTION_RUINED_PORTAL_CHANCE
    };

    public static final Dictionary<Item, Integer> NETHERITE_ARMOR_EFFECT_LENGTH_TABLE = new Hashtable<>();

    static {
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_HELMET, 3);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_CHESTPLATE, 5);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_LEGGINGS, 4);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_BOOTS, 3);
    }
}
