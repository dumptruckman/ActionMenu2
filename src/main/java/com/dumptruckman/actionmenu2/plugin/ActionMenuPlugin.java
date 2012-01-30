package com.dumptruckman.actionmenu2.plugin;

import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.impl.Menus;
import com.dumptruckman.actionmenu2.api.event.MenuItemEvent;
import com.dumptruckman.actionmenu2.api.event.MenuItemListener;
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

    private MenuHandle menuHandle = null;

    private Block block = null;

    public final void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getLogger().info("enabled");
    }

    public final void onDisable() {
        this.getLogger().info("disabled");
    }

    @EventHandler
    public final void signChange(final SignChangeEvent event) {
        this.getLogger().info("sign change");
        if (event.getLine(0).contains("menu")) {
            menuHandle = Menus.newMenuHandle(this,
                    (Sign) event.getBlock().getState());
            MenuItem test = new SimpleMenuItem(this);
            test.setText("test");
            test.getMenuItemListeners().add(new TestMenuItemListener());
            MenuItem poop = new SimpleMenuItem(this);
            poop.setText("poop");
            MenuItem scoop = new SimpleMenuItem(this);
            scoop.setText("scoop");

            menuHandle.getMenu().getContents().add(test);
            menuHandle.getMenu().getContents().add(poop);
            menuHandle.getMenu().getContents().add(scoop);
            menuHandle.touch(event.getPlayer());
            menuHandle.updateViews(event.getPlayer());
            this.block = event.getBlock();
        }
    }

    @EventHandler
    public final void playerInteract(final PlayerInteractEvent event) {
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
        menuHandle.touch(player);

        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            menuHandle.runSelected();
        } else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            menuHandle.cycleMenu();
        }
        menuHandle.updateViews(player);
    }

    @EventHandler
    public final void blockPlace(final BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (this.block == null) {
            return;
        }
        Block blockAgainst = event.getBlockAgainst();
        if (blockAgainst.equals(block)) {
            event.setCancelled(true);
        }
    }

    private class TestMenuItemListener implements MenuItemListener {
        public void onAction(final MenuItemEvent event) {
            if (event.getSender() != null
                    && event.getSender() instanceof Player) {
                Player player = (Player) event.getSender();
                final Vector direction = player.getEyeLocation()
                        .getDirection().multiply(2);
                player.getWorld().spawn(player.getEyeLocation()
                        .add(direction.getX(), direction.getY(),
                        direction.getZ()), Fireball.class);
            }
        }
        
        public void onMenuItemChange(final MenuItemEvent event) {

        }
    }
}
