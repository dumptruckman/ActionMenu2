package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.ActionEvent;
import com.dumptruckman.actionmenu2.api.event.ActionListener;
import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class DefaultMenu implements Menu {

    MenuContents<MenuItem> contents;
    List<MenuListener> listeners = new ArrayList<MenuListener>();
    CommandSender sender = null;

    public DefaultMenu(MenuContents<MenuItem> contents) {
        this.contents = contents;
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
    public List<MenuListener> getListeners() {
        return this.listeners;
    }
    
    public void run(MenuItem item) {
        for (ActionListener listener : item.getListeners()) {
            listener.onAction(new ActionEvent(this, this.getSender(), item));
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
