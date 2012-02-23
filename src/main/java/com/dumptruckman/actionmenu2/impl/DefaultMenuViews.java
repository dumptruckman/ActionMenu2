package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.MenuView;
import com.dumptruckman.actionmenu2.api.MenuViews;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class DefaultMenuViews implements MenuViews {

    private Set<MenuView> views = new LinkedHashSet<MenuView>();
    private Plugin plugin;
    private Menu menu;
    
    public DefaultMenuViews(Plugin plugin, Menu menu) {
        this.plugin = plugin;
        this.menu = menu;
    }

    @Override
    public boolean add(MenuView view) {
        if (view == null) {
            throw new IllegalArgumentException("view may not be null!");
        }
        boolean added = this.views.add(view);
        if (added) {
            menu.getMenuListeners().add(view);
        }
        return added;
    }

    @Override
    public boolean remove(MenuView view) {
        boolean removed = this.views.remove(view);
        if (removed) {
            menu.getMenuListeners().remove(view);
        }
        return removed;
    }

    @Override
    public void update(Player player) {
        for (MenuView view : this.views) {
            view.update(this.plugin, this.menu, player);
        }
    }

    @Override
    public Iterator<MenuView> iterator() {
        return this.views.iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (MenuView view : this) {
            if (builder.toString().isEmpty()) {
                builder.append("[ ");
            } else {
                builder.append(", ");
            }
            builder.append(view.toString());
        }
        builder.append(" ]");
        return builder.toString();
    }
}
