package com.dumptruckman.actionmenu2.api;

import org.bukkit.entity.Player;

public interface Menu {

    MenuModel getModel();

    void setModel(MenuModel model);
    
    MenuItem getSelected();

    void cycleSelection();

    void cycleSelection(boolean reverse);

    MenuViews getViews();

    void setUser(Player player);

    Player getUser();
    
    void updateViews();
}
