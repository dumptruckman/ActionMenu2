package com.dumptruckman.minecraft.actionmenu2.impl;

import com.avaje.ebean.EbeanServer;
import com.dumptruckman.minecraft.actionmenu2.api.MenuPlugin;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BukkitPlugin implements Plugin, MenuPlugin {
    
    private static Map<Plugin, BukkitPlugin> pluginMap = new HashMap<Plugin, BukkitPlugin>();
    
    public static BukkitPlugin get(Plugin plugin) {
        if (!pluginMap.containsKey(plugin)) {
            pluginMap.put(plugin, new BukkitPlugin(plugin));
        }
        return pluginMap.get(plugin);
    }
    
    private Plugin plugin;
    
    private BukkitPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public File getDataFolder() {
        return plugin.getDataFolder();
    }

    @Override
    public PluginDescriptionFile getDescription() {
        return plugin.getDescription();
    }

    @Override
    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    @Override
    public InputStream getResource(String s) {
        return plugin.getResource(s);
    }

    @Override
    public void saveConfig() {
        plugin.saveConfig();
    }

    @Override
    public void saveDefaultConfig() {
        plugin.saveDefaultConfig();
    }

    @Override
    public void saveResource(String s, boolean b) {
        plugin.saveResource(s, b);
    }

    @Override
    public void reloadConfig() {
        plugin.reloadConfig();
    }

    @Override
    public PluginLoader getPluginLoader() {
        return plugin.getPluginLoader();
    }

    @Override
    public Server getServer() {
        return plugin.getServer();
    }

    @Override
    public boolean isEnabled() {
        return plugin.isEnabled();
    }

    @Override
    public void onDisable() {
        plugin.onDisable();
    }

    @Override
    public void onLoad() {
        plugin.onLoad();
    }

    @Override
    public void onEnable() {
        plugin.onEnable();
    }

    @Override
    public boolean isNaggable() {
        return plugin.isNaggable();
    }

    @Override
    public void setNaggable(boolean b) {
        plugin.setNaggable(b);
    }

    @Override
    public EbeanServer getDatabase() {
        return plugin.getDatabase();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String s, String s1) {
        return plugin.getDefaultWorldGenerator(s, s1);
    }

    @Override
    public Logger getLogger() {
        return plugin.getLogger();
    }

    @Override
    public String getName() {
        return plugin.getName();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return plugin.onCommand(commandSender, command, s, strings);
    }
}
