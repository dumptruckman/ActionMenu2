package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.view.MenuView;
import com.dumptruckman.actionmenu2.api.view.ViewUpdater;

public interface MenuHandle extends Menu, MenuInterface, ViewUpdater {

    void cycleMenu();

    void cycleMenu(Boolean reverse);

    Menu getMenu();
    
    MenuView getView();
}
