package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.AbstractSignView;
import com.dumptruckman.actionmenu2.api.Menu;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

class DefaultSignView extends AbstractSignView {

    protected DefaultSignView(Plugin plugin, Sign sign) {
        super(plugin, sign);
    }

    protected DefaultSignView(Plugin plugin, Sign sign, Menu menu) {
        super(plugin, sign, menu);
    }

    @Override
    public void show(Player player) {
        if (this.getMenu() == null) {
            System.out.println("No menu");
            return;
        }
        Sign sign = this.getSign();
        for(int i = 0; i < this.getMenu().getContents().size() && i < 4; i++) {
            String text = this.getMenu().getContents().get(i).getText();
            if (this.getMenu().getContents().getSelectedIndex() == i) {
                text = "> " + text;
            } else {
                text = "  " + text;
            }
            sign.setLine(i, text);
        }
        sign.update(true);
    }
}
