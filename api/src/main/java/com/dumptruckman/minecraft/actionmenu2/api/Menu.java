package com.dumptruckman.minecraft.actionmenu2.api;

import com.dumptruckman.minecraft.actionmenu2.api.event.MenuListener;

import java.util.Set;

public interface Menu<P extends MenuPlugin, U extends MenuUser, B extends MenuBlock> extends MenuListener {

    MenuModel<U, B> getModel();

    void setModel(MenuModel<U, B> model);
    
    MenuItem<U, B> getSelected();

    void cycleSelection();

    void cycleSelection(boolean reverse);

    MenuViews getViews();

    void setUser(U user);

    U getUser();
    
    void updateViews();

    Set<MenuListener> getMenuListeners();
}
