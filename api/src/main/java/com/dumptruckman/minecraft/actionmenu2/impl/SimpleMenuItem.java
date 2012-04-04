package com.dumptruckman.minecraft.actionmenu2.impl;

import com.dumptruckman.minecraft.actionmenu2.api.MenuBlock;
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

public class SimpleMenuItem<U extends MenuUser, B extends MenuBlock> implements MenuItem<U, B> {

    private final Set<MenuItemListener<U, B>> listeners = new LinkedHashSet<MenuItemListener<U, B>>();
    private List<String> text = null;
    private Image image = null;
    private final List<B> blocks = new ArrayList<B>();
    private U user = null;
    private boolean selectable = true;

    protected final void fireChangeEvent() {
        for (MenuItemListener<U, B> listener : this.getMenuItemListeners()) {
            listener.onMenuItemChange(new MenuItemEvent<U, B>(
                    this.user, this));
        }
    }

    public void run() {
        for (MenuItemListener<U, B> listener : this.getMenuItemListeners()) {
            listener.onAction(new MenuItemEvent<U, B>(this.user, this));
        }
    }
    
    @Override
    public Set<MenuItemListener<U, B>> getMenuItemListeners() {
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
    public void update(U user) {
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
    public List<B> getBlocks() {
        return this.blocks;
    }
    
    @Override
    public String toString() {
        return this.getFullText();
    }
}
