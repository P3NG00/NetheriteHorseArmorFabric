package com.p3ng00.netheritehorsearmor.settings;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

public class P3Util {

    private final String modName;
    private final String modId;

    public P3Util(String modName, String modId) {
        this.modName = modName;
        this.modId = modId;
    }

    public String getModName() {
        return modName;
    }

    public String getModId() {
        return modId;
    }

    public void register(String name, ItemConvertible item) {

        Identifier id = new Identifier(modId, name);

        if (item instanceof Item) {

            Registry.register(Registry.ITEM, id, (Item)item);

        } else if (item instanceof Block) {

            Registry.register(Registry.BLOCK, id, (Block)item);

        } else {

            throw new IllegalArgumentException("Can only use this method for ITEMs or BLOCKs");

        }

    }

    public void log(Level level, String message) {
        LogManager.getLogger().log(level, String.format("[%s] %s", modName, message));
    }

}
