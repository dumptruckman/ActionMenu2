package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.AbstractSignView;
import com.dumptruckman.actionmenu2.api.Menu;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;

public class SimpleSignView extends AbstractSignView {

    public SimpleSignView(Sign sign) {
        super(sign);
    }

    public SimpleSignView(Sign sign, Menu menu) {
        super(sign, menu);
    }

    @Override
    public void show(CommandSender sender) {
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
