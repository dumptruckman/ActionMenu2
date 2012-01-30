package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.MenuView;
import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class DefaultHandle implements MenuHandle {

    private Menu menu;
    private MenuView view;

    public DefaultHandle(final Menu m, final MenuView v) {
        this.menu = m;
        this.view = v;
    }

    @Override
    public final void cycleMenu() {
        this.cycleMenu(false);
    }

    @Override
    public final void cycleMenu(final Boolean reverse) {
        MenuContents contents = this.getMenu().getContents();
        int index = contents.getSelectedIndex();
        if (reverse) {
            index--;
        } else {
            index++;
        }
        if (contents.isEmpty()) {
            index = -1;
        } else if (index >= contents.size()) {
            index = 0;
        } else if (index < 0) {
            index = contents.size() - 1;
        }
        contents.setSelectedIndex(index);
    }

    @Override
    public final Menu getMenu() {
        return this.menu;
    }

    @Override
    public final MenuView getView() {
        return this.view;
    }

    @Override
    public final void setSender(final CommandSender sender) {
        this.getMenu().setSender(sender);
    }

    @Override
    public final CommandSender getSender() {
        return this.getMenu().getSender();
    }

    @Override
    public final void updateView(final Plugin plugin,
                                 final CommandSender sender) {
        this.getView().updateView(plugin, sender);
    }

    @Override
    public final MenuContents<MenuItem> getContents() {
        return this.getMenu().getContents();
    }

    @Override
    public final MenuItem getSelected() {
        return this.getMenu().getSelected();
    }

    @Override
    public final List<MenuListener> getMenuListeners() {
        return this.getMenu().getMenuListeners();
    }

    @Override
    public final void run(final MenuItem item) {
        this.getMenu().run(item);
    }

    @Override
    public final void runSelected() {
        this.getMenu().runSelected();
    }
}
