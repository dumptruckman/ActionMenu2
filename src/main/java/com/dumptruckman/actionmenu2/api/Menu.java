package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuListener;

import java.util.Set;

public interface Menu extends MenuInterface {

    MenuContents<MenuItem> getContents();
    
    MenuItem getSelected();

    Set<MenuListener> getMenuListeners();
    
    void run(MenuItem item);

    void runSelected();
}
