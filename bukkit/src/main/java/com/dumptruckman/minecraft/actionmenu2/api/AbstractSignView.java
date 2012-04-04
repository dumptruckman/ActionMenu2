package com.dumptruckman.minecraft.actionmenu2.api;

import com.dumptruckman.minecraft.actionmenu2.impl.BukkitPlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

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
    public abstract void showMenu(Menu menu, BukkitPlayer player);

    @Override
    protected void updateView(Menu menu, BukkitPlayer player) {
        if (player != null) {
            player.sendBlockChange(this.getBlock().getLocation(), 0, (byte) 0);
        }
        this.showMenu(menu, player);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getCanonicalName()).append(" : Sign @ ").append(this.block.getLocation().toString());
        return builder.toString();
    }
}
