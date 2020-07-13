package com.p3ng00.netheritehorsearmor;

import com.p3ng00.netheritehorsearmor.item.HorseArmorItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.*;
import java.util.Properties;

public class NetheriteHorseArmor implements ModInitializer {

    public static final String MODID = "netheritehorsearmor";   // Mod ID

    // Netherite Horse Armor Item
    public static final Identifier NETHERITE_HORSE_ARMOR_ID = new Identifier(MODID, "netherite_horse_armor");
    public static final Item NETHERITE_HORSE_ARMOR = new HorseArmorItem(15, "netherite", new Item.Settings().maxCount(1).group(ItemGroup.MISC).fireproof());

    @Override
    public void onInitialize() {

        // Register Item
        Registry.register(Registry.ITEM, new Identifier(MODID, NETHERITE_HORSE_ARMOR_ID.getPath()), NETHERITE_HORSE_ARMOR);

        // Add Netherite Horse Armor to loot tables...
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
            switch (identifier.toString()) {
                case "minecraft:chests/bastion_treasure":   // Minecraft's Bation Treasure
                    fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(Config.OPTION_BASTION_TREASURE_AMOUNT.value, Config.OPTION_BASTION_TREASURE_CHANCE.value)).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                    break;
                case "minecraft:chests/ruined_portal":      // Minecraft's Ruined Portal
                    fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(Config.OPTION_RUINED_PORTAL_AMOUNT.value, Config.OPTION_RUINED_PORTAL_CHANCE.value)).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                    break;
            }
        }));
    }
}
