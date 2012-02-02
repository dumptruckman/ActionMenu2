package com.dumptruckman.actionmenu2.api;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public abstract class AbstractSignView extends AbstractView {

    protected Sign sign;
    protected Block block;

    public AbstractSignView(Sign sign) {
        this.sign = sign;
        this.block = sign.getBlock();
    }

    public Sign getSign() {
        return this.sign;
    }

    public Block getBlock() {
        return this.block;
    }
    
    @Override
    public abstract void showMenu(Menu menu, Player player);

    @Override
    protected void updateView(Menu menu, Player player) {
        if (player != null) {
            player.sendBlockChange(this.getBlock().getLocation(), 0, (byte) 0);
        }
        this.showMenu(menu, player);
    }
}
