package com.dumptruckman.minecraft.actionmenu2.api;

import com.dumptruckman.minecraft.actionmenu2.api.event.MenuListener;

public interface MenuView extends MenuListener {

    void showMenu(Menu menu, MenuUser user);

    void update(MenuPlugin plugin, Menu menu, MenuUser user);
}
