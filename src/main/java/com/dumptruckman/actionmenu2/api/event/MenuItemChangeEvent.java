package com.dumptruckman.actionmenu2.api.event;

import com.dumptruckman.actionmenu2.api.MenuItem;
import org.bukkit.command.CommandSender;

public class MenuItemChangeEvent extends MenuItemEvent {
    
    public MenuItemChangeEvent(CommandSender sender, MenuItem item) {
        super(null, sender, item);
    }
}
