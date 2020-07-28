package com.p3ng00.netheritehorsearmor;

import com.p3ng00.p3utils.config.options.BooleanOption;
import com.p3ng00.p3utils.config.options.FloatOption;
import com.p3ng00.p3utils.config.options.IntegerOption;
import com.p3ng00.p3utils.config.options.Option;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;

public abstract class Util {

    // Config
    public static final Option<Boolean> OPTION_NETHERITE_BURN_RESIST_HORSE = new BooleanOption("netheriteBurnResistHorse", true);
    public static final Option<Boolean> OPTION_NETHERITE_BURN_RESIST_PLAYER = new BooleanOption("netheriteBurnResistPlayer", true);
    public static final Option<Integer> OPTION_BASTION_TREASURE_AMOUNT = new IntegerOption("bastionTreasureAmount", 3);
    public static final Option<Float> OPTION_BASTION_TREASURE_CHANCE = new FloatOption("bastionTreasureChance", 0.25f);
    public static final Option<Integer> OPTION_RUINED_PORTAL_AMOUNT = new IntegerOption("ruinedPortalAmount", 2);
    public static final Option<Float> OPTION_RUINED_PORTAL_CHANCE = new FloatOption("ruinedPortalChance", 0.1f);

    public static final Option<?>[] OPTIONS = {OPTION_NETHERITE_BURN_RESIST_HORSE, OPTION_NETHERITE_BURN_RESIST_PLAYER, OPTION_BASTION_TREASURE_AMOUNT, OPTION_BASTION_TREASURE_CHANCE, OPTION_RUINED_PORTAL_AMOUNT, OPTION_RUINED_PORTAL_CHANCE};

    public static final Map<Item, Integer> NETHERITE_ARMOR_EFFECT_LENGTH_TABLE = new HashMap<>();

    static {
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_HELMET, 3);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_CHESTPLATE, 5);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_LEGGINGS, 4);
        NETHERITE_ARMOR_EFFECT_LENGTH_TABLE.put(Items.NETHERITE_BOOTS, 3);
    }
}
