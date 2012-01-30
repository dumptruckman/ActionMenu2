package com.dumptruckman.actionmenu2.api;

public interface MenuHandle extends Menu, MenuContainer, MenuInterface, ViewUpdater {

    void cycleMenu();

    void cycleMenu(Boolean reverse);
    
    boolean addView(MenuView view);
    
    boolean removeView(MenuView view);
}
