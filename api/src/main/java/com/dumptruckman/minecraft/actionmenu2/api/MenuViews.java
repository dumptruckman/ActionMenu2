package com.dumptruckman.minecraft.actionmenu2.api;

import java.util.Iterator;

public interface MenuViews extends Iterable<MenuView> {

    boolean add(MenuView view);

    boolean remove(MenuView view);

    void update(MenuUser player);

    @Override
    Iterator<MenuView> iterator();
}
