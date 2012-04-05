package com.dumptruckman.minecraft.actionmenu2.impl;

import com.dumptruckman.minecraft.actionmenu2.api.MCBlock;
import com.dumptruckman.minecraft.actionmenu2.api.MenuItem;
import com.dumptruckman.minecraft.actionmenu2.api.MenuUser;
import com.dumptruckman.minecraft.actionmenu2.api.event.MenuItemEvent;
import com.dumptruckman.minecraft.actionmenu2.api.event.MenuItemListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SimpleMenuItem implements MenuItem {

    private final Set<MenuItemListener> listeners = new LinkedHashSet<MenuItemListener>();
    private List<String> text = null;
    private Image image = null;
    private final List<MCBlock> blocks = new ArrayList<MCBlock>();
    private MenuUser user = null;
    private boolean selectable = true;

    protected final void fireChangeEvent() {
        for (MenuItemListener listener : this.getMenuItemListeners()) {
            listener.onMenuItemChange(new MenuItemEvent(
                    this.user, this));
        }
    }

    public void run() {
        for (MenuItemListener listener : this.getMenuItemListeners()) {
            listener.onAction(new MenuItemEvent(this.user, this));
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
    public void update(MenuUser user) {
        this.user = user;
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
    public List<MCBlock> getBlocks() {
        return this.blocks;
    }
    
    @Override
    public String toString() {
        return this.getFullText();
    }
}
