package com.dumptruckman.actionmenu.plugin;

import com.dumptruckman.actionmenu.ActionMenu;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.Menus;
import com.dumptruckman.actionmenu2.api.event.ActionEvent;
import com.dumptruckman.actionmenu2.api.event.ActionListener;
import com.dumptruckman.actionmenu2.impl.SimpleMenuItem;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class ActionMenuPlugin extends JavaPlugin implements Listener {

    MenuHandle menuHandle = null;
    
    Block block = null;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getLogger().info("enabled");
    }

    public void onDisable() {
        this.getLogger().info("disabled");
    }

    @EventHandler
    public void signChange(SignChangeEvent event) {
        this.getLogger().info("sign change");
        if (event.getLine(0).contains("menu")) {
            menuHandle = Menus.getMenuHandle((Sign) event.getBlock().getState());
            MenuItem test = new SimpleMenuItem();
            test.setText("test");
            test.getListeners().add(new TestActionListener());
            MenuItem poop = new SimpleMenuItem();
            poop.setText("poop");
            MenuItem scoop = new SimpleMenuItem();
            scoop.setText("scoop");

            menuHandle.getMenu().getContents().add(test);
            menuHandle.getMenu().getContents().add(poop);
            menuHandle.getMenu().getContents().add(scoop);
            menuHandle.setSender(event.getPlayer());
            menuHandle.updateView(this, event.getPlayer());
            this.block = event.getBlock();
            this.getLogger().info("menu made");
        }
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        if (block == null) {
            return;
        }
        if (event.getClickedBlock() == null) {
            return;
        }
        if (!(event.getClickedBlock().getState() instanceof Sign)) {
            return;
        }
        if (!event.getClickedBlock().equals(this.block)) {
            return;
        }

        Player player = event.getPlayer();
        menuHandle.setSender(player);

        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            menuHandle.runSelected();
        } else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            menuHandle.cycleMenu();
        }
        menuHandle.updateView(this, player);
    }
    
    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;
        if (this.block == null) return;
        Block blockAgainst = event.getBlockAgainst();
        if (blockAgainst.equals(block)) event.setCancelled(true);
    }

    private class TestActionListener implements ActionListener {
        public void onAction(ActionEvent event) {
            System.out.println(event.getSender());
            if (event.getSender() != null && event.getSender() instanceof Player) {
                Player player = (Player)event.getSender();
                final Vector direction = player.getEyeLocation().getDirection().multiply(2);
                player.getWorld().spawn(player.getEyeLocation().add(direction.getX(), direction.getY(),
                        direction.getZ()), Fireball.class);
            }

        }
    }
}
