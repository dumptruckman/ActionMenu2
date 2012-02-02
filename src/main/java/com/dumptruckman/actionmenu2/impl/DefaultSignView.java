package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.AbstractSignView;
import com.dumptruckman.actionmenu2.api.Menu;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

class DefaultSignView extends AbstractSignView {

    protected DefaultSignView(Sign sign) {
        super(sign);
    }

    @Override
    public void showMenu(Menu menu, Player player) {
        if (menu == null) {
            throw new IllegalArgumentException("Menu may not be null!");
        }
        Sign sign = this.getSign();
        for(int i = 0; i < menu.getContents().size() && i < 4; i++) {
            String text = menu.getContents().get(i).getText();
            if (menu.getContents().getSelectedIndex() == i) {
                text = "> " + text;
            } else {
                text = "  " + text;
            }
            sign.setLine(i, text);
        }
        sign.update(true);
    }
}
