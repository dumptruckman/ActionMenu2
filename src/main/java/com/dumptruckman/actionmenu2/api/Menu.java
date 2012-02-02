package com.dumptruckman.actionmenu2.api;

import org.bukkit.entity.Player;

import java.util.Set;

public interface Menu {

    MenuContents getContents();
    
    MenuItem getSelected();

    void cycleSelection();

    void cycleSelection(boolean reverse);

    MenuViews getViews();

    void setUser(Player player);

    Player getUser();
}
