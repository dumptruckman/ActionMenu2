package com.dumptruckman.actionmenu2.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class AbstractView implements MenuView {

    private Plugin plugin;
    private Menu menu = null;

    public AbstractView(Plugin plugin) {
        this(plugin, null);
    }
    
    public AbstractView(Plugin plugin, Menu menu) {
        this.plugin = plugin;
        this.menu = menu;
    }

    @Override
    public final void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public final Menu getMenu() {
        return this.menu;
    }

    @Override
    public final void touch(Player player) {
        if (this.getMenu() != null) {
            this.getMenu().touch(player);
        }
    }
    
    @Override
    public final Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public final Player getPlayer() {
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
                updateViews(player);
            }
        });
    }
    
    protected abstract void updateView(Player player);
}
