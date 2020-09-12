package com.p3ng00.netheritehorsearmor;

import com.p3ng00.netheritehorsearmor.item.HorseArmorItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import org.apache.logging.log4j.Level;

import static com.p3ng00.netheritehorsearmor.Settings.*;
import static jdk.nashorn.internal.objects.NativeMath.log;

public class NetheriteHorseArmor implements ModInitializer {

    // Util
    public static P3Util UTIL;

    // Items
    public static Item NETHERITE_HORSE_ARMOR;
    public static Item ENDERITE_HORSE_ARMOR;

    // Compatibility
    public static boolean isEnderiteModLoaded;

    @Override
    public void onInitialize() {

        // Initialize Utilities
        UTIL = new P3Util("Netherite Horse Armor", "netheritehorsearmor");

        UTIL.log(Level.INFO, "Beginning initialization...");

        // Get Compatibility
        isEnderiteModLoaded = FabricLoader.getInstance().isModLoaded("enderitemod");

        // Create Items
        NETHERITE_HORSE_ARMOR = new HorseArmorItem(15, "netherite");
        ENDERITE_HORSE_ARMOR = new HorseArmorItem(20, "enderite");

        // Register Items
        UTIL.register("netherite_horse_armor", NETHERITE_HORSE_ARMOR);
        UTIL.register("enderite_horse_armor", ENDERITE_HORSE_ARMOR);

        // Warn about possible errors if mod not installed
        if (!isEnderiteModLoaded)
            log(Level.WARN, "'Enderite Mod' not installed. Ignore following errors relating to 'Enderite Mod'");

        // Add Netherite Horse Armor to loot tables...
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {

            switch (identifier.toString()) {

                // Minecraft's Bastion Treasure
                case "minecraft:chests/bastion_treasure":
                    fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(OPTION_BASTION_TREASURE_AMOUNT.get(), OPTION_BASTION_TREASURE_CHANCE.get())).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                    break;

                // Minecraft's Ruined Portal
                case "minecraft:chests/ruined_portal":
                    fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(OPTION_RUINED_PORTAL_AMOUNT.get(), OPTION_RUINED_PORTAL_CHANCE.get())).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                    break;

            }

        }));

        log(Level.INFO, "Finished initialization!");

    }

}
