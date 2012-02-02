package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuView;
import com.dumptruckman.actionmenu2.api.MenuViews;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class DefaultMenuViews implements MenuViews {

    private Set<MenuView> views = new LinkedHashSet<MenuView>();

    @Override
    public boolean add(MenuView view) {
        if (view == null) {
            throw new IllegalArgumentException("view may not be null!");
        }
        return this.views.add(view);
    }

    @Override
    public boolean remove(MenuView view) {
        return this.views.remove(view);
    }

    @Override
    public void update(Plugin plugin, Menu menu, Player player) {
        for (MenuView view : this.views) {
            view.update(plugin, menu, player);
        }
    }

    @Override
    public Iterator<MenuView> iterator() {
        return this.views.iterator();
    }
}
