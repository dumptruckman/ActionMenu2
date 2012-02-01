package com.dumptruckman.actionmenu2.api;

import java.util.Set;

public interface MenuHandle extends Menu, MenuContainer, MenuInterface, ViewUpdater {

    void cycleMenu();

    void cycleMenu(Boolean reverse);

    Set<MenuView> getViews();
    
    boolean addView(MenuView view);
    
    boolean removeView(MenuView view);
}
