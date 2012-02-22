package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.entity.Player;

import java.util.Set;

public interface Menu extends MenuListener {

    MenuModel getModel();

    void setModel(MenuModel model);
    
    MenuItem getSelected();

    void cycleSelection();

    void cycleSelection(boolean reverse);

    MenuViews getViews();

    void setUser(Player player);

    Player getUser();
    
    void updateViews();

    Set<MenuListener> getMenuListeners();
}
