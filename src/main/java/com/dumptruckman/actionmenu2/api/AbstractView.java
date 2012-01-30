package com.dumptruckman.actionmenu2.api;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public abstract class AbstractView implements MenuView {

    private Menu menu = null;

    @Override
    public final void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public final Menu getMenu() {
        return this.menu;
    }

    @Override
    public final void setSender(CommandSender sender) {
        this.getMenu().setSender(sender);
    }

    @Override
    public final CommandSender getSender() {
        return null;
    }
    
    @Override
    public abstract void show(CommandSender sender);

    @Override
    public void updateView(Plugin plugin, final CommandSender sender) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                updateView(sender);
            }
        });
    }
    
    protected abstract void updateView(CommandSender sender);
}
