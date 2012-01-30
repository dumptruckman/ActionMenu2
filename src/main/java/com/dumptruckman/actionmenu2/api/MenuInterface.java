package com.dumptruckman.actionmenu2.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface MenuInterface {
    
    void touch(Player player);

    Plugin getPlugin();

    Player getPlayer();
}
