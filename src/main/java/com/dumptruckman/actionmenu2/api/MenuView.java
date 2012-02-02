package com.dumptruckman.actionmenu2.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface MenuView {

    void showMenu(Menu menu, Player player);

    void update(Plugin plugin, Menu menu, Player player);
}
