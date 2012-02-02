package com.dumptruckman.actionmenu2.api.event;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuItem;
import org.bukkit.command.CommandSender;

public class MenuItemEvent {

    private final MenuItem menuItem;
    private final CommandSender sender;

    public MenuItemEvent(final CommandSender sender,
                         final MenuItem item) {
        this.sender = sender;
        this.menuItem = item;
    }

    public final MenuItem getMenuItem() {
        return this.menuItem;
    }

    public CommandSender getSender() {
        return this.sender;
    }
}
