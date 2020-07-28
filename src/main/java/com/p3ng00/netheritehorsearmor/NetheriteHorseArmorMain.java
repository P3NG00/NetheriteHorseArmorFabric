package com.p3ng00.netheritehorsearmor;

import com.p3ng00.netheritehorsearmor.item.NetheriteHorseArmorItem;
import com.p3ng00.p3utils.config.Config;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.p3ng00.netheritehorsearmor.Util.*;

public class NetheriteHorseArmorMain implements ModInitializer {

    // Mod ID
    public static final String MODID = "netheritehorsearmor";

    // Netherite Horse Armor Item
    public static final Item NETHERITE_HORSE_ARMOR = new NetheriteHorseArmorItem();

    // Config
    public static final Config CONFIG = new Config("Netherite Horse Armor", "netherite_horse_armor.txt", OPTIONS);

    @Override
    public void onInitialize() {

        // Register Item
        Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_horse_armor"), NETHERITE_HORSE_ARMOR);

        // Add Netherite Horse Armor to loot tables...
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
            switch (identifier.toString()) {
                case "minecraft:chests/bastion_treasure":   // Minecraft's Bation Treasure
                    fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(OPTION_BASTION_TREASURE_AMOUNT.get(), OPTION_BASTION_TREASURE_CHANCE.get())).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                    break;
                case "minecraft:chests/ruined_portal":      // Minecraft's Ruined Portal
                    fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(OPTION_RUINED_PORTAL_AMOUNT.get(), OPTION_RUINED_PORTAL_CHANCE.get())).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                    break;
            }
        }));
    }
}
