package com.dumptruckman.actionmenu2.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class AbstractView implements MenuView {

    @Override
    public abstract void showMenu(Menu menu, Player player);

    @Override
    public void update(Plugin plugin, final Menu menu, final Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                updateView(menu, player);
            }
        });
    }
    
    protected abstract void updateView(Menu menu, Player player);
}
