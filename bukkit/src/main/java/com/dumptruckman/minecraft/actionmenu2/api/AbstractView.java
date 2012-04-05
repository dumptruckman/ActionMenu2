package com.dumptruckman.minecraft.actionmenu2.api;

import com.dumptruckman.minecraft.actionmenu2.api.event.MenuEvent;
import com.dumptruckman.minecraft.actionmenu2.api.event.ModelChangeEvent;
import com.dumptruckman.minecraft.actionmenu2.impl.BukkitPlayer;
import com.dumptruckman.minecraft.actionmenu2.impl.BukkitPlugin;
import org.bukkit.Bukkit;

public abstract class AbstractView implements MenuView {

    @Override
    public abstract void showMenu(Menu menu, MenuUser player);

    @Override
    public void update(MenuPlugin plugin, final Menu menu, final MenuUser player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask((BukkitPlugin) plugin, new Runnable() {
            @Override
            public void run() {
                updateView(menu, (BukkitPlayer) player);
            }
        });
    }
    
    protected abstract void updateView(Menu menu, BukkitPlayer player);

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
