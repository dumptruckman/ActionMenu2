package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuItem;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

public class Menus {

    private Menus() { }
    
    public static MenuHandle newMenuHandle(Plugin plugin, Sign sign) {
        return new DefaultHandle(
                plugin,
                Menus.newMenu(plugin),
                new DefaultSignView(plugin, sign));
    }
    
    public static MenuHandle newMenuHandle(Plugin plugin, Menu menu) {
        return new DefaultHandle(plugin, menu);
    }
    
    public static MenuHandle newMenuHandle(Plugin plugin) {
        return Menus.newMenuHandle(plugin, Menus.newMenu(plugin));
    }
    
    public static Menu newMenu(Plugin plugin) {
        return new DefaultMenu(plugin);
    }
}
