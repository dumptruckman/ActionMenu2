package com.dumptruckman.minecraft.actionmenu2.api.event;

import com.dumptruckman.minecraft.actionmenu2.api.MenuItem;
import com.dumptruckman.minecraft.actionmenu2.api.MenuUser;

public class MenuItemEvent {

    private final MenuItem menuItem;
    private final MenuUser user;

    public MenuItemEvent(final MenuUser user,
                         final MenuItem item) {
        this.user = user;
        this.menuItem = item;
    }

    public final MenuItem getMenuItem() {
        return this.menuItem;
    }

    public MenuUser getUser() {
        return this.user;
    }
}
