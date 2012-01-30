package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.MenuItemChangeEvent;
import com.dumptruckman.actionmenu2.api.event.MenuItemListener;
import org.bukkit.command.CommandSender;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleMenuItem implements MenuItem {

    private final List<MenuItemListener> listeners = new ArrayList<MenuItemListener>();
    private List<String> text = null;
    private Image image = null;
    private CommandSender sender = null;

    protected void fireChangeEvent() {
        for (MenuItemListener listener : this.getMenuItemListeners()) {
            listener.onMenuItemChange(new MenuItemChangeEvent(
                    this.getSender(), this));
        }
    }
    
    @Override
    public List<MenuItemListener> getMenuItemListeners() {
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
    public void setSender(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public CommandSender getSender() {
        return this.sender;
    }
}
