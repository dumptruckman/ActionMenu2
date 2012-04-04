package com.dumptruckman.minecraft.actionmenu2.impl;

import com.dumptruckman.minecraft.actionmenu2.api.Menu;
import com.dumptruckman.minecraft.actionmenu2.api.MenuPlugin;
import com.dumptruckman.minecraft.actionmenu2.api.MenuUser;
import com.dumptruckman.minecraft.actionmenu2.api.MenuView;
import com.dumptruckman.minecraft.actionmenu2.api.MenuViews;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class DefaultMenuViews implements MenuViews {

    private Set<MenuView> views = new LinkedHashSet<MenuView>();
    private MenuPlugin plugin;
    private Menu menu;
    
    public DefaultMenuViews(MenuPlugin plugin, Menu menu) {
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
    public void update(MenuUser player) {
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
