package com.dumptruckman.actionmenu2.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class AbstractView implements MenuView {

    protected Plugin plugin;
    protected Menu menu = null;

    public AbstractView(Plugin plugin) {
        this(plugin, null);
    }
    
    public AbstractView(Plugin plugin, Menu menu) {
        this.plugin = plugin;
        this.menu = menu;
    }

    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public void touch(Player player) {
        if (this.getMenu() != null) {
            this.getMenu().touch(player);
        }
    }
    
    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public Player getPlayer() {
        if (this.getMenu() == null) {
            return null;
        }
        return this.getMenu().getPlayer();
    }
    
    @Override
    public abstract void show(Player player);

    @Override
    public void updateViews(final Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable() {
            @Override
            public void run() {
                updateView(player);
            }
        });
    }
    
    protected abstract void updateView(Player player);
}
