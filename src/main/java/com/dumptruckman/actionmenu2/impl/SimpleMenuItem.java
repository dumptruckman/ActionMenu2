package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.MenuItemEvent;
import com.dumptruckman.actionmenu2.api.event.MenuItemListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

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
    private boolean selectable = true;

    protected final void fireChangeEvent() {
        for (MenuItemListener listener : this.getMenuItemListeners()) {
            listener.onMenuItemChange(new MenuItemEvent(
                    this.player, this));
        }
    }

    public void run() {
        for (MenuItemListener listener : this.getMenuItemListeners()) {
            listener.onAction(new MenuItemEvent(this.player, this));
        }
    }
    
    @Override
    public Set<MenuItemListener> getMenuItemListeners() {
        return this.listeners;
    }

    @Override
    public void setText(String... text) {
        this.text = Arrays.asList(text);
        this.fireChangeEvent();
    }

    @Override
    public void setLine(int index, String line) {
        this.text.set(index, line);
        this.fireChangeEvent();
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        this.fireChangeEvent();
    }

    @Override
    public String getText() {
        if (this.text == null) {
            return null;
        }
        if (this.text.isEmpty()) {
            return "";
        }
        return this.text.get(0);
    }

    @Override
    public String getFullText() {
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
    public List<String> getLines() {
        return this.text;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public void update(Player player) {
        this.player = player;
    }

    @Override
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    @Override
    public boolean isSelectable() {
        return this.selectable;
    }
    
    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }
    
    @Override
    public String toString() {
        return this.getFullText();
    }
}
