package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.MenuItemChangeEvent;
import com.dumptruckman.actionmenu2.api.event.MenuItemListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SimpleMenuItem implements MenuItem {

    private final Set<MenuItemListener> listeners = new LinkedHashSet<MenuItemListener>();
    private List<String> text = null;
    private Image image = null;
    private final List<Block> blocks = new ArrayList<Block>();
    private Player player = null;
    private Plugin plugin;
    private boolean selectable = true;

    public SimpleMenuItem(final Plugin plugin) {
        this.plugin = plugin;
    }

    protected final void fireChangeEvent() {
        for (MenuItemListener listener : this.getMenuItemListeners()) {
            listener.onMenuItemChange(new MenuItemChangeEvent(
                    this.getPlayer(), this));
        }
    }
    
    @Override
    public final Set<MenuItemListener> getMenuItemListeners() {
        return this.listeners;
    }

    @Override
    public final void setText(String... text) {
        this.text = Arrays.asList(text);
        this.fireChangeEvent();
    }

    @Override
    public final void setLine(int index, String line) {
        this.text.set(index, line);
        this.fireChangeEvent();
    }

    @Override
    public final void setImage(Image image) {
        this.image = image;
        this.fireChangeEvent();
    }

    @Override
    public final String getText() {
        if (this.text == null) {
            return null;
        }
        if (this.text.isEmpty()) {
            return "";
        }
        return this.text.get(0);
    }

    @Override
    public final String getFullText() {
        if (this.text == null) {
            return null;
        }
        if (this.text.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String line : this.text) {
            if (!builder.toString().isEmpty()) {
                builder.append(System.getProperty("line.separator"));
            }
            builder.append(line);
        }
        return builder.toString();
    }

    @Override
    public final List<String> getLines() {
        return this.text;
    }

    @Override
    public final Image getImage() {
        return this.image;
    }

    @Override
    public final void touch(Player player) {
        this.player = player;
    }

    @Override
    public final Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public final Player getPlayer() {
        return this.player;
    }

    @Override
    public final void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    @Override
    public final boolean isSelectable() {
        return this.selectable;
    }
    
    public final List<Block> getBlocks() {
        return this.blocks;
    }
}
