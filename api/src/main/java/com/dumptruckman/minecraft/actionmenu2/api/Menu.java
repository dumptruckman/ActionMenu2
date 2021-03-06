package com.dumptruckman.minecraft.actionmenu2.api;

import com.dumptruckman.minecraft.actionmenu2.api.event.MenuListener;

import java.util.Set;

public interface Menu extends MenuListener {

    MenuModel getModel();

    void setModel(MenuModel model);
    
    MenuItem getSelected();

    void cycleSelection();

    void cycleSelection(boolean reverse);

    MenuViews getViews();

    void setUser(MenuUser user);

    MenuUser getUser();
    
    void updateViews();

    Set<MenuListener> getMenuListeners();
}
