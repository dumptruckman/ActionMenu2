package com.dumptruckman.actionmenu2.api;

import org.bukkit.command.CommandSender;

public interface MenuView extends MenuInterface, ViewUpdater {

    void setMenu(Menu menu);
    
    Menu getMenu();
    
    void show(CommandSender sender);
}
