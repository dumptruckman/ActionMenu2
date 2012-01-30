package com.dumptruckman.actionmenu2.api;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractSignView extends AbstractView {

    private Sign sign;
    private Block block;

    public AbstractSignView(Sign sign) {
        this.sign = sign;
        this.block = sign.getBlock();
    }

    public final Sign getSign() {
        return this.sign;
    }

    public final Block getBlock() {
        return this.block;
    }

    protected void updateView(CommandSender sender) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player)sender;
        }
        if (player != null) {
            player.sendBlockChange(this.getBlock().getLocation(), 0, (byte) 0);
        }
        this.show(sender);
    }
}
