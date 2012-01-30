package com.dumptruckman.actionmenu2.api.event;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuItem;
import org.bukkit.command.CommandSender;

public class MenuItemEvent extends MenuEvent {

    private final MenuItem menuItem;

    public MenuItemEvent(final Menu source, final CommandSender sender,
                         final MenuItem item) {
        super(source, sender);
        this.menuItem = item;
    }

    public final MenuItem getMenuItem() {
        return this.menuItem;
    }
}
