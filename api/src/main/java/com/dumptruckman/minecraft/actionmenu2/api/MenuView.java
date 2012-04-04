package com.dumptruckman.minecraft.actionmenu2.api;

import com.dumptruckman.minecraft.actionmenu2.api.event.MenuListener;

public interface MenuView<P extends MenuPlugin, U extends MenuUser> extends MenuListener {

    void showMenu(Menu menu, U player);

    void update(P plugin, Menu menu, U player);
}
