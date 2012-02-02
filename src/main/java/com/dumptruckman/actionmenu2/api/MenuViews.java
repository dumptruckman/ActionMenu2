package com.dumptruckman.actionmenu2.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Iterator;

public interface MenuViews extends Iterable<MenuView> {

    boolean add(MenuView view);

    boolean remove(MenuView view);

    void update(Plugin plugin, Menu menu, Player player);

    @Override
    Iterator<MenuView> iterator();
}
