package com.dumptruckman.minecraft.actionmenu2.api;

import org.bukkit.entity.Player;

import java.util.Iterator;

public interface MenuViews extends Iterable<MenuView> {

    boolean add(MenuView view);

    boolean remove(MenuView view);

    void update(Player player);

    @Override
    Iterator<MenuView> iterator();
}
