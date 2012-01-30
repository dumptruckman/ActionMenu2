package com.dumptruckman.actionmenu2.api;

public interface MenuHandle extends Menu, MenuInterface, ViewUpdater {

    void cycleMenu();

    void cycleMenu(Boolean reverse);

    Menu getMenu();
    
    MenuView getView();
}
