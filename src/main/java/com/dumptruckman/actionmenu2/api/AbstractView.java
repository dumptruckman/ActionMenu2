package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuEvent;
import com.dumptruckman.actionmenu2.api.event.ModelChangeEvent;
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

    @Override
    public void onContentsAdd(MenuEvent event) {

    }

    @Override
    public void onContentsRemove(MenuEvent event) {

    }

    @Override
    public void onContentsChange(MenuEvent event) {

    }

    @Override
    public void onSelectionChange(MenuEvent event) {

    }

    @Override
    public void onModelChange(ModelChangeEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getCanonicalName());
        return builder.toString();
    }
}
