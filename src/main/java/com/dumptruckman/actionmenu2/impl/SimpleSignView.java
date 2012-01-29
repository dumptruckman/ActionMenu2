package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.view.MenuView;
import com.dumptruckman.actionmenu2.api.view.SignView;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SimpleSignView implements MenuView {
    
    private Block block;
    private Sign sign;
    private Menu menu = null;

    public SimpleSignView(Sign sign) {
        this.block = sign.getBlock();
        this.sign = sign;
    }

    public Sign getSign() {
        /*
        BlockState state = this.getBlock().getState();
        if (state instanceof Sign) {
            return (Sign)state;
        }
        return null;
        */
        return this.sign;
    }

    public Block getBlock() {
        return this.block;
    }

    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Menu getMenu() {
        return this.menu;
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

    @Override
    public void setSender(CommandSender sender) {
        this.getMenu().setSender(sender);
    }

    @Override
    public CommandSender getSender() {
        return null;
    }

    @Override
    public void updateView(Plugin plugin, final CommandSender sender) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Player player = null;
                if (sender instanceof Player) {
                    player = (Player)sender;
                }
                if (player != null) {
                    player.sendBlockChange(SimpleSignView.this.getBlock().getLocation(), 0, (byte) 0);
                }
                SimpleSignView.this.show(sender);
            }
        });
    }
}
