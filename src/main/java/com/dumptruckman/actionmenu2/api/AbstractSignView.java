package com.dumptruckman.actionmenu2.api;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class AbstractSignView extends AbstractView {

    private Sign sign;
    private Block block;

    public AbstractSignView(Plugin plugin, Sign sign) {
        this(plugin, sign, null);
    }

    public AbstractSignView(Plugin plugin, Sign sign, Menu menu) {
        super(plugin, menu);
        this.sign = sign;
        this.block = sign.getBlock();
    }

    public final Sign getSign() {
        return this.sign;
    }

    public final Block getBlock() {
        return this.block;
    }
    
    @Override
    public abstract void show(Player player);

    @Override
    protected void updateView(Player player) {
        if (player != null) {
            player.sendBlockChange(this.getBlock().getLocation(), 0, (byte) 0);
        }
        this.show(player);
    }
}
