package com.p3ng00.netheritehorsearmor;

import com.p3ng00.netheritehorsearmor.item.HorseArmorItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.loader.util.sat4j.tools.encoding.Binomial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetheriteHorseArmor implements ModInitializer {

    /*

    TODO

    price? 1 nether ingot, 2, 3?

     */

    public static final String MODID = "netheritehorsearmor"; // Mod ID

    public static final Item NETHERITE_HORSE_ARMOR = new HorseArmorItem(15, "netherite", new Item.Settings().maxCount(1).group(ItemGroup.MISC).fireproof());

    @Override
    public void onInitialize() {

        // Register Item
        Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_horse_armor"), NETHERITE_HORSE_ARMOR);

        // Add Item to Bastion Treasure loot table
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
            switch (identifier.toString()) {
                case "minecraft:chests/bastion_treasure":
                    fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(3, 0.25f)).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                    break;
                case "minecraft:chests/ruined_portal":
                    fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(1, 0.5f)).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                    break;
            }
        }));


    }
}
