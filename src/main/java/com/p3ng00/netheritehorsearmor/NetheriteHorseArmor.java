package com.p3ng00.netheritehorsearmor;

import com.p3ng00.netheritehorsearmor.item.HorseArmorItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetheriteHorseArmor implements ModInitializer {

    public static final String MODID = "netheritehorsearmor"; // Mod ID

    public static final Item NETHERITE_HORSE_ARMOR = new HorseArmorItem(15, "netherite", new Item.Settings().maxCount(1).group(ItemGroup.MISC).fireproof());

    @Override
    public void onInitialize() {

        // Register Item
        Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_horse_armor"), NETHERITE_HORSE_ARMOR);

        // Add Item to Bastion Treasure loot table
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
            if (identifier.toString().equals("minecraft:chests/bastion_treasure")) {
                fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(3, 0.25f)).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
            }
        }));
    }
}
