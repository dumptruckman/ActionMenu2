package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.MenuView;
import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.LinkedHashSet;
import java.util.Set;

public class DefaultHandle implements MenuHandle {

    private Plugin plugin;
    private Menu menu;
    private Set<MenuView> views = new LinkedHashSet<MenuView>();

    protected DefaultHandle(Plugin p, final Menu m) {
        this(p, m, null);
    }

    protected DefaultHandle(Plugin p, final Menu m, final MenuView v) {
        if (m == null) {
            throw new IllegalArgumentException("menu may not be null!");
        }
        this.plugin = p;
        this.menu = m;
        if (v != null) {
            v.setMenu(this.menu);
            this.views.add(v);
        }
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
    public final void setMenu(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("menu may not be null!");
        }
        this.menu = menu;
        for (MenuView view : this.getViews()) {
            view.setMenu(menu);
        }
    }

    @Override
    public final Menu getMenu() {
        return this.menu;
    }

    private Set<MenuView> getViews() {
        return this.views;
    }

    @Override
    public boolean addView(MenuView view) {
        if (view == null) {
            throw new IllegalArgumentException("view may not be null!");
        }
        if (this.getViews().add(view)) {
            view.setMenu(this.getMenu());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeView(MenuView view) {
        return this.getViews().remove(view);
    }

    @Override
    public final void touch(final Player player) {
        this.getMenu().touch(player);
    }
    
    @Override
    public final Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public final Player getPlayer() {
        return this.getMenu().getPlayer();
    }

    @Override
    public final void updateViews(final Player player) {
        for (MenuView view : this.getViews()) {
            view.updateViews(player);
        }
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
    public final Set<MenuListener> getMenuListeners() {
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
