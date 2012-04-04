package com.dumptruckman.minecraft.actionmenu2.plugin;

import com.dumptruckman.minecraft.actionmenu2.api.Menu;
import com.dumptruckman.minecraft.actionmenu2.api.MenuItem;
import com.dumptruckman.minecraft.actionmenu2.api.event.MenuItemEvent;
import com.dumptruckman.minecraft.actionmenu2.api.event.MenuItemListener;
import com.dumptruckman.minecraft.actionmenu2.impl.BukkitBlock;
import com.dumptruckman.minecraft.actionmenu2.impl.BukkitMenuItem;
import com.dumptruckman.minecraft.actionmenu2.impl.BukkitPlayer;
import com.dumptruckman.minecraft.actionmenu2.impl.BukkitPlugin;
import com.dumptruckman.minecraft.actionmenu2.impl.Menus;
import com.dumptruckman.minecraft.actionmenu2.impl.SimpleMenuItem;
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

    private Block block = null;
    private Menu<BukkitPlugin, BukkitPlayer> menu = null;

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
            menu = Menus.newMenu(this,
                    (Sign) event.getBlock().getState());
            MenuItem<BukkitBlock> test = new BukkitMenuItem();
            test.setText("test");
            test.getMenuItemListeners().add(new TestMenuItemListener());
            MenuItem poop = new SimpleMenuItem();
            poop.setText("poop");
            MenuItem scoop = new SimpleMenuItem();
            scoop.setText("scoop");

            menu.getModel().add(test);
            menu.getModel().add(poop);
            menu.getModel().add(scoop);
            menu.setUser(BukkitPlayer.get(event.getPlayer()));
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
        menu.setUser(BukkitPlayer.get(player));

        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            menu.getSelected().run();
        } else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            menu.cycleSelection();
        }
        menu.setUser(BukkitPlayer.get(player));
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
            if (event.getUser() != null
                    && event.getUser() instanceof Player) {
                Player player = (Player) event.getUser();
                final Vector direction = player.getEyeLocation()
                        .getDirection().multiply(2);
                player.getWorld().spawn(player.getEyeLocation()
                        .add(direction.getX(), direction.getY(),
                        direction.getZ()), Fireball.class);
            }
        }

        @Override
        public void onMenuItemChange(MenuItemEvent event) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
