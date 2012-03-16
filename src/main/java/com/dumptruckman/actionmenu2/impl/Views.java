package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.MenuView;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

public class Views {

    private Views() {
        throw new AssertionError();
    }

    public static MenuView newView(Plugin plugin, Sign sign) {
        return new DefaultSignView(plugin, sign);
    }
}
