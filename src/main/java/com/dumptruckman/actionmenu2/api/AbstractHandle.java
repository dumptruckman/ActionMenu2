package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractHandle implements MenuHandle {

    protected Plugin plugin;
    protected Menu menu;
    protected Set<MenuView> views = new LinkedHashSet<MenuView>();

    protected AbstractHandle(Plugin p, final Menu m) {
        this(p, m, null);
    }

    protected AbstractHandle(Plugin p, final Menu m, final MenuView v) {
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
    public void cycleMenu() {
        this.cycleMenu(false);
    }

    @Override
    public abstract void cycleMenu(final boolean reverse);

    @Override
    public void setMenu(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("menu may not be null!");
        }
        this.menu = menu;
        for (MenuView view : this.getViews()) {
            view.setMenu(menu);
        }
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public Set<MenuView> getViews() {
        return Collections.unmodifiableSet(this.views);
    }

    @Override
    public boolean addView(MenuView view) {
        if (view == null) {
            throw new IllegalArgumentException("view may not be null!");
        }
        if (this.views.add(view)) {
            view.setMenu(this.getMenu());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeView(MenuView view) {
        return this.views.remove(view);
    }

    @Override
    public void touch(final Player player) {
        this.getMenu().touch(player);
    }

    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public Player getPlayer() {
        return this.getMenu().getPlayer();
    }

    @Override
    public void show(Player player) {
        for (MenuView view : this.getViews()) {
            view.show(player);
        }
    }

    @Override
    public void updateViews(final Player player) {
        for (MenuView view : this.getViews()) {
            view.updateViews(player);
        }
    }

    @Override
    public MenuContents getContents() {
        return this.getMenu().getContents();
    }

    @Override
    public MenuItem getSelected() {
        return this.getMenu().getSelected();
    }

    @Override
    public Set<MenuListener> getMenuListeners() {
        return this.getMenu().getMenuListeners();
    }

    @Override
    public void run(final MenuItem item) {
        this.getMenu().run(item);
    }

    @Override
    public void runSelected() {
        this.getMenu().runSelected();
    }
}