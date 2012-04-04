package com.dumptruckman.minecraft.actionmenu2.api.event;

import com.dumptruckman.minecraft.actionmenu2.api.MenuBlock;
import com.dumptruckman.minecraft.actionmenu2.api.MenuItem;
import com.dumptruckman.minecraft.actionmenu2.api.MenuUser;

public class MenuItemEvent<U extends MenuUser, B extends MenuBlock> {

    private final MenuItem<U, B> menuItem;
    private final U user;

    public MenuItemEvent(final U user,
                         final MenuItem<U, B> item) {
        this.user = user;
        this.menuItem = item;
    }

    public final MenuItem<U, B> getMenuItem() {
        return this.menuItem;
    }

    public U getUser() {
        return this.user;
    }
}
