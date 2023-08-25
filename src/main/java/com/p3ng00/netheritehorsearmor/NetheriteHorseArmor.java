package com.p3ng00.netheritehorsearmor;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.List;

import static com.p3ng00.netheritehorsearmor.settings.Settings.*;

public class NetheriteHorseArmor implements ModInitializer {

    public static final String MODID = "netheritehorsearmor";

    // Items
    public static Item NETHERITE_HORSE_ARMOR;
    public static Item ENDERITE_HORSE_ARMOR;

    // Compatibility
    public static boolean isEnderiteModLoaded;

    @Override
    public void onInitialize() {

        log(Level.INFO, "Beginning initialization...");

        // Get Compatibility
        isEnderiteModLoaded = FabricLoader.getInstance().isModLoaded("enderitemod");

        // Create Items
        NETHERITE_HORSE_ARMOR = new HorseArmorItem(15, "netherite");
        ENDERITE_HORSE_ARMOR = new HorseArmorItem(20, "enderite");

        // Register Items
        Registry.register(Registries.ITEM, new Identifier(MODID, "netherite_horse_armor"), NETHERITE_HORSE_ARMOR);
        Registry.register(Registries.ITEM, new Identifier(MODID, "enderite_horse_armor"), ENDERITE_HORSE_ARMOR);

        // Warn about possible errors if mod not installed
        if (!isEnderiteModLoaded)
            log(Level.WARN, "'Enderite Mod' not installed. Ignore following errors relating to 'Enderite Mod'");

        // Add Netherite Horse Armor to loot tables...
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (!source.isBuiltin()) return;

            switch (id.toString()) {

                // Minecraft's Bastion Treasure
                case "minecraft:chests/bastion_treasure" -> {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(NETHERITE_HORSE_ARMOR))
                            .rolls(BinomialLootNumberProvider.create(
                                    OPTION_BASTION_TREASURE_AMOUNT.get(),
                                    OPTION_BASTION_TREASURE_CHANCE.get()));
                    tableBuilder.pool(poolBuilder);
                    // fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(OPTION_BASTION_TREASURE_AMOUNT.get(), OPTION_BASTION_TREASURE_CHANCE.get())).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                }

                // Minecraft's Ruined Portal
                case "minecraft:chests/ruined_portal" -> {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(NETHERITE_HORSE_ARMOR))
                            .rolls(BinomialLootNumberProvider.create(
                                    OPTION_RUINED_PORTAL_AMOUNT.get(),
                                    OPTION_RUINED_PORTAL_CHANCE.get()));
                    tableBuilder.pool(poolBuilder);
                    // fabricLootSupplierBuilder.withPool(FabricLootPoolBuilder.builder().rolls(BinomialLootTableRange.create(OPTION_RUINED_PORTAL_AMOUNT.get(), OPTION_RUINED_PORTAL_CHANCE.get())).withEntry(ItemEntry.builder(NETHERITE_HORSE_ARMOR).build()).build());
                }

            }

        });

        log(Level.INFO, "Finished initialization!");

    }

    public static void log(Level level, String format, Object... args) {
        LogManager.getLogger().log(level, format, args);
    }

    public static MutableText createTranslatableText(String category, String path) {
        return Text.translatable(String.format("%s.%s.%s", category, MODID, path));
    }

    private static class HorseArmorItem extends net.minecraft.item.HorseArmorItem {

        private final String texturePath;

        public HorseArmorItem(int bonus, String materialName) {
            super(bonus, null, new Item.Settings().maxCount(1).fireproof());
            // TODO comment line below to test working
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(this));
            texturePath = String.format("textures/entity/horse/armor/horse_armor_%s.png", materialName);
        }

        @Override
        public Identifier getEntityTexture() {
            return new Identifier(NetheriteHorseArmor.MODID, texturePath);
        }

        @Override
        public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {

            if (NetheriteHorseArmor.isEnderiteModLoaded || asItem() != NetheriteHorseArmor.ENDERITE_HORSE_ARMOR)
                return;

            tooltip.add(createTranslatableText("tooltip", "enderite_mod_missing_0"));
            tooltip.add(createTranslatableText("tooltip", "enderite_mod_missing_1"));

        }

    }

}
