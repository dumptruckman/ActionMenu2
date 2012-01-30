package com.dumptruckman.actionmenu2.api;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public interface ViewUpdater {
    
    void updateView(Plugin plugin, CommandSender sender);
}
