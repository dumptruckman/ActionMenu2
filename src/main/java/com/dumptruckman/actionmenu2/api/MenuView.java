package com.dumptruckman.actionmenu2.api;

import org.bukkit.entity.Player;

public interface MenuView extends MenuContainer, MenuInterface, ViewUpdater {
    
    void show(Player player);
}
