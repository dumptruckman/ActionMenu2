package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface MenuView extends MenuListener {

    void showMenu(Menu menu, Player player);

    void update(Plugin plugin, Menu menu, Player player);
}
