package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.MenuItemEvent;
import com.dumptruckman.actionmenu2.api.event.MenuItemListener;
import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class DefaultMenu implements Menu {

    private MenuContents<MenuItem> contents;
    private List<MenuListener> listeners = new ArrayList<MenuListener>();
    private CommandSender sender = null;

    public DefaultMenu(final MenuContents<MenuItem> c) {
        this.contents = c;
    }

    public DefaultMenu() {
        this(new DefaultContents<MenuItem>());
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
    public List<MenuListener> getMenuListeners() {
        return this.listeners;
    }
    
    public void run(MenuItem item) {
        for (MenuItemListener listener : item.getMenuItemListeners()) {
            listener.onAction(new MenuItemEvent(this, this.getSender(), item));
        }
    }
    
    public void runSelected() {
        this.run(this.getSelected());
    }

    @Override
    public void setSender(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public CommandSender getSender() {
        return this.sender;
    }
}
