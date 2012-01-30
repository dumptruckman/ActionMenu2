package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.MenuItemEvent;
import com.dumptruckman.actionmenu2.api.event.MenuItemListener;
import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.LinkedHashSet;
import java.util.Set;

public class DefaultMenu implements Menu {

    private MenuContents<MenuItem> contents;
    private Set<MenuListener> listeners = new LinkedHashSet<MenuListener>();
    private Plugin plugin = null;
    private Player player = null;

    protected DefaultMenu(Plugin plugin, final MenuContents<MenuItem> c) {
        this.plugin = plugin;
        this.contents = c;
    }

    protected DefaultMenu(Plugin plugin) {
        this(plugin, new DefaultContents<MenuItem>());
    }

    @Override
    public MenuContents<MenuItem> getContents() {
        return this.contents;
    }

    @Override
    public MenuItem getSelected() {
        return this.getContents().get(this.getContents().getSelectedIndex());
    }

    @Override
    public Set<MenuListener> getMenuListeners() {
        return this.listeners;
    }
    
    public void run(MenuItem item) {
        for (MenuItemListener listener : item.getMenuItemListeners()) {
            listener.onAction(new MenuItemEvent(this, this.getPlayer(), item));
        }
    }
    
    public void runSelected() {
        this.run(this.getSelected());
    }

    @Override
    public void touch(Player player) {
        this.player = player;
    }
    
    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
