package com.dumptruckman.actionmenu2.api;

import java.util.Set;

public interface MenuHandle extends Menu, MenuView, MenuContainer, MenuInterface, ViewUpdater {

    void cycleMenu();

    void cycleMenu(boolean reverse);

    Set<MenuView> getViews();
    
    boolean addView(MenuView view);
    
    boolean removeView(MenuView view);
}
