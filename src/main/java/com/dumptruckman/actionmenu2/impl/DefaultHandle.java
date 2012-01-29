package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.MenuListener;
import com.dumptruckman.actionmenu2.api.view.MenuView;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class DefaultHandle implements MenuHandle {
    
    Menu menu;
    MenuView view;

    public DefaultHandle(Menu menu, MenuView view) {
        this.menu = menu;
        this.view = view;
    }

    @Override
    public void cycleMenu() {
        this.cycleMenu(false);
    }

    @Override
    public void cycleMenu(Boolean reverse) {
        MenuContents contents = this.getMenu().getContents();
        int index = contents.getSelectedIndex();
        if (reverse) {
            index--;
        } else {
            index++;
        }
        if (contents.isEmpty()) {
            System.out.println("Menu item, selecting nothing");
            index = -1;
        } else if (index >= contents.size()) {
            System.out.println("reached end of menu, going to beginning.");
            index = 0;
        } else if (index < 0) {
            System.out.println("reached beginning of menu, going to end");
            index = contents.size() - 1;
        }
        contents.setSelectedIndex(index);
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public MenuView getView() {
        return this.view;
    }

    @Override
    public void setSender(CommandSender sender) {
        this.getMenu().setSender(sender);
    }

    @Override
    public CommandSender getSender() {
        return this.getMenu().getSender();
    }

    @Override
    public void updateView(Plugin plugin, CommandSender sender) {
        this.getView().updateView(plugin, sender);
    }

    @Override
    public MenuContents<MenuItem> getContents() {
        return this.getMenu().getContents();
    }

    @Override
    public MenuItem getSelected() {
        return this.getMenu().getSelected();
    }

    @Override
    public List<MenuListener> getListeners() {
        return this.getMenu().getListeners();
    }

    @Override
    public void run(MenuItem item) {
        this.getMenu().run(item);
    }

    @Override
    public void runSelected() {
        this.getMenu().runSelected();
    }
}
